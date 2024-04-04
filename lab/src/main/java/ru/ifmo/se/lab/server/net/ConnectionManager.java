package ru.ifmo.se.lab.server.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;

public class ConnectionManager{
    
    public static Integer port = 7777;
    private static ServerSocket socket = null;
    private static Socket clientSocket = null;
    public static Integer timeout = 40;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    
    public static void initSocket() throws IOException{
        if (socket != null && !socket.isClosed()){
            socket.close();
        }
        socket = new ServerSocket(port);
        clientSocket = socket.accept();
        in = new ObjectInputStream(clientSocket.getInputStream());
        out = new ObjectOutputStream(clientSocket.getOutputStream());
    }

    public static void close() throws IOException{
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public static <T extends Serializable> boolean send(T request) throws IOException{
        if (socket != null){
            out.writeObject(request);
            return true;
        }
        System.out.println("You should run initSocket() method before using sockets.");
        return false;
    }

    public static <T extends Serializable> T recieve() {
        if (socket == null ){
            System.out.println("run initSocket() first.");
            return null;
        }
        try {
            Object obj = in.readObject();
            T request = (T) obj;
            return request;
        } catch (IOException | ClassCastException | ClassNotFoundException e) {
            // TODO: handle
            return null;
        }
    }

    public static Socket getClientSocket(){
        return clientSocket;
    }

    public static ServerSocket getServerSocket(){
        return socket;
    }

}