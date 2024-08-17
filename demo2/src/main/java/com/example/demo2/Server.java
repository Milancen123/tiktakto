package com.example.demo2;

import javafx.scene.layout.VBox;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;


public class Server{

    private ServerSocket serverSocket;
    private Socket socket;


    public Server(ServerSocket serverSocket1)
    {
        try{
            this.serverSocket = serverSocket1;

        }catch (Exception e)
        {
            e.printStackTrace();
            System.out.println("Error sreating a server constructor");
        }
    }

    public void startServer()
    {
        try{
            while(!serverSocket.isClosed())
            {
                Socket socket = serverSocket.accept();
                System.out.println("Client has connected");
                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }

        } catch (IOException e)
        {
            e.printStackTrace();
            System.out.println("Error starting a server");
        }
    }

    public ServerSocket getServerSocket()
    {
        return this.serverSocket;
    }

    public Socket getSocket()
    {
        return  this.socket;
    }

    public void setSocket(Socket socket)
    {
        this.socket = socket;
    }







}
