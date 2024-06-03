package ru.ifmo.se.client.GUIHelp;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import ru.ifmo.se.client.Invoker;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.commands.RemoveLoc;
import ru.ifmo.se.client.commands.RemoveLower;
import ru.ifmo.se.common.collections.Location;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

public class RemoveController implements Initializable{

    private static ResourceBundle bundle = ResourceBundle.getBundle("locale");
    public static void setBundle(ResourceBundle newBundle){
        bundle = newBundle;
    }
     @FXML
    private Button startButton;

    @FXML
    private ComboBox<String> commandBox;
    
    @FXML
    private ComboBox<String> hairColorField;

    
    @FXML
    private DatePicker birthdayField;

    
    @FXML
    private TextField idField;

    
    @FXML
    private TextField heightField;

    
    @FXML
    private TextField weightField;

    
    @FXML
    private TextField nameField;

    
    @FXML
    private TextField coordXField;

    
    @FXML
    private TextField coordYField;
    
    @FXML
    private TextField locXField;
    
    @FXML
    private TextField locYField;
    
    @FXML
    private TextField locNameField;

    @FXML
    private Text answerText;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hairColorField.getItems().clear();
        hairColorField.getItems().addAll("RED","YELLOW","ORANGE","BROWN","WHITE");
        hairColorField.getSelectionModel().select("RED");

        commandBox.getItems().clear();
        commandBox.getItems().addAll("clear","removeId","removeLoc","removeLower","removeLast");
        commandBox.getSelectionModel().select("clear");

        startButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                String selectedCommand = commandBox.getSelectionModel().getSelectedItem();
                String command = null;
                if(selectedCommand == "clear"){
                    command = "clear dsf";
                } else if (selectedCommand == "removeId"){
                    command = "remove_by_id " + idField.getText();
                } else if (selectedCommand == "removeLoc") {
                    Location loc = AskPerson.createLoction(locXField.getText(),locYField.getText(),locNameField.getText());
                    RemoveLoc.setLoc(loc);
                    command = "remove_by_location dsfsdf";
                } else if (selectedCommand == "removeLower"){
                    String[] args = new String[9];
                    args[0] = "1";
                    args[1] = nameField.getText();
                    args[2] = heightField.getText();
                    args[3] = weightField.getText();
                    args[4] = hairColorField.getSelectionModel().getSelectedItem();
                    args[5] = coordXField.getText();
                    args[6] = coordYField.getText();
                    args[7] = locXField.getText();
                    args[8] = locYField.getText();
                    String locName = locNameField.getText();
                    LocalDate birthday = birthdayField.getValue();
                    String newBirthday = null;
                    if (birthday == null){
                        newBirthday = "";
                    } else {
                        newBirthday = birthday.toString();
                    }

                    Person p = AskPerson.createPerson(args, locName, newBirthday);
                    RemoveLower.setPerson(p);
                    command = "remove_lower asdfdsaf";
                } else {
                    command = "remove_last dsfad";
                }
                Request request = Invoker.execute(command.split(" "));
                if(request == null){
                    answerText.setText("Error during execution. Recheck your data.");
                }
                answerText.setText(request.getMsg());
            }});
        
    }
    
}
