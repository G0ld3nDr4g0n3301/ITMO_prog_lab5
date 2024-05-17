package ru.ifmo.se.server.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.commands.EmergencySave;

public class DBConnection {

    private static String DB_URL;
    private static String DB_LOGIN;
    private static String DB_PASS;

    static {
        Properties config = new Properties();
        try {
            config.load(new FileInputStream("db.properties"));
            DB_URL = config.getProperty("url");
            DB_LOGIN = config.getProperty("login");
            DB_PASS = config.getProperty("password");
        } catch (IOException e) {
            System.out.println(e);
            LogFile.warning("Can't access file db.properties");
            EmergencySave.save();
        }

    }

    public static Connection connect(){
        try {
            return DriverManager.getConnection(DB_URL, DB_LOGIN, DB_PASS);
        } catch (SQLException e) {
            LogFile.warning(e.getMessage());
            return null;
        }
    }

}
