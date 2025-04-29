package com.example.mailserver.controller;

import javafx.application.Platform;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.ServerSocket;

public class ServerController {
    @FXML
    private TextArea logArea;

    private static final int PORT = 8080;
    private boolean running = true;
    private ServerSocket serverSocket;

    @FXML
    public void initialize() {
        log("Server started. Waiting for connection to the port : " + PORT + "..." );

        Thread serverThread = new Thread(() -> {
            try (serverSocket = new ServerSocket(PORT)) {
                while (running) {
                    Socket clientSocket = serverSocket.accept();
                    log("New connection from " + clientSocket.getInetAddress());

                    new Thread(new com.example.mailserver.server.ClientHandler(clientSocket, this)).start();

                }
            } catch (IOException e){
                log("Server error " + e.getMessage());
            }
        });
        serverThread.setDaemon(true);
        serverThread.start();
    }
    private void log(String msg){
        Platform.runLater(() -> {logArea.appendText(message)});
    }
}
