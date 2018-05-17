package com.controladores;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        Login login = new Login(primaryStage);
        Scene scene = new Scene(login,707, 481);

        primaryStage.setResizable(false);
        primaryStage.setTitle("Chino Jr");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}

