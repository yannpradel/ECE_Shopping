package org.example.Controller;

import org.example.View.*;
import org.example.model.*;
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
        data.addNewCustomer(15,"John", "Doe", "john.doe@example.com", "mypassword123", 1000.0);
        data.addNewCustomer();
    }
}

