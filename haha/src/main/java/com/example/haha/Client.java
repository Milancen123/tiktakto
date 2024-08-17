package com.example.haha;

import javafx.event.EventHandler;

import java.io.*;
import java.net.*;

public class Client {

    private Socket socket;
    private BufferedReader bufferedReader;
    private BufferedWriter bufferedWriter;
    private String username;

    public Client(Socket socket)
    {
        try {
            this.socket = socket;
            this.bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            this.bufferedWriter = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            if (socket.isConnected())
                System.out.println("Connected to a server at port " + this.socket.getPort());


        }catch(IOException e)
        {
            e.printStackTrace();
            System.out.println("Error creating a client constructor");
        }
    }

    public void sendButtonClick(String move)
    {
        try{
            this.bufferedWriter.write(move);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }catch(IOException e)
        {e.printStackTrace();}
    }

    public void sendUsername(String username)
    {
        try {
            this.bufferedWriter.write(username);
            this.bufferedWriter.newLine();
            this.bufferedWriter.flush();
        }catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Error sending username to server");
        }
    }

    public String getMessage() throws IOException
    {
        return this.bufferedReader.readLine();
    }

    public Socket getSocket()
    {
        return this.socket;
    }

    public BufferedReader returnReader()
    {
        return this.bufferedReader;
    }

    public String getUsername()
    {
        return this.username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }

}
