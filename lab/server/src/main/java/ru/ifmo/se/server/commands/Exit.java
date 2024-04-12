package ru.ifmo.se.server.commands;

import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.server.net.ConnectionManager;
import ru.ifmo.se.common.net.Request;


/**
 * exit from program
 * @author raistlin
 */
public class Exit extends Command {

    private static final Logger logger = Logger.getLogger(Exit.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Exit(String name, String descr){
        this.name = name;
        this.description = descr;
    }
   
    @Override
    public Request execute(Request args){
        logger.info("client disconnected");
        return null;
    }
}
