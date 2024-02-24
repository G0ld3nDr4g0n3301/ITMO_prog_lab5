package ru.ifmo.se.lab.server.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.ReadFile;
import ru.ifmo.se.lab.server.Validator;

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
            OutputManager.print("Specify the filename.");
            return false;
        }
        if (filenames.contains(args[1])){
            OutputManager.print("Script execution recursion is prohibited.");
            return false;
        }
        Invoker.setModeState(true);
        try{
            Invoker.setCurrReadFile(new ReadFile(args[1]));
            filenames.add(args[1]);
        } catch(IOException e){
            OutputManager.print("No such file.");
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
                OutputManager.print("Not aproppriate command found.");
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
