package ru.ifmo.se.server;

import java.util.EnumSet;
import java.util.HashMap;
import ru.ifmo.se.server.commands.*;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

import java.util.Stack;
import java.io.File;
import java.io.Serializable;

/**
 * Invokes the commands using String argument
 * 
 * @author raistlin
 */
public class Invoker {

    /**
     * HashMap containing all the commands' classes.
     */
    private static HashMap<Commands,Command> commands;

    private static EnumSet<Commands> clientForbidden = EnumSet.noneOf(Commands.class);
    
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
        Show execScr = new Show("execute_script","execute_script (filename) - executing a lines from a file,like it's normal CLI input.");
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
        
        commands.put(Commands.EXIT, exit);
        commands.put(Commands.HELP, help);
        commands.put(Commands.ADD, add);
        commands.put(Commands.SHOW, show);
        commands.put(Commands.EXEC, execScr);
        commands.put(Commands.SAVE, save);
        commands.put(Commands.LOAD, load);
        commands.put(Commands.INFO, info);
        commands.put(Commands.UPDATE, update);
        commands.put(Commands.REMOVE_BY_ID, remove);
        commands.put(Commands.REMOVE_LAST, removeLast);
        commands.put(Commands.CLEAR, clear);
        commands.put(Commands.REMOVE_BY_LOC, removeLoc);
        commands.put(Commands.REMOVE_LOWER, removeLower);
        commands.put(Commands.HAIR, printColor);
        commands.put(Commands.COUNT, count);
        commands.put(Commands.ADDIF, addIf);
        
        clientForbidden.add(Commands.EXEC);
        clientForbidden.add(Commands.SAVE);
        clientForbidden.add(Commands.LOAD);
        clientForbidden.add(Commands.RESPONSE);
    }
    
    /**
     * Executes the command.
     * @param args
     * @return true, if no errors encountered during runtime of command.
     */
    public static Request execute(Request args){
        System.out.println(args);
        if (!(commands.containsKey(args.getCommandType()))){
            System.out.println(commands.containsKey(args.getCommandType()));
            System.out.println(args.getCommandType());
            System.out.println("Wrong command. Type \"help\" for command list");
            return null;
        }
        switch (args.getStatusCode()) {
            case 300:
                System.out.println(args.getCommandType());
                Request request = commands.get(args.getCommandType()).execute(args);
                System.out.println(request);
                return request;

            default:
                System.out.println("Unknown command type");
                return null;
        }

    }

    public static EnumSet<Commands> getClientForbidden(){
        return clientForbidden;
    }
    
    /**
     * Returns the hashMap of CommandName + CommandObject
     * @return commands
     */
    public static HashMap<Commands, Command> getCommands(){
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
