package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * Prints info about collection(init date, size,type)
 * @author raistlin
 */
public class Info extends Command {

    private static final Logger logger = Logger.getLogger(Info.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Info(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Info";
    }
    
    @Override
    public Request execute(Request args){
        System.out.println("info is executed");
        String info = CollectionManager.getType().toString() + "\n" + CollectionManager.getInitDate().toString() + "\n" + CollectionManager.getSize();
        logger.info("got the info");
        return new Request(400,info);
    }
}
