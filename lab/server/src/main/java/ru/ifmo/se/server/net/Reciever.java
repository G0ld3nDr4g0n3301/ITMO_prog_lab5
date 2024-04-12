package ru.ifmo.se.server.net;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.logging.Logger;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.commands.Save;

public class Reciever {

    
    private static final Logger logger = Logger.getLogger(Reciever.class.getName());
    
    public static Request recieve(SelectionKey key) throws SocketException, IOException {
            SocketChannel client = (SocketChannel) key.channel();
            client.configureBlocking(false);
            
            
            ByteBuffer buffer = ByteBuffer.allocate(512*3);
            client.read(buffer);
            
            //ByteBuffer header = ByteBuffer.allocate(5);
            ByteBuffer bigBuffer = ByteBuffer.allocate(512*4);
            //bigBuffer.put(header.array());
            bigBuffer.put(buffer.array());
            //ByteArrayInputStream bi = new ByteArrayInputStream(bigBuffer.array());
            //ObjectInputStream oi = new ObjectInputStream(bi);
            Request rq =  Deserialize.deserializeRequest(bigBuffer.array());
            if (rq == null) {
                logger.warning("package is null!");
                throw new SocketException();
            }
            logger.info("Recieved a package");
            return rq;
        
    }
}
