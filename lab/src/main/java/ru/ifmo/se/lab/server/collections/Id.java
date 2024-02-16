package ru.ifmo.se.lab.server.collections;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;

public class Id extends AbstractField<Person, Integer> {
    
    @Override
    public void set(Person person, Integer newValue){
        person.setId(newValue);
    }
    
    @Override
    public String ask(){
        String input = InputManager.ask("Enter ID of a person: ");
        return input;
    }
    
    @Override
    public Integer create(String input){
        return Integer.parseInt(input);
    }
    
    @Override
    public boolean validate(String input){
        return Validator.validateId(input);
    }
    
    @Override
    public String toString(){
        return "Id";
    }
}
