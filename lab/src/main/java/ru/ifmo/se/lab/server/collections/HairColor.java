package ru.ifmo.se.lab.server.collections;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;

class HairColor extends AbstractField<Person, Color>{
    
    @Override
    public void set(Person p, Color c){
        p.setHairColor(c);
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
    public Color create(String Uselessinput){
        OutputManager.print("Available hair colors: \n");
        for (Color c : Color.values()){
            OutputManager.print(c);
        }
        String input = InputManager.ask("Enter the hair color: ");
        if(Validator.validateColor(input)){
            return Color.valueOf(input);
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "Color";
    }
}