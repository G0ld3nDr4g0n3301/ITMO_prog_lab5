package ru.ifmo.se.client.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.ReadFile;
import ru.ifmo.se.client.Validator;

/**
 * execute given file, as if it's a bunch of user input lines.
 * @author raistlin
 */
public class ExecuteScript extends Command{
    private static ArrayList<String> filenames = new ArrayList<>();
    
    public ExecuteScript(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        
        if (args.length == 1){
            CLIOutputManager.print("Specify the filename.");
            return false;
        }
        if (filenames.contains(args[1])){
            CLIOutputManager.print("Script execution recursion is prohibited.");
            return false;
        }
        Invoker.setModeState(true);
        try{
            Invoker.setCurrReadFile(new ReadFile(args[1]));
            filenames.add(args[1]);
        } catch(IOException e){
            CLIOutputManager.print("No such file.");
            Invoker.setModeState(false);
            return false;
        }
        boolean keepGoing = true;
        String line = null;
        while(keepGoing){
            line = Invoker.getCurrReadFile().readLine();
            if (line == null){
                break;
            }
            if(Validator.validateCommand(line)){
                Invoker.execute(line.split(" "));
            } else {
                CLIOutputManager.print("Not aproppriate command found.");
                Invoker.removeCurrReadFile();
                Invoker.setModeState(false);
                return false;
            }
            
        }
        Invoker.removeCurrReadFile();
        Invoker.setModeState(false);
        return true;
    }
}
