package ru.ifmo.se.server.net;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.StreamCorruptedException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Arrays;
import java.util.logging.Logger;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.commands.Save;

public class Reciever implements Runnable{

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(Reciever.class.getName());


    private SelectionKey key;
    private Selector selector;

    
    static {
        logger.addHandler(LogFile.getHandler());
    }

    public Reciever(SelectionKey key, Selector selector){
        this.key = key;
        this.selector = selector;
    }


    /**
     * recieve data from user
     * @param key user
     * @return User Request
     * @throws SocketException
     * @throws IOException
     */
    public void run(){

        try {

            Request request;
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
            request = Deserialize.deserializeRequest(bigBuffer.array());
            logger.info("Recieved a package " + request.toString());
            
            String login = request.getLogin();
            String password = request.getPassword();

            if(login == null || password == null){
                Request errorRequest = new Request(404);
                errorRequest.setMsg("Unauthorized users can't execute commands.To exit type Ctrl+C.");
                SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
                keyNew.attach(errorRequest);
                throw new IOException();
            }

            Runnable handler = new Handler(key, selector, request);
            ConnectionManager.addToHandlePool(handler);

        } catch (SocketException | StreamCorruptedException e) {
            logger.warning("client disconnected");
            ConnectionManager.decrementUsersConnected();
            if (ConnectionManager.getUsersConnected() == 0) {
                new Save("","").execute(new Request(Commands.SAVE));
            }
            key.cancel();
        } catch (IOException e) {
            //ignore
        }
    }
}
