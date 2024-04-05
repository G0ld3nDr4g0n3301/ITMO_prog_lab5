package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import java.util.ArrayList;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Request;

/**
 * Counts all persons with given height
 * @author raistlin
 */
public class CountHeight extends Command{
    
    public CountHeight(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "CountHeight";
    }
    
    @Override
    public Request execute(Serializable args){
        ArrayList<String> arguments = (ArrayList<String>) args;
        if(arguments.size() < 1){
            Request<String> request = new Request<>(404, "Not enough arguments");
            return request;
        }
        int count = 0;
        for(Person p : CollectionManager.getCollection()){
            if(p.getHeight().toString().compareTo(arguments.get(0)) == 0){
                ++count;
            }
        }
        return new Request<Integer>(400, count);
    }
}
