package ru.ifmo.se.client;

import java.util.Scanner;

/**
 * Responsible for getting user input(from CLI or from file)
 * @author raistlin
 */
public class CLIInputManager {
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
        if(!Invoker.getModeState()){
            return CLAsk(question);
        }
        return askFile();
    }
    
    /**
     * get user input through Command Line.If got EOF - restarts scanner(to prevent fatal error)
     * @param question
     * @return user input
     */
    public static String CLAsk(String question){
        String input = GetCLInput.ask(scan, question);
        if(input == null){
            reloadScanner();
            return null;
        }
        return input;
    }
    
    /**
     * reloads scanner to prevent fatal error
     */
    public static void reloadScanner(){
        CLIOutputManager.print("\n");
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
