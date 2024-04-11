package ru.ifmo.se.server.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.server.collections.CreationDate;
import ru.ifmo.se.server.collections.Id;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

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
        person.setCreationDate(new CreationDate().create(""));
        person.setId(new Id().create(""));
        List<Person> removeList = new ArrayList<Person>();
        if (!Validator.validatePerson(person)) {
            return null;
        }
  /*      for(Person p : CollectionManager.getCollection()){
            if(p.compareTo(person) < 0){
                removeList.add(p);
            }
        } */
        removeList = CollectionManager.getCollection().stream()
        .filter((Person p) -> p.compareTo(person) < 0)
        .collect(Collectors.toList());
        CollectionManager.remove(removeList);
        return new Request(200);
    }
}
