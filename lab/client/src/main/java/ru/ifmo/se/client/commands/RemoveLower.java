package ru.ifmo.se.client.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.LogFile;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * removes all collection's elements, that are less than the given one.
 * @author raistlin
 */
public class RemoveLower extends Command{
    
    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
            logger.addHandler(LogFile.getHandler());
    }

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
        logger.info("Sending REMOVE_LOWER request");
        return request;
    }
}
