package ru.ifmo.se.server;

import java.util.Scanner;

/**
 * Responsible for getting user input(from CLI or from file)
 * @author raistlin
 */
public class InputManager {
    /**
     * scanner to get CL input
     */
    private static Scanner scan = new Scanner(System.in);
    
    /**
     * Decides(based on fromFile mode state),which method to use for gathering user input
     * @param question
     * @return user input
     */
    public static String ask(String question){
        return askFile();
    }
    
   
    
    /**
     * reloads scanner to prevent fatal error
     */
    public static void reloadScanner(){
        OutputManager.print("\n");
        scan = new Scanner(System.in);
    }
    
    /**
     * get user input through file
     * @return user input
     */
    public static String askFile(){
        String input = Invoker.getCurrReadFile().readLine();
        return input;
    }
}
