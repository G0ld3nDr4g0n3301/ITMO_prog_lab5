package ru.ifmo.se.lab.server;

import ru.ifmo.se.lab.server.collections.Person;

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
    
    public static boolean validateArg(String input){
        try{
            input.split("=");
        } catch(Exception e){
            return false;
        }
        return input.split(input).length == 2;
    }
    
    public static boolean validatePerson(Person person){
        return person.getName() != null;
    }
    
    public static boolean validateHeight(String input){
        try{
            if(Long.parseLong(input) <= 0){
                throw new Exception();
            }
        } catch (Exception e){
            OutputManager.print("Wrong options for height(must be >0)");
            return false;
        }
        return true;
    }
}
