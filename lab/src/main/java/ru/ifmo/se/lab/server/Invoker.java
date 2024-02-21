package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;
import java.util.Stack;
import java.io.File;

public class Invoker {
    private static HashMap<String,Command> commands;
    private static boolean fromFileMode = false;
    private static Stack<ReadFile> fileReadStack = new Stack<>();
    private static Stack<File> mainFileStack = new Stack<>();
    
    static{
        commands = new HashMap<>();
        
        Exit exit = new Exit("exit","quit the program");
        Help help = new Help("help", "print this screen");
        Add add = new Add("add", "(add name height weight {birthday})create a new element, and add it to collection.");
        Show show = new Show("show", "print all collection's elements.");
        ExecuteScript execScr = new ExecuteScript("execute_script","execute_script (filename) - executing a lines from a file,like it's normal CLI input.");
        Save save = new Save("save", "saves collection list in the file.");
        Load load = new Load("load","loads collection from the file,specified in command line.");
        Info info = new Info("info", "Prints info about collection(type,init date,size.)");
        Update update = new Update("update", "(update id {element}) replace collection element id with new element.");
        Remove remove = new Remove("remove_by_id", "(remove_by_id id) removes element with this id.");
        Clear clear = new Clear("clear", "removes all the elements from collection.");
        RemoveLoc removeLoc = new RemoveLoc("remove_by_location", "removes all elements with specified location.");
        RemoveLast removeLast = new RemoveLast("remove_last", "removes last collection element");
        RemoveLower removeLower = new RemoveLower("remove_lower", "remove_lower {element} - removes all the elements, that are lower than the given one");
        PrintHairColor printColor = new PrintHairColor("hair", "prints all hair colors in collection(descending)");
        CountHeight count = new CountHeight("count","count [height] - prints the number of objects with this height.");
        AddIfMax addIf = new AddIfMax("addif", "addif [element] - adds new element, if it's greater than max element");
        
        commands.put(exit.getName(), exit);
        commands.put(help.getName(), help);
        commands.put(add.getName(), add);
        commands.put(show.getName(), show);
        commands.put(execScr.getName(), execScr);
        commands.put(save.getName(), save);
        commands.put(load.getName(), load);
        commands.put(info.getName(), info);
        commands.put(update.getName(), update);
        commands.put(remove.getName(), remove);
        commands.put(removeLast.getName(), removeLast);
        commands.put(clear.getName(), clear);
        commands.put(removeLoc.getName(), removeLoc);
        commands.put(removeLower.getName(), removeLower);
        commands.put(printColor.getName(), printColor);
        commands.put(count.getName(), count);
        commands.put(addIf.getName(), addIf);
        
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
    
    public static File getCurrMainFile(){
        if (mainFileStack.empty()){
            return null;
        }
        return mainFileStack.peek();
    }
    
    public static void setCurrMainFile(File file){
        mainFileStack.push(file);
    }
    
    public static File removeCurrMainFile(){
        return mainFileStack.pop();
    }
    
}
