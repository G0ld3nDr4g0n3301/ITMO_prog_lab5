package ru.ifmo.se.lab;

import ru.ifmo.se.lab.server.Invoker;

public class Main {
    public static void main(String[] args){
        Invoker.init();
        Invoker.execute(args);
    }
    
    public static void stopSession(){
        try{
            throw new Exception("Stop");
        } catch(Exception e){
            System.out.println("Stopping session...");
        }
    }
}
