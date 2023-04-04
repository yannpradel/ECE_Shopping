package com.example.demo;

import com.example.demo.Controller.Controller;
import com.example.demo.View.View;
import com.example.demo.model.DatabaseModel;
import com.example.demo.model.Model;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application{
    @Override
    public void start(Stage stage) throws IOException {

        Model model = new Model();
        View view = new View();

        DatabaseModel databasemodel= new DatabaseModel();
        //Controller controller = new Controller(databasemodel,model, view);
        //databasemodel.run();

        System.out.println("dfsdf");
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1360, 768);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();


    }
    public static void main(String[] args) {
        launch();
    }
}
