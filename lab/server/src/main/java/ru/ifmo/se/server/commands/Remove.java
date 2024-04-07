package ru.ifmo.se.server.commands;

import java.io.Serializable;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

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
    public Request execute(Request arguments){
        Integer id = arguments.getId();
        Person person = CollectionManager.findPerson(id);
        Request request = new Request(404);
        if(person == null) {
            request.setMsg("No such id in collection.");
            request.setStatusCode(404);
            return request;
        }
        CollectionManager.remove(person);
        request.setStatusCode(200);
        return request;
    }
}
