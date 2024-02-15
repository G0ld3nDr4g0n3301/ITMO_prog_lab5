package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;

public class Invoker {
    private static HashMap<String,Command> commands;
    
    public static void init(){
        commands = new HashMap<>();
        
        Exit exit = new Exit("exit","quit the program");
        Help help = new Help("help", "print this screen");
//        Add add = new Add("add", "create a new element, and add it to collection.");
        
        commands.put(exit.getName(), exit);
        commands.put(help.getName(), help);
//        commands.put(add.getName(), add);
    }
    
    public static int execute(String[] args){
        if (!(commands.containsKey(args[0]))){
            OutputManager.print("Wrong command. Type \"help\" for command list");
            return 1;
        }
        commands.get(args[0]).execute(args);
        return 0;
    }
    
    public static HashMap<String, Command> getCommands(){
        return commands;
    }
}
