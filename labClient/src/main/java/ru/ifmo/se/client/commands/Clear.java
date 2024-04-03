package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.CollectionManager;
import ru.ifmo.se.client.Command;

/**
 * completely clears the collection
 * @author raistlin
 */
public class Clear extends Command{
    
    public Clear(String name, String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Clear";
    }
    
    @Override
    public boolean execute(String[] args){
        CollectionManager.clear();
        return true;
    }
}
