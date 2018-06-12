/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import domein.Spel;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class SpelLaden extends GridPane {

    private DomeinController dc;

    private List<Spel> spelletjes;

    /**
     * Constructor van de klasse SpelLaden. Update het klassement, laadt het spel en initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     * @see DomeinController#updateKlassement() 
     * @see DomeinController#startLadenSpel() 
     */
    public SpelLaden(DomeinController dc) {

        this.dc = dc;
        dc.updateKlassement();
        this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        try {
            spelletjes = dc.startLadenSpel();
            buildGui();

        } catch (IllegalArgumentException e) {
       /*     Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
            alert.setHeaderText(Taal.getWoordUitBundle("geenSpelBesch"));
            alert.showAndWait();
         */   
         throw new IllegalArgumentException("meh");
          //  stage.close();
          //  Stage stage = (Stage) (getScene().getWindow());
           // stage.close();

        }

    }

    /**
     * Bouwt het scherm waar je een spel kan selecteren om te laden
     */
    public void buildGui() {
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25));
        ChoiceBox spellen = new ChoiceBox();

        Button laadspel = new Button(Taal.getWoordUitBundle("guiLaad"));
        laadspel.getStyleClass().add("OrangeButton");
        laadspel.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        for (int i = 0; i < spelletjes.size(); i++) {
            Spel get = spelletjes.get(i);
            String retour = "";
            if (get.getMoeilijkheidsgraad() == 1) {
                retour = "makkelijk";
            } else if (get.getMoeilijkheidsgraad() == 2) {
                retour = "normaal";
            } else {
                retour = "moeilijk";
            }
            String text = String.format("%s %s", retour, get.getNaam());
            spellen.getItems().add(text);
        }

        laadspel.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {

                try {

                    String spelnaam = spellen.getValue().toString();
                    String[] naam = spelnaam.split(" ");
                    dc.kiesSpel(naam[1]);

                    startspelbod();
               } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("MasterMind");
                    alert.setHeaderText(null);
                    alert.setContentText(e.toString());
                    alert.showAndWait();

                }
            }
        });
        add(laadspel, 0, 1);
        add(spellen, 0, 0);
    }

    /**
     * Creëert het spelbord voor het spel dat geladen werd op een Scene van 650x550
     */
    public void startspelbod() {
      //  System.out.printf("%s", dc.geefSpelbord().toString());
        SpelbordController spelbord = new SpelbordController(dc);
        Scene scene = new Scene(spelbord, 650, 550);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }

    /**
     * @param args the command line arguments
     */
}
