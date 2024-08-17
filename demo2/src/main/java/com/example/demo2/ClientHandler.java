package com.example.demo2;

import javafx.scene.layout.VBox;

import javax.swing.*;
import java.io.*;
import java.net.Socket;
import java.util.ArrayList;

public class ClientHandler implements Runnable{

    public static ArrayList<ClientHandler> clientHandlers = new ArrayList<>();
    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String clientUsername;
    private Connect connectDB;

    public ClientHandler(Socket socket)
    {
        try {
            Connect connectDB = new Connect();
            this.socket = socket;
            bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            this.clientUsername = bufferedReader.readLine();
            this.connectDB = connectDB;
            System.out.println(this.clientUsername);
            clientHandlers.add(this);
        }catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Error creating client handler object");
        }
    }

    @Override
    public void run() {
        String dugme;
        while(socket.isConnected())
        {
            try {
                dugme = bufferedReader.readLine();
                System.out.println(dugme);
               this.odigranPotez(dugme);
            } catch (Exception e)
            {
                e.printStackTrace();
                System.out.println("Error running a new thread in ClientHandler");
            }
        }

    }

    public void odigranPotez(String dugme)
    {
        System.out.println("odigran potez");
        for(int i=0;i<clientHandlers.size();i++)
            try {
                if (clientHandlers.get(i).clientUsername.equals(this.clientUsername)) {
                    if(i % 2 == 0) {

                        clientHandlers.get(i + 1).bufferedWriter.write(dugme);
                        clientHandlers.get(i + 1).bufferedWriter.newLine();
                        clientHandlers.get(i + 1).bufferedWriter.flush();
                        if(dugme.equals("kraj"))
                        {
                            connectDB.initDB(this.clientUsername, clientHandlers.get(i+1).clientUsername, 1);
                        }
                    }
                    else
                    {

                        clientHandlers.get(i - 1).bufferedWriter.write(dugme);
                        clientHandlers.get(i - 1).bufferedWriter.newLine();
                        clientHandlers.get(i - 1).bufferedWriter.flush();
                        if(dugme.equals("kraj"))
                        {
                            connectDB.initDB(this.clientUsername, clientHandlers.get(i-1).clientUsername, 0);
                        }
                    }
                }


            } catch (IOException e)
            {
                e.printStackTrace();
                System.out.println("Error sending an action");
            }
    }




}


