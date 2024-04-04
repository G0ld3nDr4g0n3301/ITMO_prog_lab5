package ru.ifmo.se.client.collections;

import java.io.Serializable;

/**
 * Coordinates(x,y) of person.
 * @author raistlin
 */
public class Coordinates implements Serializable{
    
    /**
     * x value
     */
    
    private double cordX; //Значение поля должно быть больше -92
    
    /**
     * y value
     */
    
    private Long cordY; //Поле не может быть null

    /**
     * setter for x
     * @param x .
     */
    public void setX(Double x){
        this.cordX = x;
    }
    
    /**
     * setter for y
     * @param y .
     */
    public void setY(Long y){
        this.cordY = y;
    }
    
    public Coordinates(Double x, Long y){
        this.cordX = x;
        this.cordY = y;
    }
    
    /**
     * Getter for x
     * @return x
     */
    public Double getX(){
        return this.cordX;
    }
    
    /**
     * getter for y
     * @return y
     */
    public Long getY(){
        return this.cordY;
    }
    
    
    @Override
    public String toString(){
        return "\n\t x = " + this.cordX + "\n\t y = " + this.cordY;
    }
}
