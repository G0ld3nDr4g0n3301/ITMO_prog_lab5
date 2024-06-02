package ru.ifmo.se.client.GUIHelp;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


import javafx.beans.property.SimpleStringProperty;
import ru.ifmo.se.common.collections.Coordinates;
import ru.ifmo.se.common.collections.Person;

public class PersonData {
    
    
    private SimpleStringProperty id = new SimpleStringProperty();
    
    

    private SimpleStringProperty name = new SimpleStringProperty();
    


    private SimpleStringProperty ownerId = new SimpleStringProperty();
    
    private SimpleStringProperty coordinates = new SimpleStringProperty();
    
    
    private SimpleStringProperty creationDate = new SimpleStringProperty();
    
    private SimpleStringProperty height = new SimpleStringProperty();
    
    private SimpleStringProperty birthday = new SimpleStringProperty();
    
    
    private SimpleStringProperty weight = new SimpleStringProperty();
    
    
    private SimpleStringProperty hairColor = new SimpleStringProperty();
    
    
    private SimpleStringProperty location = new SimpleStringProperty();
    
    public PersonData(Person p){
        setOwnerId(p.getOwnerId().toString());
        if(p.getBirthday() != null){
        setBirthday(p.getBirthday().toString());
        }
        setCoordinates("(" + p.getCoordinates().getX() + "," + p.getCoordinates().getY() + ")");
        setCreationDate(p.getCreationDate().toString());
        setHairColor(getHairColor());
        setHeight(getHeight());
        setId(getId());
        String loc = "(" + p.getLocation().getLocX()  + p.getLocation().getLocY();
        loc = loc += p.getLocation().getName() != null ? p.getLocation().getName() + ")" : ")";
        setLocation(loc);
        setName(p.getName());
        setWeight(p.getWeight().toString());
    }
    
    public static List<PersonData> calc(List<Person> list){
        Iterator iter = list.iterator();
        List<PersonData> res = new ArrayList<>();
        while(iter.hasNext()){
            Person p = (Person) iter.next();
            PersonData pD = new PersonData(p);
            res.add(pD);
        }
        return res;
    }
    

    public void setOwnerId(String id) {
        ownerId.set(id);
    }

    public String getOwnerId(){
        return ownerId.get();
    }
    
    public void setLocation(String location){
        this.location.set(location);
    }
    
    public void setHairColor(String color){
        this.hairColor.set(color);
    }
    
    public void setWeight(String weight){
        this.weight.set(weight);
    }
    
    public void setBirthday(String birthday){
        this.birthday.set(birthday);
    }
    
    public void setHeight(String height){
        this.height.set(height);
    }

    public void setCreationDate(String crDate){
        this.creationDate.set(crDate);
    }
    
    public void setCoordinates(String coordinates){
        this.coordinates.set(coordinates);
    }

    public void setName(String name){
        this.name.set(name);
    }
    
    public void setId(String id){
        this.id.set(id);
    }
    
    public String getId(){
        return id.get();
    }
    
    public String getLocation(){
        return location.get();
    }
    
    public String getHairColor(){
        return hairColor.get();
    }
    
    public String getWeight(){
        return weight.get();
    }
    
    public String getBirthday(){
        return birthday.get();
    }
    
    public String getHeight(){
        return height.get();
    }
    
    public String getCreationDate(){
        return creationDate.get();
    }

    public String getCoordinates(){
        return coordinates.get();
    }
    
    public String getName(){
        return name.get();
    }
    
}
