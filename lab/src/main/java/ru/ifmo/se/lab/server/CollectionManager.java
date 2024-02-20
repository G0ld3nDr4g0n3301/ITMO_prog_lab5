package ru.ifmo.se.lab.server;

import java.util.ArrayList;
import ru.ifmo.se.lab.server.collections.Person;
import java.time.LocalDate;

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
}
