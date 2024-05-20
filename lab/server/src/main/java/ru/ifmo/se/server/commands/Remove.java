package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * remove given person from the collection
 * @author raistlin
 */
public class Remove extends Command{
    
    private static final Logger logger = Logger.getLogger(Remove.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Remove(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Remove";
    }
    
    @Override
    public Request execute(Request arguments){
        Integer id = arguments.getId();
        Person person = CollectionManager.findPerson(id);
        Request request = new Request(404);
        if(person == null) {
            request.setMsg("No such id in collection.");
            request.setStatusCode(404);
            logger.warning("given id is wrong.no such element in collection");
            return request;
        }
        if(person.getOwnerId() != arguments.getOwnerId()){
            request.setMsg("Permission denied. It's not your element.");
            logger.warning("User has no permisstion for this operation");
            return request;
        }
        CollectionManager.remove(person);
        request.setStatusCode(200);
        logger.info("Successfully removed person from collection");
        return request;
    }
}
