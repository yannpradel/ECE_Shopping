package com.example.demo.model;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
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
            //Class.forName(JDBC_DRIVER);
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

            System.out.println("Creating all table...");
            String createTableQuery = "CREATE TABLE comptes " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " first_name VARCHAR(50), " +
                    " last_name VARCHAR(50), " +
                    " email VARCHAR(50), " +
                    " password VARCHAR(50), " +
                    " balance DECIMAL(10,2), " +
                    " adress VARCHAR(100)," +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table comptes created successfully...");

            String createTable3Query = "CREATE TABLE produits " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " price DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable3Query);
            System.out.println("Table produits created successfully...");

            String createTable1Query = "CREATE TABLE employes " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " age INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable1Query);
            System.out.println("Table employes created successfully...");

            String createTable2Query = "CREATE TABLE livres " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " title VARCHAR(100), " +
                    " author VARCHAR(100), " +
                    " publisher VARCHAR(100), " +
                    " publication_date VARCHAR(12), " +
                    " isbn VARCHAR(20), " +
                    " price DECIMAL(10,2), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable2Query);
            System.out.println("Table livres created successfully...");

            String createTable4Query = "CREATE TABLE accessoires " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " price DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable4Query);
            System.out.println("Table accessoires created successfully...");

            String createTable6Query = "CREATE TABLE panier " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " user_id INT, " +
                    " product_id INT, " +
                    " quantity INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable6Query);
            System.out.println("Table panier created successfully...");


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

        System.out.print("Enter the client's adress: ");
        String adress = scanner.nextLine();

        // create a new client object with the input data
       addNewCustomer(firstName, lastName, email, password, balance, adress);

    }
    public void addNewCustomer(String firstName, String lastName, String email, String password, double balance, String adress) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {

            stmt = conn.createStatement();

             // Create SQL statement to insert new customer
             String insertCustomerQuery = "INSERT INTO comptes (first_name, last_name, email, password, balance, adress) " +
                     "VALUES ('" + firstName + "', '" + lastName + "', '" + email + "', '" + password + "', " + balance + ", '"+ adress + "'" +")";

             // Execute the statement
             stmt.executeUpdate(insertCustomerQuery);
             System.out.println("New customer added successfully...");

             // Close the statement object
             stmt.close();
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }


    public void addProduct() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter product name:");
        String name = scanner.nextLine();
        System.out.println("Enter product description:");
        String description = scanner.nextLine();
        System.out.println("Enter product price:");
        double price = scanner.nextDouble();
        System.out.println("Enter product stock quantity:");
        int stockQuantity = scanner.nextInt();

        addProduct(name,description,price,stockQuantity);
    }
    public void addProduct(String name, String description, double price, int stockQuantity) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();

            // Create SQL statement to insert new product
            String insertProductQuery = "INSERT INTO produits (name, description, price, stock_quantity) " +
                    "VALUES (?, ?, ?, ?)";

            // Prepare statement with parameters
            PreparedStatement pstmt = conn.prepareStatement(insertProductQuery);
            pstmt.setString(1, name);
            pstmt.setString(2, description);
            pstmt.setDouble(3, price);
            pstmt.setInt(4, stockQuantity);

            // Execute statement
            int rowsAffected = pstmt.executeUpdate();

            // Check if insertion was successful
            if (rowsAffected > 0) {
                System.out.println("Product added successfully.");
            } else {
                System.out.println("Failed to add product.");
            }

            // Close the statement object
            stmt.close();
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }

    public void addEmployes() {
        Scanner scanner= new Scanner(System.in);

        System.out.println("Enter employee name:");
        String name = scanner.nextLine();

        System.out.println("Enter employee age:");
        int age = scanner.nextInt();

        addEmployes(name, age);

    }
    public void addEmployes(String name, int age) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            // Create SQL statement to insert new employee
            String insertEmployeeQuery = "INSERT INTO employes (name, age) " +
                    "VALUES ('" + name + "', " + age + ")";

            // Execute the SQL statement
            stmt.executeUpdate(insertEmployeeQuery);
            System.out.println("Employee added successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    public void addLivres(){
        Scanner scanner = new Scanner(System.in);

    // Demander les informations du livre à l'utilisateur
        System.out.print("Titre du livre: ");
        String title = scanner.nextLine();

        System.out.print("Auteur du livre: ");
        String author = scanner.nextLine();

        System.out.print("Editeur du livre: ");
        String publisher = scanner.nextLine();

        System.out.print("Date de publication (yyyy-mm-dd): ");
        String publicationDate= scanner.nextLine();

        System.out.print("ISBN du livre: ");
        String isbn = scanner.nextLine();

        System.out.print("Prix du livre: ");
        double price = scanner.nextDouble();

        System.out.print("Quantité en stock: ");
        int stockQuantity = scanner.nextInt();

        // Ajouter le livre à la base de données
        addLivres(title, author, publisher, publicationDate, isbn, price);
    }
    public void addLivres(String title, String author, String publisher, String publicationDate, String isbn, double price) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            // Create SQL statement to insert new book
            String insertBookQuery = "INSERT INTO livres (title, author, publisher, publication_date, isbn, price) " +
                    "VALUES ('" + title + "', '" + author + "', '" + publisher + "', '" + publicationDate + "', '" + isbn + "', " + price + ")";

            // Execute the SQL statement
            stmt.executeUpdate(insertBookQuery);
            System.out.println("Book added successfully...");
        } catch (SQLException se) {
            se.printStackTrace();
        }
    }


    public void addAccessoires() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter accessory name: ");
        String name = sc.nextLine();
        System.out.println("Enter accessory description: ");
        String description = sc.nextLine();
        System.out.println("Enter accessory price: ");
        double price = sc.nextDouble();
        System.out.println("Enter Quantity: ");
        int quantity=sc.nextInt();

        addAccessoires(name, description, price, quantity);
    }
    public void addAccessoires(String name, String description, double price, int quantity) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            // Create SQL statement to insert new accessory
            String insertAccessoryQuery = "INSERT INTO accessoires (name, description, price, stock_quantity) " +
                    "VALUES ('" + name + "', '" +description+"', "+ price + ", " + quantity + ")";

            // Execute the query
            stmt.executeUpdate(insertAccessoryQuery);

            System.out.println("L'accessoire a été ajouté avec succès.");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'accessoire : " + e.getMessage());
        }
    }

    public void addPanier() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Veuillez saisir le nom du produit :");
        String name = scanner.nextLine();

        System.out.println("Veuillez saisir la quantité :");
        int quantity = scanner.nextInt();

    }
    public void addPanier(int customerId, int productId, int quantity) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            // Create SQL statement to insert new cart item
            String insertCartQuery = "INSERT INTO panier (user_id, product_id, quantity) " +
                    "VALUES (" + customerId + ", " + productId + ", " + quantity + ")";

            stmt.executeUpdate(insertCartQuery);

            System.out.println("Le produit a été ajouté au panier avec succès.");
            stmt.close();
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout du produit au panier : " + e.getMessage());
        }
    }


    public void addSomething(String nomTab){
        System.out.println("----------addSomething-------------");
        // Récupération du nom du tableau
        Scanner sc = new Scanner(System.in);

        // Récupération des noms de colonnes du tableau
        ResultSet rs = null;
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            rs = conn.getMetaData().getColumns(null, null, nomTab, null);
            String colonnes = "";
            String valeurs = "";
            while (rs.next()) {
                String nomColonne = rs.getString("COLUMN_NAME");
                String typeColonne = rs.getString("TYPE_NAME");
                System.out.print("Entrez la valeur pour la colonne " + nomColonne + " de type " + typeColonne + " : ");
                String valeurColonne = sc.nextLine();
                colonnes += nomColonne + ",";
                valeurs += "'" + valeurColonne + "',";
            }
            // suppression de la dernière virgule
            colonnes = colonnes.substring(0, colonnes.length()-1);
            valeurs = valeurs.substring(0, valeurs.length()-1);

            // Ajout de la valeur dans le tableau
            String query = "INSERT INTO " + nomTab + " (" + colonnes + ") VALUES (" + valeurs + ")";
            PreparedStatement ps = conn.prepareStatement(query);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }



    public void afficherTableau(){
        Scanner saisie=new Scanner(System.in);

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SHOW TABLES");
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nom de tab:");
        String nomTable= saisie.nextLine();

        //addSomething(nomTable);

        //descriptiontab(nomTable,1);
        descriptiontab(nomTable,0,0);
        descriptiontab(nomTable,0,1);
        descriptiontab(nomTable,1,0);
        descriptiontab(nomTable,1,1);
        //descriptiontab(nomTable,0);
    }

    public void descriptiontab(String nomTab, int avecrecherche) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query;
            // Création de la requête SQL pour récupérer toutes les données du tableau "compte"
            if (avecrecherche==1) {
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrez le nom à rechercher : ");
                String nomRecherche = scanner.nextLine();

                DatabaseMetaData metadata = conn.getMetaData();
                ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
                List<String> columnNames = new ArrayList<>();
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    columnNames.add(columnName);
                }
                rsColumns.close();

                // construction dynamique de la clause WHERE
                StringBuilder whereClause = new StringBuilder();
                for (String columnName : columnNames) {
                    whereClause.append(columnName).append(" LIKE '%").append(nomRecherche).append("%' OR ");
                }
                // suppression de la dernière occurrence de "OR" dans la clause WHERE
                whereClause.delete(whereClause.length() - 4, whereClause.length());

                // construction de la requête complète
                query = "SELECT * FROM " + nomTab + " WHERE " + whereClause.toString();

                // Création de la requête SQL pour récupérer les produits avec le nom saisi
                //query = "SELECT * FROM "+nomTab+" WHERE * LIKE '%" + nomRecherche + "%'";

            }else {
                query = "SELECT * FROM "+nomTab;
            }


            // Exécution de la requête et récupération du résultat
            ResultSet rs = stmt.executeQuery(query);


            // Boucle pour parcourir le résultat et afficher les données sur la console
            while (rs.next()) {
                switch(nomTab){
                     case "comptes":
                         System.out.println("ID: " + rs.getInt("id"));
                         System.out.println("Nom: " + rs.getString("last_name"));
                         System.out.println("Prénom: " + rs.getString("first_name"));
                         System.out.println("Email: " + rs.getString("email"));
                         System.out.println("Mot de passe: " + rs.getString("password"));
                         System.out.println("Solde: " + rs.getDouble("balance"));
                         System.out.println("Adresse: " + rs.getString("adress"));
                         System.out.println("--------------------------");
                    break;

                    case "livres":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Titre: " + rs.getString("title"));
                        System.out.println("Auteur: " + rs.getString("author"));
                        System.out.println("Editeur: "+ rs.getString("publisher"));
                        System.out.println("Date de Parrution: " + rs.getString("publication_date"));
                        System.out.println("ISBN: " + rs.getDouble("isbn"));
                        System.out.println("Prix: " + rs.getDouble("price"));
                        System.out.println("--------------------------");
                        break;

                    case "employes":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom et Prenom: " + rs.getString("name"));
                        System.out.println("Age: " + rs.getString("age"));
                        break;
                    case "produits":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom: " + rs.getString("name"));
                        System.out.println("Description: " + rs.getString("description"));
                        System.out.println("Prix: " + rs.getDouble("price"));
                        System.out.println("Stock: " + rs.getDouble("stock_quantity"));
                        break;

                    case "accessoires":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom: " + rs.getString("name"));
                        System.out.println("Description: " + rs.getString("description"));
                        System.out.println("Prix: " + rs.getDouble("price"));
                        System.out.println("Stock: " + rs.getDouble("stock_quantity"));
                        break;

                    case "panier":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("User_id: " + rs.getString("user_id"));
                        System.out.println("Product_id: " + rs.getString("product_id"));
                        System.out.println("Stock: " + rs.getDouble("quantity"));
                        break;
                default:
                    break;
                }
            }

            // Fermeture des ressources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void descriptiontab(String nomTab, int avecrecherche, int trie) {

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query;
            // Création de la requête SQL pour récupérer toutes les données du tableau "compte"
            if (avecrecherche==1&&trie==1) {
                System.out.println("-----------AVEC RECHERCHE ET AVEC TRIE-------------");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrez le nom à rechercher : ");
                String nomRecherche = scanner.nextLine();

                DatabaseMetaData metadata = conn.getMetaData();
                ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
                List<String> columnNames = new ArrayList<>();
                System.out.println("Colonnes de la table " + nomTab + " : ");
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    columnNames.add(columnName);
                    System.out.println(columnName);
                }
                rsColumns.close();

                // construction dynamique de la clause WHERE
                StringBuilder whereClause = new StringBuilder();
                for (String columnName : columnNames) {
                    whereClause.append(columnName).append(" LIKE '%").append(nomRecherche).append("%' OR ");
                }
                // suppression de la dernière occurrence de "OR" dans la clause WHERE
                whereClause.delete(whereClause.length() - 4, whereClause.length());



                // construction de la requête complète
                query = "SELECT * FROM " + nomTab + " WHERE " + whereClause.toString();


                Scanner col= new Scanner(System.in);
                System.out.println("Quel colonne?");
                String colonne=col.nextLine();

                System.out.println("Quel ordre?");
                System.out.println("1:DESC");
                System.out.println("2: ASC");
                int orderchoix=col.nextInt();
                String ordre;
                if (orderchoix==1){
                    ordre="DESC";
                }else{
                    ordre="ASC";
                }
                // Ajout de l'ordre de tri
                query += " ORDER BY " + colonne + " " + ordre;


            } else if (avecrecherche==1) {
                System.out.println("-----------AVEC RECHERCHE ET SANS TRIE-------------");
                Scanner scanner = new Scanner(System.in);
                System.out.print("Entrez le nom à rechercher : ");
                String nomRecherche = scanner.nextLine();

                DatabaseMetaData metadata = conn.getMetaData();
                ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
                List<String> columnNames = new ArrayList<>();
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    columnNames.add(columnName);
                }
                rsColumns.close();

                // construction dynamique de la clause WHERE
                StringBuilder whereClause = new StringBuilder();
                for (String columnName : columnNames) {
                    whereClause.append(columnName).append(" LIKE '%").append(nomRecherche).append("%' OR ");
                }
                // suppression de la dernière occurrence de "OR" dans la clause WHERE
                whereClause.delete(whereClause.length() - 4, whereClause.length());

                // construction de la requête complète
                query = "SELECT * FROM " + nomTab + " WHERE " + whereClause.toString();


            } else if(avecrecherche==0&&trie==1) {
                System.out.println("-----------SANS RECHERCHE ET AVEC TRIE-------------");

                DatabaseMetaData metadata = conn.getMetaData();
                ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
                List<String> columnNames = new ArrayList<>();
                System.out.println("Colonnes de la table " + nomTab + " : ");
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    columnNames.add(columnName);
                    System.out.println(columnName);
                }
                rsColumns.close();

                Scanner col= new Scanner(System.in);
                System.out.println("Quel colonne?");
                String colonne=col.nextLine();

                System.out.println("Quel ordre?");
                System.out.println("1:DESC");
                System.out.println("2: ASC");
                int orderchoix=col.nextInt();
                String ordre;
                if (orderchoix==1){
                    ordre="DESC";
                }else{
                    ordre="ASC";
                }
                query = "SELECT * FROM "+nomTab;
                // Ajout de l'ordre de tri
                query += " ORDER BY " + colonne + " " + ordre;

            }
            else {
                System.out.println("-----------SANS RECHERCHE ET SANS TRIE-------------");
                query = "SELECT * FROM "+nomTab;
            }



            // Exécution de la requête et récupération du résultat
            ResultSet rs = stmt.executeQuery(query);


            // Boucle pour parcourir le résultat et afficher les données sur la console
            while (rs.next()) {
                switch(nomTab){
                    case "comptes":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom: " + rs.getString("last_name"));
                        System.out.println("Prénom: " + rs.getString("first_name"));
                        System.out.println("Email: " + rs.getString("email"));
                        System.out.println("Mot de passe: " + rs.getString("password"));
                        System.out.println("Solde: " + rs.getDouble("balance"));
                        System.out.println("Adresse: " + rs.getString("adress"));
                        System.out.println("--------------------------");
                        break;

                    case "livres":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Titre: " + rs.getString("title"));
                        System.out.println("Auteur: " + rs.getString("author"));
                        System.out.println("Editeur: "+ rs.getString("publisher"));
                        System.out.println("Date de Parrution: " + rs.getString("publication_date"));
                        System.out.println("ISBN: " + rs.getDouble("isbn"));
                        System.out.println("Prix: " + rs.getDouble("price"));
                        System.out.println("--------------------------");
                        break;

                    case "employes":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom et Prenom: " + rs.getString("name"));
                        System.out.println("Age: " + rs.getString("age"));
                        break;
                    case "produits":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom: " + rs.getString("name"));
                        System.out.println("Description: " + rs.getString("description"));
                        System.out.println("Prix: " + rs.getDouble("price"));
                        System.out.println("Stock: " + rs.getDouble("stock_quantity"));
                        break;

                    case "accessoires":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("Nom: " + rs.getString("name"));
                        System.out.println("Description: " + rs.getString("description"));
                        System.out.println("Prix: " + rs.getDouble("price"));
                        System.out.println("Stock: " + rs.getDouble("stock_quantity"));
                        break;

                    case "panier":
                        System.out.println("ID: " + rs.getInt("id"));
                        System.out.println("User_id: " + rs.getString("user_id"));
                        System.out.println("Product_id: " + rs.getString("product_id"));
                        System.out.println("Stock: " + rs.getDouble("quantity"));
                        break;
                    default:
                        break;
                }
            }

            // Fermeture des ressources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void supprimeligne(){
        System.out.println("-----------------SUPPRESSION LIGNE---------------");
        Scanner saisie=new Scanner(System.in);

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SHOW TABLES");
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nom de tab:");
        String nomTable= saisie.nextLine();
        supprimeLigne(nomTable);
    }
    public void supprimeLigne(String nomTable) {
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez le nom à rechercher : ");
            String nomRecherche = scanner.nextLine();

            DatabaseMetaData metadata = conn.getMetaData();
            ResultSet rsColumns = metadata.getColumns(null, null, nomTable, null);
            List<String> columnNames = new ArrayList<>();
            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                columnNames.add(columnName);
            }
            rsColumns.close();

            // construction dynamique de la clause WHERE
            StringBuilder whereClause = new StringBuilder();
            for (String columnName : columnNames) {
                whereClause.append(columnName).append(" LIKE '%").append(nomRecherche).append("%' OR ");
            }
            // suppression de la dernière occurrence de "OR" dans la clause WHERE
            whereClause.delete(whereClause.length() - 4, whereClause.length());

            // construction de la requête complète
            String query = "DELETE FROM " + nomTable + " WHERE " + whereClause.toString();

            // Exécution de la requête et récupération du résultat
            PreparedStatement ps = conn.prepareStatement(query);
            int rowsDeleted = ps.executeUpdate();

            // Affichage du résultat
            System.out.println(rowsDeleted + " ligne(s) ont été supprimées.");

            // Fermeture des ressources
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void metAJourLigne(){
        System.out.println("-----------------MAJ LIGNE---------------");
        Scanner saisie=new Scanner(System.in);

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            ResultSet resultSet = stmt.executeQuery("SHOW TABLES");
            while(resultSet.next()) {
                System.out.println(resultSet.getString(1));
            }
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("Nom de tab:");
        String nomTable= saisie.nextLine();
        metAJourLigne(nomTable);
    }
    public void metAJourLigne(String nomTable) {
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Entrez le nom à rechercher : ");
            String nomRecherche = scanner.nextLine();

            DatabaseMetaData metadata = conn.getMetaData();
            ResultSet rsColumns = metadata.getColumns(null, null, nomTable, null);
            List<String> columnNames = new ArrayList<>();
            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                columnNames.add(columnName);
            }
            rsColumns.close();

            // construction dynamique de la clause WHERE
            StringBuilder whereClause = new StringBuilder();
            for (String columnName : columnNames) {
                whereClause.append(columnName).append(" LIKE '%").append(nomRecherche).append("%' OR ");
            }
            // suppression de la dernière occurrence de "OR" dans la clause WHERE
            whereClause.delete(whereClause.length() - 4, whereClause.length());

            // construction de la requête complète pour récupérer la ligne à mettre à jour
            String querySelect = "SELECT * FROM " + nomTable + " WHERE " + whereClause.toString();

            // exécution de la requête SELECT pour récupérer la ligne à mettre à jour
            PreparedStatement psSelect = conn.prepareStatement(querySelect);
            ResultSet rs = psSelect.executeQuery();

            // récupération des informations de la ligne
            ResultSetMetaData rsmd = rs.getMetaData();
            int numColumns = rsmd.getColumnCount();
            rs.next();

            // construction de la requête UPDATE pour mettre à jour les informations de la ligne
            StringBuilder updateClause = new StringBuilder();
            for (int i = 1; i <= numColumns; i++) {
                String columnName = rsmd.getColumnName(i);
                System.out.print("Entrez la nouvelle valeur pour " + columnName + " : ");
                String nouvelleValeur = scanner.nextLine();
                updateClause.append(columnName).append("='").append(nouvelleValeur).append("', ");
            }
            // suppression de la dernière occurrence de la virgule dans la clause UPDATE
            updateClause.delete(updateClause.length() - 2, updateClause.length());

            String queryUpdate = "UPDATE " + nomTable + " SET " + updateClause.toString() + " WHERE " + whereClause.toString();

            // exécution de la requête UPDATE pour mettre à jour les informations de la ligne
            PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
            int rowsUpdated = psUpdate.executeUpdate();

            // affichage du résultat
            System.out.println(rowsUpdated + " ligne(s) ont été mises à jour.");

            // fermeture des ressources
            rs.close();
            psSelect.close();
            psUpdate.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
