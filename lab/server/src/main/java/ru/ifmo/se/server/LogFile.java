package ru.ifmo.se.server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Logger;

public class LogFile {

    private static Logger logger = Logger.getLogger(LogFile.class.getName());
    private static volatile Handler fileHandler;

    private static void createHandler() {
        try{
            fileHandler = new FileHandler("java.log");
            logger.addHandler(fileHandler);
        } catch (IOException e){
            System.out.println("jslakjflksdjfalksjdf");
            // handle
        }
    }

    public static void warning(String input){
        logger.warning(input);
    }

    public static void info(String input){
        logger.info(input);
    }

    public static synchronized Handler getHandler(){
        if(fileHandler == null){
            createHandler();
        }
        return fileHandler;
    }
}
