package ru.ifmo.se.server.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;

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
            DB_URL = "jdbc:postgresql://pg/studs";
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

}
