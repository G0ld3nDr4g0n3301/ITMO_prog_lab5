package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.Location;
import ru.ifmo.se.client.collections.LocationField;
import ru.ifmo.se.client.collections.Person;

/**
 * removes all persons with given location
 * @author raistlin
 */
public class RemoveLoc extends Command{
    
    public RemoveLoc(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        
        Location loc = new LocationField().create("");
        Person person = CollectionManager.findPerson(loc);
        while(person != null){
            CollectionManager.remove(person);
            person = CollectionManager.findPerson(loc);
        }
        return true;
    }
}
