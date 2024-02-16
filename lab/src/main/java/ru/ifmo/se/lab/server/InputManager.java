package ru.ifmo.se.lab.server;

import java.util.Scanner;

public class InputManager {
    private static Scanner scan = new Scanner(System.in);
    
    public static String ask(String question){
        return CLAsk(question);
    }
    
    public static String CLAsk(String question){
        String input = GetCLInput.ask(scan, question);
        if(input == null){
            reloadScanner();
            return null;
        }
        return input;
    }
    
    public static void reloadScanner(){
        System.out.print("\n");
        scan = new Scanner(System.in);
    }
}
