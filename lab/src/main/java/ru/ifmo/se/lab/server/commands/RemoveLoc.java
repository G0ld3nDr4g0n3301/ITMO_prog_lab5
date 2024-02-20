package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.collections.Location;
import ru.ifmo.se.lab.server.collections.LocationField;
import ru.ifmo.se.lab.server.collections.Person;

public class RemoveLoc extends Command{
    
    public RemoveLoc(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        
        Location loc = new LocationField().create("");
        for(Person person = CollectionManager.findPerson(loc); person != null; ){
            CollectionManager.remove(person);
        }
        return true;
    }
}
