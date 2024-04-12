package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.server.collections.CreationDate;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

/**
 * replace person with given id,with another person 
 * @author raistlin
 */
public class Update extends Command{

    private static final Logger logger = Logger.getLogger(Update.class.getName());
    
    
    public Update(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Update";
    }
    
    @Override
    public Request execute(Request arguments){
        if(arguments.getId() == null || arguments.getPerson() == null){
            logger.warning("not enough args");
            return new Request(404, "Not enough arguments.");
        }
        Integer id = arguments.getId();
        Person person = CollectionManager.findPerson(id);
        if(person == null) {
            logger.warning("no such id in collection");
            return new Request(404,"No such id in collection.");
        }
        Person newPerson = arguments.getPerson();
        if(newPerson == null){
            logger.warning("No person is given");
            return new Request(404, "Error in creating new Person. Try again.");
        }
        
        newPerson.setId(id);
        newPerson.setCreationDate(new CreationDate().create(null));
        if (!Validator.validatePerson(person)) {
            logger.warning("given person is inappropriate");
            return null;
        }
        CollectionManager.remove(person);
        logger.info("removed old person");
        CollectionManager.add(newPerson);
        logger.info("added new person");
        return new Request(200);
    }
}
