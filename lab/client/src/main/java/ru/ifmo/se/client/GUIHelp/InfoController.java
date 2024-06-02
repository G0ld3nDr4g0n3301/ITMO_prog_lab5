package ru.ifmo.se.client.GUIHelp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.common.net.Request;

public class InfoController implements Initializable{

    @FXML
    private Text commandLabel;

    @FXML
    private Text dataLabel;

    @FXML
    private TextField dataField;

    @FXML
    private ComboBox<String> commandBox;

    @FXML
    private Button startButton;

    @FXML
    private Text answerText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        commandBox.getItems().clear();
        commandBox.getItems().addAll("info", "count_by_height","print_hair_colors");
        commandBox.getSelectionModel().select("info");


        startButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                String command = commandBox.getSelectionModel().getSelectedItem() + " " + dataField.getText();
                if(commandBox.getSelectionModel().getSelectedItem() == "count_by_height" && dataField.getText() == ""){
                    answerText.setText("Can't execute this command with null height.");
                } else {
                    Request answer = Invoker.execute(command.split(" "));
                    if (answer == null){
                        answerText.setText("Error in command execution");
                    } else {
                        answerText.setText(answer.getMsg());
                    }
                }
            }
            
        });
    }

    
    
}
