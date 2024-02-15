package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.*;
import java.util.HashMap;
import java.util.Set;

public class Help extends Command {
    private HashMap<String, Command> commands;
    
    public Help(String name, String description){
        this.commands = Invoker.getCommands();
        this.name = name;
        this.description = description;
    }
    
    public int execute(String[] args){
        Set<String> commandSet = this.commands.keySet();
        for(String key : commandSet){
            OutputManager.print(key + " - " + this.commands.get(key).getDescription());
        }
        return 0;
    }
    
    
}
