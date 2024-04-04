package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.Command;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

import java.io.Serializable;

import ru.ifmo.se.client.CLIOutputManager;

/**
 * Prints info about collection(init date, size,type)
 * @author raistlin
 */
public class Info extends Command {
    
    public Info(String name,String desc){
        this.name = name;
        this.description = desc;
    }
    
    @Override
    public String toString(){
        return "Info";
    }
    
    @Override
    public Serializable execute(String[] args){
        return new Request<>(Commands.INFO);
    }
}
