package ru.ifmo.se.client.GUIHelp;

import java.net.ConnectException;
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
import ru.ifmo.se.client.MainController;
import ru.ifmo.se.client.collections.AskPerson;
import ru.ifmo.se.client.commands.Add;
import ru.ifmo.se.client.commands.AddIfMax;
import ru.ifmo.se.client.commands.Update;
import ru.ifmo.se.client.net.Collection;
import ru.ifmo.se.client.net.ConnectionManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

public class UpdateController implements Initializable{

     @FXML
    private Button updateButton;

    
    @FXML
    private Text ownerIdField;

    @FXML
    private Text ownerIdLabel;


    @FXML
    private Text creationDateField;

    
    @FXML
    private Text creationDateLabel;
    
    @FXML
    private ComboBox<String> hairColorField;

    
    @FXML
    private DatePicker birthdayField;

    
    @FXML
    private Text idField;

    
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

    private static PersonData currentObject= null;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        hairColorField.getItems().clear();
        hairColorField.getItems().addAll("RED","YELLOW","ORANGE","WHITE","BROWN");
        if (currentObject.getHairColor() != null) {
        
        hairColorField.getSelectionModel().select(currentObject.getHairColor());
        
        idField.setText(currentObject.getId());
        nameField.setText(currentObject.getName());
        heightField.setText(currentObject.getHeight());
        weightField.setText(currentObject.getWeight());
        ownerIdField.setText(currentObject.getOwnerId());
        creationDateField.setText(currentObject.getCreationDate());
        Person person = null;
        for (Person p : MainController.getCollection()){
            if(currentObject.getId().equals(p.getId().toString())){
                person = p;
                break;
            }
        }
        coordXField.setText(person.getCoordinates().getX().toString());
        
        coordYField.setText(person.getCoordinates().getY().toString());

        locXField.setText(person.getLocation().getLocX().toString());
        locYField.setText(person.getLocation().getLocY().toString());
        if(person.getLocation().getName() != null){
            locNameField.setText(person.getLocation().getName());
        }
        if(currentObject.getBirthday() != "" && currentObject.getBirthday() != null){
            birthdayField.setValue(LocalDate.parse(currentObject.getBirthday()));
        }

        updateButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
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
                if(p == null){
                    answerText.setText("Values failed validation.Recheck if the values are correct.");
                } else {
                    String command = "update " + currentObject.getId();
                    Update.setPerson(p);
                    Request r = Invoker.execute(command.split(" "));
                    if (r == null){
                        answerText.setText("Error in execution.Recheck the data.");
                    }
                    answerText.setText(r.getMsg());
                }
            }
            
        });
    }
   
}

    public static void setPerson(PersonData p){
        currentObject = p;
    }

    public static PersonData getPerson(){
        return currentObject;
    }
}
