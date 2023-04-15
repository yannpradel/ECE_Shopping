package com.example.demo;


import com.example.demo.View.View;
import com.example.demo.model.DatabaseModel;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws IOException {

        View view = new View();

        DatabaseModel database = new DatabaseModel();
        //Controller controller = new Controller(databasemodel, view); //il sert a r
        //database.run();



        database.createDatabase();


        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("connexionPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 356);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }
}
