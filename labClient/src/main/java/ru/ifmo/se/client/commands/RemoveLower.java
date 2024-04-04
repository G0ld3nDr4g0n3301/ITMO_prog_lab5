package ru.ifmo.se.client.commands;

import java.io.Serializable;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.collections.Person;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

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
    public Serializable execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if (person == null){
            return null;
        }
        
        Request<Person> request = new Request<>(Commands.REMOVE_LOWER);
        return request;
    }
}
