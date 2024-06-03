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

    private static Person p = null;

    static {
            logger.addHandler(LogFile.getHandler());
    }

    public Add(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    public static void setPerson(Person newP){
        p = newP;
    }

    @Override
    public Request execute(String[] args){
        if (p == null){
            return null;
        }
        Request request = new Request(Commands.ADD);
        request.setPerson(p);
        logger.info("Sending ADD request");
        return request;
    }
}
