package ru.ifmo.se.server;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Handler;

public class LogFile {

    private static Handler fileHandler;

    static {
        try{
            fileHandler = new FileHandler("java.log");
        } catch (IOException e){
            System.out.println("jslakjflksdjfalksjdf");
            // handle
        }
    }

    public static Handler getHandler(){
        return fileHandler;
    }
}
