package com.example.demo.model;
import java.lang.Exception;
import java.math.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;

public class DatabaseModel {
    private static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private static final String DB_URL = "jdbc:mysql://localhost/";

    // Database credentials
    private static final String USER = "root";
    private static final String PASS = "";

    private static final String DATABASE_NAME = "projetECE_Booking";

    private Connection conn = null;
    private Statement stmt = null;

    private String identifiantS=null;
    private String motdepasseS=null;

    private int adminS=666;

    public int getAdminS() {
        return adminS;
    }

    public void setAdminS(int adminS) {
        this.adminS = adminS;
    }

    public String getIdentifiantS() {
        return identifiantS;
    }

    public String getMotdepasseS() {
        return motdepasseS;
    }



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
                    " admin INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTableQuery);
            System.out.println("Table comptes created successfully...");

            String createTable3Query = "CREATE TABLE produits " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " en_reduction INT, "+
                    " price DECIMAL(10,2), " +
                    " price_reduc DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " venduTotal INT, " +
                    " vendu_reduc INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable3Query);
            System.out.println("Table produits created successfully...");

            /*String createTable1Query = "CREATE TABLE employes " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " age INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable1Query);
            System.out.println("Table employes created successfully...");*/

            String createTable2Query = "CREATE TABLE livres " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " title VARCHAR(100), " +
                    " author VARCHAR(100), " +
                    " publisher VARCHAR(100), " +
                    " publication_date VARCHAR(12), " +
                    " isbn VARCHAR(20), " +
                    " en_reduction INT, "+
                    " price DECIMAL(10,2), " +
                    " price_reduc DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " venduTotal INT, " +
                    " vendu_reduc INT, " +
                    " image VARCHAR(1000), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable2Query);
            System.out.println("Table livres created successfully...");

            String createTable4Query = "CREATE TABLE accessoires " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " en_reduction INT, "+
                    " price DECIMAL(10,2), " +
                    " price_reduc DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " venduTotal INT, " +
                    " vendu_reduc INT, " +
                    " image VARCHAR(1000), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable4Query);
            System.out.println("Table accessoires created successfully...");

