package ru.ifmo.se.client.collections;

import ru.ifmo.se.client.CLIInputManager;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.CLIOutputManager;
import ru.ifmo.se.client.Validator;

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
            CLIOutputManager.print("Available hair colors: \n");
            for (Color c : Color.values()){
                CLIOutputManager.print(c);
            }
        }
        String input = CLIInputManager.ask("Enter the hair color: ");
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