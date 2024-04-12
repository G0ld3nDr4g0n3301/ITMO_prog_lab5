package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.Validator;
import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.server.collections.LocationField;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

/**
 * removes all persons with given location
 * @author raistlin
 */
public class RemoveLoc extends Command{

    private static final Logger logger = Logger.getLogger(RemoveLoc.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public RemoveLoc(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){
        
        Location loc = args.getLoc();
        if (loc == null) {
            logger.warning("no location specified in request");
            return new Request(404, "No location specified");
        }
        if (!Validator.validateLoc(loc)) {
            logger.warning("location is incorrect");
            return new Request(404,"Incorrect location");
        }
        Person person = CollectionManager.findPerson(loc);
        while(person != null){
            CollectionManager.remove(person);
            person = CollectionManager.findPerson(loc);
        }
        logger.info("removed persons with given location");
        return new Request(200);
    }
}
