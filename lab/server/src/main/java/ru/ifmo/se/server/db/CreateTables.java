package ru.ifmo.se.server.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {

    private static final String statement = """
        CREATE TABLE user (
            SERIAL PRIMARY KEY id,
            STRING UNIQUE NOT NULL login,
            STRING NOT NULL password,
            VARCHAR[7] NOT NULL salt
        );
        CREATE TYPE COLOR AS ENUM ("RED","YELLOW", "ORANGE", "WHITE", "BROWN");
        CREATE TABLE collection (
            SERIAL PRIMARY KEY id,
            INT NOT NULL owner,
            STRING NOT NULL name,
            DATE NOT NULL creation_date,
            INT NOT NULL height,
            DATE birthday,
            INT NOT NULL weight,
            INT NOT NULL coord_x,
            INT NOT NULL coord_y,
            INT NOT NULL loc_x,
            INT NOT NULL loc_y,
            STRING loc_name,
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
