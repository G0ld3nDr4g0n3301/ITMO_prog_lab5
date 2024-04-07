package ru.ifmo.se.client.collections;

import ru.ifmo.se.client.CLIInputManager;

/**
 * Name field of person
 * @author raistlin
 */
class Name extends AbstractField<Person, String>{
    
    @Override
    public void set(Person p, String name){
        p.setName(name);
    }
    
    @Override
    public boolean validate(String input){
        if(input != ""){
            return true;
        }
        return false;
    }
    
    @Override
    public String ask(){
        return CLIInputManager.ask("Type the name: ");
    }
    
    @Override
    public String create(String input){
        return input;
    }
    
    @Override
    public String toString(){
        return "Name";
    }
}
