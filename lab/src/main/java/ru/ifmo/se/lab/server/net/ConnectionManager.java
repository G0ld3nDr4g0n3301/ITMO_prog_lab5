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
    
    public static Integer port = 7777;
    private static ServerSocketChannel socket = null;
    private static SocketChannel channel = null;
    public static Integer timeout = 40;
    private static ObjectInputStream in;
    private static ObjectOutputStream out;
    
    public static void initSocket() throws IOException {
        socket = ServerSocketChannel.open();
        socket.socket().bind(new InetSocketAddress(port));
        socket.configureBlocking(true);
        channel = socket.accept();
        System.out.println("Connection Established.");
        in = new ObjectInputStream(channel.socket().getInputStream());
        out = new ObjectOutputStream(channel.socket().getOutputStream());
        run();
    }
    
    public static void run() throws IOException{
        while(true){    
            Serializable pack = ConnectionManager.recieve();
            Request input = (Request) pack;
            if(input != null){
                if(Validator.validateCommand(input)){
                    Request output = Invoker.execute(input.getCommandType(), input.getStatusCode(), input.getArgs());
                    if (output == null) {
                        Request<String> error = new Request<>(404);
                        error.setArgument("Error in program");
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
        socket.close();
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
        return channel.socket();
    }

    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

}