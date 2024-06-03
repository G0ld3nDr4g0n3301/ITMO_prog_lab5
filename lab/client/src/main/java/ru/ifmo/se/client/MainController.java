package ru.ifmo.se.client;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.FillTransition;
import javafx.animation.KeyFrame;
import javafx.animation.ParallelTransition;
import javafx.animation.RotateTransition;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import ru.ifmo.se.client.GUIHelp.Animate;
import ru.ifmo.se.client.GUIHelp.AnimationHandler;
import ru.ifmo.se.client.GUIHelp.PersonData;
import ru.ifmo.se.client.GUIHelp.UpdateController;
import ru.ifmo.se.client.net.Collection;
import ru.ifmo.se.client.net.ConnectionManager;
import ru.ifmo.se.common.collections.Person;
import ru.ifmo.se.common.net.Commands;
import ru.ifmo.se.common.net.Request;

public class MainController implements Initializable{

    private static HashMap<Person,Rectangle> animated = new HashMap<>();

    private static List<Person> collectionCopy = new ArrayList<>();

    private static ArrayList<Person> currentlyAnimated = new ArrayList<>();

    private static HashSet<Integer> idSet = new HashSet();

    private static HashMap<Integer, Color> colors = new HashMap<>();

    @FXML
    private Button infoCommandsButton;



    @FXML
    private Button removeCommandsButton;


    @FXML
    private Button addCommandsButton;


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

    @FXML
    private AnchorPane visual;

    private ExecutorService pool = Executors.newFixedThreadPool(5);

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

        addCommandsButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                URL url = null;
                try {
                    url = new File("fxml/add.fxml").toURI().toURL();
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


        removeCommandsButton.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                URL url = null;
                try {
                    url = new File("fxml/remove.fxml").toURI().toURL();
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

        table.setRowFactory(e -> {
            TableRow<PersonData> row = new TableRow<>();
            row.setOnMouseClicked(event -> {
                UpdateController.setPerson(row.getItem());
                callUpdateWindow();
            });
            return row;
        });
        

    }

    private void callUpdateWindow(){
        URL url = null;
                try {
                    url = new File("fxml/update.fxml").toURI().toURL();
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
    
    public void refreshTable(){
        ObservableList<PersonData> data = FXCollections.observableArrayList(filterValues());
        if (ConnectionManager.getUserId() != null){
            elNum.setText(ConnectionManager.getUserId().toString());
        }
        ObservableList<TableColumn<PersonData, ?>> currentSortedColumns = FXCollections.observableArrayList();
        currentSortedColumns.addAll(sortedColumns);
        table.setItems(data);
        table.getSortOrder().addAll(currentSortedColumns);
        table.sort();
        table.refresh();
        calcAnimations();
    }

    public void setRectangle(){
        ArrayList<Rectangle> rec = new ArrayList<>();
        for (Animate a : AnimationHandler.getAnimated().values()){
            rec.add(a.getRec());
        }
        visual.getChildren().addAll(rec);
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

    public static synchronized ArrayList<Person> getCollection(){
        return collection;
    }

    public static void setCollection(ArrayList<Person> p){
        collection = p;
    }

    public void animate(Person p){
        Rectangle rec = new Rectangle(p.getHeight() > 200 ? 200 : p.getHeight(),p.getWeight() > 200 ? 200 : p.getWeight());
        rec.setX(p.getCoordinates().getX() > 200 ? 200 : p.getCoordinates().getX());
        rec.setY(p.getCoordinates().getY() > 200 ? 200 : p.getCoordinates().getY());
        rec.setFill(getColor(p));

        rec.setOnMouseClicked(new EventHandler<MouseEvent>(){

            @Override
            public void handle(MouseEvent event) {
                UpdateController.setPerson(new PersonData(p));
                callUpdateWindow();

            }});
        TranslateTransition tt = new TranslateTransition();
        tt.setDuration(Duration.seconds(5));
        tt.setToX(p.getLocation().getLocY() * 1.5);
        tt.setToY(p.getLocation().getLocX() * 1.3);
        tt.setAutoReverse(true);
        tt.setCycleCount(2);
        tt.setNode(rec);
        
        FillTransition ft = new FillTransition(Duration.seconds(4),rec, getColor(p), Color.color(Math.random(),Math.random(),Math.random()));
        ft.setCycleCount(4);
        ft.setAutoReverse(true);
        
        RotateTransition rt = new RotateTransition(Duration.seconds(3));
        rt.setByAngle(270);
        rt.setCycleCount(4);
        rt.setAutoReverse(true);

        ParallelTransition pt = new ParallelTransition(rec, tt, ft, rt);
        pt.play();

        animated.put(p, rec);
    }
    
    public void calcAnimations(){
        collectionCopy = new ArrayList<>();
        collectionCopy.addAll(collection);
        collectionCopy.removeAll(currentlyAnimated);
        for (Person p : collectionCopy){
            if(!idSet.contains(p.getId())){
                animate(p);
                idSet.add(p.getId());
            }
        }
        collectionCopy = List.copyOf(collection);
        currentlyAnimated.removeAll(collectionCopy);
        for (Person p : currentlyAnimated){
            System.out.println(animated.remove(p));
            
        }
        visual.getChildren().clear();
        visual.getChildren().addAll(animated.values());

    }


    public Color getColor(Person p){
        if(colors.containsKey(p.getOwnerId())){
            return colors.get(p.getOwnerId());
        } else {
            Color color = Color.color(Math.random(), Math.random(), Math.random());
            colors.put(p.getOwnerId(), color);
            return color;
        }
    }

}
