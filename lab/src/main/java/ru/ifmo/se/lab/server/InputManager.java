package ru.ifmo.se.lab.server;

import java.util.Scanner;

public class InputManager {
    private static Scanner scan = new Scanner(System.in);
    
    public static String[] CLAsk(){
        String input = GetCLInput.askCommand(scan);
        if(input == null){
            System.out.print("\n");
            scan = new Scanner(System.in);
            return null;
        }else if(input.length() == 0 || input.split(" ").length == 0){
            return null;
        } else{
            return input.split(" ");
        }
    }
}
