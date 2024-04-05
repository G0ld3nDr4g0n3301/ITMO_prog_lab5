package ru.ifmo.se.client.net;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionManager{
    
    public static Integer port = 777;
    public static String host = "localhost";
    private static Socket socket = null;
    public static Integer timeout = 40;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    
    public static void initSocket() throws IOException{
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), timeout);
        in = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new ObjectOutputStream(socket.getOutputStream());
        out.flush();
    }

    public static void close() throws IOException{
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public static boolean send(Request request) throws IOException{
        System.out.println(request);
        if (socket != null){
            out.writeObject(request);
            return true;
        }
        System.out.println("You should run initSocket() method before using sockets.");
        return false;
    }

    public static Request recieve() {
        if (socket == null ){
            System.out.println("run initSocket() first.");
            return null;
        }
        try {
            Object obj = in.readObject();
            Request request = (Request) obj;
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