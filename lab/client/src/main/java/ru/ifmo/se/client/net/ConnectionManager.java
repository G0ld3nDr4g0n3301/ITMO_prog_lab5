package ru.ifmo.se.client.net;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;

import ru.ifmo.se.common.net.Request;

public class ConnectionManager{
    
    public static Integer port = 777;
    public static String host = "localhost";
    private static Socket socket = null;
    public static Integer timeout = 40;
    private static DataOutputStream out;
    private static DataInputStream in;
    
    public static void initSocket() throws IOException{
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port));
        out = new DataOutputStream(socket.getOutputStream());
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
    }

    public static void close() throws IOException{
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public static boolean send(Request request) throws IOException{
        System.out.println(request);


        if (socket != null){
            out.write(Serialize.serializeRequest(request));
            out.flush();
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
            Request request = Deserialize.deserializeRequest(in.readAllBytes());
            return request;
        } catch (IOException | ClassCastException e) {
            return null;
        }
    }

    public static Socket getSocket(){
        return socket;
    }

}