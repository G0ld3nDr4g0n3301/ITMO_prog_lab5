package ru.ifmo.se.server.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.serialization.ReadPerson;

/**
 * loads collection from file.
 * @author raistlin
 */
public class Load extends Command{
    
    public Load(String name, String desc){
        this.name= name;
        this.description = desc;
    }
    
    @Override
    public Request execute(Request args){
        List<Person> newList = null;
        try{
            newList = ReadPerson.read(Invoker.getCurrMainFile());
            if(newList == null){
                OutputManager.print("File is damaged, or contains wrong data.");
                return null;
            }
        } catch(IOException e) {
            OutputManager.print("Error in file reading.");
            return null;
        } catch(EmptyStackException | NullPointerException e){
            OutputManager.print("No input file specified.");
            return null;
        }
        CollectionManager.addAll(newList);
        return null;
    }
    
    @Override
    public String toString(){
        return "Load";
    }
}
