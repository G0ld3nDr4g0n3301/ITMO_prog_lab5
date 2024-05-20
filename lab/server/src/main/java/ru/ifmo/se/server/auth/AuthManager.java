package ru.ifmo.se.server.auth;

import java.beans.Statement;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

import ru.ifmo.se.common.net.Request;
import ru.ifmo.se.server.db.DBConnection;
import ru.ifmo.se.server.net.ConnectionManager;
import ru.ifmo.se.server.net.Handler;

public class AuthManager implements Runnable {

    private String salt;
    private static final String[] pepper = {"0","1","2","3","4","5","6","7","8","9"};
    private static final String[] saltArray = {"a", "b", "c", "d", "e", "f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
    private static final String registerSQL = "INSERT INTO USERS (login, password, salt) VALUES (?, ?, ?)";
    private String login;
    private String password;
    private static Connection connection = DBConnection.connect();
    private SelectionKey key;
    private Selector selector;
    private Request request;

    public AuthManager(SelectionKey key, Selector selector, Request request, String login, String password){
        this.login = login;
        this.password = password;
        this.key = key;
        this.selector = selector;
        this.request = request;
    }


    public void run(){
        try {
            ResultSet userId = connection.createStatement().executeQuery("SELECT id,password,salt FROM users WHERE login = '" + login + "';");
            boolean hasRows = userId.next();
            if (!hasRows){
                register();
            }
            userId = connection.createStatement().executeQuery("SELECT id,password,salt FROM users WHERE login = '" + login + "';");
            userId.next();
            Integer id = userId.getInt(1);
            String userHash = userId.getString(2);
            String userSalt = userId.getString(3);
            if (CompareHash.compare(password, userSalt, pepper, userHash)) {
                request.setOwnerId(id);
                Runnable handler = new Handler(key, selector, request);
                ConnectionManager.addToHandlePool(handler);
            } else {
                SocketChannel client = (SocketChannel) key.channel();
                Request errorRequest = new Request(404);
                errorRequest.setMsg("Wrong password.");
                SelectionKey keyNew = client.register(selector, SelectionKey.OP_WRITE);
                keyNew.attach(errorRequest);
            }
        } catch (SQLException | ClosedChannelException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private Boolean register() {
        this.salt = generateSalt();
        String hash = Hash.hash(password, salt, pepper);
        System.out.println("register: " + hash);
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(registerSQL);
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, hash);
            preparedStatement.setString(3, salt);
            if(!preparedStatement.execute()){
                return false;
            }
            return true;

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return false;
    }

    private String generateSalt(){
        String[] salt = new String[7];
        Random random = new Random();
        for (int i = 0; i < salt.length; i++){
            salt[i] = saltArray[random.nextInt(7)];
        }
        String res = String.join("",salt);
        System.out.println(res);
        return res;
    }
    
}
