package ru.ifmo.se.client.commands;

import ru.ifmo.se.client.*;
import ru.ifmo.se.client.net.Commands;
import ru.ifmo.se.client.net.Request;

import java.io.Serializable;

/**
 * Prints all available commands and their descriptions.
 * @author raistlin
 */
public class Help extends Command {
    
    public Help(String name, String description){
        this.name = name;
        this.description = description;
    }
    
    @Override
    public Serializable execute(String[] args){
        return new Request<>(Commands.HELP);
    }
    
    
}
