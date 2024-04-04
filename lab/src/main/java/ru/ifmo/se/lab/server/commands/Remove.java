package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request<String> execute(Serializable arguments){
        Integer id = (Integer) arguments;
        Person person = CollectionManager.findPerson(id);
        Request<String> request = new Request<>(Commands.RESPONSE);
        if(person == null) {
            request.setArgument("No such id in collection.");
            request.setStatusCode(404);
            return request;
        }
        CollectionManager.remove(person);
        request.setStatusCode(200);
        return request;
    }
}
