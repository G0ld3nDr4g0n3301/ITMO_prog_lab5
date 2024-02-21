package ru.ifmo.se.lab.server.collections;

import com.opencsv.bean.CsvBindByName;

public class Location implements Comparable<Location> {
    public static final Double ERROR = 0.000000001d;
    
    @CsvBindByName
    private Float locX;
    @CsvBindByName
    private Double locY;
    @CsvBindByName
    private String locName; //Поле может быть null
    
    @Override
    public int compareTo(Location loc){
        boolean good = (this.locX - loc.locX) < ERROR;
        good = good && (this.locY - loc.locY) < ERROR;
        good = good && (this.locName.compareTo(loc.getName()) == 0);
        if(good){
            return 0;
        }
        return 1;
    }
    
    public Location(Float x,Double y,String name){
        this.locX = x;
        this.locY = y;
        this.locName = name;
    }
    
    @Override
    public String toString(){
        return "\n\t x = " + locX + "\n\t y = " + locY + "\n\t name = " + locName;
    }
    
    public Float getLocX(){
        return this.locX;
    }
    
    public Double getLocY(){
        return this.locY;
    }
    
    public String getName(){
        return this.locName;
    }
}
