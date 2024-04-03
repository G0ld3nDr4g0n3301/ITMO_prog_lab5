package ru.ifmo.se.client;

/**
 * Gets user input from cli
 * @author raistlin
 */
public class GetCLInput {
    
    /**
     * Asks given question, and returns the answer
     * @param scan
     * @param question
     * @return answer
     */
    public static String ask(java.util.Scanner scan,String question){
        CLIOutputManager.print(question);
        if (scan.hasNextLine()){
            return scan.nextLine();
        } else{
            return null;
        }
    } 
}
