package com.example.demo.model;

public class Compte {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double balance;
    private String address;
    private int isAdmin;

    public Compte(int id, String firstName, String lastName, String email, String password, double balance, String address, int isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.address = address;
        this.isAdmin = isAdmin;
    }

    public Compte (String username, String password)
    {
        this.firstName=username;
        this.password=password;

        //la ici code taemrei icic az ckjsdfl sdn,abzduqshd qshmzapdqs hj 
    }

    public Compte (String username, String password,int admin)
    {
        this.firstName=username;
        this.password=password;
        this.isAdmin=admin;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }

    public int getIsAdmin() {
        return isAdmin;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassword() {
        return password;
    }
}
