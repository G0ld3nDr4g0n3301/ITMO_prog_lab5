package ru.ifmo.se.lab.server.commands;

import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.Main;
import java.io.IOException;

public class Exit extends Command {
    
    public Exit(String name, String descr){
        this.name = name;
        this.description = descr;
    }
   
    @Override
    public void execute(String[] args){
        System.exit(0);
    }
}
