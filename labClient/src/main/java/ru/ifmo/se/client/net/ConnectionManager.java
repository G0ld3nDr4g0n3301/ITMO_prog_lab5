package ru.ifmo.se.client.net;

import java.io.Closeable;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionManager implements Closeable{
    
    private static Socket socket = new Socket();
    public static Integer port = 7777;
    public static String host = "127.0.0.1";
    public static Integer timeout = 40;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    
    public static void initSocket() throws IOException{
        socket.close();
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), timeout);
        in = new ObjectInputStream(socket.getInputStream());
        out = new ObjectOutputStream(socket.getOutputStream());
    }

    public void close() throws IOException{
        socket.close();
    }

    public static <T extends Serializable> void send(T request) throws IOException{
        Sender.send(request, out);
    }

    public <T extends Serializable> T recieve() {
        try {
            return Reciever.recieve(in);
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            // TODO: handle
            return null;
        }
    }

    public static Socket getSocket(){
        return socket;
    }

}