package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.*;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.Request;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Prints all available commands and their descriptions.
 * @author raistlin
 */
public class Help extends Command {
    
    public Help(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    @Override
    public Request execute(Serializable args){
        Set<Commands> commandSet = Invoker.getCommands().keySet();
        for(Commands key : commandSet){
            OutputManager.print(Invoker.getCommands().get(key).getName() + " - " + Invoker.getCommands().get(key).getDescription());
        }
        Request request = new Request<>(Commands.RESPONSE);
        request.setStatusCode(200); 
        return request;
    }
    
    
}
