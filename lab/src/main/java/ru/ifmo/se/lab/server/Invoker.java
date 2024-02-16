package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;

public class Invoker {
    private static HashMap<String,Command> commands;
    
    static{
        commands = new HashMap<>();
        
        Exit exit = new Exit("exit","quit the program");
        Help help = new Help("help", "print this screen");
        Add add = new Add("add", "(add name height weight {birthday})create a new element, and add it to collection.");
        
        commands.put(exit.getName(), exit);
        commands.put(help.getName(), help);
        commands.put(add.getName(), add);
    }
    
    public static boolean execute(String[] args){
        if (!(commands.containsKey(args[0]))){
            OutputManager.print("Wrong command. Type \"help\" for command list");
            return false;
        }
        commands.get(args[0]).execute(args);
        return true;
    }
    
    public static HashMap<String, Command> getCommands(){
        return commands;
    }
}
