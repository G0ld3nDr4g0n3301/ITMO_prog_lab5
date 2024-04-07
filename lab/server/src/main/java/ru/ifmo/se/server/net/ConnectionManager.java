package ru.ifmo.se.server.net;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Arrays;

import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.Invoker;


public class ConnectionManager{
    
    public static Integer port = 777;
    private static ServerSocketChannel socket;
    public static Integer timeout = 40;
    private static Selector selector;
    
    public static void initSocket() throws IOException {
        socket = ServerSocketChannel.open();
        socket.bind(new InetSocketAddress(port));
        socket.configureBlocking(false);
        selector = Selector.open();
        socket.register(selector, SelectionKey.OP_ACCEPT);
/*
        System.out.println("Connection Established.");
        out = new DataOutputStream(channel.socket().getOutputStream());
        out.flush();
        in = new DataInputStream(channel.socket().getInputStream()); */
        while (true) {
            try {
                run();
            } catch (IOException e) {
                System.out.println(e);
                System.out.println("lol, error");
            }
        }
    }
    
    public static void run() throws IOException{

        selector.select();
        for (SelectionKey key : selector.selectedKeys()) {

            if(key.isAcceptable()) {
                ServerSocketChannel tempServerChannel = (ServerSocketChannel) key.channel();
                SocketChannel client = tempServerChannel.accept();
                if (client == null) {
                    continue;
                }
                client.configureBlocking(false);
                client.register(selector, SelectionKey.OP_READ);
            } else if (key.isReadable()) {
                Request request;
                SocketChannel client = (SocketChannel) key.channel();
                request = recieve(key);
                System.out.println("Recieved user request");
                Request answerRequest = Invoker.execute(request);
                System.out.println("Got a request!");
                SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
                System.out.println("Marked as writable");
                keyNew.attach(request);
                System.out.println("attached!");

        //    } else if (key.isWritable()) {
                System.out.println("WRITING");
                send(key);
                client = (SocketChannel) key.channel();
                client.register(selector, SelectionKey.OP_READ);
            }
        }
/* 
        System.out.println("LOL I WON!");
        while(true){
            Request input = null;
            System.out.println("ADSFHADSKSFJBK");
            input = ConnectionManager.recieve();
            System.out.println("recieved");
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
        } */
    }

    public static void close() throws IOException{
        socket.close();
    }

    
    public static boolean send(SelectionKey key) throws IOException{

        if (socket != null){
            SocketChannel client = (SocketChannel) key.channel();
            client.configureBlocking(false);

            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
            ByteBuffer tempHeader = ByteBuffer.wrap(byteArrayOutputStream.toByteArray());
            byteArrayOutputStream.reset();
            objectOutputStream.writeObject(key.attachment());
            objectOutputStream.flush();
            objectOutputStream.close();
            System.out.println("here comes the sun");
            System.out.println(byteArrayOutputStream.size() + 4);

            ByteBuffer buffer = ByteBuffer.allocate(byteArrayOutputStream.size() + 8);
            buffer.putInt(byteArrayOutputStream.size() + 4);
            buffer.put(tempHeader);
            buffer.put(byteArrayOutputStream.toByteArray());

            System.out.println(Arrays.toString(buffer.array()));
            
                System.out.println("MAMA I AM WRITING TO THE OUTPUT STREAM!!!");
                buffer.flip();
                client.write(buffer);
            return true;
        }
        System.out.println("You should run initSocket() method before using sockets.");
        return false;
    }

    public static Request recieve(SelectionKey key) {
        if (socket == null ){
            System.out.println("run initSocket() first.");
            return null;
        }
        try {
            SocketChannel client = (SocketChannel) key.channel();
            client.configureBlocking(false);
            
            
            ByteBuffer buffer = ByteBuffer.allocate(512*3);
            client.read(buffer);
            
            //ByteBuffer header = ByteBuffer.allocate(5);
            ByteBuffer bigBuffer = ByteBuffer.allocate(512*4);
            //bigBuffer.put(header.array());
            bigBuffer.put(buffer.array());
            
            //ObjectInputStream oi = new ObjectInputStream(bi);
            Request rq = Deserialize.deserializeRequest(bigBuffer.array());
            System.out.println(rq);
            if (rq == null) {
                System.out.println("package is null!");
                System.exit(0);
            }
            return Invoker.execute(rq);

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    


    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

}