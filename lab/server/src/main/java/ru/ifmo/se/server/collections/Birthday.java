package ru.ifmo.se.server.collections;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ru.ifmo.se.server.InputManager;
import ru.ifmo.se.server.Validator;

/**
 * Birthday field of Person.
 * @author raistlin
 */
class Birthday extends AbstractField<Person, LocalDate>{
    
    @Override
    public void set(Person p, LocalDate b){
        p.setBirthday(b);
    }
    
    @Override
    public boolean validate(String input){
        return Validator.validateBirthday(input);
    }
    
    @Override
    public String ask(){
        return InputManager.ask("Type the Birthday: ");
    }
    
    @Override
    public LocalDate create(String input){
        return LocalDate.parse(input, DateTimeFormatter.ISO_DATE);
    }
    
    @Override
    public String toString(){
        return "Birthday";
    }
    
}