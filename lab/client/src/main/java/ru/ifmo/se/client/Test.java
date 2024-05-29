package ru.ifmo.se.client;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Test extends Application{
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage stage){
        Group group = new Group();
        Scene scene = new Scene(group);

        stage.setTitle("Hello World");
        stage.setScene(scene);
        stage.show();
    }
}
