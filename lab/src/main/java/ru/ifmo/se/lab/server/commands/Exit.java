package ru.ifmo.se.lab.server.commands;

import java.io.IOException;
import java.io.Serializable;

import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.ConnectionManager;
import ru.ifmo.se.lab.server.net.Request;


/**
 * exit from program
 * @author raistlin
 */
public class Exit extends Command {
    
    public Exit(String name, String descr){
        this.name = name;
        this.description = descr;
    }
   
    @Override
    public Request execute(Request args){
        try {
            ConnectionManager.send(new Request(200));
            ConnectionManager.close();
        } catch (IOException e) {
            // i'm lost
        }

        System.exit(0);
        return new Request(200);
    }
}
