package com.example.demo.Controller;

import com.example.demo.View.*;
import com.example.demo.model.*;
public class Controller {

    private DatabaseModel data;
    private View view;


    public Controller(DatabaseModel data, View view) {
        this.view = view;
        this.data = data;
    }

}

