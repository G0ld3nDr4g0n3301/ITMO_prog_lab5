package ru.ifmo.se.lab;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.Validator;

public class Main {
    public static void main(String[] args){
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
