package ru.ifmo.se.lab.server.commands;

import java.util.ArrayList;
import java.util.Comparator;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.AskPerson;
import ru.ifmo.se.lab.server.collections.Person;

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
            OutputManager.print("Adding element to collection...");
        } else {
            OutputManager.print("Element is less than max element of collection.");
        }
        return true;
    }
}
