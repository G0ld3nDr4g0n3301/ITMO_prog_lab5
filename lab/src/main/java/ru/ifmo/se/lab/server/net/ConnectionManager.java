package ru.ifmo.se.lab.server.net;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import ru.ifmo.se.lab.server.Invoker;
import ru.ifmo.se.lab.server.Validator;

public class ConnectionManager{
    
    public static Integer port = 777;
    private static ServerSocketChannel socket = null;
    private static SocketChannel channel = null;
    public static Integer timeout = 40;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    
    public static void initSocket() throws IOException {
        socket = ServerSocketChannel.open();
        socket.socket().bind(new InetSocketAddress(port));
        socket.configureBlocking(true);
        try {
        channel = socket.accept();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("Connection Established.");
        out = new ObjectOutputStream(channel.socket().getOutputStream());
        out.flush();
        in = new ObjectInputStream(channel.socket().getInputStream());
        run();
    }
    
    public static void run() throws IOException{
        System.out.println("LOL I WON!");
        while(true){    
            Request input = ConnectionManager.<Request>recieve();
            System.out.println(input);
            if(input != null){
                if(Validator.validateCommand(input)){
                    Request output = Invoker.execute(input);
                    if (output == null) {
                        Request error = new Request(404);
                        error.setMsg("Error in program");
                        try {
                            ConnectionManager.send(error);
                        } catch (IOException e) {
                            // lol
                        }
                    } else {
                        try {
                            ConnectionManager.send(output);
                        } catch (IOException e){
                            // kek
                        }
                    }
                }
            }
        }
    }

    public static void close() throws IOException{
        out.flush();
        socket.close();
    }

    public static boolean send(Request request) throws IOException{
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

    public static Socket getClientSocket(){
        return channel.socket();
    }

    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

}