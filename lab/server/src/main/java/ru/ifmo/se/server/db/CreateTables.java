package ru.ifmo.se.server.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    private static final String statement = """
        CREATE TABLE IF NOT EXISTS users (
            id SERIAL PRIMARY KEY,
            login TEXT UNIQUE NOT NULL,
            password TEXT NOT NULL,
            salt VARCHAR[7] NOT NULL
        );
        CREATE TYPE IF NOT EXISTS COLOR AS ENUM ("RED","YELLOW", "ORANGE", "WHITE", "BROWN");
        CREATE TABLE IF NOT EXISTS collection (
            SERIAL PRIMARY KEY id,
            INT NOT NULL owner,
            TEXT NOT NULL name,
            DATE NOT NULL creation_date,
            INT NOT NULL height,
            DATE birthday,
            INT NOT NULL weight,
            REAL NOT NULL coord_x,
            INT NOT NULL coord_y,
            REAL NOT NULL loc_x,
            REAL NOT NULL loc_y,
            TEXT loc_name,
            COLOR NOT NULL color
        );
        """;

    public static Boolean create(Connection connection){
        try {
           Statement creationQuery = connection.createStatement();
           creationQuery.execute(statement);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return true;
    }    
}
