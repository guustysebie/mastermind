/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import domein.DomeinController;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import resourceBundles.Taal;
import domein.DomeinController;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author guust
 */
public class UC1Controller extends AnchorPane {

    @FXML
    private Button btnMeldAan;
    @FXML
    private Label lblWelkom;
    @FXML
    private Button btnNederlands;
    @FXML
    private Button btnengels;
    @FXML
    private Button btnFrans;
    @FXML
    private Button btnDuits;
    @FXML
    private AnchorPane A_MeldAan;
    @FXML
    private AnchorPane A_TekstInlogscherm;
    @FXML
    private AnchorPane A_Registreer;
    @FXML
    private AnchorPane A_Taal;
    @FXML
    private PasswordField PW_wachtwoord;
    @FXML
    private TextField TXT_gebruikersnaam;
    @FXML
    private Button btnRegistreer;
    @FXML
    private Label TXT_Welkom;
    @FXML
    private Label TXT_Uitleg;
    @FXML
    private Button btnRegistreerRegistratie;
    @FXML
    private Label lblWelkomRegistratie;
    @FXML
    private TextField TXT_GebruikersnaamRegistratie;
    @FXML
    private PasswordField PW_WachtwoordRegistratie;
    @FXML
    private PasswordField PW_VerificatieWachtwoordRegistratie;
    @FXML
    private AnchorPane A_RegistratiePaneel;
    @FXML
    private AnchorPane A_KeuzeMenu;
    @FXML
    private Button btn_UC2;
    @FXML
    private Button btn_UC4;
    @FXML
    private Button btn_UC5;
    @FXML
    private Button btn_UC6;
    @FXML
    private Button btn_UC7;
    @FXML
    private Label lbl_KeuzePaneelNaamSpeler;
    @FXML
    private AnchorPane rootPane;

    private DomeinController dc;
    @FXML
    private Button btn_Taal;
    @FXML
    private Tooltip tool_gebruikersnaam;
    @FXML
    private Tooltip tool_wachtwoord;
    @FXML
    private Tooltip tool_bevestegingswachtwoord;

    /**
     * Constructor van de klasse UC1Controller. Initialiseert de FXMLLoader en de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public UC1Controller(DomeinController dc) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("UC1.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        this.dc = dc;

        try {
            loader.load();
        } catch (IOException ex) {
            Logger.getLogger(UC1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void initialize(URL url, ResourceBundle rb) {

    }

    /**
     * Laadt het startscherm aan de hand van de geselecteerde taal 
     * @param event 
     */
    @FXML
    private void handleTaal(ActionEvent event) {
        if (event.getTarget() == btnFrans || event.getTarget() == btnNederlands || event.getTarget() == btnDuits || event.getTarget() == btnengels) {
            A_Taal.setVisible(false);
            A_MeldAan.setVisible(true);
            A_TekstInlogscherm.setVisible(true);
            A_Registreer.setVisible(true);

        }
        if (event.getTarget() == btnFrans) {
            Taal.kiesTaal("fr");

        }
        if (event.getTarget() == btnNederlands) {
            Taal.kiesTaal("nl");

        }
        if (event.getTarget() == btnengels) {
            Taal.kiesTaal("en");

        }
        if (event.getTarget() == btnDuits) {
            Taal.kiesTaal("de");

        }

        btn_Taal.setVisible(true);
        btn_Taal.setText(Taal.getWoordUitBundle("guiTaal"));
        toonStartScherm();
    }

    /**
     * Eventhandler om de speler aan te melden
     * @param event 
     * @throws IllegalArgumentException wanneer de inloggegevens niet correct zijn
     */
    @FXML
    private void MeldAan(ActionEvent event) {
        if (event.getTarget() == btnMeldAan) {

            try {
                dc.meldAan(TXT_gebruikersnaam.getText(), PW_wachtwoord.getText());
                toonKeuzescherm();
            } catch (IllegalArgumentException ex) {
                Alert alert = new Alert(AlertType.WARNING);
                alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                alert.setHeaderText(ex.getMessage());
                alert.showAndWait();
            }

        }

    }

