package ru.ifmo.se.client.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionManager{
    
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

    public static void close() throws IOException{
        socket.close();
    }

    public static <T extends Serializable> void send(T request) throws IOException{
        out.writeObject(request);
    }

    public static <T extends Serializable> T recieve() {
        try {
            Object obj = in.readObject();
            T request = (T) obj;
            return request;
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            // TODO: handle
            return null;
        }
    }

    public static Socket getSocket(){
        return socket;
    }

}