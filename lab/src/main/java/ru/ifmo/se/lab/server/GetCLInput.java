package ru.ifmo.se.lab.server;

public class GetCLInput {
    
    public static String ask(java.util.Scanner scan,String question){
        OutputManager.print(question);
        if (scan.hasNextLine()){
            return scan.nextLine();
        } else{
            return null;
        }
    } 
}
