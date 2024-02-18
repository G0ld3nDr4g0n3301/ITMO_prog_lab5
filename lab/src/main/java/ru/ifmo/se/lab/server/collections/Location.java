package ru.ifmo.se.lab.server.collections;

import com.opencsv.bean.CsvBindByName;

public class Location {
    @CsvBindByName
    private float locX;
    @CsvBindByName
    private double locY;
    @CsvBindByName
    private String locName; //Поле может быть null
    
    public Location(Float x,Double y,String name){
        this.locX = x;
        this.locY = y;
        this.locName = name;
    }
    
    @Override
    public String toString(){
        return "\n\t x = " + locX + "\n\t y = " + locY + "\n\t name = " + locName;
    }
}
