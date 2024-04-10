package ru.ifmo.se.server.commands;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.server.collections.Id;
import ru.ifmo.se.server.collections.CreationDate;


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
    public Request execute(Request args){
        Person person = args.getPerson();
        if(person == null){
            return null;
        }
        person.setId(new Id().create(null));
        person.setCreationDate(new CreationDate().create(null));
        if (!Validator.validatePerson(person)) {
            return null;
        }
        CollectionManager.add(person);
        Request request = new Request(Commands.RESPONSE, 200);
        
        return request;
    }
}
