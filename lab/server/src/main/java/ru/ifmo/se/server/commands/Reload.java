package ru.ifmo.se.server.commands;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.logging.Logger;
import java.util.stream.Collectors;

import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.LogFile;

public class Reload extends Command{
    private static final Logger logger = Logger.getLogger(PrintHairColor.class.getName());
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    public Reload(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Reload";
    }
    
    @Override
    public Request execute(Request args){
        for (Person p : CollectionManager.getCollection()){
            System.out.println(p);
        }
        args.setCollection((ArrayList) CollectionManager.getCollection());
        return args;
    }
    
}
