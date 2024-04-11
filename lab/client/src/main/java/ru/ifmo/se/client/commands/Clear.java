package ru.ifmo.se.client.commands;

import java.io.Serializable;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * completely clears the collection
 * @author raistlin
 */
public class Clear extends Command{
    
    public Clear(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Clear";
    }
    
    @Override
    public Request execute(String[] args){
        Request request = new Request(Commands.CLEAR);
        return request;
    }
}