package ru.ifmo.se.client;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

public class GUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

     public void start(Stage stage){
        WebView webView = new WebView();
        WebEngine webEngine = webView.getEngine();
        InputStreamReader in = new InputStreamReader(this.getClass().getClassLoader().getResourceAsStream("html/login.html"));
        String text = new BufferedReader(in)
        .lines()
        .collect(Collectors.joining("\n"));
        webEngine.loadContent(text);


        Group root = new Group();
        root.getChildren().addAll(webView);
        root.getChildren().addAll();
        stage.setScene(new Scene(root,900,500));
        stage.show();
    }
}
