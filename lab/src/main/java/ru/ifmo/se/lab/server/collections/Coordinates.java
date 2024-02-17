package ru.ifmo.se.lab.server.collections;

public class Coordinates {
    private double x; //Значение поля должно быть больше -92
    private Long y; //Поле не может быть null

    public void setX(Double x){
        this.x = x;
    }
    
    public void setY(Long y){
        this.y = y;
    }
    
    public Coordinates(Double x, Long y){
        this.x = x;
        this.y = y;
    }
    
    
    @Override
    public String toString(){
        return "\n\t x = " + this.x + "\n\t y = " + this.y;
    }
}
