package ru.ifmo.se.lab.server.collections;

import java.time.LocalDate;
import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvRecurse;

/**
 * Main class of the collection.
 * @author raistlin
 */
public class Person implements Comparable<Person>{
    
    /**
     * id of a person
     */
    @CsvBindByName
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    
    /**
     * Name of a Person
     */
    @CsvBindByName
    private String name; //Поле не может быть null, Строка не может быть пустой
    
    /**
     * Coordinates(x,y) of a person
     */
    @CsvRecurse
    private Coordinates coordinates; //Поле не может быть null
    
    /**
     * Creation date of a person
     */
    @CsvBindByName
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    
    /**
     * height of a person
     */
    @CsvBindByName
    private long height; //Значение поля должно быть больше 0
    
    /**
     * birthday of a person(can be null)
     */
    @CsvBindByName
    private LocalDate birthday; //Поле может быть null
    
    /**
     * Weight of a person
     */
    @CsvBindByName
    private int weight; //Значение поля должно быть больше 0
    
    /**
     * Hair color of a person(enum)
     */
    @CsvBindByName
    private Color hairColor; //Поле не может быть null
    
    /**
     * Location of a person
     */
    @CsvRecurse
    private Location location; //Поле не может быть null
    
    /**
     * Empty constructor needed for deserialization.
     */
    public Person(){
        
    }
    
    /**
     * Compares persons by their ID's.
     * @param p
     * @return 0 if equals.
     */
    @Override
    public int compareTo(Person p){
        if(this.getId() > p.getId()){
            return 1;
        }else if(this.getId() == p.getId()){
            return 0;
        }
        return -1;
    }
    
    /**
     * set new location to person.
     * @param location 
     */
    public void setLocation(Location location){
        this.location = location;
    }
    
    /**
     * sets new hair color to person
     * @param color 
     */
    public void setHairColor(Color color){
        this.hairColor = color;
    }
    
    /**
     * sets new weight to person
     * @param weight 
     */
    public void setWeight(Integer weight){
        this.weight = weight;
    }
    
    /**
     * setter for birthday
     * @param birthday 
     */
    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }
    
    /**
     * setter for height
     * @param height 
     */
    public void setHeight(Long height){
        this.height = height;
    }
    
    /**
     * setter for creation date
     * @param crDate 
     */
    public void setCreationDate(LocalDate crDate){
        this.creationDate = crDate;
    }
    
    /**
     * setter for coordinates
     * @param coordinates 
     */
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    /**
     * setter for name
     * @param name 
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * setter for id
     * @param id 
     */
    public void setId(Integer id){
        this.id = id;
    }
    
    /**
     * getter for id
     * @return 
     */
    public Integer getId(){
        return id;
    }
    
    /**
     * getter for location
     * @return 
     */
    public Location getLocation(){
        return location;
    }
    
    /**
     * getter for hair color
     * @return 
     */
    public Color getHairColor(){
        return hairColor;
    }
    
    /**
     * getter for weight
     * @return 
     */
    public Integer getWeight(){
        return weight;
    }
    
    /**
     * getter for birthday
     * @return 
     */
    public LocalDate getBirthday(){
        return birthday;
    }
    
    /**
     * getter for height
     * @return 
     */
    public Long getHeight(){
        return height;
    }
    
    /**
     * getter for creation date
     * @return 
     */
    public LocalDate getCreationDate(){
        return creationDate;
    }
    /**
     * getter for coordinates
     * @return 
     */
    public Coordinates getCoordinates(){
        return coordinates;
    }
    
    /**
     * getter for name
     * @return 
     */
    public String getName(){
        return name;
    }
    
    /**
     * defining how to print a person
     * @return 
     */
    @Override
    public String toString(){
        String str = "id = " + id;
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
