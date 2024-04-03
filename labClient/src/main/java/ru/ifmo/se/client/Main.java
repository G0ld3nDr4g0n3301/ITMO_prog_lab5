package ru.ifmo.se.client;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        while(true){
            String input = CLIInputManager.ask("> ");
            if(input != null){
                if(Validator.validateCommand(input)){
                    Invoker.execute(input.split(" "));
                }
            }
        }
    }
}
