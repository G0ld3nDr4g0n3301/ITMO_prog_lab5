package ru.ifmo.se.client.commands;

import java.io.IOException;
import java.io.Serializable;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.client.net.ConnectionManager;
import ru.ifmo.se.common.net.Request;


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
    public Request execute(String[] args){
        try {
            Request request = new Request(Commands.EXIT);
            ConnectionManager.send(request);
            System.exit(0);
        } catch (IOException e) {
            // TODO: handle
            try {
                ConnectionManager.close();
            } catch (IOException ex){
                // TODO: handle
            }
            System.exit(0);

            return null;
        } 
        return null;
    }
}
