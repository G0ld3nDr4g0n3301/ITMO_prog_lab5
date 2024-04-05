package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Comparator;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.AskPerson;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request execute(Serializable args){
        Person person = (Person) args;
        if(person == null){
            return null;
        }
        ArrayList<Person> collection = CollectionManager.getCollection();
        Request request = new Request<>(Commands.RESPONSE, null);
        if(person.compareTo(collection.get(collection.size() - 1)) > 0){
            CollectionManager.add(person);
            request.setStatusCode(200);
            OutputManager.print("Adding element to collection...");
        } else {
            request.setStatusCode(400);
            request.setArgument("Element is less than max element of collection.");
        }
        return request;
    }
}
