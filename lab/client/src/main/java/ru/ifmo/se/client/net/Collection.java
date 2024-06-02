package ru.ifmo.se.client.net;

import java.io.IOException;
import java.util.ArrayList;

import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.Main;
import ru.ifmo.se.client.MainController;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

public class Collection implements Runnable{
    
    private static ArrayList<Person> collection;

    public static ArrayList<Person> getCollection(){
        return collection;
    }

    public static void setCollection(ArrayList<Person> newCollection){
        collection = newCollection;
    }

    public void run(){
        Request request = new Request(Commands.RELOAD);
        try {
            ConnectionManager.send(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request answer = ConnectionManager.recieve();
        setCollection(answer.getCollection());
    }
}
