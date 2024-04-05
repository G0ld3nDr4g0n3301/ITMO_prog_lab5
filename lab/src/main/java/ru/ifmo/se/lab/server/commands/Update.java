package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Request;

/**
 * replace person with given id,with another person 
 * @author raistlin
 */
public class Update extends Command{
    
    
    public Update(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Update";
    }
    
    @Override
    public Request execute(Request arguments){
        if(arguments.getId() == null || arguments.getPerson() == null){
            return new Request(404, "Not enough arguments.");
        }
        Integer id = arguments.getId();
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            return new Request(404,"No such id in collection.");
        }
        Person newPerson = arguments.getPerson();
        if(newPerson == null){
            return new Request(404, "Error in creating new Person. Try again.");
        }
        CollectionManager.remove(person);
        newPerson.setId(id);
        CollectionManager.add(newPerson);
        return new Request(200);
    }
}
