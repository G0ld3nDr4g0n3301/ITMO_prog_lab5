package ru.ifmo.se.lab.server.commands;

import java.io.File;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.serialization.WritePerson;
import java.io.IOException;

public class Save extends Command {
    
    public Save(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        try{
            File currFile = Invoker.getCurrMainFile();
            if(currFile == null){
                OutputManager.print("You must specify file,when executing this program.");
                return false;
            }
            WritePerson.write(CollectionManager.getCollection(), currFile);
        }catch(IOException e){
            OutputManager.print("Error in file writing");
            return false;
        }
        return true;
    }
    
    @Override
    public String toString(){
        return "Save";
    }
}
