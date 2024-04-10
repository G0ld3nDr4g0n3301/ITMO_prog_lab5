package ru.ifmo.se.server.commands;

import java.io.Serializable;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.server.collections.LocationField;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

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
        
        Location loc = args.getLoc();
        if (loc == null) {
            return new Request(404, "No location specified");
        }
        if (!Validator.validateLoc(loc)) {
            return new Request(404,"Incorrect location");
        }
        Person person = CollectionManager.findPerson(loc);
        while(person != null){
            CollectionManager.remove(person);
            person = CollectionManager.findPerson(loc);
        }
        return new Request(200);
    }
}
