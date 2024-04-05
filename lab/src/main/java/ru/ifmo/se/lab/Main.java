package ru.ifmo.se.lab;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.Validator;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import ru.ifmo.se.lab.server.commands.Load;
import ru.ifmo.se.lab.server.net.Commands;
import ru.ifmo.se.lab.server.net.ConnectionManager;
import ru.ifmo.se.lab.server.net.Request;

public class Main {
    public static void main(String[] args){
        if(args.length > 0){
            Invoker.setCurrMainFile(new File(args[0]));
        }
        new Load(null,null).execute(new Request(200));
        try{
            ConnectionManager.initSocket();
        } catch (IOException e ) {
            System.out.println(e.getMessage());
            System.out.println("Can't accept a connection");
        }
        
    }

}
