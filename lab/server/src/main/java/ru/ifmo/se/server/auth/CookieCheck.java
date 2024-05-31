package ru.ifmo.se.server.auth;

import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.net.ConnectionManager;
import ru.ifmo.se.server.net.Handler;

public class CookieCheck implements Runnable{
    
    private SelectionKey key;
    private Selector selector;
    private Request request;

    private static Map<String, Integer> cookies = new ConcurrentHashMap<>(); 

    public CookieCheck(SelectionKey key, Selector selector, Request request){
        this.key = key;
        this.selector = selector;
        this.request = request;
    }

    public void run(){
        try {
        while(true){
        SocketChannel client = (SocketChannel) key.channel();
        if(!cookies.containsKey(request.getCookie())){
            Request answer = new Request(404);
            answer.setMsg("Unknown Cookie!");
            SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
            keyNew.attach(answer);
            break;
        } else {
            Request answer = new Request(Commands.RESPONSE);
            answer.setOwnerId(cookies.get(request.getCookie()));
            Runnable handler = new Handler(key, selector, answer);
            ConnectionManager.addToHandlePool(handler);
            break;
        }
    }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void setCookie(String uuid, Integer id){
        cookies.put(uuid, id);
    }
}
