package ru.ifmo.se.lab.server.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
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
    private static DataInputStream in;
    private static DataOutputStream out;
    
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
        out = new DataOutputStream(channel.socket().getOutputStream());
        out.flush();
        in = new DataInputStream(channel.socket().getInputStream());
        run();
    }
    
    public static void run() throws IOException{
        System.out.println("LOL I WON!");
        while(true){    
            Request input = ConnectionManager.recieve();
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
    

    public static Socket getClientSocket(){
        return channel.socket();
    }

    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

}