package ru.ifmo.se.server;

import java.util.ArrayList;
import ru.ifmo.se.common.collections.Person;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import ru.ifmo.se.common.collections.Color;
import ru.ifmo.se.common.collections.Location;

/**
 * A bunch of methods to manage the main collection
 * @author raistlin
 */
public class CollectionManager {
    /**
     * Main collection
     */
    private static List<Person> collection = new ArrayList<>();

    private static ReadWriteLock lock = new ReentrantReadWriteLock();

    /**
     * Date of initialization of collection
     */
    private static final LocalDate initDate = LocalDate.now();
    
    /**
     * Returns the main collections
     * @return 
     */
    public static List<Person> getCollection(){
        return collection;
    }
    
    /**
     * Adds an element to collection
     * @param p 
     */
    public static void add(Person p){
        collection.add(p);
        sort();
    }
    
    /**
     * returns the initialization date of collection
     * @return init date
     */
    public static LocalDate getInitDate(){
        return initDate;
    }

    public static boolean addIfMax(Person p){
        lock.writeLock().lock();
        if (collection.stream().allMatch(p1 -> p1.compareTo(p) < 0)){
            add(p);
            lock.writeLock().unlock();
            return true;
        }
        lock.writeLock().unlock();
        return false;
    }
    
    /**
     * returns the size of collection
     * @return collection size
     */
    public static int getSize(){
        lock.readLock().lock();
        int i = collection.size();
        lock.readLock().unlock();
        return i;
    }
    
    /**
     * returns the type of collection(Hardcoded for now)
     * @return 
     */
    public static String getType(){
        return collection.getClass().descriptorString();
    }
    
    /**
     * Searches a Person by id
     * @param id
     * @return first match in collection
     */
    public static Person findPerson(int id){
        lock.readLock().lock();
        List<Person> list = collection.stream()
        .filter((Person p) -> p.getId() == id)
        .collect(Collectors.toList());
        for(Person p : list){
                lock.readLock().unlock();
                return p;
        }
        lock.readLock().unlock();
        return null;
    }
    
    /**
     * remove element from collection
     * @param p 
     */
    public static void remove(Person p){
        lock.writeLock().lock();
        collection.remove(p);
        lock.writeLock().unlock();
    }
    
    /**
     * clears the collection
     */
    public static void clear(){
        lock.writeLock().lock();
        collection.clear();
        lock.writeLock().unlock();
    }
    
    /**
     * searches a person by Location field
     * @param loc
     * @return first match in collection
     */
    public static Person findPerson(Location loc){
        lock.readLock().lock();
        List<Person> list = collection.stream()
        .filter((Person p) -> p.getLocation().equals(loc))
        .collect(Collectors.toList());
        for(Person p : list){
                lock.readLock().unlock();
                return p;
        }
        lock.readLock().unlock();
        return null;
    }

    public static ReadWriteLock getLock(){
        return lock;
    }
    
    /**
     * removes last element of collection
     */
    public static void removeLast(){
        lock.writeLock().lock();
        collection = collection.stream()
        .filter((Person p) -> collection.indexOf(p) != collection.size() - 1)
        .collect(Collectors.toList());
        lock.writeLock().unlock();
    }
    
    /**
     * removes all collection's elements, which are contained in removeList
     * @param removeList 
     */
    public static void remove(List<Person> removeList){
        lock.writeLock().lock();
        collection = collection.stream()
        .filter((Person p) -> !removeList.contains(p))
        .collect(Collectors.toList());
        lock.writeLock().unlock();
//        collection.removeAll(removeList);
    }
    
    /**
     * returns the list of Hair colors(unsorted)
     * @return 
     */
    public static List<Color> getHairColors(){
        lock.readLock().lock();
        ArrayList<Color> colors = new ArrayList<>();
        collection.stream()
        .forEachOrdered((Person p) -> colors.add(p.getHairColor()));
        lock.readLock().unlock();
//        for(Person p : collection){
//            colors.add(p.getHairColor());
//        }
        return colors;
    }
    
    /**
     * sorts the collection
     */
    public static void sort(){
        lock.writeLock().lock();
        collection = collection.stream()
        .sorted()
        .collect(Collectors.toList());
        Collections.sort(collection);
        lock.writeLock().unlock();
    }
    
    /**
     * adds all newList elements to collection
     */
    public static void addAll(List<Person> newList){
        lock.writeLock().lock();
        newList.stream()
        .forEachOrdered(collection::add);
        sort();
        lock.writeLock().unlock();
    }

    public static List<Person> sortLoc(List<Person> persons) {
        lock.readLock().lock();
        List<Person> res = new ArrayList<>();
        res = persons.stream()
            .sorted((Person p1, Person p2) -> p1.getLocation().compareTo(p2.getLocation()))
            .collect(Collectors.toList());
        lock.readLock().unlock();
        return res;
    }
}
