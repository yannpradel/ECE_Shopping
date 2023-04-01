package org.example.model;

import java.sql.*;
import java.util.Scanner;

public class DatabaseModel {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String DATABASE_NAME = "mydatabase";

    private Connection conn = null;
    private Statement stmt = null;

    public DatabaseModel() {
        try {
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conn.createStatement();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void createDatabase() {
        try {
            // Execute a query to check if database exists
            String checkDatabaseQuery = "SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME = '" + DATABASE_NAME + "'";
            ResultSet rs = stmt.executeQuery(checkDatabaseQuery);
            if (rs.next()) {
                System.out.println("Database already exists...");
                return;
            }

            // Execute a query to create database
            System.out.println("Creating database...");
            String createDatabaseQuery = "CREATE DATABASE " + DATABASE_NAME;
            stmt.executeUpdate(createDatabaseQuery);
            System.out.println("Database created successfully...");

            // Reconnect to the newly created database
            conn.close();
            conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS);
            stmt = conn.createStatement();

            String createTableQuery = "CREATE TABLE customer_accounts " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " first_name VARCHAR(50), " +
                    " last_name VARCHAR(50), " +
                    " email VARCHAR(50), " +
                    " password VARCHAR(50), " +
                    " balance DECIMAL(10,2), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table created successfully...");

            String createTable3Query = "CREATE TABLE products " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " price DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable3Query);
            System.out.println("Table 3 created successfully...");


            // Execute a query to create table 1
            System.out.println("Creating table 1...");
            String createTable1Query = "CREATE TABLE employees " +
                    "(id INT not NULL, " +
                    " name VARCHAR(255), " +
                    " age INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable1Query);
            System.out.println("Table 1 created successfully...");

            // Execute a query to create table 2
            String createTable2Query = "CREATE TABLE books " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " title VARCHAR(100), " +
                    " author VARCHAR(100), " +
                    " publisher VARCHAR(100), " +
                    " publication_date DATE, " +
                    " isbn VARCHAR(20), " +
                    " price DECIMAL(10,2), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable2Query);
            System.out.println("Table 2 created successfully...");

            String createTable4Query = "CREATE TABLE accessories " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " price DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable4Query);
            System.out.println("Table 4 created successfully...");

            String createTable6Query = "CREATE TABLE cart " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " user_id INT, " +
                    " product_id INT, " +
                    " quantity INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable6Query);
            System.out.println("Table 6 created successfully...");


        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(stmt!=null) stmt.close();
            } catch(SQLException se2) {
            }
            try {
                if(conn!=null) conn.close();
            } catch(SQLException se) {
                se.printStackTrace();
            }
        }
    }


    public void addNewCustomer() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the client's id: ");
        int id = scanner.nextInt();

        System.out.print("Enter the client's first name: ");
        String firstName = scanner.nextLine();

        System.out.print("Enter the client's last name: ");
        String lastName = scanner.nextLine();

        System.out.print("Enter the client's email address: ");
        String email = scanner.nextLine();

        System.out.print("Enter the client's password: ");
        String password = scanner.nextLine();

        System.out.print("Enter the client's balance: ");
        double balance = scanner.nextDouble();

        // create a new client object with the input data
       addNewCustomer(id, firstName, lastName, email, password, balance);

    }




    public void addNewCustomer(int id, String firstName, String lastName, String email, String password, double balance) {
        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/mydatabase", "root","")) {
                // Votre code ici
            stmt = conn.createStatement();

             // Create SQL statement to insert new customer
             String insertCustomerQuery = "INSERT INTO customer_accounts (id,first_name, last_name, email, password, balance) " +
                     "VALUES ('" + id + "','" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', " + balance + ")";

             // Execute the statement
             stmt.executeUpdate(insertCustomerQuery);
             System.out.println("New customer added successfully...");

             // Close the statement object
             stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

}
