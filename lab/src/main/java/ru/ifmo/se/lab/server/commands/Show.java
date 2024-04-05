package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request execute(Serializable args){
        String answer = "";
        for(Person p : CollectionManager.getCollection()){
            answer += "------------------------------------";
            answer += p;
            answer += "------------------------------------";
        }
        return new Request<>(400, answer);
    }
}
