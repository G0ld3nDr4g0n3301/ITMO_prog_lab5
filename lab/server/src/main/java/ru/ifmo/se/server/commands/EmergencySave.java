package ru.ifmo.se.server.commands;

import java.io.IOException;
import java.util.logging.Logger;

import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.net.ConnectionManager;

public class EmergencySave {

    private static final Logger logger = Logger.getLogger(EmergencySave.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public static void save(){
        try {
        logger.info("emergency save...");        
        new Save("","").execute(null);
        logger.info("saved successfully");
        ConnectionManager.close();
        System.exit(0);
        } catch (IOException e){
            System.exit(0);
        }
    }
    
}
