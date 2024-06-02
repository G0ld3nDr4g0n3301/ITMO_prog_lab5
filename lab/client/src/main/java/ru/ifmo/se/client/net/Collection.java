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
    

    public void run(){
        Request request = new Request(Commands.RELOAD);
        try {
            ConnectionManager.send(request);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Request answer = ConnectionManager.recieve();
        MainController.setCollection(answer.getCollection());
    }
}
