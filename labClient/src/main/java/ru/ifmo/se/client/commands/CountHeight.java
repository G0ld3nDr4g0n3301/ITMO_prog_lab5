package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.Person;

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
            CLIOutputManager.print("Not enough arguments");
            return false;
        }
        int count = 0;
        for(Person p : CollectionManager.getCollection()){
            if(p.getHeight().toString().compareTo(args[1]) == 0){
                ++count;
            }
        }
        CLIOutputManager.print(count);
        return true;
    }
}
