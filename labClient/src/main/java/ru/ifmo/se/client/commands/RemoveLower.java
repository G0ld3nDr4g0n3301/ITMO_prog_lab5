package ru.ifmo.se.client.commands;

import java.util.ArrayList;
import java.util.List;
import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.collections.Person;

/**
 * removes all collection's elements, that are less than the given one.
 * @author raistlin
 */
public class RemoveLower extends Command{
    
    public RemoveLower(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "RemoveLower";
    }
    
    @Override
    public boolean execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if (person == null){
            return false;
        }
        ArrayList<Person> removeList = new ArrayList<Person>();
        
        for(Person p : CollectionManager.getCollection()){
            if(p.compareTo(person) < 0){
                removeList.add(p);
            }
        }
        CollectionManager.remove(removeList);
        return true;
    }
}
