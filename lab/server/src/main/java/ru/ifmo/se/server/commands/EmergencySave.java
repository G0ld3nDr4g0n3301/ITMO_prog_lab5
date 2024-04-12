package ru.ifmo.se.server.commands;

import java.util.logging.Logger;

public class EmergencySave {

    private static final Logger logger = Logger.getLogger(EmergencySave.class.getName());

    public static void save(){
        logger.info("emergency save...");        
        new Save("","").execute(null);
        logger.info("saved successfully");
        System.exit(0);
    }
    
}
