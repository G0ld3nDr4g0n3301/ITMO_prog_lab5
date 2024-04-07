package ru.ifmo.se.client;

import java.io.IOException;
import java.io.Serializable;

import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.client.net.ConnectionManager;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(Integer.class instanceof Serializable);
            System.out.println(String.class instanceof Serializable);
            System.out.println(Location.class instanceof Serializable);
            System.out.println(Person.class instanceof Serializable);
            System.out.println(Commands.class instanceof Serializable);
            ConnectionManager.initSocket();
        } catch (IOException e){
            System.out.println(e.getMessage());
            System.exit(0);
        }
        try {
            System.out.println("HEh");
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
