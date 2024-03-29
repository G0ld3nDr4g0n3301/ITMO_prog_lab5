package ru.ifmo.se.lab.server.collections;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.Validator;

/**
 * Height field of person.
 * @author raistlin
 */
class Height extends AbstractField<Person,Long>{
    @Override
    public void set(Person p, Long h){
        p.setHeight(h);
    }
    
    @Override
    public boolean validate(String input){
        return Validator.validateHeight(input);
    }
    
    @Override
    public String ask(){
        return InputManager.ask("Type the height: ");
    }
    
    @Override
    public Long create(String input){
        return Long.parseLong(input);
    }
    
    @Override
    public String toString(){
        return "Height";
    }
}