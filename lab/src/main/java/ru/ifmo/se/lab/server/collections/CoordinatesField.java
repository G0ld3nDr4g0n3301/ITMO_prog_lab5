package ru.ifmo.se.lab.server.collections;

import ru.ifmo.se.lab.server.InputManager;
import ru.ifmo.se.lab.server.OutputManager;
import ru.ifmo.se.lab.server.Validator;

/**
 * Coordinates field of Person
 * @author raistlin
 */
class CoordinatesField extends AbstractField<Person, Coordinates>{

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
        return "";
    }
    
    @Override
    public Coordinates create(String input){
        Double newX = null;
        Long newY = null;
        String x = InputManager.ask("Enter x coordinate: ");
        if(Validator.validateCoordX(x)){
            newX = Double.parseDouble(x);
        } else{
            return null;
        }
        String y = InputManager.ask("Enter y coordinate: ");
        if(Validator.validateCoordY(y)){
            newY = Long.parseLong(y);
        } else{
            return null;
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