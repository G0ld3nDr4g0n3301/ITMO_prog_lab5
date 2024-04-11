package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

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
    public Request execute(Request args){
        Integer arguments = args.getId();
        if(arguments == null){
            Request request = new Request(404, "Not enough arguments");
            return request;
        }
        Long count = null;
        /* for(Person p : CollectionManager.getCollection()){
            if(p.getHeight().toString().compareTo(arguments.toString()) == 0){
                ++count;
            }
        } */
        count = CollectionManager.getCollection().stream()
        .filter((Person p) -> p.getHeight().toString().compareTo(arguments.toString()) == 0)
        .count();
        return new Request(400, count.toString());
    }
}
