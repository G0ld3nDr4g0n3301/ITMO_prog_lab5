package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Person;

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
            OutputManager.print("------------------------------------");
            OutputManager.print(p);
            OutputManager.print("------------------------------------");
        }
        return true;
    }
}
