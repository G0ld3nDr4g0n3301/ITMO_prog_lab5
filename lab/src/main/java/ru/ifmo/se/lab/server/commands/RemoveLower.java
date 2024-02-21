package ru.ifmo.se.lab.server.commands;

import java.util.ArrayList;
import java.util.List;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.collections.AskPerson;
import ru.ifmo.se.lab.server.collections.Person;

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
