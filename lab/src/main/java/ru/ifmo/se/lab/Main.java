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
        new Load(null,null).execute(args);
        try{
            ConnectionManager.initSocket();
        } catch (IOException e ) {
            System.out.println("Can't accept a connection");
        }
        while(true){
            Serializable pack = ConnectionManager.recieve();
            Request input = (Request) pack;
            if(input != null){
                if(Validator.validateCommand(input)){
                    Request output = Invoker.execute(input.getCommandType(), input.getStatusCode(), input.getArgs());
                    if (output == null) {
                        Request<String> error = new Request<>(404);
                        error.setArgument("Error in program");
                        try {
                            ConnectionManager.send(error);
                        } catch (IOException e) {
                            // lol
                        }
                    } else {
                        try {
                            ConnectionManager.send(output);
                        } catch (IOException e){
                            // kek
                        }
                    }
                }
            }
        }
    }

}
