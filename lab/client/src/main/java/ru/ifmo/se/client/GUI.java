package ru.ifmo.se.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

     public void start(Stage stage){
        
        
        
        Parent root = null;
        try {
            URL url = new File("fxml/login.fxml").toURI().toURL();
            System.out.println(url);
            FXMLLoader fxmlLoader = new FXMLLoader(url);
            System.out.println("IDK");
            //fxmlLoader.setController(new LoginController());
            //System.out.println(fxmlLoader.getController().toString());
            root = fxmlLoader.load();
        
        Scene scene = new Scene(root,800,500);
        stage.setResizable(false);
        stage.sizeToScene();
        stage.setScene(scene);
        stage.show();
    } catch (Exception e) {
        // TODO Auto-generated catch block
        System.out.println(e);
        e.printStackTrace();
    }
    }
}
