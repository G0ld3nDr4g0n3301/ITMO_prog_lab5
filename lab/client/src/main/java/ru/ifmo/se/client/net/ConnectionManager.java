package ru.ifmo.se.client.net;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.logging.Logger;

import ru.ifmo.se.client.EmergencyExit;
import ru.ifmo.se.client.LogFile;
import ru.ifmo.se.common.net.Request;

public class ConnectionManager{
    
    public static Integer port = 32444;
    public static String host = "localhost";
    private static Socket socket = null;
    public static Integer timeout = 40;
    private static OutputStream out;
    private static InputStream in;
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
    }

    
    public static void initSocket() throws IOException{
        socket = new Socket(host, port);
        //socket.connect(new InetSocketAddress(host, port));
        out = socket.getOutputStream();
        in = socket.getInputStream();
    }

    public static void close() throws IOException{
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public static boolean send(Request request) throws IOException{


        if (socket != null){
            System.out.println("Sending...");

            out.write(Serialize.serializeRequest(request));
            out.flush();
            return true;
        }
        System.out.println("You should run initSocket() method before using sockets.");
        return false;
    }

    public static Request recieve() {
        System.out.println("Recieving");
        if (socket == null ){
            System.out.println("run initSocket() first.");
            return null;
        }
        try {
            int length = 0;
            ByteBuffer header = ByteBuffer.allocate(4);
            System.out.println("about to get header");

            header = ByteBuffer.wrap(in.readNBytes(4));
            length = header.getInt();
            ByteBuffer bytes = ByteBuffer.allocate(length+50);
            System.out.println("about to get remaining bytes");
            bytes.put(in.readNBytes(length));
            bytes.flip();



            System.out.println("deserializing...");
            Request request = Deserialize.deserializeRequest(bytes.array());
            return request;
        } catch (BufferUnderflowException e) {
            logger.warning("SERVER DISCONNECTED!!!(That might be either your connection problem, or server-side issue)");
            EmergencyExit.execute();
            return null;
        } catch (IOException e){
            System.out.println(e);
            return null;
        }
    }

    public static Socket getSocket(){
        return socket;
    }

}
