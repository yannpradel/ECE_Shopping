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
}

