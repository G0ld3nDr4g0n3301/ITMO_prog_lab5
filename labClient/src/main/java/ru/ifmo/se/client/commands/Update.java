package ru.ifmo.se.client.commands;

import java.util.Arrays;
import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.collections.Person;

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
    public boolean execute(String[] args){
        if(args.length < 5){
            CLIOutputManager.print("Not enough arguments.");
            return false;
        }
        Integer id = null;
        try{
            id = Integer.parseInt(args[1]);
        } catch(NullPointerException e){
            CLIOutputManager.print("Not enough arguments.");
            return false;
        } catch (NumberFormatException e){
            CLIOutputManager.print("id must be a number");
            return false;
        }
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            CLIOutputManager.print("No such id in collection.");
            return false;
        }
        args = Arrays.copyOfRange(args, 1, args.length - 1);
        Person newPerson = AskPerson.generatePerson(args);
        if(newPerson == null){
            CLIOutputManager.print("Error in creating new Person. Try again.");
            return false;
        }
        CollectionManager.remove(person);
        newPerson.setId(id);
        CollectionManager.add(newPerson);
        return true;
    }
}
