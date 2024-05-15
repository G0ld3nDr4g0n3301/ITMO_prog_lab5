package ru.ifmo.se.server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class LogFile {

    private static volatile Handler fileHandler;

    private static void createHandler() {
        try{
            fileHandler = new FileHandler("java.log");
        } catch (IOException e){
            System.out.println("jslakjflksdjfalksjdf");
            // handle
        }
    }

    public static synchronized Handler getHandler(){
        if(fileHandler == null){
            createHandler();
        }
        return fileHandler;
    }
}
