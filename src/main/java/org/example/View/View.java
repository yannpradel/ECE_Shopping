package org.example.View;

import java.util.*;

public class View {
    private Scanner scanner;
    public View() {
        scanner = new Scanner(System.in);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public String getInput() {
        return scanner.nextLine();
    }
}
