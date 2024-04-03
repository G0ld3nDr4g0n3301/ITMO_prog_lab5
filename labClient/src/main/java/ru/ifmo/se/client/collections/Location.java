package ru.ifmo.se.client.collections;


/**
 * Person's location(x,y,name)
 * @author raistlin
 */
public class Location implements Comparable<Location> {
    
    /**
     * x value
     */
    
    private Float locX;
    
    /**
     * y value
     */
    
    private Double locY;
    
    /**
     * name of location(can be null)
     */
    
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
    
    @Override
    public int hashCode(){
        return this.getLocX().intValue() * 3 + this.getLocY().intValue() * 7 + this.getName().length() * 13;
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj == this){
            return true;
        }
        if(this.hashCode() != obj.hashCode()){
            return false;
        }
        if(!(obj instanceof Location)){
            return false;
        }
        Location obj2 = (Location) obj;
        return (this.getLocX().compareTo(obj2.getLocX()) == 0 && (this.getLocY().compareTo(obj2.getLocY()) == 0 && this.getName().compareTo(obj2.getName()) == 0));
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
