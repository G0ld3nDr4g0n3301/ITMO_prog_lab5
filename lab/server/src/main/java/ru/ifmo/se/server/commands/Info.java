package ru.ifmo.se.server.commands;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Command;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

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
    public Request execute(Request args){
        System.out.println("info is executed");
        String info = CollectionManager.getType().toString() + "\n" + CollectionManager.getInitDate().toString() + "\n" + CollectionManager.getSize();
        return new Request(400,info);
    }
}
