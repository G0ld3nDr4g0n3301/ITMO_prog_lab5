package ru.ifmo.se.lab.server;

import java.util.Scanner;

public class InputManager {
    private static Scanner scan = new Scanner(System.in);
    
    public static String[] CLAsk(){
        return GetCLInput.askCommand(scan).split(" ");
    }
}
