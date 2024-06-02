package ru.ifmo.se.client;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
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
import javafx.util.Duration;
import ru.ifmo.se.client.GUIHelp.PersonData;
import ru.ifmo.se.client.net.Collection;
import ru.ifmo.se.client.net.ConnectionManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

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

    private static ArrayList<Person> collection;

    private static TableView<PersonData> table = new TableView<>();

    
    @Override
    public void initialize(URL url, ResourceBundle resources){

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(7), e -> refreshTable()));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent e){
                Stage stage = (Stage) elNum.getScene().getWindow();
                stage.close();
            }
            
        });

        

        
        TableColumn id = new TableColumn<>("id");
        id.setCellValueFactory(new PropertyValueFactory<>("id"));
        TableColumn name = new TableColumn<>("name");
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        TableColumn ownerId = new TableColumn<>("ownerId");
        ownerId.setCellValueFactory(new PropertyValueFactory<>("ownerId"));
        TableColumn height = new TableColumn<>("height");
        height.setCellValueFactory(new PropertyValueFactory<>("height"));
        TableColumn weight = new TableColumn<>("weight");
        weight.setCellValueFactory(new PropertyValueFactory<>("weight"));
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
        



        table.getColumns().addAll(id,name,ownerId,height,weight,birthday,creationDate,hairColor,location,coordinates);
        
        tablePane.setContent(table);



        currUser.setText(ConnectionManager.getLogin());

        langChoice.getItems().clear();
        langChoice.getItems().addAll("RU", "UA", "BL", "SP");
        langChoice.getSelectionModel().select("RU");

    }
    
    public void refreshTable(){
        System.out.println("DFLDSJFK");
        Request request = new Request(Commands.RELOAD);
    try {
        ConnectionManager.send(request);
    } catch (IOException e) {
        e.printStackTrace();
    }
    Request answer = ConnectionManager.recieve();
    collection = answer.getCollection();
        ObservableList<PersonData> data = FXCollections.observableArrayList(PersonData.calc(collection));
        table.setItems(data);
    }


}
