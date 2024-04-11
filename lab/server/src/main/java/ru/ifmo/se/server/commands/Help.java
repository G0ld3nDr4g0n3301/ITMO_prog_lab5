package ru.ifmo.se.server.commands;

import ru.ifmo.se.server.*;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

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
    public Request execute(Request args){
        Set<Commands> commandSet = Invoker.getCommands().keySet();
        String answer = "";
        for(Commands key : commandSet){
            answer += Invoker.getCommands().get(key).getName() + " - " + Invoker.getCommands().get(key).getDescription() + "\n";
        }
        
        return new Request(400, answer);
    }
    
    
}