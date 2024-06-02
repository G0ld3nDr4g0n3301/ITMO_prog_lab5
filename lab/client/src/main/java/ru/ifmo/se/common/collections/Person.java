package ru.ifmo.se.common.collections;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Main class of the collection.
 * @author raistlin
 */
public class Person implements Comparable<Person>,Serializable{
    
    /**
     * id of a person
     */
    
    private  Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    

    private  Integer ownerId;

    /**
     * Name of a Person
     */
    
    private String name; //Поле не может быть null, Строка не может быть пустой
    
    /**
     * Coordinates(x,y) of a person
     */
    
    private Coordinates coordinates; //Поле не может быть null
    
    /**
     * Creation date of a person
     */
    
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    
    /**
     * height of a person
     */
    
    private long height; //Значение поля должно быть больше 0
    
    /**
     * birthday of a person(can be null)
     */
    
    private LocalDate birthday; //Поле может быть null
    
    /**
     * Weight of a person
     */
    
    private int weight; //Значение поля должно быть больше 0
    
    /**
     * Hair color of a person(enum)
     */
    
    private Color hairColor; //Поле не может быть null
    
    /**
     * Location of a person
     */
    
    private Location location; //Поле не может быть null
    
    /**
     * Empty constructor needed for deserialization.
     */
    public Person(){
        
    }
    
    /**
     * Compares persons by their ID's.
     * @param p Another person
     * @return 0 if equals.
     */
    @Override
    public int compareTo(Person p){
        return this.getLocation().compareTo(p.getLocation());
    }
    
    @Override
    public boolean equals(Object obj){
        if(obj == null){
            return false;
        }
        if(obj.hashCode() != this.hashCode()){
            return false;
        }
        if(obj == this){
            return true;
        }
        if(!(obj instanceof Person)){
            return false;
        }
        Person obj2 = (Person) obj;
        return this.compareTo(obj2) == 0 ? true : false;
    }
    
    @Override
    public int hashCode(){
        return this.getId();
    }
    
    public void setOwnerId(Integer id) {
        this.ownerId = id;
    }

    public Integer getOwnerId(){
        return this.ownerId;
    }

    /**
     * set new location to person.
     * @param location .
     */
    public void setLocation(Location location){
        this.location = location;
    }
    
    /**
     * sets new hair color to person
     * @param color .
     */
    public void setHairColor(Color color){
        this.hairColor = color;
    }
    
    /**
     * sets new weight to person
     * @param weight .
     */
    public void setWeight(Integer weight){
        this.weight = weight;
    }
    
    /**
     * setter for birthday
     * @param birthday .
     */
    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }
    
    /**
     * setter for height
     * @param height .
     */
    public void setHeight(Long height){
        this.height = height;
    }
    
    /**
     * setter for creation date
     * @param crDate .
     */
    public void setCreationDate(LocalDate crDate){
        this.creationDate = crDate;
    }
    
    /**
     * setter for coordinates
     * @param coordinates .
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    /**
     * setter for name
     * @param name .
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * setter for id
     * @param id .
     */
    public void setId(Integer id){
        this.id = id;
    }
    
    /**
     * getter for id
     * @return id
     */
    public Integer getId(){
        return id;
    }
    
    /**
     * getter for location
     * @return location
     */
    public Location getLocation(){
        return location;
    }
    
    /**
     * getter for hair color
     * @return hair color
     */
    public Color getHairColor(){
        return hairColor;
    }
    
    /**
     * getter for weight
     * @return weight
     */
    public Integer getWeight(){
        return weight;
    }
    
    /**
     * getter for birthday
     * @return birthday
     */
    public LocalDate getBirthday(){
        return birthday;
    }
    
    /**
     * getter for height
     * @return height
     */
    public Long getHeight(){
        return height;
    }
    
    /**
     * getter for creation date
     * @return creation date
     */
    public LocalDate getCreationDate(){
        return creationDate;
    }
    /**
     * getter for coordinates
     * @return coordinates
     */
    public Coordinates getCoordinates(){
        return coordinates;
    }
    
    /**
     * getter for name
     * @return name
     */
    public String getName(){
        return name;
    }
    
    
    @Override
    public String toString(){
        String str = "id = " + id;
        str += "\nowner = " + ownerId;
        str += "\nname = " + name;
        str += "\ncoordinates = " + coordinates;
        str += "\ncreationDate = " + creationDate;
        str += "\nheight = " + height;
        str += "\nbirthday = " + birthday;
        str += "\nweight = " + weight;
        str += "\nhairColor = " + hairColor;
        str += "\nlocation = " + location;
        return str;
    }
}
