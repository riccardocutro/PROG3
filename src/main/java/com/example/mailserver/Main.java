package com.example.mailserver;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

//Classe main per l'avvio del mail server
public class Main extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        // File FXML contenente la definizione dell'interfaccia grafica
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/mailserver/view/server_view.fxml"));

        //Creazione della scena che utilizza la vista caricata
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Mail Server");
        stage.setScene(scene); //Scena associata alla finestra principale
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
