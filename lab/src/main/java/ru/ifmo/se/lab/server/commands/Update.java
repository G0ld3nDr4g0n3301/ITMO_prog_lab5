package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import java.util.Arrays;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.AskPerson;
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
    public Request execute(Serializable args){
        if(args.length < 5){
            OutputManager.print("Not enough arguments.");
            return false;
        }
        Integer id = null;
        try{
            id = Integer.parseInt(args[1]);
        } catch(NullPointerException e){
            OutputManager.print("Not enough arguments.");
            return false;
        } catch (NumberFormatException e){
            OutputManager.print("id must be a number");
            return false;
        }
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            OutputManager.print("No such id in collection.");
            return false;
        }
        args = Arrays.copyOfRange(args, 1, args.length - 1);
        Person newPerson = AskPerson.generatePerson(args);
        if(newPerson == null){
            OutputManager.print("Error in creating new Person. Try again.");
            return false;
        }
        CollectionManager.remove(person);
        newPerson.setId(id);
        CollectionManager.add(newPerson);
        return true;
    }
}
