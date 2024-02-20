package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;

public class RemoveLast extends Command{
    
    public RemoveLast(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "RemoveLast";
    }
    
    @Override
    public boolean execute(String[] args){
        CollectionManager.removeLast();
        return true;
    }
}
