package ru.ifmo.se.client.commands;

import java.io.Serializable;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.collections.AskPerson;
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
    public Request execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if(person == null){
            return null;
        }
        Request request = new Request(Commands.ADDIF);
        request.setPerson(person);
        return request;
    }
}
