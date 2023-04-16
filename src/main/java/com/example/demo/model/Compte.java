package com.example.demo.model;

import java.sql.*;

/**
 * Cette classe représente un compte d'utilisateur dans l'application.
 */

public class Compte {
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private double balance;
    private String address = "---";
    private int isAdmin;

    /**
     * Constructeur qui permet de créer un compte avec tous les détails.
     *
     * @param id l'identifiant du compte
     * @param firstName le prénom de l'utilisateur
     * @param lastName le nom de famille de l'utilisateur
     * @param email l'adresse e-mail de l'utilisateur
     * @param password le mot de passe du compte
     * @param balance le solde du compte
     * @param address l'adresse de l'utilisateur
     * @param isAdmin 1 si l'utilisateur est un administrateur, 0 sinon
     */
    public Compte(int id, String firstName, String lastName, String email, String password, double balance, String address, int isAdmin) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.balance = balance;
        this.address = address;
        this.isAdmin = isAdmin;

        if(this.address == null)
            this.address = "---";
    }
    /**
     * Constructeur qui permet de créer un compte en utilisant uniquement le nom d'utilisateur et le mot de passe.
     *
     * @param username le nom d'utilisateur du compte
     * @param password le mot de passe du compte
     */
    public Compte (String username, String password) {
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

        if(this.address == null)
            this.address = "---";
    }

    /**
     * Constructeur qui permet de créer un compte en utilisant uniquement le nom d'utilisateur, le mot de passe et le statut d'administrateur.
     *
     * @param username le nom d'utilisateur du compte
     * @param password le mot de passe du compte
     * @param admin 1 si l'utilisateur est un administrateur, 0 sinon
     */
    public Compte (String username, String password,int admin) {
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

    public String getAddress()

    {
        if(this.address == null)
            this.address = "---";
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
    

    public void setBalance(int balance) {
        this.balance=balance;
    }
}
