package ru.ifmo.se.server.commands;

import java.io.Serializable;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.collections.Person;
import ru.ifmo.se.server.net.Request;

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
    public Request execute(Request args){
        String answer = "";
        for(Person p : CollectionManager.getCollection()){
            answer += "------------------------------------";
            answer += p;
            answer += "------------------------------------";
        }
        return new Request(400, answer);
    }
}
