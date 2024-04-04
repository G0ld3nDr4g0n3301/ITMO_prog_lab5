package ru.ifmo.se.client.collections;

import ru.ifmo.se.client.CLIInputManager;
import ru.ifmo.se.client.Validator;

/**
 * location field of person
 * @author raistlin
 */
public class LocationField extends AbstractField<Person, Location>{
    private Float newX = null;
    private Double newY = null;
    private int stoppedOn = 0;
    
    
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
        this.stoppedOn = 0;
        return "";
    }
    
    @Override
    public Location create(String input){
        switch (this.stoppedOn) {
            case 0: {
                String x = CLIInputManager.ask("Enter location X coordinate: ");
                if(Validator.validateLocX(x)){
                    newX = Float.parseFloat(x);
                } else{
                    return null;
                }
                this.stoppedOn += 1;
            }
            case 1: {
                String y = CLIInputManager.ask("Enter location Y coordinate: ");
                if(Validator.validateLocY(y)){
                    newY = Double.parseDouble(y);
                } else{
                    return null;
                }
                this.stoppedOn += 1;
                break;
            }
            default: return null;
        }
        
        
        String name = CLIInputManager.ask("Enter the name of Location: ");
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