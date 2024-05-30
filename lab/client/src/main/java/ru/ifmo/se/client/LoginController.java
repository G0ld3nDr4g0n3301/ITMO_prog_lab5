package ru.ifmo.se.client;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LoginController implements Initializable {

    @FXML
    private ImageView rus_img;

    @FXML
    private ImageView ukr_img;
    
    @FXML
    private ImageView bel_img;
    
    @FXML
    private ImageView spa_img;
    



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        System.out.println("DSFSDFDSF");
        File file = new File("0.png");
        Image image = new Image(file.toURI().toString());
        rus_img.setImage(image);
        file = new File("1.png");
        image = new Image(file.toURI().toString());
        ukr_img.setImage(image);
        file = new File("2.png");
        image = new Image(file.toURI().toString());
        bel_img.setImage(image);
        file = new File("3.png");
        image = new Image(file.toURI().toString());
        spa_img.setImage(image);
    }
}
