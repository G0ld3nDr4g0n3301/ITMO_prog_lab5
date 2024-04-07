package ru.ifmo.se.server.commands;

import java.io.Serializable;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.collections.Location;
import ru.ifmo.se.server.collections.LocationField;
import ru.ifmo.se.server.collections.Person;
import ru.ifmo.se.server.net.Request;

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
    public Request execute(Request args){
        
        Location loc = new LocationField().create("");
        Person person = CollectionManager.findPerson(loc);
        while(person != null){
            CollectionManager.remove(person);
            person = CollectionManager.findPerson(loc);
        }
        return new Request(200);
    }
}
