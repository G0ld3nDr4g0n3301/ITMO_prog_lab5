package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;
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
    

    private static final Logger logger = Logger.getLogger(AddIfMax.class.getName());
    
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
        if (person == null){
            logger.warning("No person specified");
            return null;
        }
        person.setId(new Id().create(null));
        person.setCreationDate(new CreationDate().create(null));
        Request request = new Request(Commands.RESPONSE, null);
        if (!Validator.validatePerson(person)) {
            logger.warning("person info is incorrect");
            return null;
        }
        if(CollectionManager.addIfMax(person)){
            request.setStatusCode(200);
            logger.info("added an element");
        } else {
            request.setStatusCode(400);
            logger.info("Element wasn't added");
        }
        
        return request;
    }
}
