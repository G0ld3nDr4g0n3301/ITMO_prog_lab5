package ru.ifmo.se.lab.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.ifmo.se.lab.server.CollectionManager;
import ru.ifmo.se.lab.server.Command;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.Request;

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
    public Request<String> execute(Serializable args){
        String info = CollectionManager.getType().toString() + "\n" + CollectionManager.getInitDate().toString() + "\n" + CollectionManager.getSize();
        return new Request<String>(400,info);
    }
}
