package ru.ifmo.se.client.net;

import java.io.ByteArrayInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.BufferOverflowException;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.logging.Logger;

import ru.ifmo.se.client.EmergencyExit;
import ru.ifmo.se.client.LogFile;
import ru.ifmo.se.client.Main;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

public class ConnectionManager{
    
    private static String login;

    private static String password;

    private static String cookie;

    /**
     * target port
     */
    public static Integer port = 32444;

    /**
     * Target host
     */
    public static String host = "localhost";

    /**
     * target socket
     */
    private static Socket socket = null;

    /**
     * connection timeout
     */
    public static Integer timeout = 40;

    /**
     * Output stream for connection with server
     */
    private static OutputStream out;

    /**
     * Input stream for connection with server
     */
    private static InputStream in;

    /**
     * Logger
     */
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
    }

    /**
     * Initialize Socket
     * @throws IOException
     */
    public static void initSocket() throws IOException{
        out = socket.getOutputStream();
        in = socket.getInputStream();
        if (out == null || in == null){
            throw new IOException("Not connected");
        }
        System.out.println("Socket = " + socket.getRemoteSocketAddress());
    }

    public static void setPort(int userPort) {
        port = userPort;
    }

    public static void setLogin(String userLogin){
        login = userLogin;
    }

    public static void setPassword(String userPassword){
        password = userPassword;
    }

    /**
     * Close socket
     * @throws IOException
     */
    public static void close() throws IOException{
        if (!socket.isClosed()) {
            socket.close();
        }
    }

    public static void setSocket(Socket s) {
        socket = s;
    }

    public static String getHost() {
        return host;
    }

    public static void setOut(OutputStream o) {
        out = o;
    }

    public static void setIn(InputStream i){
        in = i;
    }

    /**
     * Send data to server
     * @param request Request to send
     * @return true if no errors encountered
     * @throws IOException
     */
    public static boolean send(Request request) throws IOException{
        request.setCookie(cookie);
        
        if (socket != null){
            System.out.println("Sending...");
            out.write(Serialize.serializeRequest(request));
            out.flush();
            return true;
        }
        System.out.println("You should run initSocket() method before using sockets.");
        return false;
    }

    /**
     * Recieve Response from server
     * @return Response
     */
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
            try {
            socket.close();
            } catch (IOException io){
                // handle
            }
            boolean isConnected = false;
            for (int tries = 0; tries < 5 && !isConnected; tries++) {
                logger.info("Server doesn't respond.Retrying, try "+ tries +"...");
                isConnected = Main.connect();
            }
            if (!isConnected) {
                logger.warning("server is unreachable");
                EmergencyExit.execute();
                return null;
            }
            logger.info("Connected!");
            return null; 
            //    logger.warning("server is unreachable");
            //    EmergencyExit.execute();
            //return null;
        } catch (IOException e){
            System.out.println(e);
            return null;
        }
    }

    /**
     * Just returns socket.
     * @return socket
     */
    public static Socket getSocket(){
        return socket;
    }

    public static void setCookie(String newCookie){
        cookie = newCookie;
    }

    public static String getCookie(){
        return cookie;
    }

    public static String register(){
        Request request = new Request(Commands.REGISTER);
        request.setLogin(login);
        request.setPassword(password);
        try {
            send(request);
            Request result = recieve();
            if(result.getStatusCode() == 404){
                return null;
            }
            setCookie(result.getCookie());
            return result.getMsg();
        } catch (IOException e) {
            
            e.printStackTrace();
            return null;
        }
        

    }

    public static String login(){
        Request request = new Request(Commands.LOGIN);
        request.setLogin(login);
        request.setPassword(password);
        try {
            send(request);
            Request result = recieve();
            if(result.getStatusCode() == 404){
                return null;
            }
            setCookie(result.getCookie());
            return result.getMsg();
        } catch (IOException e) {
            
            e.printStackTrace();
            return null;
        }
    }

    public static int getPort() {
        return port;
    }

}
