package ru.ifmo.se.client.commands;

import java.io.Serializable;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
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
    public Request execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if (person == null){
            return null;
        }
        
        Request request = new Request(Commands.REMOVE_LOWER);
        request.setPerson(person);
        return request;
    }
}
