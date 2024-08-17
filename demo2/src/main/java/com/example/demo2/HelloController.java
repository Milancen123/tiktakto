package com.example.demo2;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {





    @FXML
    private AnchorPane main;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private TextField textField;
    @FXML
    private VBox vBox;

    private Server server;
    private ClientHandler clientHandler;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            server = new Server(new ServerSocket(1234));
            server.startServer();

        } catch (IOException e)
        {e.printStackTrace(); System.out.println("Error creating a server");}
        
    }

    public static void addLabel(String username,VBox vBox)
    {
        HBox hBox = new HBox();
        Text text = new Text(username+"  is connected");
        TextFlow textFlow = new TextFlow(text);

        textFlow.setStyle("-fx-color: rgb(239,242,255);"+
                "-fx-background-color: rgb(15,125,242);");

        textFlow.setPadding(new Insets(5, 10, 5, 10));
        text.setFill(Color.color(0.934, 0.945, 0.996));

        hBox.getChildren().add(textFlow);

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                vBox.getChildren().add(hBox);
            }
        });

    }


}