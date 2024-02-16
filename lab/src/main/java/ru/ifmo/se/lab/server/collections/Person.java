package ru.ifmo.se.lab.server.collections;

import java.time.LocalDate;

public class Person {
    private Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private long height; //Значение поля должно быть больше 0
    private LocalDate birthday; //Поле может быть null
    private int weight; //Значение поля должно быть больше 0
    private Color hairColor; //Поле не может быть null
    private Location location; //Поле не может быть null
    
    
    public Person(){
        
    }
    
    public void setLocation(Location location){
        this.location = location;
    }
    
    public void setHairColor(Color color){
        this.hairColor = color;
    }
    
    public void setWeight(Integer weight){
        this.weight = weight;
    }
    
    public void setBirthday(LocalDate birthday){
        this.birthday = birthday;
    }
    
    public void setHeight(Long height){
        this.height = height;
    }
    
    public void setCreationDate(LocalDate crDate){
        this.creationDate = crDate;
    }
    
    public void setCoordinates(Coordinates coordinates){
        this.coordinates = coordinates;
    }
    
    public void setName(String name){
        this.name = name;
    }
    
    public void setId(Integer id){
        this.id = id;
    }
    
    public Integer getId(){
        return id;
    }
    
    public Location getLocation(){
        return location;
    }
    
    public Color getHairColor(){
        return hairColor;
    }
    
    public Integer getWeight(){
        return weight;
    }
    
    public LocalDate getBirthday(){
        return birthday;
    }
    
    public Long getHeight(){
        return height;
    }
    
    public LocalDate getCreationDate(){
        return creationDate;
    }
    
    public Coordinates getCoordinates(){
        return coordinates;
    }
    
    public String getName(){
        return name;
    }
    
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
