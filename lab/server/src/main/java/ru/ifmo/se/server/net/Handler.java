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
import ru.ifmo.se.server.commands.Save;

public class Handler implements Runnable {
    
    private volatile SelectionKey key;
    private volatile Selector selector;
    private volatile Request request;
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
            SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
            if(answerRequest != null){
                answerRequest.setId(ConnectionManager.getUsersConnected());
            }
            keyNew.attach(answerRequest);
        } catch (IOException e) {
        }
    }
}
