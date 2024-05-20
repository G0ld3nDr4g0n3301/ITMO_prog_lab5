package ru.ifmo.se.server.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;

import ru.ifmo.se.common.collections.Color;
import ru.ifmo.se.common.collections.Coordinates;
import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.commands.EmergencySave;

public class DBConnection {

    private static String DB_URL;
    private static String DB_LOGIN;
    private static String DB_PASS;
    private static Properties info;
    private static Connection connection;

    static {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("db.properties"));
            DB_URL = config.getProperty("url");
            DB_LOGIN = config.getProperty("login");
            DB_PASS = config.getProperty("password");
            Properties info = new Properties();
            info.put("user", DB_LOGIN);
            info.put("password", DB_PASS);
        } catch (IOException e) {
            System.out.println(e);
            LogFile.warning("Can't access file db.properties");
            EmergencySave.save();
        }

    }

    public static synchronized Connection connect(){
        try {
            if (connection == null) {
                Class.forName("org.postgresql.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASS);
            }
            return connection;
        } catch (SQLException e) {
            LogFile.warning(e.getMessage());
            return null;
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        } 
    }

    public static List<Person> getPerson(String additionalQuery){
        List<Person> list = new ArrayList<>();
        ResultSet collection;
        try {
            collection = DBConnection.connect().createStatement().executeQuery("SELECT id, owner, name, creation_date, height, birthday, weight, coord_x, coord_y, loc_x, loc_y, loc_name, color FROM collection " + additionalQuery + ";");
            while (collection.next()){
                Person person = new Person();
                person.setId(collection.getInt(1));
                person.setOwnerId(collection.getInt(2));
                person.setName(collection.getString(3));
                person.setCreationDate(collection.getDate(4).toLocalDate());
                person.setHeight(collection.getLong(5));
                if(collection.getDate(6) != null) {
                person.setBirthday(collection.getDate(6).toLocalDate());
                }
                person.setWeight(collection.getInt(7));
                Coordinates coordinates = new Coordinates(collection.getDouble(8), collection.getLong(9));
                Location location = new Location(collection.getFloat(10), collection.getDouble(11), collection.getString(12));
                Color color = Color.valueOf(collection.getString(13));
                person.setCoordinates(coordinates);
                person.setLocation(location);
                person.setHairColor(color);
                list.add(person);
            }
            return list;
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
                return null;
            }
    } 

    public static synchronized Boolean deletePerson(Integer[] IDs){
        String[] strings = new String[IDs.length];
        for (int i = 0; i < strings.length; i++){
            strings[i] = IDs[i].toString();
        }
        try {
            connect().createStatement().execute("DELETE FROM collection WHERE id IN (" + String.join(",", strings) + ");");
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }

    public static synchronized Boolean putPerson(Person person){
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connect().prepareStatement("INSERT INTO collection (owner, name, creation_date, height, birthday, weight, coord_x, coord_y, loc_x, loc_y, loc_name, color) VALUES (?,?,?,?,?,?,?,?,?,?,?,?);");
        preparedStatement.setInt(1, person.getOwnerId());
        preparedStatement.setString(2, person.getName());
        preparedStatement.setDate(3, Date.valueOf(person.getCreationDate()));
        preparedStatement.setLong(4, person.getHeight());
        if (person.getBirthday() != null) {
        preparedStatement.setDate(5, Date.valueOf(person.getBirthday()));
        } else {
            preparedStatement.setNull(5, Types.DATE);
        }
        preparedStatement.setInt(6, person.getWeight());
        preparedStatement.setDouble(7, person.getCoordinates().getX());
        preparedStatement.setDouble(8, person.getCoordinates().getY());
        preparedStatement.setDouble(9, person.getLocation().getLocX());
        preparedStatement.setDouble(10, person.getLocation().getLocY());
        if (person.getLocation().getName() != null) {
        preparedStatement.setString(11, person.getLocation().getName());
        } else {
            preparedStatement.setNull(11, Types.OTHER);
        }
        preparedStatement.setString(12, person.getHairColor().toString());
        if(preparedStatement.executeUpdate() != 0){
            return true;
        }
    } catch (SQLException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    return false;
    }

    public static synchronized Boolean deletePerson(Person p){
        try {
            PreparedStatement preparedStatement = connect().prepareStatement("DELETE FROM collection WHERE id = " + p.getId() + ";");
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return false;
        }
    }

    public static synchronized Boolean truncate(Request request){
        try {
            PreparedStatement preparedStatement = connect().prepareStatement("DELETE FROM collection WHERE owner = " + request.getOwnerId() + ";");
            return preparedStatement.executeUpdate() != 0;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return false;
    }

    public static synchronized Person getLast(Request request){
        try {
            PreparedStatement preparedStatement = connect().prepareStatement("SELECT MAX(id) FROM collection WHERE owner = " + request.getOwnerId() + ";");
            ResultSet inf = preparedStatement.executeQuery();
            if(inf.next()){
            System.out.println(inf.getInt(1));
            return getPerson("WHERE id = " + inf.getInt(1)).get(0);
        }
        return null;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return null;
        }
    }

    public static synchronized Integer getNextId(){
        try {
           ResultSet resultSet = connect().createStatement().executeQuery("select currval('collection_id_seq');");
            resultSet.next();
            return resultSet.getInt(1);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println("NOPE");
            return null;
        }
    }

}
