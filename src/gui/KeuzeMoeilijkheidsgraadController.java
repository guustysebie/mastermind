/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import resourceBundles.Taal;

/**
 * FXML Controller class
 *
 * @author KriNi
 */
public class KeuzeMoeilijkheidsgraadController extends GridPane {

    private DomeinController dc;
    private boolean isuitdaging;
    @FXML
    private Button btn_makkelijk;
    @FXML
    private Button btn_normaal;
    @FXML
    private Button btn_moeilijk;
    @FXML
    private Label lbl_moeilijkheidsgraad;

    /**
     * Bouwt het scherm om de moeilijkheidsgraad te selecteren en initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     * @param uitdaging geeft mee of het al dan niet een uitdaging is
     */
    public KeuzeMoeilijkheidsgraadController(DomeinController dc, boolean uitdaging) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("keuzeMoeilijkheidsgraad.fxml"));
        loader.setController(this);
        this.dc = dc;
        this.isuitdaging = uitdaging;
        this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        try {

            this.getChildren().add(loader.load());
            lbl_moeilijkheidsgraad.setText(dc.geefMoeilijkheidsgraadEnGewonnenSpellen());
            btn_makkelijk.setText(Taal.getWoordUitBundle("guiMakkelijk"));
            btn_normaal.setText(Taal.getWoordUitBundle("guiNormaal"));
            btn_moeilijk.setText(Taal.getWoordUitBundle("guiMoeilijk"));
            btn_makkelijk.getStyleClass().add("OrangeButton");
            btn_normaal.getStyleClass().add("OrangeButton");
            btn_moeilijk.getStyleClass().add("OrangeButton");

        } catch (IOException ex) {
            Logger.getLogger(UC1Controller.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    /**
     * Maakt het spelbord aan op basis van de geselecteerde moeilijkheidsgraad
     * @param event 
     * @throws IllegalArgumentException wanneer de speler iemand probeert uit te dagen maar er doet zich een fout voor tijdens de runtime
     */
    @FXML
    private void kiesmoeilijkheidsgraad(ActionEvent event) {

        if (event.getTarget() == btn_makkelijk) {
            dc.maakSpelAan(1);

            if (isuitdaging == true) {
                try {
                    daagIemandUit();
                } catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                    startspelbod();

                }
            } else {

                startspelbod();

            }

        } else if (event.getTarget() == btn_normaal) {

            dc.maakSpelAan(2);
            if (isuitdaging == true) {
                try {
                    daagIemandUit();
                } catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                    startspelbod();

                }

            } else {

                startspelbod();

            }

        } else if (event.getTarget() == btn_moeilijk) {

            dc.maakSpelAan(3);
            if (isuitdaging == true) {

                try {
                    daagIemandUit();
                } catch (IllegalArgumentException e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();
                    startspelbodMoeilijk();

                }

            } else {

                startspelbodMoeilijk();

            }

        }
    }
    /**
     * Creëert het spelbord voor de moeilijkheidsgraad makkelijk en normaal aan de hand van de creatie van een Scene met meegegeven hoogte en breedte van 550x550
     */
    public void startspelbod() {

        SpelbordController spelbord = new SpelbordController(dc);
        Scene scene = new Scene(spelbord, 550, 550);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    
    /**
     * Creëert het spelbord voor de moeilijkheidsgraad moeilijk aan de hand van de creatie van een Scene met meegegeven hoogte en breedte van 650x550
     */
    public void startspelbodMoeilijk() {

        SpelbordController spelbord = new SpelbordController(dc);
        Scene scene = new Scene(spelbord, 650, 550);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }
    
    /**
     * Creëert het scherm waar je een speler kan selecteren om uit te dagen op een Scene van 250x200
     */
    public void daagIemandUit() {

        DaagIemandUit startuitdaging = new DaagIemandUit(dc);
        Scene scene = new Scene(startuitdaging, 250, 200);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
