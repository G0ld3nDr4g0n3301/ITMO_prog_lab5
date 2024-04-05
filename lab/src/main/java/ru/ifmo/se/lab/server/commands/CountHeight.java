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
    public Request execute(Request args){
        String arguments = args.getMsg();
        if(arguments == null){
            Request request = new Request(404, "Not enough arguments");
            return request;
        }
        Integer count = 0;
        for(Person p : CollectionManager.getCollection()){
            if(p.getHeight().toString().compareTo(arguments) == 0){
                ++count;
            }
        }
        return new Request(400, count.toString());
    }
}
