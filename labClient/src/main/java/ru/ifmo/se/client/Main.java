package ru.ifmo.se.client;

import java.io.IOException;

import ru.ifmo.se.client.net.ConnectionManager;

public class Main {
    public static void main(String[] args) {
        try {
            ConnectionManager.initSocket();
        } catch (IOException e){
            // TODO: handle
        }
        try {
            while(true){
                String input = CLIInputManager.ask("> ");
                if(input != null){
                    if(Validator.validateCommand(input)){
                        Invoker.execute(input.split(" "));
                    }
                }
            }
        } finally {
            try {
                ConnectionManager.close();
            } catch (IOException e) {
                // TODO: handle
            }
        }
}
}