    /**
     * Eventhandler om naar het registratiescherm te gaan
     * @param event 
     */
    @FXML
    private void startregistratie(ActionEvent event) {
        if (event.getTarget() == btnRegistreer) {

            toonRegistratiescherm();

        }

    }

    /**
     * Eventhandler om de speler te registreren
     * @param event 
     * @throws IllegalArgumentException wanneer de ingevoerde gegevens niet voldoen aan de voorwaarden
     */
    @FXML
    private void registreer(ActionEvent event) {

        try {
            dc.registreer(TXT_GebruikersnaamRegistratie.getText(), PW_WachtwoordRegistratie.getText(), PW_VerificatieWachtwoordRegistratie.getText());
            toonKeuzescherm();
        } catch (IllegalArgumentException ex) {
            Alert alert = new Alert(AlertType.WARNING);
            alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
            alert.setHeaderText(ex.getMessage());
            alert.showAndWait();
        }

    }

    /**
     * Toont het scherm waar de speler zich kan registreren
     */
    public void toonRegistratiescherm() {

        A_MeldAan.setVisible(false);
        A_TekstInlogscherm.setVisible(false);
        A_Registreer.setVisible(false);
        A_RegistratiePaneel.setVisible(true);
        lblWelkomRegistratie.setText(Taal.getWoordUitBundle("welkomRegistreren"));
        TXT_GebruikersnaamRegistratie.setPromptText(Taal.getWoordUitBundle("naam"));
        PW_WachtwoordRegistratie.setPromptText(Taal.getWoordUitBundle("ww"));
        PW_VerificatieWachtwoordRegistratie.setPromptText(Taal.getWoordUitBundle("ww"));
        btnRegistreerRegistratie.setText(Taal.getWoordUitBundle("guiRegistreer"));
        tool_wachtwoord.setText(Taal.getWoordUitBundle("guiWwTool"));
        tool_gebruikersnaam.setText(Taal.getWoordUitBundle("guiGebnaamTool"));
        tool_bevestegingswachtwoord.setText("moet zelfde zijn als bevestegingswachtwoord");

    }

    /**
     * Toont het scherm waar de aangemelde speler kan selecteren wat hij wenst te doen. Hij kan kiezen uit spel starten, spel laden, iemand uitdagen,
     * een uitdaging aanvaarden of het klassement bekijken
     */
    public void toonKeuzescherm() {
        A_MeldAan.setVisible(false);
        A_TekstInlogscherm.setVisible(false);
        A_Registreer.setVisible(false);
        A_KeuzeMenu.setVisible(true);
        A_RegistratiePaneel.setVisible(false);
        lbl_KeuzePaneelNaamSpeler.setText(Taal.getWoordUitBundle("welkom") + " " + dc.geefAangemeldeSpelerNaam());
        btn_UC2.setText(Taal.getWoordUitBundle("guiStartSpel"));
        btn_UC4.setText(Taal.getWoordUitBundle("guiLaadSpel"));
        btn_UC5.setText(Taal.getWoordUitBundle("guiDaagUit"));
        btn_UC6.setText(Taal.getWoordUitBundle("guiAanvaardUitdaging"));
        btn_UC7.setText(Taal.getWoordUitBundle("guiKlassement"));
    }

    /**
     * Toont het startscherm waar de speler op terechtkomt nadat hij een taal heeft geselecteerd. Hier kan hij kiezen tussen aanmelden of registreren
     */
    public void toonStartScherm() {

        lblWelkom.setText(Taal.getWoordUitBundle("welkom"));
        PW_wachtwoord.setPromptText(Taal.getWoordUitBundle("ww"));
        TXT_gebruikersnaam.setPromptText(Taal.getWoordUitBundle("naam"));
        btnMeldAan.setText(Taal.getWoordUitBundle("guiMeldAan"));
        btnRegistreer.setText(Taal.getWoordUitBundle("guiRegistreer"));
        TXT_Welkom.setText(Taal.getWoordUitBundle("welkomMastermind"));
        TXT_Uitleg.setText(Taal.getWoordUitBundle("guiUitleg"));

    }

