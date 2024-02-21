package ru.ifmo.se.lab.server;

import java.util.ArrayList;
import ru.ifmo.se.lab.server.collections.Person;
import java.time.LocalDate;
import java.util.List;
import ru.ifmo.se.lab.server.collections.Color;
import ru.ifmo.se.lab.server.collections.Location;

public class CollectionManager {
    private static ArrayList<Person> collection = new ArrayList<>();
    private static final LocalDate initDate = LocalDate.now();
    
    public static ArrayList<Person> getCollection(){
        return collection;
    }
    
    public static void add(Person p){
        collection.add(p);
    }
    
    public static LocalDate getInitDate(){
        return initDate;
    }
    
    public static int getSize(){
        return collection.size();
    }
    
    public static String getType(){
        return "ArrayList<Person>";
    }
    
    public static Person findPerson(int id){
        for(Person p : collection){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    public static void remove(Person p){
        collection.remove(p);
    }
    
    public static void clear(){
        collection.clear();
    }
    
    public static Person findPerson(Location loc){
        for(Person p : collection){
            if(p.getLocation().compareTo(loc) == 0){
                return p;
            }
        }
        return null;
    }
    
    public static void removeLast(){
        collection.remove(collection.size() - 1);
    }
    
    public static void remove(List<Person> removeList){
        collection.removeAll(removeList);
    }
    
    public static List<Color> getHairColors(){
        ArrayList<Color> colors = new ArrayList<>();
        for(Person p : collection){
            colors.add(p.getHairColor());
        }
        return colors;
    }
}
