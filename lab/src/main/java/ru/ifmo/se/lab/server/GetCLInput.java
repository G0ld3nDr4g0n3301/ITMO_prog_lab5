package ru.ifmo.se.lab.server;

public class GetCLInput {
    
    public static String askCommand(java.util.Scanner scan){
        OutputManager.print("> ");
        return scan.nextLine();
    }
}
