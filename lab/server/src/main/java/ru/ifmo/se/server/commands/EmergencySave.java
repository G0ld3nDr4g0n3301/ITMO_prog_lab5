package ru.ifmo.se.server.commands;

public class EmergencySave {

    public static void save(){
        new Save("","").execute(null);
        System.exit(0);
    }
    
}
