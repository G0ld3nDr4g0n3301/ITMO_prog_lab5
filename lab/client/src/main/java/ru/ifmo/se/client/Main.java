package ru.ifmo.se.client;

import java.io.IOException;
import java.io.Serializable;
import java.net.Socket;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import ru.ifmo.se.client.net.ConnectionManager;
import sun.misc.Signal;

public class Main {
    private static final Logger logger = Logger.getLogger(ConnectionManager.class.getName());

    static {
        logger.addHandler(LogFile.getHandler());
        Signal.handle(new Signal("INT"), signal -> EmergencyExit.execute());
    }

    public static void main(String[] args) {
        GUI.main(args);


        boolean isConnected = false;
        for (int tries = 0; tries < 5 && !isConnected; tries++) {
            logger.info("Server doesn't respond.Retrying, try "+ tries +"...");
            isConnected = connect();
        }
        if (!isConnected) {
            logger.warning("server is unreachable");
            System.exit(0);
        }
        logger.info("Connected!");
        try {
            while(true){
                String input = CLIInputManager.ask("> ");
                if(input != null){
                    if(Validator.validateCommand(input)){
                        Invoker.execute(input.split(" "));
                    }
                }
            }
        
        } finally {
            try {
                ConnectionManager.close();
            } catch (IOException e) {
                // TODO: handle
            }
        }
}

   

    public static boolean connect() {
        try {
            Thread.sleep(200);
        ConnectionManager.setSocket(new Socket(ConnectionManager.getHost(), ConnectionManager.getPort()));
        ConnectionManager.initSocket();
        return true;
        } catch (IOException | InterruptedException e){
            System.out.println(e.getMessage());
        }
        return false;
    }
}
