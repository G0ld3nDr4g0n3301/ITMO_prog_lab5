package ru.ifmo.se.server.commands;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.server.collections.Id;
import ru.ifmo.se.server.collections.CreationDate;

import java.io.IOException;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * Command for adding new person
 * @author raistlin
 */
public class Add extends Command{
    

    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Add(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){
        Person person = args.getPerson();
        if(person == null){
            logger.warning("No person specified in request");
            return null;
        }
        ReadWriteLock lock = CollectionManager.getLock();
        lock.writeLock().lock();
        person.setOwnerId(args.getOwnerId());
        person.setCreationDate(new CreationDate().create(null));
        if (!Validator.validatePerson(person)) {
            logger.warning("Given person contains malicious data");
            return null;
        }
        CollectionManager.add(person);
        lock.writeLock().unlock();
        Request request = new Request(Commands.RESPONSE, 200);
        logger.info("Successfully added person to collection");
        return request;
    }
}
