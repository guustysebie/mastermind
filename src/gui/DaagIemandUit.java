/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
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
public class DaagIemandUit extends GridPane {

    private DomeinController dc;
    private List<String[]> lijstvanspelers;
    private int moeilijkheidsgraad;

    /**
     * Constructor van de klasse DaagIemandUit. Initialiseert de DomeinController en stelt de moeilijkheidsgraad in 
     * @param dc geeft de domeincontroler mee 
     * @see DomeinController#geefMoeilijkheidsgraad() 
     */
    public DaagIemandUit(DomeinController dc) {
        this.dc = dc;
        moeilijkheidsgraad = dc.geefMoeilijkheidsgraad();
this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        buildGui();

    }
    /**
     * Deze methode bouwt het scherm om iemand uit te dagen
     * @throws IllegalArgumentException wanneer je een openstaande uitdaging hebt staan
     * @see Taal#getWoordUitBundle(java.lang.String) 
     */
    public void buildGui() {

        try {
            lijstvanspelers = dc.geefLijstMetSpelers(moeilijkheidsgraad);

            Label uitleg = new Label(Taal.getWoordUitBundle("guiKiesSpelerUitdaging"));
            Label uitleg2 = new Label(Taal.getWoordUitBundle("guiKiesNaamUitdaging"));
            TextField naamUitdaging = new TextField();
            naamUitdaging.getStyleClass().add("GeelTxt");
            ChoiceBox beschikbareSpelers = new ChoiceBox();
            Button maakUitdagingAan = new Button(Taal.getWoordUitBundle("guiDaagUit"));
            maakUitdagingAan.getStyleClass().add("OrangeButton");

            setVgap(10);
            setHgap(10);
            setPadding(new Insets(25));

            add(uitleg, 0, 0);

            for (int i = 0; i < lijstvanspelers.size(); i++) {
                String[] get = lijstvanspelers.get(i);

                String text = String.format("%-25.25s %-10.10s", get[0], get[1]);
                beschikbareSpelers.getItems().add(text);

            }

            add(beschikbareSpelers, 0, 1);
            add(uitleg2, 0, 2);
            add(naamUitdaging, 0, 3);
            add(maakUitdagingAan, 0, 4);

            maakUitdagingAan.setOnAction((ActionEvent evt) -> {

               // try {
                    if (beschikbareSpelers.getValue() == null) {

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                        alert.setHeaderText(Taal.getWoordUitBundle("guiKiesSpeler"));
                        alert.showAndWait();

                    }else
                    if ("".equals(naamUitdaging.getText()) ){

                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                        alert.setHeaderText(Taal.getWoordUitBundle("guiKiesNaam"));
                        alert.showAndWait();

                    }else{

                    String spelnaam = beschikbareSpelers.getValue().toString();
                    String[] naam = spelnaam.split(" ");

                    dc.maakUitdagingAan(naam[0], moeilijkheidsgraad, naamUitdaging.getText());

                    if (moeilijkheidsgraad == 1 || moeilijkheidsgraad == 2) {

                        startspelbodnormaal();
                    } else {

                        startspelbodMoeilijk();
                    }}

            /*   } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                    alert.setHeaderText(e.getMessage());
                    alert.showAndWait();

               }*/

            });

        } catch (IllegalArgumentException e) {
   
          throw new IllegalArgumentException(Taal.getWoordUitBundle("guiUitdagingOpen"));

        }

    }
    
    /**
     * Creëert het spelbord voor moeilijkheidsgraad makkelijk en normaal
     */
    public void startspelbodnormaal() {

        SpelbordController spelbord = new SpelbordController(dc);
        Scene scene = new Scene(spelbord, 550, 550);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

    /**
     * Creëert het spelbord voor moeilijkheidsgraad moeilijk
     */
    public void startspelbodMoeilijk() {

        SpelbordController spelbord = new SpelbordController(dc);
        Scene scene = new Scene(spelbord, 650, 550);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setResizable(false);
        stage.setScene(scene);
        stage.show();

    }

}
