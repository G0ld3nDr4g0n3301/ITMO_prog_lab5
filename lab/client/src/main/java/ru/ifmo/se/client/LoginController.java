package ru.ifmo.se.client;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Locale;
import java.util.Properties;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import ru.ifmo.se.client.GUIHelp.AddController;
import ru.ifmo.se.client.GUIHelp.InfoController;
import ru.ifmo.se.client.GUIHelp.RemoveController;
import ru.ifmo.se.client.GUIHelp.UpdateController;
import ru.ifmo.se.client.net.ConnectionManager;

public class LoginController implements Initializable {

    private static ResourceBundle bundle = ResourceBundle.getBundle("locale");


    @FXML
    private ImageView rus_img;

    @FXML
    private ImageView ukr_img;
    
    @FXML
    private ImageView bel_img;
    
    @FXML
    private ImageView spa_img;
    
    @FXML
    private ImageView vt_img;

    @FXML
    private TextField login_field;

    @FXML
    private PasswordField pass_field;

    @FXML
    private Button login_button;

    @FXML
    private Button register_button;

    @FXML
    private Text error_text;

    @FXML
    private Label login_label;

    @FXML
    private Label pass_label;

    @FXML
    private CheckBox remember;

    public void localize(){
        login_label.setText(bundle.getString("username"));
        pass_label.setText(bundle.getString("password"));
        login_button.setText(bundle.getString("login"));
        register_button.setText(bundle.getString("register"));
        remember.setText(bundle.getString("remember data"));
    }

    public static void setBundle(ResourceBundle newBundle) {
        bundle = newBundle;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

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
        
        file = new File("vt.png");
        image = new Image(file.toURI().toString());
        vt_img.setImage(image);

        
        loadUserData();

        rus_img.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                ResourceBundle resourcesBundle = ResourceBundle.getBundle("resources", new Locale("ru","RU"));
                AddController.setBundle(resourcesBundle);
                UpdateController.setBundle(resourcesBundle);
                LoginController.setBundle(resourcesBundle);
                MainController.setBundle(resourcesBundle);
                RemoveController.setBundle(resourcesBundle);
                InfoController.setBundle(resourcesBundle);
                localize();
            }
        });
        bel_img.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                ResourceBundle resourcesBundle = ResourceBundle.getBundle("locale", new Locale("bl","BL"));
                AddController.setBundle(resourcesBundle);
                UpdateController.setBundle(resourcesBundle);
                LoginController.setBundle(resourcesBundle);
                MainController.setBundle(resourcesBundle);
                RemoveController.setBundle(resourcesBundle);
                InfoController.setBundle(resourcesBundle);
                localize();
            }
        });
        ukr_img.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                ResourceBundle resourcesBundle = ResourceBundle.getBundle("locale", new Locale("ua","UA"));
                AddController.setBundle(resourcesBundle);
                UpdateController.setBundle(resourcesBundle);
                LoginController.setBundle(resourcesBundle);
                MainController.setBundle(resourcesBundle);
                RemoveController.setBundle(resourcesBundle);
                InfoController.setBundle(resourcesBundle);
                localize();
            }
        });
        spa_img.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                ResourceBundle resourcesBundle = ResourceBundle.getBundle("locale", new Locale("sp","SP"));
                AddController.setBundle(resourcesBundle);
                UpdateController.setBundle(resourcesBundle);
                LoginController.setBundle(resourcesBundle);
                MainController.setBundle(resourcesBundle);
                RemoveController.setBundle(resourcesBundle);
                InfoController.setBundle(resourcesBundle);
                localize();
            }
        });

        login_field.setOnKeyTyped(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                login_label.setVisible(false);
            }
        });


        login_field.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                if(login_field.getCharacters().length() == 0){
                login_label.setVisible(true);
                }
            }
        });


        pass_field.setOnKeyTyped(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                pass_label.setVisible(false);
            }
        });


        pass_field.setOnKeyReleased(new EventHandler<KeyEvent>(){
            @Override
            public void handle(KeyEvent e){
                if(pass_field.getCharacters().length() == 0){
                pass_label.setVisible(true);
                }
            }
        });

        EventHandler<MouseEvent> handler1 = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                ConnectionManager.setLogin(login_field.getText());
                ConnectionManager.setPassword(pass_field.getText());
                String msg = ConnectionManager.login();
                storeUserData(login_field.getText(), pass_field.getText());
                if(msg != null){
                    error_text.setText(msg);
                } else {
                    error_text.setText("");
                    showMain();
                }
                
            }
        };
        
        EventHandler<MouseEvent> handler2 = new EventHandler<MouseEvent>(){
            @Override
            public void handle(MouseEvent e){
                ConnectionManager.setLogin(login_field.getText());
                ConnectionManager.setPassword(pass_field.getText());
                storeUserData(login_field.getText(), pass_field.getText());
                String msg = ConnectionManager.register();
                if(msg != null){
                    error_text.setText(msg);
                }else {
                    error_text.setText("");
                    showMain();
                }
            }
        };

        login_button.setOnMouseClicked(handler1);
        register_button.setOnMouseClicked(handler2);

    }

    private void loadUserData(){
        try {
            File dataFile = new File("userData.properties");
            if(!dataFile.exists()){
                dataFile.createNewFile();
            }
            Properties userData = new Properties();
            userData.load(new FileInputStream(dataFile));
            if(!(userData.getProperty("login") == null || userData.getProperty("password") == null)){
                login_label.setVisible(false);
                pass_label.setVisible(false);
                login_field.setText(userData.getProperty("login"));
                pass_field.setText(userData.getProperty("password"));
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private void storeUserData(String login,String password){
        try{
            if(remember.isSelected()){
            File dataFile = new File("userData.properties");
            if(!dataFile.exists()){
                dataFile.createNewFile();
            }
            Properties userData = new Properties();
            InputStream outS = new FileInputStream(dataFile);
            userData.load(outS);
            if(!userData.containsKey("login") || !userData.containsKey("password")){
                FileWriter out = new FileWriter("userData.properties");
                out.write("login=" + login + "\n");
                out.write("password=" + password + "\n");

                out.flush();
            }
            userData.setProperty("login", login);
            userData.setProperty("password", password);
            userData.store(new FileWriter("userData.properties"), null);
        }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showMain(){
        login_button.getScene().getWindow().hide();
        URL url = null;
        try {
            url = new File("fxml/main.fxml").toURI().toURL();
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
        GUI.setStage(stage);
        stage.setTitle("Raistlin!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    
}
