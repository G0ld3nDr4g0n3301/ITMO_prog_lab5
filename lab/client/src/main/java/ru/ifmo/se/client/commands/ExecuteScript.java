package ru.ifmo.se.client.commands;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Logger;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.LogFile;
import ru.ifmo.se.client.ReadFile;
import ru.ifmo.se.client.Validator;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

/**
 * execute given file, as if it's a bunch of user input lines.
 * @author raistlin
 */
public class ExecuteScript extends Command{

    private static final Logger logger = Logger.getLogger(Add.class.getName());

    static {
            logger.addHandler(LogFile.getHandler());
    }
    private static ArrayList<String> filenames = new ArrayList<>();
    
    public ExecuteScript(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(String[] args){
        
        if (args.length == 1){
            logger.warning("filename not specified");
            System.out.println("Specify the filename.");
            return null;
        }
        if (filenames.contains(args[1])){
            logger.warning("Tried to execute recursively");
            System.out.println("Script execution recursion is prohibited.");
            return null;
        }
        Invoker.setModeState(true);
        logger.info("Executing the script");
        try{
            Invoker.setCurrReadFile(new ReadFile(args[1]));
            filenames.add(args[1]);
        } catch(IOException e){
            logger.warning("wrong file specified");
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
                logger.warning("wrong command \"" + line + "\"");
                System.out.println("Not aproppriate command found.");
                Invoker.removeCurrReadFile();
                Invoker.setModeState(false);
                return null;
            }
            
        }
        Invoker.removeCurrReadFile();
        Invoker.setModeState(false);
        return new Request(Commands.EXEC);
    }
}
