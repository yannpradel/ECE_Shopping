package com.example.demo;

import com.example.demo.Controller.Controller;
import com.example.demo.View.View;
import com.example.demo.model.DatabaseModel;
import com.example.demo.model.Model;

public class Main{
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();

        DatabaseModel databasemodel= new DatabaseModel();


        Controller controller = new Controller(databasemodel,model, view);
        controller.run();



    }
}
