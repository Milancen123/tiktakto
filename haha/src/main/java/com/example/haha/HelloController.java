package com.example.haha;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private Stage stage;

    @FXML
    private AnchorPane main;
    @FXML
    private Button button1;
    @FXML
    private TextField textField;

    private Client client;
    @Override
    @FXML
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.client = new Client(new Socket("localhost", 1234));
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error creating a client");
        }

        button1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = textField.getText();
                client.setUsername(username);
                if (!username.isEmpty()) {
                    try {
                        switchToScene1(event);
                        client.sendUsername(username);


                    } catch (IOException e) {
                        e.printStackTrace();
                        System.out.println("Error changing scene");
                    }
                }
            }
        });







    }

    public void switchToScene1(ActionEvent event) throws IOException {

        System.out.println("fxml loaded");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/com/example/haha/hello-view.fxml"));
        Parent root = loader.load();
        System.out.println("pre slanja");
        TTTcontroller controller = loader.getController();
        System.out.println("poslao usera");
        controller.sceneSwitch(client);
        System.out.println("fxml loaded");
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

}

