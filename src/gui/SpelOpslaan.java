/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class SpelOpslaan extends GridPane {

    private DomeinController dc;

    /**
     * Constructor van de klasse SpelOpslaan. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public SpelOpslaan(DomeinController dc) {
        
        this.dc = dc;
        this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        buildGui();

    }

    /**
     * Bouwt het scherm om het spel op te slaan
     * @see DomeinController#slaSpelOp(java.lang.String) 
     */
    public void buildGui() {

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25));
        TextField naamspel = new TextField();
        Label instructies = new Label(Taal.getWoordUitBundle("guiGeefNaamSpel"));
        Button slaspelop = new Button(Taal.getWoordUitBundle("guiSlaOp"));
        naamspel.setPromptText(Taal.getWoordUitBundle("guiSpelnaam"));

        add(instructies, 0, 0);
        add(naamspel, 0, 1);
        add(slaspelop, 0, 2);

        slaspelop.setOnAction((ActionEvent evt) -> {
            //    try {

            dc.slaSpelOp(naamspel.getText());
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle(Taal.getWoordUitBundle("guiSucces"));
            alert.setHeaderText(Taal.getWoordUitBundle("guiOpgeslagen"));
            alert.showAndWait();
            Stage stage = (Stage) (getScene().getWindow());
            stage.close();

            /* } catch (Exception e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
            alert.setHeaderText(e.getMessage());
            alert.showAndWait();
            
            //}*/
        });
    }

}
