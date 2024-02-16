package ru.ifmo.se.lab.server.collections;

import java.util.ArrayList;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;


public class AskPerson {
    private static ArrayList<AbstractField> simpleFields = new ArrayList<>();
    
    static {
        Id id = new Id();
        
        simpleFields.add(id);
    }
    
    public static Person generatePerson(String[] args){
        Person person = new Person();
        for (int i = 0; i < simpleFields.size(); i++){
            try{
                String input = args[i+1];
                AbstractField field = simpleFields.get(i);
                if(!field.validate(input)){
                    OutputManager.print("Wrong input format for field " + field);
                    return null;
                }
                field.set(person, field.create(input));
            } catch(NullPointerException e){
                OutputManager.print("Not enough arguments.");
                return null;
            } catch(Exception e){
                OutputManager.print("Wrong input");
                return null;
            }
        }
        
        if(Validator.validatePerson(person)){
            return person;
        }
        OutputManager.print("Not enough args.");
        return null;
    }
    
    public static ArrayList<AbstractField> getFields(){
        return simpleFields;
    }
}
