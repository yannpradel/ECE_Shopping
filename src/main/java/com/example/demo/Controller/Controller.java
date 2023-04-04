package com.example.demo.Controller;

import com.example.demo.View.*;
import com.example.demo.model.*;
public class Controller {
    private Model model;
    private DatabaseModel data;
    private View view;


    public Controller(DatabaseModel data,Model model, View view) {
        this.model = model;
        this.view = view;
        this.data = data;
    }

    public void run() {
        view.displayMessage(model.getMessage());
        view.displayMessage("Please enter a new message:");
        String newMessage = view.getInput();
        model.setMessage(newMessage);
        view.displayMessage(model.getMessage());
        data.createDatabase();


        data.afficherTableau();
        data.supprimeligne();
        data.metAJourLigne();

        //data.addSomething("livres");


    }


}

