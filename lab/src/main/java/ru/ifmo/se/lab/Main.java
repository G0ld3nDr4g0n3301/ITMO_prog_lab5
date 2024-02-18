package ru.ifmo.se.lab;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.Validator;
import java.io.File;

public class Main {
    public static void main(String[] args){
        if(args.length > 0){
            Invoker.setCurrMainFile(new File(args[0]));
        }
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
