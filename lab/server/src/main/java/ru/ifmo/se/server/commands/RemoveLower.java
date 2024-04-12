package ru.ifmo.se.server.commands;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.server.collections.CreationDate;
import ru.ifmo.se.server.collections.Id;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

/**
 * removes all collection's elements, that are less than the given one.
 * @author raistlin
 */
public class RemoveLower extends Command{
    
    private static final Logger logger = Logger.getLogger(RemoveLower.class.getName());

    public RemoveLower(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "RemoveLower";
    }
    
    @Override
    public Request execute(Request args){
        Person person = args.getPerson();
        if (person == null){
            logger.warning("No person specified in request");
            return null;
        }
        person.setCreationDate(new CreationDate().create(""));
        person.setId(new Id().create(""));
        List<Person> removeList = new ArrayList<Person>();
        if (!Validator.validatePerson(person)) {
            logger.warning("Given person contains malicious data");
            return null;
        }
        removeList = CollectionManager.getCollection().stream()
        .filter((Person p) -> p.compareTo(person) < 0)
        .collect(Collectors.toList());
        CollectionManager.remove(removeList);
        logger.info("Successfully removed persons from list");
        return new Request(200);
    }
}
