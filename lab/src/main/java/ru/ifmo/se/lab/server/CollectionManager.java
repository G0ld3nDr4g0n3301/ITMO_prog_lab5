package ru.ifmo.se.lab.server;

import java.util.ArrayList;
import ru.ifmo.se.lab.server.collections.Person;
import java.time.LocalDate;
import java.util.List;
import ru.ifmo.se.lab.server.collections.Color;
import ru.ifmo.se.lab.server.collections.Location;

/**
 * A bunch of methods to manage the main collection
 * @author raistlin
 */
public class CollectionManager {
    /**
     * Main collection
     */
    private static ArrayList<Person> collection = new ArrayList<>();
    /**
     * Date of initialization of collection
     */
    private static final LocalDate initDate = LocalDate.now();
    
    /**
     * Returns the main collections
     * @return 
     */
    public static ArrayList<Person> getCollection(){
        return collection;
    }
    
    /**
     * Adds an element to collection
     * @param p 
     */
    public static void add(Person p){
        collection.add(p);
    }
    
    /**
     * returns the initialization date of collection
     * @return init date
     */
    public static LocalDate getInitDate(){
        return initDate;
    }
    
    /**
     * returns the size of collection
     * @return collection size
     */
    public static int getSize(){
        return collection.size();
    }
    
    /**
     * returns the type of collection(Hardcoded for now)
     * @return 
     */
    public static String getType(){
        return "ArrayList<Person>";
    }
    
    /**
     * Searches a Person by id
     * @param id
     * @return first match in collection
     */
    public static Person findPerson(int id){
        for(Person p : collection){
            if (p.getId() == id){
                return p;
            }
        }
        return null;
    }
    
    /**
     * remove element from collection
     * @param p 
     */
    public static void remove(Person p){
        collection.remove(p);
    }
    
    /**
     * clears the collection
     */
    public static void clear(){
        collection.clear();
    }
    
    /**
     * searches a person by Location field
     * @param loc
     * @return first match in collection
     */
    public static Person findPerson(Location loc){
        for(Person p : collection){
            if(p.getLocation().compareTo(loc) == 0){
                return p;
            }
        }
        return null;
    }
    
    /**
     * removes last element of collection
     */
    public static void removeLast(){
        collection.remove(collection.size() - 1);
    }
    
    /**
     * removes all collection's elements, which are contained in removeList
     * @param removeList 
     */
    public static void remove(List<Person> removeList){
        collection.removeAll(removeList);
    }
    
    /**
     * returns the list of Hair colors(unsorted)
     * @return 
     */
    public static List<Color> getHairColors(){
        ArrayList<Color> colors = new ArrayList<>();
        for(Person p : collection){
            colors.add(p.getHairColor());
        }
        return colors;
    }
}
