package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;

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
