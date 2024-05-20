package ru.ifmo.se.client.commands;

import java.io.Serializable;
import java.util.logging.Logger;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.LogFile;

/**
 * Prints all hair colors of collection's elements, in descending order
 * @author raistlin
 */
public class PrintHairColor extends Command{
    
    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
            logger.addHandler(LogFile.getHandler());
    }

    public PrintHairColor(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "PrintHairColor";
    }
    
    @Override
    public Request execute(String[] args){
        logger.info("Sending HAIR request");
        return new Request(Commands.HAIR);
    }
}
