package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;


/**
 * exit from program
 * @author raistlin
 */
public class Exit extends Command {
    
    public Exit(String name, String descr){
        this.name = name;
        this.description = descr;
    }
   
    @Override
    public boolean execute(String[] args){
        System.exit(0);
        return true;
    }
}
