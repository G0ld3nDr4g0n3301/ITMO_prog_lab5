package ru.ifmo.se.client;

/**
 * Class for printing messages to user. For now, it's useless, but it will be useful when network will be added.
 * @author raistlin
 */
public class CLIOutputManager {
    
    /**
     * Prints the object to console.
     * @param <T>
     * @param obj 
     */
    public static <T> void print(T obj){
        if(obj != null){
            System.out.print("\n " + obj);
        }
    }
}
