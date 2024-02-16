package ru.ifmo.se.lab.server;

public class Validator {
    
    public static boolean validateCommand(String input){
        if(input.length() == 0 || input.split(" ").length == 0){
            return false;
        } else{
            return true;
        }
    }
    
    public static boolean validateId(String input){
        try{
            Integer.parseInt(input);
        } catch(Exception e){
            return false;
        }
        return true;
    }
}
