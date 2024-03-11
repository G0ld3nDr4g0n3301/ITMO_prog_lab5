package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.*;
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
    public boolean execute(String[] args){
        Set<String> commandSet = Invoker.getCommands().keySet();
        for(String key : commandSet){
            OutputManager.print(key + " - " + Invoker.getCommands().get(key).getDescription());
        }
        return true;
    }
    
    
}
