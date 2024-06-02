package ru.ifmo.se.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
    private Button infoCommandsButton;


    @FXML
    private Button removeCommandsButton;


    @FXML
    private Button addComandsButton;


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

    @FXML
    private ComboBox filterColumn;

    @FXML
    private TextField filterValue;

    @FXML
    private Button filterButton;

    private static ArrayList<Person> collection = new ArrayList<>();

    private static TableView<PersonData> table = new TableView<>();

    private static String columnSelected = null;

    private static String valueSelected = "";

    private ObservableList<TableColumn<PersonData, ?>> sortedColumns = FXCollections.observableArrayList();

    
    @Override
    public void initialize(URL url, ResourceBundle resources){

        Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Collection(), 0, 10, TimeUnit.SECONDS);

        refreshTable();
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(3), e -> refreshTable()));

        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.playFromStart();

        logoutButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent e){
                Stage stage = (Stage) elNum.getScene().getWindow();
                stage.close();
            }
            
        });

        


        filterColumn.getItems().clear();
        filterColumn.getItems().addAll("None","Id", "Name", "OwnerId", "Height", "Weight", "Birthday", "CreationDate","HairColor","Location","Coordinates");
        filterColumn.getSelectionModel().select("Id");

        filterButton.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                columnSelected = filterColumn.getSelectionModel().getSelectedItem().toString();
                valueSelected = filterValue.getText();
                refreshTable();
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
        
        table.setOnSort(e -> {
            this.sortedColumns = table.getSortOrder();
        });




        table.getColumns().addAll(id,name,ownerId,height,weight,birthday,creationDate,hairColor,location,coordinates);
        
        tablePane.setContent(table);

        infoCommandsButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                URL url = null;
                try {
                    url = new File("fxml/info.fxml").toURI().toURL();
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                }
                FXMLLoader fxmlLoader = new FXMLLoader(url);

                try {
                    fxmlLoader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Parent root = fxmlLoader.getRoot();
                Scene scene = new Scene(root);
                Stage stage = new Stage();
                stage.setScene(scene);
                stage.setResizable(false);
                stage.show();
            }

        });

        currUser.setText(ConnectionManager.getLogin());

        langChoice.getItems().clear();
        langChoice.getItems().addAll("RU", "UA", "BL", "SP");
        langChoice.getSelectionModel().select("RU");

        

    }
    
    public void refreshTable(){
        ObservableList<PersonData> data = FXCollections.observableArrayList(filterValues());
        ObservableList<TableColumn<PersonData, ?>> currentSortedColumns = FXCollections.observableArrayList();
        currentSortedColumns.addAll(sortedColumns);
        table.setItems(data);
        table.getSortOrder().addAll(currentSortedColumns);
        table.sort();
        table.refresh();
    }

    public List<PersonData> filterValues(){
        List<PersonData> oldList = PersonData.calc(collection);
        if(columnSelected == "None"){
            return oldList;
        }
        List<PersonData> newList = oldList.stream()
        .filter(p -> matchPredicate(p))
        .collect(Collectors.toList());
        return newList;
    }

    public boolean matchPredicate(PersonData p){
            if(columnSelected == null){
                return true;
            }
            try {
                String value = (String) p.getClass().getMethod("get" + columnSelected).invoke(p);
                if (value == null){
                    if(valueSelected == "") {
                        return true;
                    }
                    return false;
                }
                return value.equals(valueSelected);
            } catch (IllegalAccessException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IllegalArgumentException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (SecurityException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return false;
    }


    public static void setCollection(ArrayList<Person> p){
        collection = p;
    }
}
