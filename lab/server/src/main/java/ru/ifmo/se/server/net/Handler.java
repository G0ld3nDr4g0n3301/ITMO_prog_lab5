package ru.ifmo.se.server.net;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.LogFile;

public class Handler implements Runnable {
    
    private SelectionKey key;
    private Selector selector;
    private Request request;
    private static final Logger logger = Logger.getLogger(Handler.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
    }

    public Handler(SelectionKey key, Selector selector, Request request) {
        this.key = key;
        this.selector = selector;
        this.request = request;
    }

    public void run(){

        try {
            SocketChannel client = (SocketChannel) key.channel();

            Request answerRequest = Invoker.execute(request);
            if(answerRequest != null){
                System.out.println("great!");
                answerRequest.setId(ConnectionManager.getUsersConnected());
            }
            SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
            keyNew.attach(answerRequest);
        } catch (IOException e) {
        }
    }
}
