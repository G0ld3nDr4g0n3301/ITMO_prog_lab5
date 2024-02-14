package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;

public class Invoker {
    private static HashMap<String,ICommand> commands;
    
    public static void init(){
        commands = new HashMap<>();
        Exit exit = new Exit("exit","F");
        commands.put(exit.getName(), exit);
    }
    
    public static void execute(String[] args){
        commands.get(args[0]).execute(args);
    }
}
