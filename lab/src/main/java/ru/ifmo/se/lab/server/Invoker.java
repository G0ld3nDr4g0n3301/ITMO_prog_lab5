package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;

public class Invoker {
    private static HashMap<String,Command> commands;
    
    public static void init(){
        commands = new HashMap<>();
        
        Exit exit = new Exit("exit","quit the program");
        Help help = new Help("help", "print this screen");
        
        commands.put(exit.getName(), exit);
        commands.put(help.getName(), help);
    }
    
    public static void execute(String[] args){
        commands.get(args[0]).execute(args);
    }
    
    public static HashMap<String, Command> getCommands(){
        return commands;
    }
}
