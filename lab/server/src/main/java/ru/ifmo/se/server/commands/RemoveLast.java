package ru.ifmo.se.server.commands;

import java.io.Serializable;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.common.net.Request;

/**
 * remove last element from collection
 * @author raistlin
 */
public class RemoveLast extends Command{
    
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
        return new Request(200);
    }
}