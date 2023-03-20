package org.example;

import org.example.Controller.Controller;
import org.example.View.View;
import org.example.model.Model;

public class Main {
    public static void main(String[] args) {
        Model model = new Model();
        View view = new View();
        Controller controller = new Controller(model, view);
        controller.run();
    }
}
