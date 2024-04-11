package ru.ifmo.se.server.net;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.commands.Save;


public class ConnectionManager{
    
    public static Integer port = 777;
    private static ServerSocketChannel socket;
    public static Integer timeout = 40;
    private static Selector selector;
    
    public static void initSocket() throws IOException {
        socket = ServerSocketChannel.open();
        socket.bind(new InetSocketAddress(port));
        socket.configureBlocking(false);
        selector = Selector.open();
        socket.register(selector, SelectionKey.OP_ACCEPT);

    }


    public static boolean run() throws IOException{
        
            selector.select();
            for (SelectionKey key : selector.selectedKeys()) {
                try {
                if(key.isAcceptable()) {
                    ServerSocketChannel tempServerChannel = (ServerSocketChannel) key.channel();
                    SocketChannel client = tempServerChannel.accept();
                    if (client == null) {
                        continue;
                    }
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);

                } else if (key.isReadable()) {
                    Request request;
                    SocketChannel client = (SocketChannel) key.channel();
                    request = Reciever.recieve(key);
                    if (request == null) {
                        return false;
                    }
                    Request answerRequest = Invoker.execute(request);
                    SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
                    keyNew.attach(answerRequest);
                
                    //SocketChannel client = (SocketChannel) key.channel();
                    Sender.send(key);
                    client = (SocketChannel) key.channel();
                    client.register(selector, SelectionKey.OP_READ);
                } 
            } catch (SocketException | StreamCorruptedException e) {
                new Save("","").execute(new Request(Commands.SAVE));
                key.cancel();
                return false;
            } 
            }
        return true;
        
    
    }
    
    public static Selector getSelector(){
        return selector;
    }

    public static void close() throws IOException{
        socket.close();
    }


    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

}