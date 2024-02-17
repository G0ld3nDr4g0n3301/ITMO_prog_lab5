package ru.ifmo.se.lab.server;

import java.util.ArrayList;
import ru.ifmo.se.lab.server.collections.Person;

public class CollectionManager {
    private static ArrayList<Person> collection = new ArrayList<>();
    
    public static ArrayList<Person> getCollection(){
        return collection;
    }
    
    public static void add(Person p){
        collection.add(p);
    }
}
