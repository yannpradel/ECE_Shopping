package com.example.demo;

import com.example.demo.model.Compte;

public class SessionManager {
    private static Compte loggedInUser;

    public static void setLoggedInUser(Compte compte) {
        loggedInUser = compte;
    }

    public static Compte getLoggedInUser() {
        return loggedInUser;
    }

    public static void clearSession() {
        loggedInUser = null;
    }
}