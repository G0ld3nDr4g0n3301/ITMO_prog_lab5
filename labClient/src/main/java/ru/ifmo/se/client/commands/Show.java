package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;
import java.io.Serializable;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

/**
 * prints all elements of collection
 * @author raistlin
 */
public class Show extends Command{
    
    public Show(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(String[] args){
        return new Request(Commands.SHOW);
    }
}
