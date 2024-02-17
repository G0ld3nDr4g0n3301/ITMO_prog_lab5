package ru.ifmo.se.lab.server.collections;

public class Location {
    private float x;
    private double y;
    private String name; //Поле может быть null
    
    public Location(Float x,Double y,String name){
        this.x = x;
        this.y = y;
        this.name = name;
    }
    
    @Override
    public String toString(){
        return "\n\t x = " + x + "\n\t y = " + y + "\n\t name = " + name;
    }
}
