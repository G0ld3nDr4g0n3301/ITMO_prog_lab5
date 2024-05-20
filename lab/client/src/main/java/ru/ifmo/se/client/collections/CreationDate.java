package ru.ifmo.se.client.collections;

import java.time.LocalDate;

import ru.ifmo.se.common.collections.Person;

/**
 * CreationDate field of Person(generated automatically)
 * @author raistlin
 */
class CreationDate extends AbstractField<Person,LocalDate>{
    
    @Override
    public void set(Person p, LocalDate ld){
        p.setCreationDate(ld);
    }
    
    @Override
    public boolean validate(String input){
        return false;
    }
    
    @Override
    public String ask(){
        return "";
    }
    
    @Override
    public LocalDate create(String Uselessinput){
        return LocalDate.now();
    }
    
    @Override
    public String toString(){
        return "CreationDate";
    }
}