package ru.ifmo.se.lab;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.Validator;
import java.io.File;
import ru.ifmo.se.lab.server.commands.Load;

public class Main {
    public static void main(String[] args){
        if(args.length > 0){
            Invoker.setCurrMainFile(new File(args[0]));
        }
        new Load(null,null).execute(args);
        while(true){
            String input = InputManager.ask("> ");
            if(input != null){
                if(Validator.validateCommand(input)){
                    Invoker.execute(input.split(" "));
                }
            }
        }
    }

}
