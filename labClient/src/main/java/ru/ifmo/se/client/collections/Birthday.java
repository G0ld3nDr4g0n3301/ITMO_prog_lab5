package ru.ifmo.se.client.collections;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import ru.ifmo.se.client.CLIInputManager;
import ru.ifmo.se.client.Validator;

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
        return CLIInputManager.ask("Type the Birthday: ");
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