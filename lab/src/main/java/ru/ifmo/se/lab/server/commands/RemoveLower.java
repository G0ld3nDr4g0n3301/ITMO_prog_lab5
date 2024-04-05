package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.collections.AskPerson;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request execute(Request args){
        Person person = args.getPerson();
        if (person == null){
            return null;
        }
        ArrayList<Person> removeList = new ArrayList<Person>();
        
        for(Person p : CollectionManager.getCollection()){
            if(p.compareTo(person) < 0){
                removeList.add(p);
            }
        }
        CollectionManager.remove(removeList);
        return new Request(200);
    }
}
