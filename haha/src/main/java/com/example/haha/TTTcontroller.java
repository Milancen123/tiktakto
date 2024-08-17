package com.example.haha;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Label;
import javafx.application.Platform;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class TTTcontroller implements Initializable
{
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
    @FXML
    private Label text;

    private boolean pobeda;

    private ArrayList<Button> buttons;
    private ArrayList<Button> clickedButtons;
    private Client client;

    public void sceneSwitch(Client client)
    {
        System.out.println("dobio usera");
        this.client=client;
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        buttons = new ArrayList<Button>(); //ovo sam morao da uradim, posto da niuje bilo toga bacao mi je NULLPTR exception stalno
        //jako me nerviraju exceptioni, znaci ne znas sta koji znaci
        clickedButtons = new ArrayList<Button>();
        buttons.add(gore_levo);
        buttons.add(gore_sredina);
        buttons.add(gore_desno);
        buttons.add(sredina_levo);
        buttons.add(sredina);
        buttons.add(sredina_desno);
        buttons.add(dole_levo);
        buttons.add(dole_sredina);
        buttons.add(dole_desno);
        pobeda = false;


        for (Button button : buttons) {
            button.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent actionEvent) {
                    button.setText("X");
                    client.sendButtonClick(button.getId());
                    button.setDisable(true);
                    clickedButtons.add(button);
                    for(Button button : buttons)
                    {
                        if(!(clickedButtons.contains(button)))
                        {
                            button.setDisable(true);
                        }
                    }
                    if(checkWinCondition() && !pobeda)
                    {
                        pobeda = true;
                        System.out.println("Pobeda");
                        Platform.runLater(()->{

                            text.setText("POBEDA");
                        });
                        client.sendButtonClick("kraj");
                    }
                }
            });

        }

        getMessages();

    }


    public void setButtonClicked(Button button)
    {
        Platform.runLater(()->
                {
                            button.setText("O");
                            button.setDisable(true);
                }
                );
    }

    public void getMessages()
    {
        new Thread(new Runnable() {
            String message;

            @Override
            public void run() {
                try {
                    text.setText(client.getUsername());
                    System.out.println("ovde");
                    while((message = client.getMessage()) != null && !pobeda) {
                        System.out.println(message);
                        for (Button button : buttons) {
                            if (button.getId().equals(message)) {
                                clickedButtons.add(button);
                                setButtonClicked(button);
                                for(Button button1 : buttons)
                                {
                                    if(!(clickedButtons.contains(button1)))
                                    {
                                        button1.setDisable(false);

                                    }

                                }
                            }
                            else if(message.equals("kraj"))
                            {

                                System.out.println("Poraz");
                                Platform.runLater(()->{

                                    text.setText("PORAZ");
                                });
                                for (Button button1 : buttons) {
                                    if (!(clickedButtons.contains(button1))) {
                                        button1.setDisable(true);
                                    }
                                }
                                return;
                            }
                        }
                    }
                }catch(IOException e)
                {e.printStackTrace();}
            }
        }).start();
    }

    private boolean checkWinCondition() {

        if (gore_levo.getText().equals(gore_sredina.getText()) && gore_sredina.getText().equals(gore_desno.getText()) && clickedButtons.contains(gore_desno) && clickedButtons.contains(gore_sredina) && clickedButtons.contains(gore_levo)) {
            return true;
        }
        if (sredina_levo.getText().equals(sredina.getText()) && sredina.getText().equals(sredina_desno.getText()) && clickedButtons.contains(sredina) && clickedButtons.contains(sredina_levo) && clickedButtons.contains(sredina_desno)) {
            return true;
        }
        if (dole_levo.getText().equals(dole_sredina.getText()) && dole_sredina.getText().equals(dole_desno.getText())  && clickedButtons.contains(dole_desno) && clickedButtons.contains(dole_levo) && clickedButtons.contains(dole_sredina)) {
            return true;
        }
        if (gore_levo.getText().equals(sredina_levo.getText()) && sredina_levo.getText().equals(dole_levo.getText()) && clickedButtons.contains(dole_levo) && clickedButtons.contains(sredina_levo) && clickedButtons.contains(gore_levo)) {
            return true;
        }
        if (gore_sredina.getText().equals(sredina.getText()) && sredina.getText().equals(dole_sredina.getText()) && clickedButtons.contains(gore_sredina) && clickedButtons.contains(sredina) && clickedButtons.contains(dole_sredina)) {
            return true;
        }
        if (gore_desno.getText().equals(sredina_desno.getText()) && sredina_desno.getText().equals(dole_desno.getText()) && clickedButtons.contains(gore_desno) && clickedButtons.contains(sredina_desno) && clickedButtons.contains(dole_desno)) {
            return true;
        }

        if(gore_levo.getText().equals(sredina.getText()) && sredina.getText().equals(dole_desno.getText()) && clickedButtons.contains(gore_levo) && clickedButtons.contains(sredina) && clickedButtons.contains(dole_desno))
            return true;

        if(gore_desno.getText().equals(sredina.getText()) && sredina.getText().equals(dole_levo.getText()) && clickedButtons.contains(gore_desno) && clickedButtons.contains(sredina) && clickedButtons.contains(dole_levo))
            return true;


        else
            return false;

    }


}
