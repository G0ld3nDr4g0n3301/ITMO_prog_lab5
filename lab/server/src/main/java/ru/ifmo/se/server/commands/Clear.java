package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * completely clears the collection
 * @author raistlin
 */
public class Clear extends Command{
    
    private static final Logger logger = Logger.getLogger(Clear.class.getName());
    
    public Clear(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Clear";
    }
    
    @Override
    public Request execute(Request args){
        CollectionManager.clear();
        logger.info("Cleared the collection");
        return new Request(200);
    }
}
