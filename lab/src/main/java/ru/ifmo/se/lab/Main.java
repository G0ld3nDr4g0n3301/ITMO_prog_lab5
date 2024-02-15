package ru.ifmo.se.lab;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.Invoker;

public class Main {
    public static void main(String[] args){
        Invoker.init();
        while(true){
            String[] input = InputManager.CLAsk();
            if(input != null){
                Invoker.execute(input);
            }
        }
    }

}
