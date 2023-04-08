package com.example.demo.model;

import java.sql.*;

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

        try (Connection conn= DriverManager.getConnection("jdbc:mysql://localhost/projetECE_Booking","root", "")) {
            Statement stmtlia = conn.createStatement();
            String querylia = "SELECT * FROM comptes WHERE first_name='"+username+"'";
            ResultSet rslia = stmtlia.executeQuery(querylia);
            while (rslia.next()) {
                this.id = rslia.getInt("id");
                this.lastName = rslia.getString("last_name");
                this.email = rslia.getString("email");
                this.balance = rslia.getInt("balance");
                this.address = rslia.getString("adress");
                this.isAdmin = rslia.getInt("admin");
            }
        }catch (SQLException e) {
            e.printStackTrace();
        }
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
