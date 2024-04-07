package ru.ifmo.se.server.collections;

import ru.ifmo.se.common.collections.Color;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.server.InputManager;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.Validator;

/**
 * HairColor field of person
 * @author raistlin
 */
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
        if (!Invoker.getModeState()){
            OutputManager.print("Available hair colors: \n");
            for (Color c : Color.values()){
                OutputManager.print(c);
            }
        }
        String input = InputManager.ask("Enter the hair color: ");
        if(Validator.validateColor(input.toUpperCase())){
            return Color.valueOf(input.toUpperCase());
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "Color";
    }
}