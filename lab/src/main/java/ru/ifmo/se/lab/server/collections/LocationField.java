package ru.ifmo.se.lab.server.collections;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;

public class LocationField extends AbstractField<Person, Location>{
    
    @Override
    public void set(Person p, Location l){
        p.setLocation(l);
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
    public Location create(String input){
        Float newX = null;
        Double newY = null;
        String x = InputManager.ask("Enter x for coordinates: ");
        if(Validator.validateLocX(x)){
            newX = Float.parseFloat(x);
        } else{
            return null;
        }
        String y = InputManager.ask("Enter y for coordinates: ");
        if(Validator.validateLocY(y)){
            newY = Double.parseDouble(y);
        } else{
            return null;
        }
        String name = InputManager.ask("Enter the name of Location: ");
        if(name == ""){
            name = null;
        }
        Location loc = new Location(newX,newY, name);
        if(Validator.validateLoc(loc)){
            return loc;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "Location";
    }
    
}