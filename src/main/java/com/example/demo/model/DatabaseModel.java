package com.example.demo.model;
import java.lang.Exception;
import java.math.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

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

    private List<Historique> souhaits=new ArrayList<>();
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

    public List<Historique> getSouhaits() {
        return souhaits;
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

            String createTable8Query = "CREATE TABLE souhait " +
                    "(id INT not NULL AUTO_INCREMENT, " +
                    " first_name VARCHAR(1000), " +
                    " product_id INT, " +
                    " table_nom VARCHAR(50), " +
                    " quantity INT, " +
                    " date_created DATETIME DEFAULT CURRENT_TIMESTAMP, " +
                    " PRIMARY KEY ( id ))";
            stmt.executeUpdate(createTable8Query);
            System.out.println("Table souhait client created successfully...");

            //INITILISATION DES TABLEAUX
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO comptes (last_name, first_name, email, password, balance, admin) VALUES "
                    + "('Dupont', 'Jean', 'jean.dupont@mail.com', '123456', 1000.0,1),"
                    + "('Martin', 'Lucie', 'lucie.martin@mail.com', 'abcdef', 2000.0,0),"
                    + "('Garcia', 'Pedro', 'pedro.garcia@mail.com', 'ghijkl', 500.0,0),"
                    + "('Doe', 'John', 'john.doe@mail.com', 'mnopqr', 3000.0,0),"
                    + "('Smith', 'Alice', 'alice.smith@mail.com', 'stuvwx', 1500.0,0)";
            stmt.executeUpdate(query);

            query = "INSERT INTO accessoires (name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES " +
                    "('Souris sans fil', 'Souris optique sans fil avec 5 boutons', 0, 29.99, 20.99, 70, 50, 25, 'https://m.media-amazon.com/images/I/61AWLK29YrL._AC_SX679_.jpg')," +
                    "('Clavier filaire', 'Clavier filaire avec 105 touches', 0, 19.99, 10.99, 41, 25, 10, 'https://www.mdose.fr/image/cache/catalog/migrated/c/l/clav_med_ip65-500x500.jpg')," +
                    "('Casque audio', 'Casque audio stéréo avec micro', 0, 49.99, 39.99, 90, 20, 6, 'https://encrypted-tbn0.gstatic.com/shopping?q=tbn:ANd9GcTR8XcjJuehkVX3jUpO4mJMruYfrPDXY2p4XcbUM4itEeNBeNfcSBJ_DQvZfNDyoVU4IxT7kNlAW8k&usqp=CAc')," +
                    "('Webcam HD', 'Webcam HD 720p avec microphone intégré', 1, 39.99, 29.99, 150, 15, 4, 'https://m.media-amazon.com/images/I/71xjo0lERgL.__AC_SX300_SY300_QL70_ML2_.jpg')," +
                    "('Tapis de souris', 'Tapis de souris avec surface lisse', 1, 9.99, 5.99, 100, 100, 3, 'https://encrypted-tbn1.gstatic.com/shopping?q=tbn:ANd9GcTT1ESt5FTBqbcsejdqyzbIed4-MMwhXR1tLNMTtZw04wrksvsKYk0fSUtwcSV0RhSmg8PU8o_Mdg&usqp=CAc')," +
                    "('NexiGo Camera ordi', 'Camera parfaite pour les Zoom, Skype, Teams et autres appels video, se branche en USB.', 0, 39.99, 29.99, 15, 20, 5, 'https://m.media-amazon.com/images/I/51GY0Dy3o7L._AC_SX425_.jpg')," +
                    "('PreSonus Eris', 'Haut parleurs parfait pour la stereo, comporte des egaliseurs et des amplificateurs.', 0, 95.00, 93.00, 20, 15, 5, 'https://m.media-amazon.com/images/I/71rVldZn9OL._AC_SX679_.jpg')";
            stmt.executeUpdate(query);

            String insertQuery = "INSERT INTO bijoux (name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = conn.prepareStatement(insertQuery);
            // Étape 3 : Ajout de 7 entrées uniques
            pstmt.setString(1, "Bague Princesse argentee");
            pstmt.setString(2, "Bague taille unique et reglable avec un anneau en métal.");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("18.00"));
            pstmt.setBigDecimal(5, new BigDecimal("16.99"));
            pstmt.setInt(6, 50);
            pstmt.setInt(7, 25);
            pstmt.setInt(8, 25);
            pstmt.setString(9,"https://www.luzaka.com/media/catalog/product/cache/07155abda0f160b54f88bbc3c8fc1ae9/_/5/_55296.jpg");
            pstmt.executeUpdate();

            pstmt.setString(1, "Glamira Bague Efrata");
            pstmt.setString(2, "Bague en or rouge 375 avec des pierres de cristal Swarovski AAAAAA.");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("295.00"));
            pstmt.setBigDecimal(5, new BigDecimal("279.99"));
            pstmt.setInt(6, 30);
            pstmt.setInt(7, 15);
            pstmt.setInt(8, 15);
            pstmt.setString(9,"https://cdn-media.glamira.com/media/product/newgeneration/view/1/sku/efrata/diamond/ruby-Swarovsky_AAAAA/stone2/diamond-Swarovsky_AAAAA/alloycolour/red.jpg?width=800&height=800");
            pstmt.executeUpdate();

            pstmt.setString(1, "Bague Trois Grains d'Amour");
            pstmt.setString(2, "Trio de diamants qui plaira à toutes celles qui aiment l'originalité avec ce solitaire à 3 têtes.");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("792.00"));
            pstmt.setBigDecimal(5, new BigDecimal("785.99"));
            pstmt.setInt(6, 20);
            pstmt.setInt(7, 10);
            pstmt.setInt(8, 10);
            pstmt.setString(9,"https://static.mauboussin.fr/pub/media/catalog/product/cache/8a51a7c718bce6fcfb3a50a8116c660e/b/a/bague_3gr_d_amour_or_blanc_internet.png");
            pstmt.executeUpdate();

            pstmt.setString(1, "Bague Tourmaline vert menthe et Diamant poires");
            pstmt.setString(2, "Anneau en or rose 18 carats, la couleur douce et fraîche de la tourmaline menthe se marie avec l'or dans une composition delicate.");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("2500.00"));
            pstmt.setBigDecimal(5, new BigDecimal("2500.00"));
            pstmt.setInt(6, 5);
            pstmt.setInt(7, 5);
            pstmt.setInt(8, 0);
            pstmt.setString(9,"https://loiseau-aycardi.com/wp-content/uploads/2022/10/BJLA3253-500x500.png");
            pstmt.executeUpdate();

            pstmt.setString(1, "Collier Pendant Halo Spectaculaire Scintillant");
            pstmt.setString(2, "Ce bijou au design subtil et à la forme delicate ne manquera pas de faire parler de lui avec ses somptueux détails.");
            pstmt.setInt(3, 1);
            pstmt.setBigDecimal(4, new BigDecimal("89.00"));
            pstmt.setBigDecimal(5, new BigDecimal("79.99"));
            pstmt.setInt(6, 10);
            pstmt.setInt(7, 5);
            pstmt.setInt(8, 5);
            pstmt.setString(9,"https://cdn-fsly.yottaa.net/60a2795ad93140a5dc7453d7/fr.pandora.net/v~4b.f/dw/image/v2/BFCR_PRD/on/demandware.static/-/Sites-pandora-master-catalog/default/dw69e9ff1c/productimages/main/390055C01_RGB.jpg?sw=900&sh=900&sm=fit&sfrm=png&bgcolor=F5F5F5&yocs=8_d_");
            pstmt.executeUpdate();

            pstmt.setString(1, "Bracelet sur cordon Menottes dinh van R8");
            pstmt.setString(2, "Le bracelet icone de dinh van.");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("570.00"));
            pstmt.setBigDecimal(5, new BigDecimal("570.00"));
            pstmt.setInt(6, 5);
            pstmt.setInt(7, 5);
            pstmt.setInt(8, 0);
            pstmt.setString(9,"https://media.dinhvan.com/media/catalog/product/cache/58265684fe9891cc55c77f2ab678b6e0/3/1/319101-bracelet-sur-cordon-menottes-dinh-van-r8-small_4.png");
            pstmt.executeUpdate();

            pstmt.setString(1, "Puces Move Uno");
            pstmt.setString(2, "Faciles à porter au quotidien, ces boucles d'oreilles en or rose s'associeront à tous les styles, du plus simple au plus sophistique.");
            pstmt.setInt(3, 0);
            pstmt.setBigDecimal(4, new BigDecimal("280.00"));
            pstmt.setBigDecimal(5, new BigDecimal("280.00"));
            pstmt.setInt(6, 10);
            pstmt.setInt(7, 10);
            pstmt.setInt(8, 0);
            pstmt.setString(9,"https://messika.cdn-tech.io/media/catalog/product/cache/f68e9136285bb96b85e5514f4abecbaf/b/o/boucles-oreilles-puces-diamant-or-rose-move-uno-05634_1.jpg");
            pstmt.executeUpdate();

            PreparedStatement ps = conn.prepareStatement("INSERT INTO livres (title, author, publisher, publication_date, isbn, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

            // Premier livre
            ps.setString(1, "Le Seigneur des anneaux Tome Un");
            ps.setString(2, "J.R.R. Tolkien");
            ps.setString(3, "Christian Bourgois éditeur");
            ps.setString(4, "01/09/2022");
            ps.setString(5, "2267027003");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("20.00"));
            ps.setBigDecimal(8, new BigDecimal("18.99"));
            ps.setInt(9, 50);
            ps.setInt(10, 10);
            ps.setInt(11, 40);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/fb/87/01/100347/1540-1/tsp20221022061611/Le-seigneur-des-anneaux-T1-La-fraternite-de-l-anneau.jpg");
            ps.executeUpdate();

            // Deuxième livre
            ps.setString(1, "Harry Potter à l'école des sorciers");
            ps.setString(2, "J.K. Rowling");
            ps.setString(3, "Gallimard Jeunesse");
            ps.setString(4, "12/10/2017");
            ps.setString(5, "2070543025");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("9.30"));
            ps.setBigDecimal(8, new BigDecimal("7.99"));
            ps.setInt(9, 20);
            ps.setInt(10, 10);
            ps.setInt(11, 10);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/ba/d8/1d/1956026/1540-1/tsp20230104085420/Harry-Potter-a-l-ecole-des-sorciers.jpg");
            ps.executeUpdate();

            // Troisième livre
            ps.setString(1, "1984");
            ps.setString(2, "George Orwell");
            ps.setString(3, "Gallimard");
            ps.setString(4, "28/05/2020");
            ps.setString(5, "207036822X");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("9.20"));
            ps.setBigDecimal(8, new BigDecimal("8.50"));
            ps.setInt(9, 10);
            ps.setInt(10, 5);
            ps.setInt(11, 5);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/10/35/01/79120/1540-1/tsp20230104085223/1984.jpg");
            ps.executeUpdate();

            // Quatrième livre
            ps.setString(1, "Made in Abyss Tome Un");
            ps.setString(2, "Akhito Tsukushi");
            ps.setString(3, "Ototo");
            ps.setString(4, "18/05/2018");
            ps.setString(5, "2377171176");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("9.35"));
            ps.setBigDecimal(8, new BigDecimal("8.99"));
            ps.setInt(9, 15);
            ps.setInt(10, 10);
            ps.setInt(11, 5);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/92/48/90/9455762/1540-1/tsp20230102071834/Made-in-aby.jpg");
            ps.executeUpdate();

            // Cinquième livre
            ps.setString(1, "Programmer en Java");
            ps.setString(2, "Claude Delannoy");
            ps.setString(3, "EYrolles");
            ps.setString(4, "01/10/2020");
            ps.setString(5, "2212675364");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("38.00"));
            ps.setBigDecimal(8, new BigDecimal("31.99"));
            ps.setInt(9, 20);
            ps.setInt(10, 10);
            ps.setInt(11, 10);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/dc/30/1b/1781980/1540-1/tsp20230315090641/Programmer-en-Java.jpg");
            ps.executeUpdate();

            // Sixième livre
            ps.setString(1, "Tu mourras moins bete Tome Un");
            ps.setString(2, "Marion Montaigne");
            ps.setString(3, "Ankama");
            ps.setString(4, "24/09/2021");
            ps.setString(5, "2359102206");
            ps.setInt(6, 0);
            ps.setBigDecimal(7, new BigDecimal("17.90"));
            ps.setBigDecimal(8, new BigDecimal("17.90"));
            ps.setInt(9, 10);
            ps.setInt(10, 10);
            ps.setInt(11, 0);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/1c/35/33/3355932/1540-1/tsp20220418071430/Tu-mourras-moins-bete-tome-1-Nouvelle-edition.jpg");
            ps.executeUpdate();

            // Septième livre
            ps.setString(1, "Vivre le Japon");
            ps.setString(2, "Yukata Yazawa");
            ps.setString(3, "Voyages Gallimard");
            ps.setString(4, "07/03/2019");
            ps.setString(5, "2742457933");
            ps.setInt(6, 1);
            ps.setBigDecimal(7, new BigDecimal("25.00"));
            ps.setBigDecimal(8, new BigDecimal("23.75"));
            ps.setInt(9, 15);
            ps.setInt(10, 5);
            ps.setInt(11, 10);
            ps.setString(12, "https://static.fnac-static.com/multimedia/Images/FR/NR/85/fb/a4/10812293/1540-1/tsp20230315092304/Vivre-le-Japon.jpg");
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

    public void addIntoCompte(String last_name, String first_name, String email, String password,float balance,int admin) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmt = conn.createStatement();
            String query = "INSERT INTO comptes (last_name, first_name, email, password, balance, admin) VALUES "
                    + "('"+last_name+"', '"+first_name+"', '"+email+"', '"+password+"', "+balance+","+admin+")";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addIntoAccessoires(String name, String description, int en_reduction,float price,float price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String query = "INSERT INTO accessoires (name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES "
                    + "('"+name+"', '"+description+"', "+en_reduction+", "+price+", "+price_reduc+", "+stock_quantity+", "+vendu_sans_reduc+", "+vendu_reduc+", '"+image+"')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addIntoBijoux(String name, String description, int en_reduction,float price,float price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String query = "INSERT INTO bijoux (name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES "
                    + "('"+name+"', '"+description+"', "+en_reduction+", "+price+", "+price_reduc+", "+stock_quantity+", "+vendu_sans_reduc+", "+vendu_reduc+", '"+image+"')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addIntoLivres(String title, String author, String publisher, String publication_date,String isbn,int en_reduction,float price,float price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image) {
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String query = "INSERT INTO livres (title, author, publisher, publication_date, isbn, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image) VALUES "
                    + "('"+title+"', '"+author+"', '"+publisher+"',  '"+publication_date+"', '"+isbn+"',"+en_reduction+", "+price+", "+price_reduc+", "+stock_quantity+", "+vendu_sans_reduc+", "+vendu_reduc+", '"+image+"')";
            stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void effaceSouhaituniqueitem(int ID) {
        int num = ID;
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE FROM souhait WHERE id="+num);
            System.out.println("souhait where id="+num+" cleared successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addSouhait(String first_name,int productId, String tableNom, int quantity){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String sql = "INSERT INTO souhait (first_name, product_id, table_nom, quantity) VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1,first_name);
            pstmt.setInt(2, productId);
            pstmt.setString(3, tableNom);
            pstmt.setInt(4, quantity);
            int rowsInserted = pstmt.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("L'article a été ajouté au souhait avec succès.");
            }
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void afficherSouhaittabbrutarray(String firstname){
        String querylia = "SELECT * FROM souhait WHERE first_name= '"+firstname+"'";
        souhaits.clear();
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

                            souhaits.add(accessoire);
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
                            souhaits.add(livre);
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
                            souhaits.add(bijou);
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

    public void afficherSouhaittabbrutarray(){
        String querylia = "SELECT * FROM souhait";

        souhaits.clear();
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

                            souhaits.add(accessoire);
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
                            souhaits.add(livre);
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
                            souhaits.add(bijou);
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

    public void addPanierviaSouhait(int ID){
        //addPanierviaSouhait(getSouhait(i).getId()) en vif
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {

            stmt = conn.createStatement();

            String query="SELECT * FROM souhait WHERE id="+ID;
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String tableNom = rs.getString("table_nom");
                int quantity = rs.getInt("quantity");
                int productId = rs.getInt("product_id");
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
            }



        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addPanierdansSouhaitdeco(String username){
        //addPanierdansSouhaitdeco(getSouhait(i).getId()) en vif
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {

            stmt = conn.createStatement();

            String query="SELECT * FROM panier";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()){
                String tableNom = rs.getString("table_nom");
                int quantity = rs.getInt("quantity");
                int productId = rs.getInt("product_id");
                String sql = "INSERT INTO souhait (first_name, product_id, table_nom, quantity) VALUES (?, ?, ?, ?)";
                PreparedStatement pstmt = conn.prepareStatement(sql);
                pstmt.setString(1,username);
                pstmt.setInt(2, productId);
                pstmt.setString(3, tableNom);
                pstmt.setInt(4, quantity);
                int rowsInserted = pstmt.executeUpdate();
                if (rowsInserted > 0) {
                    System.out.println("L'article a été ajouté au souhait avec succès.");
                }
                pstmt.close();
            }



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
            // Création de la requête SQL pour récupérer toutes les données du tableau "comptes"
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
            // Création de la requête SQL pour récupérer toutes les données du tableau "comptes"
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


        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query;
            // Création de la requête SQL pour récupérer toutes les données du tableau "comptes"
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
                        Accessoire accessoire = new Accessoire(id, name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image);
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
                        Accessoire accessoire = new Accessoire(id, name, description, en_reduction, price, price_reduc, stock_quantity, vendu_sans_reduc, vendu_reduc, image);

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

    public void afficherProfilUtilisateurUniqueFX(String name) {

        comptes.clear();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query = "SELECT * FROM comptes WHERE name='"+name+"'";
            // Création de la requête SQL pour récupérer toutes les données du tableau "comptes"

            // Exécution de la requête et récupération du résultat
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                        String first_name = rs.getString("first_name");
                        String last_name = rs.getString("last_name");
                        String email = rs.getString("email");
                        String password = rs.getString("password");
                        double balance = rs.getDouble("balance");
                        String address = rs.getString("adress");
                        int admin = rs.getInt("admin");
                        Compte compte = new Compte(id, first_name, last_name, email, password, balance, address, admin);
                        comptes.add(compte);
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

    public void supprimerLigneViaFX(String nomTable, int id){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String query = "DELETE FROM " + nomTable + " WHERE id="+id;
            PreparedStatement ps = conn.prepareStatement(query);
            int rowsDeleted = ps.executeUpdate();
            // Affichage du résultat
            System.out.println(rowsDeleted + " ligne(s) ont été supprimées.");
            query = "DELETE FROM panier WHERE id="+id;
            ps = conn.prepareStatement(query);
            rowsDeleted = ps.executeUpdate();
            // Affichage du résultat
            System.out.println(rowsDeleted + " ligne(s) ont été supprimées.");
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

    public void mettreAJourAccessoiresFX(int id,String name, String description, int en_reduction,double price,double price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
        String queryUpdate = "UPDATE accessoires SET name='"+name+"',description='"+description+"', en_reduction="+en_reduction+", price="+price+", price_reduc="+price_reduc+", stock_quantity="+stock_quantity+", vendu_sans_reduc="+vendu_sans_reduc+", vendu_reduc="+vendu_reduc+", image='"+image +"' WHERE id="+id;
        // exécution de la requête UPDATE pour mettre à jour les informations de la ligne
        PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
        int rowsUpdated = psUpdate.executeUpdate();
        // affichage du résultat
        System.out.println(rowsUpdated + " ligne(s) ont été mises à jour.");
    } catch (SQLException e) {
        e.printStackTrace();
    }
    }

    public void mettreAJourBijouxFX(int id,String name, String description, int en_reduction,double price,double price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String queryUpdate = "UPDATE bijoux SET name='"+name+"',description='"+description+"', en_reduction="+en_reduction+", price="+price+", price_reduc="+price_reduc+", stock_quantity="+stock_quantity+", vendu_sans_reduc="+vendu_sans_reduc+", vendu_reduc="+vendu_reduc+", image='"+image +"' WHERE id="+id;
            // exécution de la requête UPDATE pour mettre à jour les informations de la ligne
            PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
            int rowsUpdated = psUpdate.executeUpdate();
            // affichage du résultat
            System.out.println(rowsUpdated + " ligne(s) ont été mises à jour.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void mettreAJourLivresFX(int id,String title, String author, String publisher, String publication_date,String isbn, int en_reduction,double price,double price_reduc, int stock_quantity, int vendu_sans_reduc, int vendu_reduc, String image){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String queryUpdate = "UPDATE livres SET title='"+title+"', author='"+author+"', publisher='"+publisher+"', publication_date='"+publication_date+"',isbn='"+isbn+"',  en_reduction="+en_reduction+", price="+price+", price_reduc="+price_reduc+", stock_quantity="+stock_quantity+", vendu_sans_reduc="+vendu_sans_reduc+", vendu_reduc="+vendu_reduc+", image='"+image +"' WHERE id="+id;
            // exécution de la requête UPDATE pour mettre à jour les informations de la ligne
            PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
            int rowsUpdated = psUpdate.executeUpdate();
            // affichage du résultat
            System.out.println(rowsUpdated + " ligne(s) ont été mises à jour.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mettreAJourCompteFX(int id,String last_name, String first_name, String email, String password,float balance,int admin){
        try (Connection conn = DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            String queryUpdate = "UPDATE comptes SET last_name='"+last_name+"', first_name='"+first_name+"', email='"+email+"', password='"+password+"', balance="+balance+", admin="+admin+" WHERE id="+id;
            // exécution de la requête UPDATE pour mettre à jour les informations de la ligne
            PreparedStatement psUpdate = conn.prepareStatement(queryUpdate);
            int rowsUpdated = psUpdate.executeUpdate();
            // affichage du résultat
            System.out.println(rowsUpdated + " ligne(s) ont été mises à jour.");
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
                "Vente par produit", // Titre
                "Produit", // Axe des abscisses
                "Ventes", // Axe des ordonnées
                dataset // Jeu de données
        );

        // Création de la fenêtre contenant le graphique
        ChartFrame frame = new ChartFrame("Graphique en barres", chart);
        frame.pack();
        frame.setVisible(true);
    }
    public void graphventebis(String nombtab) {
        // Création du jeu de données
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query="SELECT * FROM "+nombtab;
            ResultSet resultSet = stmt.executeQuery(query);
            String namepd;
            while(resultSet.next()) {
                    namepd=resultSet.getString("name");
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
                "Vente par produit", // Titre
                "Produit", // Axe des abscisses
                "Ventes", // Axe des ordonnées
                dataset // Jeu de données
        );

        // Création de la fenêtre contenant le graphique
        ChartFrame frame = new ChartFrame("Graphique en barres", chart);
        frame.pack();
        frame.setVisible(true);
    }
    public void graphrevenu(String nombtab) {
        // Création du jeu de données
        DefaultPieDataset dataset = new DefaultPieDataset();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query="SELECT * FROM "+nombtab;
            ResultSet resultSet = stmt.executeQuery(query);
            String namepd;
            while(resultSet.next()) {
                if(resultSet.getString("title")!=null){
                    namepd=resultSet.getString("title");
                } else {
                    namepd=resultSet.getString("name");
                }
                double revenuSansReduction = resultSet.getDouble("price") * resultSet.getInt("vendu_sans_reduc");
                double revenuAvecReduction = resultSet.getDouble("price_reduc") * resultSet.getInt("vendu_reduc");
                dataset.setValue(namepd + " revenu sans reduction", revenuSansReduction);
                dataset.setValue(namepd + " revenu avec reduction", revenuAvecReduction);
            }
            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Création du camembert et affichage
        JFreeChart chart = ChartFactory.createPieChart("Revenus", dataset, true, true, false);
        // Création de la fenêtre contenant le graphique
        ChartFrame frame = new ChartFrame("Camembert", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void graphrevenubis(String nombtab) {
        // Création du jeu de données
        DefaultPieDataset dataset = new DefaultPieDataset();
        try (Connection conn= DriverManager.getConnection(DB_URL + DATABASE_NAME, USER, PASS)) {
            stmt = conn.createStatement();
            String query="SELECT * FROM "+nombtab;
            ResultSet resultSet = stmt.executeQuery(query);
            String namepd;
            while(resultSet.next()) {
                    namepd=resultSet.getString("name");
                double revenuSansReduction = resultSet.getDouble("price") * resultSet.getInt("vendu_sans_reduc");
                double revenuAvecReduction = resultSet.getDouble("price_reduc") * resultSet.getInt("vendu_reduc");
                dataset.setValue(namepd + " revenu sans reduction", revenuSansReduction);
                dataset.setValue(namepd + " revenu avec reduction", revenuAvecReduction);
            }
            stmt.close();
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Création du camembert et affichage
        JFreeChart chart = ChartFactory.createPieChart("Revenus", dataset, true, true, false);
        // Création de la fenêtre contenant le graphique
        ChartFrame frame = new ChartFrame("Camembert", chart);
        frame.pack();
        frame.setVisible(true);
    }

    public void run() {

        createDatabase();
        graphvente("livres");
        graphrevenu("livres");
        graphventebis("accessoires");
        graphrevenubis("accessoires");
        graphrevenubis("bijoux");
        graphventebis("bijoux");
        //descriptiontabbrutarray("livres",0,0);
        //mettreAJourLivresFX(livres.get(0).getId(), livres.get(2).getTitle(), livres.get(2).getAuthor(), livres.get(2).getPublisher(), livres.get(2).getPublicationDate(), livres.get(2).getIsbn(), livres.get(2).getEnReduction(),(float)livres.get(2).getPrice(), (float)livres.get(2).getPriceReduc(), livres.get(2).getStockQuantity(), livres.get(2).getVenduSansReduc(), livres.get(2).getVenduReduc(), livres.get(2).getImage());
        afficherColonne("livres");
        //for (String col : getColumnNames()) {System.out.println(col);}

       // int id = 1;
        //String name = "Nouveau nom";
        //String description = "Nouvelle description";
        //String image = "nouvelle_image.jpg";

// Appel de la méthode pour mettre à jour l'accessoire dans la base de données

        //mettreAJourAccessoiresFX(id, name, description, 1, 29.99, 0.00, 10, 5, 0, image);

        descriptiontabbrutarray("accessoires",0,0);
        for (Accessoire accessoire : accessoires) {
            System.out.println("Nom: " + accessoire.getName());
            System.out.println("Description: " + accessoire.getDescription());
            System.out.println("Prix: " + accessoire.getPrice() + "€");
            System.out.println("reduc ou pas" + accessoire.getEn_reduction());
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
