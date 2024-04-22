package ru.ifmo.se.server.net;

import java.io.IOException;
import java.io.StreamCorruptedException;
import java.net.InetSocketAddress;
import java.net.SocketException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.logging.Logger;

import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.CollectionManager;
import ru.ifmo.se.server.Invoker;
import ru.ifmo.se.server.LogFile;
import ru.ifmo.se.server.commands.Save;


public class ConnectionManager{

    /**
     * logger
     */
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    /**
     * number of users connected
     */
    private static int usersConnected = 0;
    
    static {
        logger.addHandler(LogFile.getHandler());
    }
    
    /**
     * server port
     */
    public static Integer port = 32444;

    /**
     * server channel
     */
    private static ServerSocketChannel socket;

    /**
     * connection timeout
     */
    public static Integer timeout = 40;

    /**
     * selector to switch users
     */
    private static Selector selector;
    
    /**
     * initialize the socket
     */
    public static void initSocket() throws IOException {
        socket = ServerSocketChannel.open();
        socket.bind(new InetSocketAddress(port));
        socket.configureBlocking(false);
        selector = Selector.open();
        socket.register(selector, SelectionKey.OP_ACCEPT);

    }

    /**
     * return numbers of connected users
     * @return number of connected users
     */
    public static int getUsersConnected() {
        return usersConnected;
    }

    /**
     * run user handler
     * @return true if no errors encountered
     * @throws IOException
     */
    public static boolean run() throws IOException{
        
            selector.select();
            Iterator<SelectionKey> keys = selector.selectedKeys().iterator();
            while (keys.hasNext()) {
                SelectionKey key = keys.next();
                keys.remove();

                try {
                if(key.isAcceptable()) {
                    ServerSocketChannel tempServerChannel = (ServerSocketChannel) key.channel();
                    SocketChannel client = tempServerChannel.accept();
                    if (client == null) {
                        System.out.println("NULLLLLL");
                        continue;
                    }
                    client.configureBlocking(false);
                    client.register(selector, SelectionKey.OP_READ);
                    usersConnected += 1;
                    logger.info("Added new key");
                    logger.info("Now total " + usersConnected + " users");
                } else if (key.isReadable()) {
                    Request request;
                    SocketChannel client = (SocketChannel) key.channel();
                    request = Reciever.recieve(key);
                    if (request == null) {
                        return false;
                    }
                    Request answerRequest = Invoker.execute(request);
                    SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
                    if(answerRequest != null){
                        answerRequest.setId(usersConnected);
                    }
                    keyNew.attach(answerRequest);
                
                    //SocketChannel client = (SocketChannel) key.channel();
                    Sender.send(key);
                    client = (SocketChannel) key.channel();
                    client.register(selector, SelectionKey.OP_READ);
                } 
            } catch (SocketException | StreamCorruptedException e) {
                logger.warning("client disconnected");
                usersConnected -= 1;
                if (usersConnected == 0) {
                    new Save("","").execute(new Request(Commands.SAVE));
                }
                key.cancel();
                return false;
            } 
            }
        return true;
        
    
    }
    
    public static Selector getSelector(){
        return selector;
    }

    public static void setPort(int userPort){
        port = userPort;
    }

    public static void close() throws IOException{
        socket.close();
    }


    public static ServerSocketChannel getServerSocket(){
        return socket;
    }

    public static int getPort() {
        return port;
    }

}
