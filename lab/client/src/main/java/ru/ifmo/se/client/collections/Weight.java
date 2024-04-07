package ru.ifmo.se.client.collections;

import ru.ifmo.se.client.CLIInputManager;
import ru.ifmo.se.client.Validator;

/**
 * weight field of person
 * @author raistlin
 */
class Weight extends AbstractField<Person, Integer>{

    @Override
    public void set(Person p, Integer w){
        p.setWeight(w);
    }
    
    @Override
    public boolean validate(String input){
        return Validator.validateWeight(input);
    }
    
    @Override
    public String ask(){
        return CLIInputManager.ask("Type the weight: ");
    }
    
    @Override
    public Integer create(String input){
        return Integer.parseInt(input);
    }
    
    @Override
    public String toString(){
        return "Weight";
    }
    
}