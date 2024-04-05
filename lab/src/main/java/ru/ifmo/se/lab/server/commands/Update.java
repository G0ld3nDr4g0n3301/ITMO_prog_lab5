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
    public Request execute(Serializable arguments){
        Serializable[] args = (Serializable[]) arguments;
        if(args.length < 2){
            return new Request<String>(404, "Not enough arguments.");
        }
        Integer id = null;
        try{
            id = (Integer) args[0];
        } catch(NullPointerException e){
            return new Request<String>(404, "Not enough arguments.");
        } catch (NumberFormatException e){
            return new Request<String>(404, "id must be a number");
        }
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            return new Request<String>(404,"No such id in collection.");
        }
        Person newPerson = (Person) args[1];
        if(newPerson == null){
            return new Request<String>(404, "Error in creating new Person. Try again.");
        }
        CollectionManager.remove(person);
        newPerson.setId(id);
        CollectionManager.add(newPerson);
        return new Request<>(200);
    }
}
