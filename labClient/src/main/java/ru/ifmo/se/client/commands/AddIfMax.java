package ru.ifmo.se.client.commands;

import java.util.ArrayList;
import java.util.Comparator;
import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.collections.Person;

/**
 * Adds an element,if it's greater than the max one.
 * @author raistlin
 */
public class AddIfMax extends Command{
    
    public AddIfMax(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "AddIfMax";
    }
    
    @Override
    public boolean execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if(person == null){
            return false;
        }
        ArrayList<Person> collection = CollectionManager.getCollection();
        if(person.compareTo(collection.get(collection.size() - 1)) > 0){
            CollectionManager.add(person);
            CLIOutputManager.print("Adding element to collection...");
        } else {
            CLIOutputManager.print("Element is less than max element of collection.");
        }
        return true;
    }
}
