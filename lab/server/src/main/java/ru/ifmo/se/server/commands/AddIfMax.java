package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.server.collections.AskPerson;
import ru.ifmo.se.server.collections.CreationDate;
import ru.ifmo.se.server.collections.Id;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

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
    public Request execute(Request args){
        Person person = args.getPerson();
        person.setId(new Id().create(null));
        person.setCreationDate(new CreationDate().create(null));
        List<Person> collection = CollectionManager.getCollection();
        Request request = new Request(Commands.RESPONSE, null);
        if (!Validator.validatePerson(person)) {
            return null;
        }
        if(collection.size() == 0 || person.compareTo(collection.get(collection.size() - 1)) > 0){
            CollectionManager.add(person);
            request.setStatusCode(200);
            OutputManager.print("Adding element to collection...");
        } else {
            request.setStatusCode(400);
            request.setMsg("Element is less than max element of collection.");
        }
        
        return request;
    }
}
