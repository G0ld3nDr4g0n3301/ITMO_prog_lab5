package ru.ifmo.se.client.GUIHelp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.scene.layout.Pane;
import ru.ifmo.se.client.Main;
import ru.ifmo.se.client.MainController;
import ru.ifmo.se.common.collections.Person;

public class AnimationHandler {
    
    private static Pane pane;

    private static HashMap<Person,Animate> animated = new HashMap<>();

    private static ArrayList<Person> collectionCopy = new ArrayList<>();

    private static ArrayList<Person> currentlyAnimated = new ArrayList<>();

    private static ExecutorService pool = Executors.newFixedThreadPool(5);

    public static HashMap<Person,Animate> getAnimated(){
        return animated;
    }
    
    public static void refresh(){
        collectionCopy.clear();
        collectionCopy.addAll(MainController.getCollection());
        collectionCopy.removeAll(currentlyAnimated);
        for (Person p : collectionCopy){
            pool.execute(new Animate(p));
        }
        collectionCopy.clear();
        collectionCopy.addAll(MainController.getCollection());
        currentlyAnimated.removeAll(collectionCopy);
        for (Person p : currentlyAnimated){
            Animate a = animated.get(p);
            a.remove();
        }
    }

    public static void complete(Person a, Animate b){
        animated.put(a, b);
    }

    public static void setPane(Pane p){
        pane = p;
    }

    public static Pane getPane(){
        return pane;
    }
}
