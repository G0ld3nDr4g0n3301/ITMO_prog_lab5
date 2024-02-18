package ru.ifmo.se.lab.server.collections;

import com.opencsv.bean.CsvBindByName;

public class Coordinates {
    @CsvBindByName
    private double cordX; //Значение поля должно быть больше -92
    @CsvBindByName
    private Long cordY; //Поле не может быть null

    public void setX(Double x){
        this.cordX = x;
    }
    
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