    /**
     * Toont het scherm waar de speler zijn taal kan kiezen
     */
    public void toonKeuzeTaalScherm() {

        A_Taal.setVisible(true);

    }

    /**
     * Eventhandler die ervoor zorgt dat wanneer er een knop wordt gedrukt het juiste scherm wordt geladen
     * @param event
     * @throws InterruptedException 
     */
    @FXML
    private void startAnderUC(ActionEvent event) throws InterruptedException {
        if (event.getTarget() == btn_UC2) {
            startKiesMoeilijkheidsgraad(false);
        }
        if (event.getTarget() == btn_UC4) {
            try {
                SpelLaden test = new SpelLaden(dc);
                Scene scene = new Scene(test, 250, 150);
                Stage stage = new Stage();
                stage.setResizable(false);
                stage.setScene(scene);
                stage.show();

            } catch (IllegalArgumentException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                alert.setHeaderText(Taal.getWoordUitBundle("geenSpelBesch"));
                alert.showAndWait();
            }

        }

        if (event.getTarget() == btn_UC5) {

            startKiesMoeilijkheidsgraad(true);

        }

        if (event.getTarget() == btn_UC6) {
            try {
                startLadenUitdaging();
            } catch (IllegalArgumentException e) {

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                alert.setHeaderText(Taal.getWoordUitBundle("guiGeenUitdagingBesch"));
                alert.showAndWait();

            }

        }
        if (event.getTarget() == btn_UC7) {

            toonKlassement();
        }
        /* 
         for (int i = 0; i < 55; i++) {
              ayylmao();

            

       }*/

    }

    public void ayylmao() throws InterruptedException {

        AnchorPane ap = new AnchorPane();

        BackgroundImage myBI = new BackgroundImage(new Image("/gui/ayylmao.jpg", 250, 250, false, true),
                BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        ap.setBackground(new Background(myBI));
        Scene scene = new Scene(ap, 250, 250);
        //Stage stage = (Stage) this.getScene().getWindow();
        Stage stage = new Stage();
        Random rand = new Random();
        int maxx = 1500;
        int minx = 1;
        stage.initStyle(StageStyle.UNDECORATED);
        int maxy = 1050;
        int miny = 1;
        int x = (int) (Math.random() * maxx + minx);
        int y = (int) (Math.random() * maxy + miny);

        stage.setScene(scene);
        stage.setX(x);
        stage.setY(y);
        stage.show();

    }

    /**
     * Laadt het scherm waar de moeilijkheidsgraad wordt geselecteerd
     * @param uitdaging geeft mee of het een uitdaging is ofniet
     */
    public void startKiesMoeilijkheidsgraad(boolean uitdaging) {

        KeuzeMoeilijkheidsgraadController moeilijkheidsgraadScherm;
        moeilijkheidsgraadScherm = new KeuzeMoeilijkheidsgraadController(dc, uitdaging);
        Scene scene = new Scene(moeilijkheidsgraadScherm);
        Stage stage = new Stage();
        stage.setTitle("Mastermind");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Laadt het scherm waar een uitdaging kan worden aanvaard
     */
    public void startLadenUitdaging() {
        AanvaardUitdaging uitdaging = new AanvaardUitdaging(dc);
        Scene scene = new Scene(uitdaging, 200, 200);
        Stage stage = new Stage();
        stage.setTitle("Mastermind");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Laadt het scherm waar het klassement wordt getoond
     */
    private void toonKlassement() {
        Klassement klassement = new Klassement(dc);
        Scene scene = new Scene(klassement, 700, 600);
        Stage stage = new Stage();
        stage.setTitle("Mastermind");
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Eventhandler die ervoor zorgt dat de taal kan worden aangepast
     * @param event 
     */
    @FXML
    private void terugNaarTaal(ActionEvent event) {

        toonKeuzeTaalScherm();
        handleTaal(event);
    }

}
