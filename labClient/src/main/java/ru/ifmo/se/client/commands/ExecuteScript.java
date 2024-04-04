package ru.ifmo.se.client.commands;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.ReadFile;
import ru.ifmo.se.client.Validator;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

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
    public Serializable execute(String[] args){
        
        if (args.length == 1){
            System.out.println("Specify the filename.");
            return null;
        }
        if (filenames.contains(args[1])){
            System.out.println("Script execution recursion is prohibited.");
            return null;
        }
        Invoker.setModeState(true);
        try{
            Invoker.setCurrReadFile(new ReadFile(args[1]));
            filenames.add(args[1]);
        } catch(IOException e){
            System.out.println("No such file.");
            Invoker.setModeState(false);
            return null;
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
                System.out.println("Not aproppriate command found.");
                Invoker.removeCurrReadFile();
                Invoker.setModeState(false);
                return null;
            }
            
        }
        Invoker.removeCurrReadFile();
        Invoker.setModeState(false);
        return new Request<>(Commands.EXEC);
    }
}
