package ru.ifmo.se.server.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.db.DBConnection;
import ru.ifmo.se.common.collections.Color;
import ru.ifmo.se.common.collections.Coordinates;
import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.serialization.ReadPerson;

/**
 * loads collection from file.
 * @author raistlin
 */
public class Load extends Command{
    
    private static final Logger logger = Logger.getLogger(Load.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Load(String name, String desc){
        this.name= name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){

        List<Person> newList = new ArrayList<>();
        try {
            ResultSet collection = DBConnection.connect().createStatement().executeQuery("SELECT id, owner, name, creation_date, height, birthday, weight, coord_x, coord_y, loc_x, loc_y, loc_name, color FROM users;");
            while (collection.next()){
                Person person = new Person();
                person.setId(collection.getInt(1));
                person.setOwnerId(collection.getInt(2));
                person.setName(collection.getString(3));
                person.setCreationDate(collection.getDate(4).toLocalDate());
                person.setHeight(collection.getLong(5));
                person.setBirthday(collection.getDate(6).toLocalDate());
                person.setWeight(collection.getInt(7));
                Coordinates coordinates = new Coordinates(collection.getDouble(8), collection.getLong(9));
                Location location = new Location(collection.getFloat(10), collection.getDouble(11), collection.getString(12));
                Color color = Color.valueOf(collection.getString(13));
                person.setCoordinates(coordinates);
                person.setLocation(location);
                person.setHairColor(color);
                newList.add(person);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        CollectionManager.addAll(newList);
        logger.info("Successfully loaded collection from DB");
        return null;
    }
    
    @Override
    public String toString(){
        return "Load";
    }
}
