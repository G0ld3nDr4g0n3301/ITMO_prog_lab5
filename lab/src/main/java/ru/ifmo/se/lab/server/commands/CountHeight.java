package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Person;

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
    public boolean execute(String[] args){
        if(args.length < 2){
            OutputManager.print("Not enough arguments");
            return false;
        }
        int count = 0;
        for(Person p : CollectionManager.getCollection()){
            if(p.getHeight().toString().compareTo(args[1]) == 0){
                ++count;
            }
        }
        OutputManager.print(count);
        return true;
    }
}
