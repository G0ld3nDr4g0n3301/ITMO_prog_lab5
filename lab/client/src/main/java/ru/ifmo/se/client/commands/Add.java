package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;

import java.io.Serializable;

import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * Command for adding new person
 * @author raistlin
 */
public class Add extends Command{
    
    public Add(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if(person == null){
            return null;
        }
        Request request = new Request(Commands.ADD);
        request.setPerson(person);

        return request;
    }
}
