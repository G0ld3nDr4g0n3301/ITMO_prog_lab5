package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Location;
import ru.ifmo.se.lab.server.collections.LocationField;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request execute(Serializable args){
        
        Location loc = new LocationField().create("");
        Person person = CollectionManager.findPerson(loc);
        while(person != null){
            CollectionManager.remove(person);
            person = CollectionManager.findPerson(loc);
        }
        return new Request<>(200);
    }
}
