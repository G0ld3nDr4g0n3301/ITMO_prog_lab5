package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request execute(Serializable args){
        CollectionManager.clear();
        Request request = new Request<>(Commands.RESPONSE);
        request.setStatusCode(200);
        return request;
    }
}
