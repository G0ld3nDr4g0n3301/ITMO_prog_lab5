package ru.ifmo.se.client.commands;

import java.io.Serializable;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.common.net.Commands;
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
    public Request execute(String[] args){
        return new Request(Commands.REMOVE_LAST);
    }
}
