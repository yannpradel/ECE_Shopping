package org.example.Controller;

import org.example.View.*;
import org.example.model.*;

public class Controller {
    private Model model;
    private View view;

    public Controller(Model model, View view) {
        this.model = model;
        this.view = view;
    }

    public void run() {
        view.displayMessage(model.getMessage());
        view.displayMessage("Please enter a new message:");
        String newMessage = view.getInput();
        model.setMessage(newMessage);
        view.displayMessage(model.getMessage());
    }
}

