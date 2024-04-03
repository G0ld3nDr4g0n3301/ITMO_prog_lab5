package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.Person;

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
    public boolean execute(String[] args){
        for(Person p : CollectionManager.getCollection()){
            CLIOutputManager.print("------------------------------------");
            CLIOutputManager.print(p);
            CLIOutputManager.print("------------------------------------");
        }
        return true;
    }
}
