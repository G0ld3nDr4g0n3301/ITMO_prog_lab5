package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.Command;

public class Exit extends Command {
    
    public Exit(String name, String descr){
        this.name = name;
        this.description = descr;
    }
   
    @Override
    public int execute(String[] args){
        System.exit(0);
        return 0;
    }
}
