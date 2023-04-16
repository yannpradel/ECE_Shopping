package com.example.demo.Controller;

import com.example.demo.SessionManager;
import com.example.demo.model.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
/**
 * Contrôleur de la vue profilePage.fxml.
 * Gère les interactions avec les éléments de l'interface graphique liés au profil utilisateur.
 */
public class ProfilController implements Initializable {

    @FXML
    private GridPane gridpane;

    public List<Historique> historiques;


    @FXML
    private Text firstname;

    @FXML
    private Text lastname;

    @FXML
    private Text email;

    @FXML
    private Text solde;

    @FXML
    private Text adressePostale;

    @FXML
    public List<Compte> comptes;

    @FXML
    public SplitMenuButton splitmenu = new SplitMenuButton();



    @FXML
    ScrollPane scrollpane = new ScrollPane();
    /**
     *Méthode appelée lors du clic sur le bouton qui dirige l'utilisateur vers la page des bijoux.
     *@param event Événement du clic sur le bouton
     *@throws IOException Si une erreur se produit lors du chargement de la page
     */
    @FXML
    void gotoBijoux(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bijouPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Change la scène courante vers la page du catalogue principal.
     * @param event L'événement de clic sur le bouton "Menu".
     * @throws IOException Si une erreur se produit lors du chargement de la page.
     */
    @FXML
    void gotoMenu(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/cataloguePage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Loads the "accessPage.fxml" file and displays it in the scene
     * @param event The ActionEvent triggered by clicking the "Accessoires" button
     * @throws IOException if the file "accessPage.fxml" cannot be loaded
     */
    @FXML
    void gotoAccess(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/accessPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Book" pour afficher la page de livres.
     * Charge la vue "bookPage.fxml" via FXMLLoader, la définit comme racine de la scène et affiche la scène dans la fenêtre actuelle.
     * @param event L'événement déclencheur de l'action.
     * @throws IOException Si une erreur se produit lors du chargement de la vue "bookPage.fxml".
     */

    @FXML
    void gotoBook(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/bookPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Panier".
     * Charge la page de panier et l'affiche dans la fenêtre en cours.
     * @param event L'événement de clic sur le bouton.
     * @throws IOException Si une erreur survient lors du chargement de la page.
     */

    @FXML
    void gotoPanier(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/panierPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Méthode appelée lors du clic sur le bouton "Profil".
     * Charge le fichier FXML correspondant à la page de profil et affiche la scène.
     *
     * @param event l'événement associé au clic sur le bouton "Profil"
     * @throws IOException si une erreur survient lors du chargement du fichier FXML
     */

    @FXML
    void gotoProfile(ActionEvent event) throws IOException {
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/profilePage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();

    }
    /**
     * Méthode appelée lorsqu'on clique sur le bouton "Déconnexion". Elle permet de déconnecter l'utilisateur en vidant la session en cours, puis de rediriger l'utilisateur vers la page de connexion.
     * @param event un événement ActionEvent
     * @throws IOException si une erreur d'entrée/sortie se produit
     */
    @FXML
    void gotoDisconnect(ActionEvent event) throws IOException {
        SessionManager.clearSession();
        FXMLLoader load = new FXMLLoader(getClass().getResource("/com/example/demo/ConnexionPage.fxml"));
        Parent root = load.load();

        Scene scene = new Scene(root);
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Affiche les informations d'un compte dont le prénom est égal à celui passé en paramètre.
     * Les informations sont récupérées depuis une base de données via l'objet DatabaseModel.
     * Les informations affichées incluent le prénom, le nom de famille, l'e-mail, le solde, et l'adresse postale du compte.
     * Ensuite, cette méthode affiche les objets historiques associés à ce compte dans une grille (GridPane).
     * Si un objet historique est un livre, l'image de couverture, le titre, l'auteur, le prix, et la quantité sont affichés.
     * Si un objet historique est un accessoire ou un bijou, l'image, le nom, la description, le prix, et la quantité sont affichés.
     * Chaque objet historique est affiché sur une ligne différente de la grille.
     * Enfin, un bouton "Supprimer" est affiché pour chaque objet historique.
     *
     * @param name le prénom du compte dont les informations sont à afficher
     */

    private void afficherInfo(String name)
    {
        DatabaseModel database = new DatabaseModel();
        database.descriptiontabbrutarray("comptes",0,0);
        gridpane.getChildren().clear();
        comptes = database.getComptes();
        for (Compte compte : comptes) {
            if (compte.getFirstName().equals(name)) {
                firstname.setText(compte.getFirstName());
                lastname.setText(compte.getLastName());
                email.setText(compte.getEmail());
                solde.setText(String.valueOf(compte.getBalance()));
                adressePostale.setText(compte.getAddress());
                database.afficherHistoriquetabbrutarray(compte.getFirstName());
                historiques = database.getHistoriques();
            }
        }




            int row = 0;
            for (Historique objet : historiques) {

                System.out.println(historiques.size());

                if(objet.getTable_nom().equals("accessoires") || objet.getTable_nom().equals("bijoux"))
                {
                    System.out.println("L'objet : " + row + "est un accessoire ou un bijou");
                    Image image = new Image(objet.getImage());


                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);


                    Label nomLabel = new Label(objet.getName());


                    Label authorLabel = new Label(objet.getDescription());
                    authorLabel.setWrapText(true);


                    Label quantLabel = new Label(String.valueOf(objet.getQuantity()));

                    Label priceLabel = new Label(String.valueOf(objet.getPrice()));
                    Button button2 = new Button();


                    button2.setText("Suprimmer");
                    button2.setStyle("-fx-background-color: #676767;");

                    // Ajoutez les éléments à la GridPane
                    //gridpane.add(imageView, 0, row);

                    gridpane.add(imageView,0,row);
                    gridpane.add(nomLabel, 1, row);
                    gridpane.add(authorLabel, 2, row);
                    gridpane.add(priceLabel,3,row);
                    gridpane.add(quantLabel,4,row);

                }

                if(objet.getTable_nom().equals("livres")) {
                    System.out.println("L'objet : " + row + "est un livre");
                    Image image = new Image(objet.getImage());


                    ImageView imageView = new ImageView();
                    imageView.setImage(image);
                    imageView.setFitWidth(100);
                    imageView.setPreserveRatio(true);


                    Label nomLabel = new Label(objet.getTitle());


                    Label quantLabel = new Label(String.valueOf(objet.getQuantity()));


                    Label authorLabel = new Label(objet.getAuthor());
                    authorLabel.setWrapText(true);

                    Label priceLabel = new Label(String.valueOf(objet.getPrice()));
                    Button button2 = new Button();


                    button2.setText("Suprimmer");
                    button2.setStyle("-fx-background-color: #676767;");

                    gridpane.add(imageView, 0, row);
                    gridpane.add(nomLabel, 1, row);
                    gridpane.add(authorLabel, 2, row);
                    gridpane.add(priceLabel, 3, row);
                    gridpane.add(quantLabel,4,row);
                }



                row++;
            }
            scrollpane.setContent(gridpane);


        }

    /**
     * Cette méthode est appelée lors du chargement de l'interface utilisateur pour initialiser les éléments de l'interface et afficher les informations de l'utilisateur connecté. Si l'utilisateur connecté est un administrateur, elle affiche également un menu déroulant avec la liste des utilisateurs, permettant à l'administrateur de sélectionner un utilisateur et d'afficher ses informations.
     *
     * @param url l'URL de l'emplacement racine de l'interface utilisateur
     * @param rb les ressources locales pour la localisation
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        splitmenu.setVisible(false);
        afficherInfo(SessionManager.getLoggedInUser().getFirstName());
        if(SessionManager.getLoggedInUser().getIsAdmin()==1)
        {
            splitmenu.setVisible(true);
            for (Compte compte : comptes)
            {
                MenuItem userNext = new MenuItem(compte.getFirstName());
                userNext.setOnAction(e -> {
                    afficherInfo(compte.getFirstName());
                });
                splitmenu.getItems().add(userNext);
            }
        }

    }


}
