package ru.ifmo.se.server;

import java.io.File;
import java.io.IOException;
import java.net.SocketException;

import ru.ifmo.se.server.commands.Load;
import ru.ifmo.se.server.net.ConnectionManager;
import ru.ifmo.se.common.net.Request;

public class Main {
    public static void main(String[] args){
        if(args.length > 0){
            Invoker.setCurrMainFile(new File(args[0]));
        }
        new Load(null,null).execute(new Request(200));
        try{
            ConnectionManager.initSocket();
            while (true) {
                ConnectionManager.run();
            }
        } catch (IOException e ) {
            System.out.println(e.getMessage());
            System.out.println("Can't accept a connection");

        }
        
    }



    

}
