package ru.ifmo.se.lab.server.collections;

import com.opencsv.bean.CsvBindByName;

/**
 * Coordinates(x,y) of person.
 * @author raistlin
 */
public class Coordinates {
    
    /**
     * x value
     */
    @CsvBindByName
    private double cordX; //Значение поля должно быть больше -92
    
    /**
     * y value
     */
    @CsvBindByName
    private Long cordY; //Поле не может быть null

    /**
     * setter for x
     * @param x 
     */
    public void setX(Double x){
        this.cordX = x;
    }
    
    /**
     * setter for y
     * @param y 
     */
    public void setY(Long y){
        this.cordY = y;
    }
    
    public Coordinates(Double x, Long y){
        this.cordX = x;
        this.cordY = y;
    }
    
    
    @Override
    public String toString(){
        return "\n\t x = " + this.cordX + "\n\t y = " + this.cordY;
    }
}
