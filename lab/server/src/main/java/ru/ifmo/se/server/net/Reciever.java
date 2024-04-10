package ru.ifmo.se.server.net;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

import ru.ifmo.se.common.net.Request;

public class Reciever {

    
    
    public static Request recieve(SelectionKey key) {
        try {
            SocketChannel client = (SocketChannel) key.channel();
            client.configureBlocking(false);
            
            
            ByteBuffer buffer = ByteBuffer.allocate(512*3);
            client.read(buffer);
            
            //ByteBuffer header = ByteBuffer.allocate(5);
            ByteBuffer bigBuffer = ByteBuffer.allocate(512*4);
            //bigBuffer.put(header.array());
            bigBuffer.put(buffer.array());
            
            //ObjectInputStream oi = new ObjectInputStream(bi);
            System.out.println(Arrays.toString(bigBuffer.array()));
            Request rq = Deserialize.deserializeRequest(bigBuffer.array());
            if (rq == null) {
                System.out.println("package is null!");
            }
            return rq;

        } catch (IOException e) {
            System.out.println(e);
            System.out.println("Client disconnected");
            return null;
        }
    }
}
