package ru.ifmo.se.lab.server.commands;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.List;
import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.collections.Person;
import ru.ifmo.se.lab.server.serialization.ReadPerson;

public class Load extends Command{
    
    public Load(String name, String desc){
        this.name= name;
        this.description = desc;
    }
    
    @Override
    public boolean execute(String[] args){
        List<Person> newList = null;
        try{
            newList = ReadPerson.read(Invoker.getCurrMainFile());
            if(newList == null){
                OutputManager.print("File is damaged, or contains wrong data.");
                return false;
            }
        } catch(FileNotFoundException e) {
            OutputManager.print("Error in file reading.");
            return false;
        } catch(EmptyStackException | NullPointerException e){
            OutputManager.print("No input file specified.");
            return false;
        } 
        CollectionManager.getCollection().clear();
        CollectionManager.getCollection().addAll(newList);
        return true;
    }
    
    @Override
    public String toString(){
        return "Load";
    }
}
