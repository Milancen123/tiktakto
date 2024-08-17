/*

package com.example.demo2;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TTTcontroller implements Initializable {
    @FXML
    private Button gore_levo;
    @FXML
    private Button gore_desno;
    @FXML
    private Button gore_sredina;
    @FXML
    private Button dole_levo;
    @FXML
    private Button dole_desno;
    @FXML
    private Button dole_sredina;
    @FXML
    private Button sredina;
    @FXML
    private Button sredina_levo;
    @FXML
    private Button sredina_desno;
    @FXML
    private GridPane gridPane;
    @FXML
    private AnchorPane main;

    private ArrayList<Button> buttons;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<Button>(); //ovo sam morao da uradim, posto da niuje bilo toga bacao mi je NULLPTR exception stalno
        //jako me nerviraju exceptioni, znaci ne znas sta koji znaci

        buttons.add(gore_levo);buttons.add(gore_sredina);buttons.add(gore_desno);buttons.add(sredina_levo);buttons.add(sredina);buttons.add(sredina_desno);
        buttons.add(dole_levo);buttons.add(dole_sredina);buttons.add(dole_desno);
        for(Button button : buttons)
        {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    button.setText("X");
                    button.setDisable(true);
                }
            });
        }
    }
}
*/