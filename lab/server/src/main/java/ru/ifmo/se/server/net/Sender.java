package ru.ifmo.se.server.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.logging.Logger;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.LogFile;

public class Sender implements Runnable{
    
    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(Sender.class.getName());

    private SelectionKey key;
    private Selector selector;
    
    public Sender(SelectionKey key, Selector selector){
        this.selector = selector;
        this.key = key;
    }

    static {
        logger.addHandler(LogFile.getHandler());
    }


    
    /**
     * Send response to user
     * @param key user
     * @return true if no errors encountered
     * @throws SocketException
     * @throws IOException
     */
    public void run(){
        try{
            SocketChannel client = (SocketChannel) key.channel();
            client.configureBlocking(false);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            ByteBuffer tempHeader = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.reset();
            objectOutputStream.writeObject(key.attachment());
            objectOutputStream.flush();
            objectOutputStream.close();

            ByteBuffer buffer = ByteBuffer.allocate(byteArrayOutputStream.size() + 8);
            buffer.putInt(byteArrayOutputStream.size() + 4);
            buffer.put(tempHeader);
            buffer.put(byteArrayOutputStream.toByteArray());
            buffer.flip();
            client.write(buffer);

            logger.info("package sent");
            
            client.register(selector, SelectionKey.OP_READ);

        } catch (SocketException | StreamCorruptedException e) {
            logger.warning("client disconnected");
            ConnectionManager.decrementUsersConnected();
            key.cancel();
        } catch (IOException e){
            //handle
        }
    }
}
