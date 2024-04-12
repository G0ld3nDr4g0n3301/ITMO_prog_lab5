package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.common.net.Request;

/**
 * remove last element from collection
 * @author raistlin
 */
public class RemoveLast extends Command{

    private static final Logger logger = Logger.getLogger(RemoveLast.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public RemoveLast(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "RemoveLast";
    }
    
    @Override
    public Request execute(Request args){
        CollectionManager.removeLast();
        logger.info("successfully removed last person");
        return new Request(200);
    }
}
