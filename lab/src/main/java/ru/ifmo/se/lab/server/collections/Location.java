package ru.ifmo.se.lab.server.collections;

import com.opencsv.bean.CsvBindByName;

/**
 * Person's location(x,y,name)
 * @author raistlin
 */
public class Location implements Comparable<Location> {
    
    /**
     * x value
     */
    @CsvBindByName
    private Float locX;
    
    /**
     * y value
     */
    @CsvBindByName
    private Double locY;
    
    /**
     * name of location(can be null)
     */
    @CsvBindByName
    private String locName; //Поле может быть null
    
    /**
     * Checks if all the fields have the same value.
     * @param loc another location
     * @return 0 if OK
     */
    @Override
    public int compareTo(Location loc){
        boolean good = this.locX.compareTo(loc.locX) == 0;
        good = good && (this.locY.compareTo(loc.locY) == 0);
        try{
            good = good && (this.locName.compareTo(loc.getName()) == 0);
        }catch (NullPointerException e){
            if (!(this.locName == null && loc.getName() == null)){
                good = false;
            }
        }
        
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
    
    /**
     * getter for x
     * @return x
     */
    public Float getLocX(){
        return this.locX;
    }
    
    /**
     * getter for y
     * @return y
     */
    public Double getLocY(){
        return this.locY;
    }
    
    /**
     * getter for name.
     * @return name
     */
    public String getName(){
        return this.locName;
    }
}
