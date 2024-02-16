package ru.ifmo.se.lab.server.collections;

import ru.ifmo.se.lab.server.InputManager;

class Name extends AbstractField<Person, String>{
    
    @Override
    public void set(Person p, String name){
        p.setName(name);
    }
    
    @Override
    public boolean validate(String input){
        return true;
    }
    
    @Override
    public String ask(){
        return InputManager.ask("Type the name: ");
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
