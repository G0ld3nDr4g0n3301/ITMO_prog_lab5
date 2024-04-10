package ru.ifmo.se.server.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

public class Sender {
    

    public static boolean send(SelectionKey key) throws SocketException,IOException{

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


        return true;
    }
}
