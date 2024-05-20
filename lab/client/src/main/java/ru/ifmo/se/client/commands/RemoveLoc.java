package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.LogFile;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.client.collections.LocationField;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * removes all persons with given location
 * @author raistlin
 */
public class RemoveLoc extends Command{
    
    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
            logger.addHandler(LogFile.getHandler());
    }

    public RemoveLoc(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(String[] args){
        
        Location loc = new LocationField().create("");
        Request request = new Request(Commands.REMOVE_BY_LOC);
        request.setLoc(loc);
        logger.info("Sending REMOVE_BY_LOCATION request");
        return request;
    }
}
