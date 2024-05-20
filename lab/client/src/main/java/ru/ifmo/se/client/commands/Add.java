package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.LogFile;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.client.collections.AskPerson;
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
    public Request execute(String[] args){
        Person person = AskPerson.generatePerson(args);
        if(person == null){
            logger.warning("Wrong values of Person fields given.");
            return null;
        }
        Request request = new Request(Commands.ADD);
        request.setPerson(person);
        logger.info("Sending ADD request");
        return request;
    }
}
