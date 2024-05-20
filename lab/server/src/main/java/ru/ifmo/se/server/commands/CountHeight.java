package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

/**
 * Counts all persons with given height
 * @author raistlin
 */
public class CountHeight extends Command{

    private static final Logger logger = Logger.getLogger(CountHeight.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public CountHeight(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "CountHeight";
    }
    
    @Override
    public Request execute(Request args){
        Integer arguments = args.getId();
        if(arguments == null){
            logger.warning("no id specified");
            Request request = new Request(404, "Not enough arguments");
            return request;
        }
        Long count = null;
        count = CollectionManager.getCollection().stream()
        .filter((Person p) -> p.getHeight().toString().compareTo(arguments.toString()) == 0)
        .count();
        logger.info("Counted " + count + " persons");
        return new Request(400, count.toString());
    }
}
