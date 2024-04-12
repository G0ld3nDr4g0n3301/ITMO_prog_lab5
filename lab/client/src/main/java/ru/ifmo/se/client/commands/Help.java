package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.*;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

import java.io.Serializable;
import java.util.logging.Logger;

/**
 * Prints all available commands and their descriptions.
 * @author raistlin
 */
public class Help extends Command {
    
    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
            logger.addHandler(LogFile.getHandler());
    }

    public Help(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    @Override
    public Request execute(String[] args){
        logger.info("Sending HELP request");
        return new Request(Commands.HELP);
    }
    
    
}
