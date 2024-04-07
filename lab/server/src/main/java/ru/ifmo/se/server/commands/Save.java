package ru.ifmo.se.server.commands;

import java.io.File;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.net.Request;
import ru.ifmo.se.server.serialization.WritePerson;
import java.io.IOException;
import java.io.Serializable;

/**
 * saves collection to Main file.
 * @author raistlin
 */
public class Save extends Command {
    
    public Save(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){
        try{
            File currFile = Invoker.getCurrMainFile();
            if(currFile == null){
                return new Request(404, "You must specify file,when executing this program.");
            }
            WritePerson.write(CollectionManager.getCollection(), currFile);
        }catch(IOException e){
            return new Request(404, "Error in file writing");
        }
        return new Request(200);
    }
    
    @Override
    public String toString(){
        return "Save";
    }
}
