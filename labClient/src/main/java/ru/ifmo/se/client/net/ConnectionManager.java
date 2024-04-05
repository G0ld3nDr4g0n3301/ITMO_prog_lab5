package ru.ifmo.se.client.net;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

public class ConnectionManager{
    
    public static Integer port = 777;
    public static String host = "localhost";
    private static Socket socket = null;
    public static Integer timeout = 40;
    private static DataInputStream in;
    private static DataOutputStream out;
    
    public static void initSocket() throws IOException{
        socket = new Socket();
        socket.connect(new InetSocketAddress(host, port), timeout);
        in = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
        out = new DataOutputStream(socket.getOutputStream());
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
            out.write(Serialize.serializeRequest(request));
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