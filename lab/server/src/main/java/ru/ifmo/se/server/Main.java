package ru.ifmo.se.server;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

import sun.misc.Signal;
import ru.ifmo.se.server.commands.EmergencySave;
import ru.ifmo.se.server.commands.Load;
import ru.ifmo.se.server.commands.Save;
import ru.ifmo.se.server.db.CreateTables;
import ru.ifmo.se.server.db.DBConnection;
import ru.ifmo.se.server.net.ConnectionManager;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

public class Main {

    private static final Logger logger = Logger.getLogger(Main.class.getName());

    static {
        Connection connection = DBConnection.connect();
        if(connection != null) {
            System.out.println("WEEEE ARE THE CHAAAMPIOONS");
        }
        try {
            Statement query = connection.createStatement();
            ResultSet set = query.executeQuery("SELECT * FROM how_useful_for;");
            System.out.println(set.getString(0));
            CreateTables.create(connection);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        logger.addHandler(LogFile.getHandler());
    }
    
    public static void main(String[] args){
        if(args.length > 0){
            Invoker.setCurrMainFile(new File(args[0]));
        }
        if(args.length > 1 && Validator.validateInt(args[1])) {
            ConnectionManager.setPort(Integer.parseInt(args[1]));
        }
        System.out.println(ConnectionManager.getPort());
        new Load(null,null).execute(new Request(200));
        Signal.handle(new Signal("INT"), signal -> EmergencySave.save());
        
        try{
            ConnectionManager.initSocket();
            logger.info("initialized socket");
            while (true) {
                ConnectionManager.run();
            }
        } catch (IOException e ) {
            logger.log(Level.WARNING, "Can't accept a connection", e);

        }
        
    }



    

}
