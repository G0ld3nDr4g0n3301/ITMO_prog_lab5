package ru.ifmo.se.lab.server.commands;

import java.io.FileNotFoundException;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.ReadFile;
import ru.ifmo.se.lab.server.Validator;

public class ExecuteScript extends Command{
    
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
        Invoker.setModeState(true);
        try{
            Invoker.setCurrReadFile(new ReadFile(args[1]));
        } catch(FileNotFoundException e){
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
