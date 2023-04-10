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

    private List<Compte> comptes = new ArrayList<>();

    private List<Accessoire> accessoires = new ArrayList<>();

    private List<Bijou> bijoux = new ArrayList<>();
    private List<Livre> livres= new ArrayList<>();

    private List<Panier> paniers=new ArrayList<>();
    private List<Historique> historiques=new ArrayList<>();

    private List<String> columnNames = new ArrayList<>();

    public List<Compte> getComptes() {
        return comptes;
    }

    public List<Accessoire> getAccessoires() {
        return accessoires;
    }

    public List<Bijou> getBijoux() {
        return bijoux;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    public List<Panier> getPaniers() {
        return paniers;
    }

    public List<Historique> getHistoriques() {
        return historiques;
    }

    public List<String> getColumnNames() {
        return columnNames;
    }

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
            stmt= conn.createStatement();
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


            String createTable3Query = "CREATE TABLE bijoux " +

                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(100), " +
                    " description VARCHAR(255), " +
                    " en_reduction INT, "+
                    " price DECIMAL(10,2), " +
                    " price_reduc DECIMAL(10,2), " +
                    " stock_quantity INT, " +
                    " vendu_sans_reduc INT, " +
                    " vendu_reduc INT, " +
                    " image VARCHAR(1000), " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable3Query);

            System.out.println("Table bijoux created successfully...");

            /*String createTable1Query = "CREATE TABLE man " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " age INT, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable1Query);
            System.out.println("Table man created successfully...");*/

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
                    " vendu_sans_reduc INT, " +
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
                    " vendu_sans_reduc INT, " +
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
            query = "INSERT INTO accessoires (name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES "
                    + "('Souris sans fil', 'Souris optique sans fil avec 5 boutons', 0, 29.99, 0, 50, 0, 0, 'https://m.media-amazon.com/images/I/61AWLK29YrL._AC_SX679_.jpg'),"
                    + "('Clavier filaire', 'Clavier filaire avec 105 touches', 0, 19.99, 0, 30, 0, 0, 'https://www.mdose.fr/image/cache/catalog/migrated/c/l/clav_med_ip65-500x500.jpg'),"
                    + "('Casque audio', 'Casque audio stéréo avec micro', 0, 49.99, 0, 20, 0, 0, 'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTR8XcjJuehkVX3jUpO4mJMruYfrPDXY2p4XcbUM4itEeNBeNfcSBJ_DQvZfNDyoVU4IxT7kNlAW8k&usqp=CAc'),"
                    + "('Webcam HD', 'Webcam HD 720p avec microphone intégré', 0, 39.99, 0, 15, 0, 0, 'https://m.media-amazon.com/images/I/71xjo0lERgL.__AC_SX300_SY300_QL70_ML2_.jpg'),"
                    + "('Tapis de souris', 'Tapis de souris avec surface lisse', 0, 9.99, 0, 100, 0, 0, 'https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcTT1ESt5FTBqbcsejdqyzbIed4-MMwhXR1tLNMTtZw04wrksvsKYk0fSUtwcSV0RhSmg8PU8o_Mdg&usqp=CAc')";
            stmt.executeUpdate(query);


            String insertQuery = "INSERT INTO bijoux (name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            // Étape 3 : Ajout de 5 entrées uniques
            pstmt.setString(1, "bijou 1");
            pstmt.setString(2, "Description du bijou 1");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("10.99"));
            pstmt.setBigDecimal(5, new BigDecimal("0"));
            pstmt.setInt(6, 50);
            pstmt.setInt(7, 0);
            pstmt.setInt(8, 0);
            pstmt.setString(9,"https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcSWyMYiVP5z4AgLpPbdAINjqA-8p2K-9kceO_iO6I91fjgwK2TsBnd6ioZ7yb8U9jizBr5W14NF92vcixwzyrVn21C_sYiT-08u_y7_SxWG8zhDYA3Wtgntd0Mt&usqp=CAc");
            pstmt.executeUpdate();

            pstmt.setString(1, "bijou 2");
            pstmt.setString(2, "Description du bijou 2");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("20.99"));
            pstmt.setBigDecimal(5, new BigDecimal("15.99"));
            pstmt.setInt(6, 30);
            pstmt.setInt(7, 10);
            pstmt.setInt(8, 5);
            pstmt.setString(9,"https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcTrCrXS5UGIpDgHMAxfmPgqwOi9AkVzpxUcTtkqjx0SaWQJcS5-lNrAEfVG25NTFXg8JRriv9f2HGcn&usqp=CAc");
            pstmt.executeUpdate();

            pstmt.setString(1, "bijou 3");
            pstmt.setString(2, "Description du bijou 3");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("15.99"));
            pstmt.setBigDecimal(5, new BigDecimal("12.99"));
            pstmt.setInt(6, 20);
            pstmt.setInt(7, 5);
            pstmt.setInt(8, 2);
            pstmt.setString(9,"https://example.com/tapis.jpg");
            pstmt.executeUpdate();

            pstmt.setString(1, "bijou 4");
            pstmt.setString(2, "Description du bijou 4");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("5.99"));
            pstmt.setBigDecimal(5, new BigDecimal("0"));
            pstmt.setInt(6, 100);
            pstmt.setInt(7, 20);
            pstmt.setInt(8, 0);
            pstmt.setString(9,"https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTDT6qNqw5ehD-aM1mLkwbx3Z2WCvNqwMAIgv6OhnE-TlncsffMD7YdPtY9ZToCHm5QIGjM4KmnOK3B&usqp=CAc");
            pstmt.executeUpdate();

            pstmt.setString(1, "bijou 5");
            pstmt.setString(2, "Description du bijou 5");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("25.99"));
            pstmt.setBigDecimal(5, new BigDecimal("18.99"));
            pstmt.setInt(6, 10);
            pstmt.setInt(7, 2);
            pstmt.setInt(8, 1);
            pstmt.setString(9,"https://encrypted-tbn2.gstatic.com/shopping?q=tbn:ANd9GcRgPUYXZfYpd49pfRvW50vlCg8yFzsLUV8VHRNBBRDk2NlgwAKPc0huy7Glr_C60SASLRWtjYbhow&usqp=CAc");
            pstmt.executeUpdate();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO livres (title, author, publisher, publication_date, isbn, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

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
            ps.setString(12, "https://encrypted-tbn3.gstatic.com/shopping?q=tbn:ANd9GcRq4SSTvwCmhdm4eYclVM6XTPFSQ1DdK2ngXfMKRobd1qNhNGRv0Zm6YpkoQaeZ84Sxhhr14wBpLNh_1ihrT0U18OV539FApNmGwn-BagBBtFXS_bP-vs_7GQ&usqp=CAc");
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
            ps.setString(12, "https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcRp716eeg3_XiLAO3M4ofKCX1iDmixULhXAjBnCdXErTbq9hizZDVUq6dkvZctEDTJo9Jq_umiKSz50BvonbGPsqm4VJI7iV4l8a22SwK8&usqp=CAc");
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
            ps.setString(12, "https://encrypted-tbn3.gstatic.com/images?q=tbn:ANd9GcRTUr4i3iCtjUtG-9dvDd_Z7-Mesd3RrIwpBAzPJy0Dt1zstG_p");
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

    public void effacePanieruniqueitem(int ID) {
        int num = ID;
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM panier WHERE id="+num);
            System.out.println("Panier where id="+num+" cleared successfully");
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
        System.out.print("Entrez l'ID du bijou : ");
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
    public void afficherPaniertabbrutarray(){
        String querylia = "SELECT * FROM panier";

        paniers.clear();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmtlia = conn.createStatement();

            ResultSet rslia = stmtlia.executeQuery(querylia);

            while (rslia.next()) {
                int id = rslia.getInt("id");
                String tableNom = rslia.getString("table_nom");
                int quantity = rslia.getInt("quantity");
                int productid = rslia.getInt("product_id");
                Timestamp date = rslia.getTimestamp("date_created");

                stmt= conn.createStatement();
                String query="SELECT * FROM "+tableNom+" WHERE id="+productid;
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {
                    //int id = rs.getInt("id");

                    switch (tableNom) {

                        case "accessoires":
                            String name = rs.getString("name");
                            String description = rs.getString("description");
                            int en_reduction = rs.getInt("en_reduction");
                            double price = rs.getDouble("price");
                            double price_reduc = rs.getDouble("price_reduc");
                            int stock_quantity = rs.getInt("stock_quantity");
                            int vendu_sans_reduc = rs.getInt("vendu_sans_reduc");
                            int vendu_reduc = rs.getInt("vendu_reduc");
                            String image = rs.getString("image");
                            Panier accessoire = new Panier(id, productid,tableNom, quantity,date,name, description, en_reduction, price, price_reduc, stock_quantity, vendu_reduc, vendu_reduc, image);

                            paniers.add(accessoire);
                            break;

                        case "livres":
                            String title = rs.getString("title");
                            String author = rs.getString("author");
                            String publisher = rs.getString("publisher");
                            String publicationDate = rs.getString("publication_date");
                            String isbn = rs.getString("isbn");
                            int enReduction = rs.getInt("en_reduction");
                            double pricel = rs.getDouble("price");
                            double priceReduc = rs.getDouble("price_reduc");
                            int stockQuantity = rs.getInt("stock_quantity");
                            int venduSansReduc = rs.getInt("vendu_sans_reduc");
                            int venduReduc = rs.getInt("vendu_reduc");
                            String imagel = rs.getString("image");

                            Panier livre = new Panier(id,productid,tableNom, quantity,date, title, author, publisher, publicationDate, isbn, enReduction, pricel, priceReduc, stockQuantity, venduSansReduc, venduReduc, imagel);
                            paniers.add(livre);
                            break;

                        case "bijoux":
                            String nameb = rs.getString("name");
                            String descriptionb = rs.getString("description");
                            int en_reductionb = rs.getInt("en_reduction");
                            double priceb = rs.getDouble("price");
                            double price_reducb = rs.getDouble("price_reduc");
                            int stock_quantityb = rs.getInt("stock_quantity");
                            int vendu_sans_reducb = rs.getInt("vendu_sans_reduc");
                            int vendu_reducb = rs.getInt("vendu_reduc");
                            String imageb = rs.getString("image");
                            Panier bijou = new Panier(id,productid,tableNom, quantity,date, nameb, descriptionb, en_reductionb, priceb, price_reducb, stock_quantityb, vendu_sans_reducb, vendu_reducb, imageb);
                            paniers.add(bijou);
                            break;

                        default:
                            throw new IllegalArgumentException("Tableau inconnu : " + tableNom);
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public void afficherHistorique(){
        System.out.println("--------------------------afficherHistorique-----------------------");
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

    public void afficherHistoriquetabbrutarray(String firstname){
        String querylia = "SELECT * FROM historique WHERE first_name= '"+firstname+"'";
        historiques.clear();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmtlia = conn.createStatement();

            ResultSet rslia = stmtlia.executeQuery(querylia);

            while (rslia.next()) {
                int id = rslia.getInt("id");
                String first_name=rslia.getString("first_name");
                String tableNom = rslia.getString("table_nom");
                int quantity = rslia.getInt("quantity");
                int productid = rslia.getInt("product_id");
                Timestamp date = rslia.getTimestamp("date_created");

                stmt= conn.createStatement();
                String query="SELECT * FROM "+tableNom+" WHERE id="+productid;
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {
                    //int id = rs.getInt("id");

                    switch (tableNom) {

                        case "accessoires":
                            String name = rs.getString("name");
                            String description = rs.getString("description");
                            int en_reduction = rs.getInt("en_reduction");
                            double price = rs.getDouble("price");
                            double price_reduc = rs.getDouble("price_reduc");
                            int stock_quantity = rs.getInt("stock_quantity");
                            int vendu_sans_reduc = rs.getInt("vendu_sans_reduc");
                            int vendu_reduc = rs.getInt("vendu_reduc");
                            String image = rs.getString("image");
                            Historique accessoire = new Historique(id, first_name,productid,tableNom, quantity,date,name, description, en_reduction, price, price_reduc, stock_quantity, vendu_reduc, vendu_reduc, image);

                            historiques.add(accessoire);
                            break;

                        case "livres":
                            String title = rs.getString("title");
                            String author = rs.getString("author");
                            String publisher = rs.getString("publisher");
                            String publicationDate = rs.getString("publication_date");
                            String isbn = rs.getString("isbn");
                            int enReduction = rs.getInt("en_reduction");
                            double pricel = rs.getDouble("price");
                            double priceReduc = rs.getDouble("price_reduc");
                            int stockQuantity = rs.getInt("stock_quantity");
                            int venduSansReduc = rs.getInt("vendu_sans_reduc");
                            int venduReduc = rs.getInt("vendu_reduc");
                            String imagel = rs.getString("image");

                            Historique livre = new Historique(id,first_name,productid,tableNom, quantity,date, title, author, publisher, publicationDate, isbn, enReduction, pricel, priceReduc, stockQuantity, venduSansReduc, venduReduc, imagel);
                            historiques.add(livre);
                            break;

                        case "bijoux":
                            String nameb = rs.getString("name");
                            String descriptionb = rs.getString("description");
                            int en_reductionb = rs.getInt("en_reduction");
                            double priceb = rs.getDouble("price");
                            double price_reducb = rs.getDouble("price_reduc");
                            int stock_quantityb = rs.getInt("stock_quantity");
                            int vendu_sans_reducb = rs.getInt("vendu_sans_reduc");
                            int vendu_reducb = rs.getInt("vendu_reduc");
                            String imageb = rs.getString("image");
                            Historique bijou = new Historique(id,first_name,productid,tableNom, quantity,date, nameb, descriptionb, en_reductionb, priceb, price_reducb, stock_quantityb, vendu_sans_reducb, vendu_reducb, imageb);
                            historiques.add(bijou);
                            break;

                        default:
                            throw new IllegalArgumentException("Tableau inconnu : " + tableNom);
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void afficherHistoriquetabbrutarray(){
        String querylia = "SELECT * FROM historique";

        historiques.clear();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmtlia = conn.createStatement();

            ResultSet rslia = stmtlia.executeQuery(querylia);

            while (rslia.next()) {
                int id = rslia.getInt("id");
                String first_name=rslia.getString("first_name");
                String tableNom = rslia.getString("table_nom");
                int quantity = rslia.getInt("quantity");
                int productid = rslia.getInt("product_id");
                Timestamp date = rslia.getTimestamp("date_created");

                stmt= conn.createStatement();
                String query="SELECT * FROM "+tableNom+" WHERE id="+productid;
                ResultSet rs = stmt.executeQuery(query);


                while (rs.next()) {
                    //int id = rs.getInt("id");

                    switch (tableNom) {

                        case "accessoires":
                            String name = rs.getString("name");
                            String description = rs.getString("description");
                            int en_reduction = rs.getInt("en_reduction");
                            double price = rs.getDouble("price");
                            double price_reduc = rs.getDouble("price_reduc");
                            int stock_quantity = rs.getInt("stock_quantity");
                            int vendu_sans_reduc = rs.getInt("vendu_sans_reduc");
                            int vendu_reduc = rs.getInt("vendu_reduc");
                            String image = rs.getString("image");
                            Historique accessoire = new Historique(id, first_name,productid,tableNom, quantity,date,name, description, en_reduction, price, price_reduc, stock_quantity, vendu_reduc, vendu_reduc, image);

                            historiques.add(accessoire);
                            break;

                        case "livres":
                            String title = rs.getString("title");
                            String author = rs.getString("author");
                            String publisher = rs.getString("publisher");
                            String publicationDate = rs.getString("publication_date");
                            String isbn = rs.getString("isbn");
                            int enReduction = rs.getInt("en_reduction");
                            double pricel = rs.getDouble("price");
                            double priceReduc = rs.getDouble("price_reduc");
                            int stockQuantity = rs.getInt("stock_quantity");
                            int venduSansReduc = rs.getInt("vendu_sans_reduc");
                            int venduReduc = rs.getInt("vendu_reduc");
                            String imagel = rs.getString("image");

                            Historique livre = new Historique(id,first_name,productid,tableNom, quantity,date, title, author, publisher, publicationDate, isbn, enReduction, pricel, priceReduc, stockQuantity, venduSansReduc, venduReduc, imagel);
                            historiques.add(livre);
                            break;

                        case "bijoux":
                            String nameb = rs.getString("name");
                            String descriptionb = rs.getString("description");
                            int en_reductionb = rs.getInt("en_reduction");
                            double priceb = rs.getDouble("price");
                            double price_reducb = rs.getDouble("price_reduc");
                            int stock_quantityb = rs.getInt("stock_quantity");
                            int vendu_sans_reducb = rs.getInt("vendu_sans_reduc");
                            int vendu_reducb = rs.getInt("vendu_reduc");
                            String imageb = rs.getString("image");
                            Historique bijou = new Historique(id,first_name,productid,tableNom, quantity,date, nameb, descriptionb, en_reductionb, priceb, price_reducb, stock_quantityb, vendu_sans_reducb, vendu_reducb, imageb);
                            historiques.add(bijou);
                            break;

                        default:
                            throw new IllegalArgumentException("Tableau inconnu : " + tableNom);
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    public int ConfirmationAchatpage(String firstname) {
        int newbalance=0;
        //if(this.identifiantS==null){Connexion("Jean","123456");} String firstname=this.identifiantS;
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            // Création de la commande SQL pour copier les données
            stmt = conn.createStatement();
            String appel="SELECT * FROM panier";

            int pricetot=0;
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
                    for (int i=0; i<quantity; i++) {
                        if(reduc==1||quantity>4){
                            pricetot += res.getInt("price_reduc");
                            int venteReduc= res.getInt("vendu_reduc")+1;
                            update = "UPDATE "+tableNom+" SET vendu_reduc = "+venteReduc+" WHERE id ="+productid;
                        }else{
                            pricetot += res.getInt("price");
                            int ventesansreduc= res.getInt("vendu_sans_reduc")+1;
                            update = "UPDATE "+tableNom+" SET vendu_sans_reduc = "+ventesansreduc+" WHERE id ="+productid;
                        }
                        Statement stmtU=conn.createStatement();
                        stmtU.executeUpdate(update);
                    }

                }
            }

            Statement stmtz = conn.createStatement();
            String appelz="SELECT balance FROM comptes WHERE first_name ='"+firstname+"'";
            ResultSet rsz = stmtz.executeQuery(appelz);
            int balance=0;
            while(rsz.next()){
                balance=rsz.getInt("balance");
            }
            newbalance=balance-pricetot;
            String updatetotbalance= "UPDATE comptes SET balance = "+newbalance+" WHERE first_name = '"+firstname+"'";
            Statement stmt1=conn.createStatement();
            stmt1.executeUpdate(updatetotbalance);

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
        return newbalance;
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
                        int ventetot= res.getInt("vendu_sans_reduc")+quantity;
                        update = "UPDATE "+tableNom+" SET vendu_sans_reduc = "+ventetot+" WHERE id ="+productid;
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

    public void afficherColonne(String nomTab){
        columnNames.clear();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query;
            DatabaseMetaData metadata = conn.getMetaData();
            ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
            System.out.println("Colonnes de la table " + nomTab + " : ");
            while (rsColumns.next()) {
                String columnName = rsColumns.getString("COLUMN_NAME");
                columnNames.add(columnName);
                System.out.println(columnName);
            }
            rsColumns.close();
            stmt.close();
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

                // Création de la requête SQL pour récupérer les bijous avec le nom saisi
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

    public void descriptiontabbrutarray(String nomTab, int avecrecherche, int trie) {

        comptes.clear();
        accessoires.clear();
        bijoux.clear();
        livres.clear();

        List<Panier> paniers=new ArrayList<>();
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
                columnNames.clear();
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

            while (rs.next()) {
                int id = rs.getInt("id");

                switch (nomTab) {
                    case "comptes":
                        String first_name = rs.getString("first_name");
                        String last_name = rs.getString("last_name");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        double balance = rs.getDouble("balance");
                        String address = rs.getString("adress");
                        int admin = rs.getInt("admin");
                        Compte compte = new Compte(id, first_name, last_name, email, password, balance, address, admin);
                        comptes.add(compte);
                        break;

                    case "accessoires":
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        int en_reduction = rs.getInt("en_reduction");
                        double price = rs.getDouble("price");
                        double price_reduc = rs.getDouble("price_reduc");
                        int stock_quantity = rs.getInt("stock_quantity");
                        int vendu_sans_reduc = rs.getInt("vendu_sans_reduc");
                        int vendu_reduc = rs.getInt("vendu_reduc");
                        String image = rs.getString("image");
                        Accessoire accessoire = new Accessoire(id, name, description, en_reduction, price, price_reduc, stock_quantity, vendu_reduc, vendu_reduc, image);
                        accessoires.add(accessoire);
                        break;

                    case "livres":
                        String title = rs.getString("title");
                        String author = rs.getString("author");
                        String publisher = rs.getString("publisher");
                        String publicationDate = rs.getString("publication_date");
                        String isbn = rs.getString("isbn");
                        int enReduction = rs.getInt("en_reduction");
                        double pricel = rs.getDouble("price");
                        double priceReduc = rs.getDouble("price_reduc");
                        int stockQuantity = rs.getInt("stock_quantity");
                        int venduSansReduc = rs.getInt("vendu_sans_reduc");
                        int venduReduc = rs.getInt("vendu_reduc");
                        String imagel = rs.getString("image");

                        Livre livre = new Livre(id, title, author, publisher, publicationDate, isbn, enReduction, pricel, priceReduc, stockQuantity, venduSansReduc, venduReduc, imagel);
                        livres.add(livre);
                        break;

                    case "bijoux":
                        String nameb = rs.getString("name");
                        String descriptionb = rs.getString("description");
                        int en_reductionb = rs.getInt("en_reduction");
                        double priceb = rs.getDouble("price");
                        double price_reducb = rs.getDouble("price_reduc");
                        int stock_quantityb = rs.getInt("stock_quantity");
                        int vendu_sans_reducb = rs.getInt("vendu_sans_reduc");
                        int vendu_reducb = rs.getInt("vendu_reduc");
                        String imageb = rs.getString("image");
                        Bijou bijou = new Bijou(id, nameb, descriptionb, en_reductionb, priceb, price_reducb, stock_quantityb, vendu_sans_reducb, vendu_reducb, imageb);
                        bijoux.add(bijou);
                        break;

                    default:
                        throw new IllegalArgumentException("Tableau inconnu : " + nomTab);
                }
            }

            // Fermeture des ressources
            rs.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void descriptiontabbrutarray(String nomTab, int avecrecherche, int trie, String nomRecherche, String colonne,String ordre) {
                //descriptiontabbrutarray("livres",1,1,"jdsq","trieselonquelid","DESC"/"ASC")
        comptes.clear();
        accessoires.clear();
        bijoux.clear();
        livres.clear();
        columnNames.clear();

        List<Panier> paniers=new ArrayList<>();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query;
            // Création de la requête SQL pour récupérer toutes les données du tableau "compte"
            if (avecrecherche==1&&trie==1) {
                System.out.println("-----------AVEC RECHERCHE ET AVEC TRIE-------------");
                //Scanner scanner = new Scanner(System.in);
                //System.out.print("Entrez le nom à rechercher : ");
                //String nomRecherche = scanner.nextLine();

                DatabaseMetaData metadata = conn.getMetaData();
                ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
                //List<String> columnNames = new ArrayList<>();
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

                //Scanner col= new Scanner(System.in);
                //System.out.println("Quel colonne?");
                //String colonne=col.nextLine();

                System.out.println("Quel ordre?");
                System.out.println("1:DESC");
                System.out.println("2: ASC");
                //int orderchoix=col.nextInt();String ordre;if (orderchoix==1){ordre="DESC";}else{ordre="ASC";}
                // Ajout de l'ordre de tri
                query += " ORDER BY " + colonne + " " + ordre;
            } else if (avecrecherche==1) {
                System.out.println("-----------AVEC RECHERCHE ET SANS TRIE-------------");
                //Scanner scanner = new Scanner(System.in);
                //System.out.print("Entrez le nom à rechercher : ");
                //String nomRecherche = scanner.nextLine();

                DatabaseMetaData metadata = conn.getMetaData();
                ResultSet rsColumns = metadata.getColumns(null, null, nomTab, null);
                //List<String> columnNames = new ArrayList<>();
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
                //List<String> columnNames = new ArrayList<>();
                columnNames.clear();
                System.out.println("Colonnes de la table " + nomTab + " : ");
                while (rsColumns.next()) {
                    String columnName = rsColumns.getString("COLUMN_NAME");
                    columnNames.add(columnName);
                    System.out.println(columnName);
                }
                rsColumns.close();

                //Scanner col= new Scanner(System.in);
                //System.out.println("Quel colonne?");
                //String colonne=col.nextLine();

                System.out.println("Quel ordre?");
                System.out.println("1:DESC");
                System.out.println("2: ASC");
                //int orderchoix=col.nextInt();
                //String ordre;if (orderchoix==1){ordre="DESC";}else{ordre="ASC";}
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

            while (rs.next()) {
                int id = rs.getInt("id");

                switch (nomTab) {
                    case "comptes":
                        String first_name = rs.getString("first_name");
                        String last_name = rs.getString("last_name");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        double balance = rs.getDouble("balance");
                        String address = rs.getString("adress");
                        int admin = rs.getInt("admin");
                        Compte compte = new Compte(id, first_name, last_name, email, password, balance, address, admin);
                        comptes.add(compte);
                        break;

                    case "accessoires":
                        String name = rs.getString("name");
                        String description = rs.getString("description");
                        int en_reduction = rs.getInt("en_reduction");
                        double price = rs.getDouble("price");
                        double price_reduc = rs.getDouble("price_reduc");
                        int stock_quantity = rs.getInt("stock_quantity");
                        int vendu_sans_reduc = rs.getInt("vendu_sans_reduc");
                        int vendu_reduc = rs.getInt("vendu_reduc");
                        String image = rs.getString("image");
                        Accessoire accessoire = new Accessoire(id, name, description, en_reduction, price, price_reduc, stock_quantity, vendu_reduc, vendu_reduc, image);
                        accessoires.add(accessoire);
                        break;

                    case "livres":
                        String title = rs.getString("title");
                        String author = rs.getString("author");
                        String publisher = rs.getString("publisher");
                        String publicationDate = rs.getString("publication_date");
                        String isbn = rs.getString("isbn");
                        int enReduction = rs.getInt("en_reduction");
                        double pricel = rs.getDouble("price");
                        double priceReduc = rs.getDouble("price_reduc");
                        int stockQuantity = rs.getInt("stock_quantity");
                        int venduSansReduc = rs.getInt("vendu_sans_reduc");
                        int venduReduc = rs.getInt("vendu_reduc");
                        String imagel = rs.getString("image");

                        Livre livre = new Livre(id, title, author, publisher, publicationDate, isbn, enReduction, pricel, priceReduc, stockQuantity, venduSansReduc, venduReduc, imagel);
                        livres.add(livre);
                        break;

                    case "bijoux":
                        String nameb = rs.getString("name");
                        String descriptionb = rs.getString("description");
                        int en_reductionb = rs.getInt("en_reduction");
                        double priceb = rs.getDouble("price");
                        double price_reducb = rs.getDouble("price_reduc");
                        int stock_quantityb = rs.getInt("stock_quantity");
                        int vendu_sans_reducb = rs.getInt("vendu_sans_reduc");
                        int vendu_reducb = rs.getInt("vendu_reduc");
                        String imageb = rs.getString("image");
                        Bijou bijou = new Bijou(id, nameb, descriptionb, en_reductionb, priceb, price_reducb, stock_quantityb, vendu_sans_reducb, vendu_reducb, imageb);
                        bijoux.add(bijou);
                        break;

                    default:
                        throw new IllegalArgumentException("Tableau inconnu : " + nomTab);
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

    public void graphvente(String nombtab) {
        // Création du jeu de données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query="SELECT * FROM "+nombtab;
            ResultSet resultSet = stmt.executeQuery(query);
            String namepd;
            while(resultSet.next()) {
                if(resultSet.getString("title")!=null){
                    namepd=resultSet.getString("title");
                }else {
                    namepd=resultSet.getString("name");
                }
                dataset.setValue(resultSet.getInt("vendu_sans_reduc"), "vente sans reduction", namepd);
                dataset.setValue(resultSet.getInt("vendu_reduc"), "vente avec reduction", namepd);
                int tot=resultSet.getInt("vendu_sans_reduc")+resultSet.getInt("vendu_reduc");
                dataset.setValue(tot, "Vente Total", namepd);
                dataset.setValue(resultSet.getInt("stock_quantity"), "stock_quantity", namepd);
            }
            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

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



        createDatabase();
        graphvente("livres");

        afficherColonne("livres");
        //for (String col : getColumnNames()) {System.out.println(col);}


       /* addSomething("comptes");

        addSomething("bijous");
        addSomething("employes");

        addSomething("bijoux");

        addSomething("livres");
        addSomething("accessoires");
        addSomething("panier");
        metAJourLigne();*/

        ConfirmationAchat();
        afficherHistoriquetabbrutarray();
        for (Historique historique : historiques) {
            System.out.println("Product ID: " + historique.getProduct_id());
            System.out.println("First name: " + historique.getFirst_name());
            System.out.println("Table nom: " + historique.getTable_nom());
            System.out.println("Quantity: " + historique.getQuantity());
            System.out.println("Created date: " + historique.getCreated_date());
            System.out.println("Title: " + historique.getTitle());
            System.out.println("Author: " + historique.getAuthor());
            System.out.println("Publisher: " + historique.getPublisher());
            System.out.println("Publication date: " + historique.getPublicationDate());
            System.out.println("ISBN: " + historique.getIsbn());
            System.out.println("En reduction: " + historique.getEnReduction());
            System.out.println("Price: " + historique.getPrice());
            System.out.println("Price reduc: " + historique.getPriceReduc());
            System.out.println("Stock quantity: " + historique.getStockQuantity());
            System.out.println("Vendu sans reduc: " + historique.getVenduSansReduc());
            System.out.println("Vendu reduc: " + historique.getVenduReduc());
            System.out.println("Image: " + historique.getImage());
            System.out.println("-----------------------------");
        }

        afficherPaniertabbrutarray();
        for (Panier panier : paniers) {
            System.out.println("Product ID: " + panier.getProduct_id());
            System.out.println("Table nom: " + panier.getTable_nom());
            System.out.println("Quantity: " + panier.getQuantity());
            System.out.println("Created date: " + panier.getCreated_date());
            System.out.println("Title: " + panier.getTitle());
            System.out.println("Author: " + panier.getAuthor());
            System.out.println("Publisher: " + panier.getPublisher());
            System.out.println("Publication date: " + panier.getPublicationDate());
            System.out.println("ISBN: " + panier.getIsbn());
            System.out.println("En reduction: " + panier.getEnReduction());
            System.out.println("Price: " + panier.getPrice());
            System.out.println("Price reduc: " + panier.getPriceReduc());
            System.out.println("Stock quantity: " + panier.getStockQuantity());
            System.out.println("Vendu sans reduc: " + panier.getVenduSansReduc());
            System.out.println("Vendu reduc: " + panier.getVenduReduc());
            System.out.println("Image: " + panier.getImage());
            System.out.println("-----------------------------");
        }
        descriptiontabbrutarray("comptes",0,1);
        // Affichage des comptes
        for (Compte compte : comptes) {
            System.out.println("ID : " + compte.getId());
            System.out.println("Prénom : " + compte.getFirstName());
            System.out.println("Nom : " + compte.getLastName());
            System.out.println("Email : " + compte.getEmail());
            System.out.println("Solde : " + compte.getBalance());
            System.out.println("Adresse : " + compte.getAddress());
            System.out.println("Admin : " + compte.getIsAdmin());
            System.out.println("--------------------");
        }

        descriptiontabbrutarray("livres",0,0);
        for (Livre livre : livres) {
            System.out.println("Titre: " + livre.getTitle());
            System.out.println("Auteur: " + livre.getAuthor());
            System.out.println("Editeur: " + livre.getPublisher());
            System.out.println("Date de publication: " + livre.getPublicationDate());
            System.out.println("ISBN: " + livre.getIsbn());
            System.out.println("Prix: " + livre.getPrice());
            System.out.println("Quantité en stock: " + livre.getStockQuantity());
            System.out.println("----------------------");
        }
        descriptiontabbrutarray("accessoires",1,0);
        for (Accessoire accessoire : accessoires) {
            System.out.println("Nom: " + accessoire.getName());
            System.out.println("Description: " + accessoire.getDescription());
            System.out.println("Prix: " + accessoire.getPrice() + "€");
            if (accessoire.getEn_reduction() == 1) {
                System.out.println("Prix réduit: " + accessoire.getPrice_reduc() + "€");
            }
            System.out.println("Stock: " + accessoire.getStock_quantity());
            System.out.println("Vendus sans réduction: " + accessoire.getVendu_sans_reducl());
            System.out.println("Vendus avec réduction: " + accessoire.getVendu_reduc());
            System.out.println("Image: " + accessoire.getImage());
            System.out.println("----------------------------------");
        }
        descriptiontabbrutarray("bijoux",1,1);
        for (Bijou bijou : bijoux) {
            System.out.println("ID: " + bijou.getId());
            System.out.println("Nom: " + bijou.getName());
            System.out.println("Description: " + bijou.getDescription());
            System.out.println("En réduction: " + bijou.getEn_reduction() + "%");
            System.out.println("Prix: " + bijou.getPrice() + "€");
            System.out.println("Prix réduit: " + bijou.getPrice_reduc() + "€");
            System.out.println("Quantité en stock: " + bijou.getStock_quantity());
            System.out.println("Nombre vendu sans réduction: " + bijou.getVendu_sans_reduc());
            System.out.println("Nombre vendu avec réduction: " + bijou.getVendu_reduc());
            System.out.println("Image: " + bijou.getImage());
            System.out.println("-----------------------------------------");
        }
        //addPanier();
        //addPanier();
        afficherPanier();
        ConfirmationAchat();
        afficherHistorique();
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
