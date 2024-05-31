package ru.ifmo.se.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.ifmo.se.client.net.ConnectionManager;

public class MainController implements Initializable{

    @FXML
    private Button logoutButton;

    @FXML
    private Text currUserLabel;

    @FXML
    private Text elNumLabel;

    @FXML
    private Text currUser;

    @FXML
    private Text elNum;

    @FXML
    private ComboBox langChoice;
    

    @Override
    public void initialize(URL url, ResourceBundle resources){
        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                Stage stage = (Stage) elNum.getScene().getWindow();
                stage.close();
            }
        });

        elNum.setText(ConnectionManager.getLogin());

        langChoice.getItems().clear();;
        langChoice.getItems().addAll("RU", "UA", "BL", "SP");
        langChoice.getSelectionModel().select("RU");

    }
    
}
