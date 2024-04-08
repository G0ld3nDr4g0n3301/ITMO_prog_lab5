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
                Request answerRequest = Invoker.execute(request);
                SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
                keyNew.attach(answerRequest);

        //    } else if (key.isWritable()) {
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

            ByteBuffer buffer = ByteBuffer.allocate(byteArrayOutputStream.size() + 8);
            buffer.putInt(byteArrayOutputStream.size() + 4);
            buffer.put(tempHeader);
            buffer.put(byteArrayOutputStream.toByteArray());
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
            System.out.println(Arrays.toString(bigBuffer.array()));
            Request rq = Deserialize.deserializeRequest(bigBuffer.array());
            if (rq == null) {
                System.out.println("package is null!");
            }
            return rq;

        } catch (IOException e) {
            System.out.println(e);
            return null;
        }
    }
    


    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

}