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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class AanvaardUitdaging extends GridPane {

    private DomeinController dc;
    private List<String[]> lijstvanUitdagingen;
    private int moeilijkheidsgraad;

    /**
     * Constructor van de klasse AanvaardUitdaging. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public AanvaardUitdaging(DomeinController dc) {
        this.dc = dc;
        this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());

        buildGui();

    }

    /**
     * De methode bouwt de gui
     *
     * @see DomeinController#geefLijstmetUidagingen()
     * @see Taal#getWoordUitBundle(java.lang.String)
     * @see DomeinController#startUitdaging(java.lang.String)
     * @see AanvaardUitdaging#startspelbodMoeilijk()
     * @see AanvaardUitdaging#startspelbodnormaal()
     */
    public void buildGui() {
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25));
        Label uitleg = new Label(Taal.getWoordUitBundle("guiOpenstaandeUitdaging"));
        ChoiceBox uitdaging = new ChoiceBox();
        uitdaging.getStyleClass().add("choice-boxLadenUitdaging");
        Button laadUitdaging = new Button(Taal.getWoordUitBundle("guiAanvaardUitdaging"));
         laadUitdaging.getStyleClass().add("OrangeButton");
        laadUitdaging.getStyleClass().add("OrangeButtonSpelbord");

        add(uitleg, 0, 0);
        add(uitdaging, 0, 1);
        add(laadUitdaging, 0, 2);
        try {

            lijstvanUitdagingen = dc.geefLijstmetUidagingen();
            for (int i = 0; i < lijstvanUitdagingen.size(); i++) {
                String[] get = lijstvanUitdagingen.get(i);
                String text = String.format("%-25.25s %-20.20s %-10.10s %-10.10s", get[0], get[1], get[2], get[3]);
                uitdaging.getItems().add(text);
            }

        } catch (Exception e) {

         
            throw new IllegalArgumentException("");
            /*  Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
            alert.setHeaderText(Taal.getWoordUitBundle("guiGeenUitdagingBesch"));
            alert.showAndWait();
           
            Stage stage = (Stage) (getScene().getWindow());
            stage.close();*/
        }
        
        
        
        laadUitdaging.setOnAction((ActionEvent evt) -> {

            try {
                String spelnaam = uitdaging.getValue().toString();
                String[] naam = spelnaam.split(" ");

                try {
                    dc.startUitdaging(naam[0]);

                    if ("2".equals(naam[2]) || "1".equals(naam[2])) {
                        startspelbodnormaal();
                    } else {
                        startspelbodMoeilijk();
                    }

                } catch (Exception e) {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle(Taal.getWoordUitBundle("guiFoutMeldingTitel"));
                    alert.setHeaderText(e.toString());
                    alert.showAndWait();
                    Stage stage = (Stage) (getScene().getWindow());
                    stage.close();

                }

            } catch (Exception e) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("MasterMind");
                alert.setHeaderText(null);
                alert.setContentText(Taal.getWoordUitBundle("guiUitdagingSelecteren"));
                alert.showAndWait();

            }

        });

    }

    /**
     * De methode start het maken van een spelbord voor de moeilijkheidsgraden
     * normaal en makkelijk. Ze doet dit door een nieuwe spelbordcontroller,
     * scene en stage aan te maken. We geven aan de scene dan het nieuw
     * gecreeërde spelbord mee, de breedte en de hoogte van de scene.
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
     * De methode start het maken van een spelbord voor de moeilijkheidsgraad
     * moeilijk. Ze doet dit door een nieuwe spelbordcontroller, scene en stage
     * aan te maken. We geven aan de scene dan het nieuw gecreeërde spelbord
     * mee, de breedte en hoogte van de scene.
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
