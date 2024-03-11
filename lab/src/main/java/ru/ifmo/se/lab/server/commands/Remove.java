package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Person;

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
            OutputManager.print("Not enough arguments.");
            return false;
        }catch (NumberFormatException e){
            OutputManager.print("id must be a number");
            return false;
        }
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            OutputManager.print("No such id in collection.");
            return false;
        }
        CollectionManager.remove(person);
        return true;
    }
}
