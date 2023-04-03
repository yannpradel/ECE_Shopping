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
        data.addNewCustomer("John", "Doe", "john.doe@example.com", "mypassword123", 1000.0,"dans ton cul");
        data.addProduct("Ordinateur portable", "Intel Core i7, 16 Go RAM, SSD 512 Go, écran 15 pouces", 1499.99, 10);
        data.addEmployes("John Doe", 25);
        data.addLivres("Le Petit Prince", "Antoine de Saint-Exupéry", "Gallimard", "2001-01-25", "9782070612758", 9.99);
        data.addAccessoires("Souris sans fil", "Logitech", 29.99, 50);
        data.addPanier(12,1,3);

        data.afficherTableau();
        data.metAJourLigne();
        //data.supprimeligne();

        //Averif
        data.addNewCustomer();
        data.addProduct();
        data.addEmployes();
        data.addLivres();
        data.addAccessoires();
        data.addPanier();
    }
}