            String createTable6Query = "CREATE TABLE panier " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " product_id INT, " +
                    " table_nom VARCHAR(50), " +
                    " quantity INT, " +
                    " date_created DATETIME DEFAULT CURRENT_TIMESTAMP, "+
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable6Query);
            System.out.println("Table panier created successfully...");

            String createTable7Query = "CREATE TABLE historique " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " first_name VARCHAR(1000), " +
                    " product_id INT, " +
                    " table_nom VARCHAR(50), " +
                    " quantity INT, " +
                    " date_created DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable7Query);
            System.out.println("Table historique client created successfully...");

            //INITILISATION DES TABLEAUX
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO comptes (last_name, first_name, email, password, balance, admin) VALUES "
                    + "('Dupont', 'Jean', 'jean.dupont@mail.com', '123456', 1000.0,0),"
                    + "('Martin', 'Lucie', 'lucie.martin@mail.com', 'abcdef', 2000.0,0),"
                    + "('Garcia', 'Pedro', 'pedro.garcia@mail.com', 'ghijkl', 500.0,0),"
                    + "('Doe', 'John', 'john.doe@mail.com', 'mnopqr', 3000.0,0),"
                    + "('Smith', 'Alice', 'alice.smith@mail.com', 'stuvwx', 1500.0,0)";
            stmt.executeUpdate(query);
            query = "INSERT INTO accessoires (name, description, en_reduction, price, price_reduc, stock_quantity, venduTotal, vendu_reduc, image) VALUES "
                    + "('Souris sans fil', 'Souris optique sans fil avec 5 boutons', 0, 29.99, 0, 50, 0, 0, 'https://example.com/souris.jpg'),"
                    + "('Clavier filaire', 'Clavier filaire avec 105 touches', 0, 19.99, 0, 30, 0, 0, 'https://example.com/clavier.jpg'),"
                    + "('Casque audio', 'Casque audio stéréo avec micro', 0, 49.99, 0, 20, 0, 0, 'https://example.com/casque.jpg'),"
                    + "('Webcam HD', 'Webcam HD 720p avec microphone intégré', 0, 39.99, 0, 15, 0, 0, 'https://example.com/webcam.jpg'),"
                    + "('Tapis de souris', 'Tapis de souris avec surface lisse', 0, 9.99, 0, 100, 0, 0, 'https://example.com/tapis.jpg')";
            stmt.executeUpdate(query);

            String insertQuery = "INSERT INTO produits (name, description, en_reduction, price, price_reduc, stock_quantity, venduTotal, vendu_reduc) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            // Étape 3 : Ajout de 5 entrées uniques
            pstmt.setString(1, "Produit 1");
            pstmt.setString(2, "Description du produit 1");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("10.99"));
            pstmt.setBigDecimal(5, new BigDecimal("0"));
            pstmt.setInt(6, 50);
            pstmt.setInt(7, 0);
            pstmt.setInt(8, 0);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produit 2");
            pstmt.setString(2, "Description du produit 2");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("20.99"));
            pstmt.setBigDecimal(5, new BigDecimal("15.99"));
            pstmt.setInt(6, 30);
            pstmt.setInt(7, 10);
            pstmt.setInt(8, 5);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produit 3");
            pstmt.setString(2, "Description du produit 3");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("15.99"));
            pstmt.setBigDecimal(5, new BigDecimal("12.99"));
            pstmt.setInt(6, 20);
            pstmt.setInt(7, 5);
            pstmt.setInt(8, 2);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produit 4");
            pstmt.setString(2, "Description du produit 4");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("5.99"));
            pstmt.setBigDecimal(5, new BigDecimal("0"));
            pstmt.setInt(6, 100);
            pstmt.setInt(7, 20);
            pstmt.setInt(8, 0);
            pstmt.executeUpdate();

            pstmt.setString(1, "Produit 5");
            pstmt.setString(2, "Description du produit 5");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("25.99"));
            pstmt.setBigDecimal(5, new BigDecimal("18.99"));
            pstmt.setInt(6, 10);
            pstmt.setInt(7, 2);
            pstmt.setInt(8, 1);
            pstmt.executeUpdate();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO livres (title, author, publisher, publication_date, isbn, en_reduction, price, price_reduc, stock_quantity, venduTotal, vendu_reduc, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Premier livre
            ps.setString(1, "Le Seigneur des anneaux : La Communauté de l'anneau");
            ps.setString(2, "J.R.R. Tolkien");
            ps.setString(3, "Christian Bourgois éditeur");
            ps.setString(4, "01/09/2003");
            ps.setString(5, "978-2266121018");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("12.99"));
            ps.setBigDecimal(8, new BigDecimal("10.99"));
            ps.setInt(9, 50);
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setString(12, "https://images-na.ssl-images-amazon.com/images/I/51bjgPhyqML._SX323_BO1,204,203,200_.jpg");
            ps.executeUpdate();

            // Deuxième livre
            ps.setString(1, "Harry Potter à l'école des sorciers");
            ps.setString(2, "J.K. Rowling");
            ps.setString(3, "Gallimard Jeunesse");
            ps.setString(4, "12/10/1998");
            ps.setString(5, "2070543025");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("8.50"));
            ps.setBigDecimal(8, new BigDecimal("7.99"));
            ps.setInt(9, 20);
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setString(12, "https://images-na.ssl-images-amazon.com/images/I/51A5LXdWYAL._SX305_BO1,204,203,200_.jpg");
            ps.executeUpdate();

            // Troisième livre
            ps.setString(1, "1984");
            ps.setString(2, "George Orwell");
            ps.setString(3, "Gallimard");
            ps.setString(4, "01/01/1950");
            ps.setString(5, "2070368229");
            ps.setInt(6, 0);
            ps.setBigDecimal(7, new BigDecimal("7.20"));
            ps.setBigDecimal(8, new BigDecimal("0.00"));
            ps.setInt(9, 10);
            ps.setInt(10, 0);
            ps.setInt(11, 0);
            ps.setString(12, "https://images-na.ssl-images-amazon.com/images/I/51M8+7SxSvL._SX305_BO1,204,203,200_.jpg");
            ps.executeUpdate();

            if(stmt!=null) stmt.close();
            if(conn!=null) conn.close();
        } catch(SQLException se) {
            se.printStackTrace();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public void addSomething(String nomTab){
        System.out.println("Ajout d'un element dans : " + nomTab);
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
            if (rs != null) rs.close();
            if (conn != null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void effacePanier() {
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM panier");
            System.out.println("Panier cleared successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addPanier(){


        Scanner scan=new Scanner(System.in);


        afficherTableau();
        System.out.print("Entrez le nom de la table : ");
        String tableNom = scan.nextLine();

        descriptiontab(tableNom,0);
        System.out.print("Entrez l'ID du produit : ");
        int productId = scan.nextInt();
        scan.nextLine(); // pour vider le tampon

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query;
            query = "SELECT * FROM "+tableNom+" WHERE id="+productId;
            ResultSet rs= stmt.executeQuery(query);

            while(rs.next()){
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Quantity: " + rs.getInt("stock_quantity"));
                System.out.println("--------------------------");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.print("Entrez la quantité : ");
        int quantity = scan.nextInt();

        addPanier(productId, tableNom, quantity);
    }
    public void addPanier(int productId, String tableNom, int quantity){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String sql = "INSERT INTO panier (product_id, table_nom, quantity) VALUES (?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, productId);
            pstmt.setString(2, tableNom);
            pstmt.setInt(3, quantity);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'article a été ajouté au panier avec succès.");
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void afficherPanier(){
       String query = "SELECT * FROM panier";

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String tableNom = rs.getString("table_nom");
                int quantity = rs.getInt("quantity");
                int productid = rs.getInt("product_id");
                Timestamp date = rs.getTimestamp("date_created");
                System.out.println("Panier item #" + id + ":");
                System.out.println("  Product: " + productid);
                System.out.println("  Table name: " + tableNom);
                System.out.println("  Quantity: " + quantity);
                System.out.println("  Date: " + date);

                Statement stmtlia = conn.createStatement();
                String querylia="SELECT * FROM "+tableNom+" WHERE id="+productid;
                ResultSet rslia = stmtlia.executeQuery(querylia);

                ResultSetMetaData metadata = rslia.getMetaData();
                int columnCount = metadata.getColumnCount();
                String[] columnNames = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    columnNames[i] = metadata.getColumnName(i + 1);
                }
                // Boucle pour parcourir le résultat et afficher les données sur la console
                while (rslia.next()) {
                    for (String columnName : columnNames) {
                        Object columnValue = rslia.getObject(columnName);
                        System.out.println("  "+columnName + ": " + columnValue);
                    }
                }


                System.out.println("--------------------------");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }




    }

    public void afficherHisto(){
        System.out.println("--------------------------afficherHisto-----------------------");
        String query = "SELECT * FROM historique";

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();

            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String first_name=rs.getString("first_name");
                String tableNom = rs.getString("table_nom");
                int quantity = rs.getInt("quantity");
                int productid = rs.getInt("product_id");
                Timestamp date = rs.getTimestamp("date_created");
                System.out.println("Panier item #" + id + ":");
                System.out.println("  first_name: " +first_name);
                System.out.println("  Product: " + productid);
                System.out.println("  Table name: " + tableNom);
                System.out.println("  Quantity: " + quantity);
                System.out.println("  Date: " + date);

                Statement stmtlia = conn.createStatement();
                String querylia="SELECT * FROM "+tableNom+" WHERE id="+productid;
                ResultSet rslia = stmtlia.executeQuery(querylia);

                ResultSetMetaData metadata = rslia.getMetaData();
                int columnCount = metadata.getColumnCount();
                String[] columnNames = new String[columnCount];
                for (int i = 0; i < columnCount; i++) {
                    columnNames[i] = metadata.getColumnName(i + 1);
                }
                // Boucle pour parcourir le résultat et afficher les données sur la console
                while (rslia.next()) {
                    for (String columnName : columnNames) {
                        Object columnValue = rslia.getObject(columnName);
                        System.out.println("  "+columnName + ": " + columnValue);
                    }
                }

                System.out.println("--------------------------");
            }

            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    public void ConfirmationAchat() {
        if(this.identifiantS==null){
            Connexion("Jean","123456");
        }
        String firstname=this.identifiantS;
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            // Création de la commande SQL pour copier les données
            stmt = conn.createStatement();
            String appel="SELECT * FROM panier";

            ResultSet rs = stmt.executeQuery(appel);
            while (rs.next()) {
                int id = rs.getInt("id");
                String tableNom = rs.getString("table_nom");
                int quantity = rs.getInt("quantity");
                int productid = rs.getInt("product_id");
                Timestamp date = rs.getTimestamp("date_created");
                Statement stmt2=conn.createStatement();
                String appel2="SELECT * FROM "+tableNom+" WHERE id="+productid;
                ResultSet res= stmt2.executeQuery(appel2);
                while (res.next()){
                    int stock=res.getInt("stock_quantity");
                    int reduc=res.getInt("en_reduction");
                    int newstock=stock-quantity;
                    String update1;
                        update1 = "UPDATE "+tableNom+" SET stock_quantity = "+newstock+" WHERE id ="+productid;
                    Statement stmt1=conn.createStatement();
                    stmt1.executeUpdate(update1);
                    String update;
                    if(reduc==1||quantity>4){
                        int venteReduc= res.getInt("vendu_reduc")+quantity;
                        update = "UPDATE "+tableNom+" SET vendu_reduc = "+venteReduc+" WHERE id ="+productid;
                    }else{
                        int ventetot= res.getInt("venduTotal")+quantity;
                        update = "UPDATE "+tableNom+" SET venduTotal = "+ventetot+" WHERE id ="+productid;
                    }
                    Statement stmtU=conn.createStatement();
                    stmtU.executeUpdate(update);
                }
            }
            System.out.println("voir stock");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            // Création de la commande SQL pour copier les données
            stmt = conn.createStatement();
            String appel="SELECT * FROM panier";

            ResultSet rs1 = stmt.executeQuery(appel);
            while (rs1.next()) {
                int id = rs1.getInt("id");
                String tableNom = rs1.getString("table_nom");
                int quantity = rs1.getInt("quantity");
                int productid = rs1.getInt("product_id");
                Timestamp date = rs1.getTimestamp("date_created");
                String query = "INSERT INTO historique (first_name, product_id, table_nom, quantity) VALUES "
                        + "('"+firstname+"',"+productid+", '"+tableNom+"', "+quantity+")";
                Statement stmt2=conn.createStatement();
                stmt2.executeUpdate(query);
            }
            System.out.println("voir historique");

        } catch (SQLException e) {
            e.printStackTrace();
        }

        effacePanier();
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
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metadata.getColumnName(i + 1);
            }

            // Boucle pour parcourir le résultat et afficher les données sur la console
            while (rs.next()) {
                for (String columnName : columnNames) {
                    Object columnValue = rs.getObject(columnName);
                    System.out.println(columnName + ": " + columnValue);
                }
                System.out.println("--------------------------");
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

            ResultSetMetaData metadata = rs.getMetaData();
            int columnCount = metadata.getColumnCount();
            String[] columnNames = new String[columnCount];
            for (int i = 0; i < columnCount; i++) {
                columnNames[i] = metadata.getColumnName(i + 1);
            }

            // Boucle pour parcourir le résultat et afficher les données sur la console
            while (rs.next()) {
                for (String columnName : columnNames) {
                    Object columnValue = rs.getObject(columnName);
                    System.out.println(columnName + ": " + columnValue);
                }
                System.out.println("--------------------------");
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

    public void graphtest() {

        // Création du jeu de données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        dataset.setValue(10, "Revenue", "Janvier");
        dataset.setValue(20, "Revenue", "Février");
        dataset.setValue(15, "Revenue", "Mars");
        dataset.setValue(25, "Revenue", "Avril");
        dataset.setValue(30, "Revenue", "Mai");

        // Création du graphique en barres
        JFreeChart chart = ChartFactory.createBarChart(
                "Revenus par mois", // Titre
                "Mois", // Axe des abscisses
                "Revenus (€)", // Axe des ordonnées
                dataset // Jeu de données
        );

        // Création de la fenêtre contenant le graphique
        ChartFrame frame = new ChartFrame("Graphique en barres", chart);
        frame.pack();
        frame.setVisible(true);
    }
    public void run() {
        graphtest();
        createDatabase();
       /* addSomething("comptes");
        addSomething("produits");
        addSomething("employes");
        addSomething("livres");
        addSomething("accessoires");
        addSomething("panier");
        metAJourLigne();*/

        //addPanier();
        //addPanier();
        afficherPanier();
        ConfirmationAchat();
        afficherHisto();
        Deconnexion();



        afficherTableau();
        //supprimeligne();

        Scanner saisie=new Scanner(System.in);
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

    public boolean Connexion(String username, String mdp) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query = "SELECT * FROM comptes";
            ResultSet rs= stmt.executeQuery(query);

            while(rs.next()){
                if(username.equals(rs.getString("first_name"))&&mdp.equals(rs.getString("password"))){
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Nom: " + rs.getString("last_name"));
                    System.out.println("Prénom: " + rs.getString("first_name"));
                    System.out.println("Email: " + rs.getString("email"));
                    System.out.println("Mot de passe: " + rs.getString("password"));
                    System.out.println("Solde: " + rs.getDouble("balance"));
                    System.out.println("Adresse: " + rs.getString("adress"));
                    System.out.println("Admin: " + rs.getString("admin"));
                    this.identifiantS= rs.getString("first_name");
                    this.motdepasseS= rs.getString("password");
                    this.adminS=rs.getInt("admin");
                    if(this.motdepasseS!=null&&this.identifiantS!=null){
                        System.out.println("Bienvenue "+this.identifiantS);
                    }
                    return true;
                }
                System.out.println("--------------------------");
            }
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        System.out.println("error username or password");
        return false;
}
    public void Deconnexion() {
        effacePanier();
        System.out.println("Au revoir "+this.identifiantS);
       this.identifiantS=null;
       this.motdepasseS=null;
       this.adminS=666;
       System.out.println("deconnection");
    }




}
