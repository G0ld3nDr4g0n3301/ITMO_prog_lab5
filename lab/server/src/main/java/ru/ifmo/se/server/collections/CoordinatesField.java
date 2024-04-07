package ru.ifmo.se.server.collections;

import ru.ifmo.se.common.collections.Coordinates;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.server.InputManager;
import ru.ifmo.se.server.OutputManager;
import ru.ifmo.se.server.Validator;

/**
 * Coordinates field of Person
 * @author raistlin
 */
class CoordinatesField extends AbstractField<Person, Coordinates>{
    private int stoppedOn = 0;
    private Double newX = null;
    private Long newY = null;
    
    @Override
    public void set(Person p, Coordinates c){
        p.setCoordinates(c);
    }
    
    @Override
    public boolean validate(String input){
        return false;
    }
    
    @Override
    public String ask(){
        this.stoppedOn = 0;
        return null;
    }
    
    @Override
    public Coordinates create(String input){
        switch (this.stoppedOn) {
            case 0: {
                String x = InputManager.ask("Enter x coordinate: ");
                if(Validator.validateCoordX(x)){
                    newX = Double.parseDouble(x);
                } else{
                    return null;
                }
                this.stoppedOn += 1;
            }
            case 1: {
                String y = InputManager.ask("Enter y coordinate: ");
                if(Validator.validateCoordY(y)){
                    newY = Long.parseLong(y);
                } else{
                    return null;
                }
                this.stoppedOn += 1;
                break;
            }
            default: return null;
        }
        
        
        Coordinates coords = new Coordinates(newX,newY);
        if(Validator.validateCoords(coords)){
            return coords;
        }
        return null;
    }
    
    @Override
    public String toString(){
        return "Coordinates";
    }
    
} 