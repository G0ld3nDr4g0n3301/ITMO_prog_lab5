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
import ru.ifmo.se.client.commands.Add;
import ru.ifmo.se.client.commands.AddIfMax;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Request;

public class AddController implements Initializable{
    private static ResourceBundle bundle = ResourceBundle.getBundle("locale");
    public static void setBundle(ResourceBundle newBundle){
        bundle = newBundle;
    }
    @FXML
    private Button addButton;

    
    @FXML
    private CheckBox addIfMax;

    
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

    public void localize(){
        addButton.setText(bundle.getString("add"));
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        localize();
        hairColorField.getItems().clear();
        hairColorField.getItems().addAll("RED","YELLOW","ORANGE","WHITE","BROWN");
        hairColorField.getSelectionModel().select("RED");
        
        addButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

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
                    answerText.setText(bundle.getString("Values failed validation.Recheck if the values are correct."));
                } else {
                    String command = null;
                    if (addIfMax.isSelected()){
                        AddIfMax.setPerson(p);
                        command = "addif asdf";
                    } else {
                        Add.setPerson(p);
                        command = "add vdkjld";
                    }
                    Request r = Invoker.execute(command.split(" "));
                    if (r == null){
                        answerText.setText(bundle.getString("Error in execution.Recheck the data."));
                    }
                    answerText.setText(r.getMsg());
                }
            }
            
        });




        
    }
    
}
