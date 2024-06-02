package ru.ifmo.se.client;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.ifmo.se.client.GUIHelp.PersonData;
import ru.ifmo.se.client.net.Collection;
import ru.ifmo.se.client.net.ConnectionManager;
import ru.ifmo.se.common.collections.Person;

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

    @FXML
    private ScrollPane tablePane;
    
    @Override
    public void initialize(URL url, ResourceBundle resources){
        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Collection(), 0, 10, TimeUnit.SECONDS);

        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent e){
                Stage stage = (Stage) elNum.getScene().getWindow();
                stage.close();
            }
            
        });

        TableView<PersonData> table = new TableView<>();
        
        TableColumn id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn ownerId = new TableColumn<>("ownerId");
        ownerId.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        TableColumn height = new TableColumn<>("height");
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        TableColumn width = new TableColumn<>("width");
        width.setCellValueFactory(new PropertyValueFactory<>("width"));
        TableColumn birthday = new TableColumn<>("birthday");
        birthday.setCellValueFactory(new PropertyValueFactory<>("birthday"));
        TableColumn creationDate = new TableColumn<>("creationDate");
        creationDate.setCellValueFactory(new PropertyValueFactory<>("creationDate"));
        TableColumn hairColor = new TableColumn<>("hairColor");
        hairColor.setCellValueFactory(new PropertyValueFactory<>("hairColor"));
        TableColumn location = new TableColumn<>("location");
        location.setCellValueFactory(new PropertyValueFactory<>("location"));
        TableColumn coordinates = new TableColumn<>("coordinates");
        coordinates.setCellValueFactory(new PropertyValueFactory<>("coordinates"));
        

        table.getColumns().addAll(id,name,ownerId,height,width,birthday,creationDate,hairColor,location,coordinates);
        
        tablePane.setContent(table);



        currUser.setText(ConnectionManager.getLogin());

        langChoice.getItems().clear();
        langChoice.getItems().addAll("RU", "UA", "BL", "SP");
        langChoice.getSelectionModel().select("RU");

    }
    
}
