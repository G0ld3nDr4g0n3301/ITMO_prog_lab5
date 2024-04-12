package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

/**
 * prints all elements of collection
 * @author raistlin
 */
public class Show extends Command{

    private static final Logger logger = Logger.getLogger(Show.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Show(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){
        String answer = "";
        for(Person p : CollectionManager.sortLoc(CollectionManager.getCollection())){
            answer += "------------------------------------" + "\n";
            answer += p + "\n";
            answer += "------------------------------------" + "\n";
        }
        logger.info("got the collection elements");
        return new Request(400, answer);
    }
}
