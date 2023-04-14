package com.example.demo;

import com.example.demo.model.Compte;

public class SessionManager {
    private static Compte loggedInUser;
    private static Compte previousLoggedInUser;

    public static void setLoggedInUser(Compte compte) {
        loggedInUser = compte;
    }

    public static Compte getLoggedInUser() {
        return loggedInUser;
    }

    public static void setPreviousLoggedInUser(Compte previousLoggedInUser) {
        SessionManager.previousLoggedInUser = previousLoggedInUser;
    }

    public static Compte getPreviousLoggedInUser() {
        return previousLoggedInUser;
    }

    public static void clearSession() {
        previousLoggedInUser=loggedInUser;
        loggedInUser = null;
    }
}