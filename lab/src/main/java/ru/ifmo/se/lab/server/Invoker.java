package ru.ifmo.se.lab.server;

import java.util.HashMap;
import ru.ifmo.se.lab.server.commands.*;
import java.util.Stack;
import java.io.File;

/**
 * Invokes the commands using String argument
 * 
 * @author raistlin
 */
public class Invoker {

    /**
     * HashMap containing all the commands' classes.
     */
    private static HashMap<String,Command> commands;
    
    /**
     * If true - executing in FromFile mode,needed for correct work of InputManager.
     */
    private static boolean fromFileMode = false;
    
    /**
     * Stack of files the program interacts with.Needed for execute_script command.
     */
    private static Stack<ReadFile> fileReadStack = new Stack<>();
    
    /**
     * Stack of files from which we load the collection on startup.Command "save" writes the data into this files.
     */
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
    
    /**
     * Executes the command.
     * @param args
     * @return true, if no errors encountered during runtime of command.
     */
    public static boolean execute(String[] args){
        if (!(commands.containsKey(args[0].toLowerCase()))){
            OutputManager.print("Wrong command. Type \"help\" for command list");
            return false;
        }
        commands.get(args[0].toLowerCase()).execute(args);
        return true;
    }
    
    /**
     * Returns the hashMap of CommandName + CommandObject
     * @return commands
     */
    public static HashMap<String, Command> getCommands(){
        return commands;
    }
    
    /**
     * Returns true, if fromFile mode is set.
     * @return 
     */
    public static boolean getModeState(){
        return fromFileMode;
    }
    
    /**
     * Sets new fromFile mode state.
     * @param fromFile 
     */
    public static void setModeState(boolean fromFile){
        fromFileMode = fromFile;
    }
    
    /**
     * Returns the stack of files we currently working with
     * @return null, if stack is empty
     */
    public static ReadFile getCurrReadFile(){
        if(fileReadStack.empty()){
            return null;
        }
        return fileReadStack.peek();
    }
    
    /**
     * Add a new file to fileRead stack.
     * @param file 
     */
    public static void setCurrReadFile(ReadFile file){
        fileReadStack.push(file);
    }
    
    /**
     * Remove file from fileRead stack
     * @return file,that we're removing.
     */
    public static ReadFile removeCurrReadFile(){
        fileReadStack.peek().close();
        return fileReadStack.pop();
    }
    
    
    /**
     * Returns current main file,which was specified in command line.
     * @return Current main file(into which we write our collection)
     */
    public static File getCurrMainFile(){
        if (mainFileStack.empty()){
            return null;
        }
        return mainFileStack.peek();
    }
    
    /**
     * Add a new file to mainFile stack(not used anywhere for now,but will be used in further versions)
     * @param file 
     */
    public static void setCurrMainFile(File file){
        mainFileStack.push(file);
    }
    
    /**
     * Remove file from MainFile stack
     * @return file,that we're removing.
     */
    public static File removeCurrMainFile(){
        return mainFileStack.pop();
    }
    
}
