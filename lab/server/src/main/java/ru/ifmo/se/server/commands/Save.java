package ru.ifmo.se.server.commands;

import java.io.File;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.serialization.WritePerson;
import java.io.IOException;
import java.io.Serializable;
import java.util.logging.Logger;

/**
 * saves collection to Main file.
 * @author raistlin
 */
public class Save extends Command {

    private static final Logger logger = Logger.getLogger(Save.class.getName());
    
    public Save(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){
        try{
            File currFile = Invoker.getCurrMainFile();
            if(currFile == null){
                logger.warning("file not specified");
                return new Request(404, "You must specify file,when executing this program.");
            }
            WritePerson.write(CollectionManager.getCollection(), currFile);
            logger.info("successfully saved collection to file");
        }catch(IOException e){
            logger.warning("error in file writing");
            return new Request(404, "Error in file writing");
        }
        return new Request(200);
    }
    
    @Override
    public String toString(){
        return "Save";
    }
}
