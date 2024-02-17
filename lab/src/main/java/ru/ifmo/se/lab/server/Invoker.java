package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;
import java.util.Stack;
import java.util.Scanner;

public class Invoker {
    private static HashMap<String,Command> commands;
    private static boolean fromFileMode = false;
    private static Stack<ReadFile> fileReadStack = new Stack<>();
    
    static{
        commands = new HashMap<>();
        
        Exit exit = new Exit("exit","quit the program");
        Help help = new Help("help", "print this screen");
        Add add = new Add("add", "(add name height weight {birthday})create a new element, and add it to collection.");
        Show show = new Show("show", "print all collection's elements.");
        ExecuteScript execScr = new ExecuteScript("execute_script","execute_script (filename) - executing a lines from a file,like it's normal CLI input.");
        
        
        commands.put(exit.getName(), exit);
        commands.put(help.getName(), help);
        commands.put(add.getName(), add);
        commands.put(show.getName(), show);
        commands.put(execScr.getName(), execScr);
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
    
    public static boolean getModeState(){
        return fromFileMode;
    }
    
    public static void setModeState(boolean fromFile){
        fromFileMode = fromFile;
    }
    
    public static ReadFile getCurrReadFile(){
        return fileReadStack.peek();
    }
    
    public static void setCurrReadFile(ReadFile file){
        fileReadStack.push(file);
    }
    
    public static ReadFile removeCurrReadFile(){
        fileReadStack.peek().close();
        return fileReadStack.pop();
    }
    
}
