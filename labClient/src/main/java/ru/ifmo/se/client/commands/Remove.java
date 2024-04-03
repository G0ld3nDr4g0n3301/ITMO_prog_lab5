package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.Person;

/**
 * remove given person from the collection
 * @author raistlin
 */
public class Remove extends Command{
    
    public Remove(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Remove";
    }
    
    @Override
    public boolean execute(String[] args){
        Integer id = null;
        try{
            id = Integer.parseInt(args[1]);
        } catch(ArrayIndexOutOfBoundsException e){
            CLIOutputManager.print("Not enough arguments.");
            return false;
        }catch (NumberFormatException e){
            CLIOutputManager.print("id must be a number");
            return false;
        }
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            CLIOutputManager.print("No such id in collection.");
            return false;
        }
        CollectionManager.remove(person);
        return true;
    }
}
