/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.DomeinController;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class Klassement extends GridPane {

    private DomeinController dc;
    private List<List<String[]>> klassement;

    /**
     * Constructor van de klasse Klassement. Zorgt ervoor dat het klassement geupdate wordt en initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     * @see DomeinController#updateKlassement() 
     */
    public Klassement(DomeinController dc) {

        this.dc = dc;
        dc.updateKlassement();
        this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        
        buildGui();

    }

    /**
     * Bouwt het scherm voor het klassement aan de hand van ListViews en ObservableLists
     */
    public void buildGui() {

        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25));

        klassement = dc.geefKlassement();
        ListView<String> displayklassementmakkelijk = new ListView<String>();
        ListView<String> displayklassementnormaal = new ListView<String>();
        ListView<String> displayklassementMoeilijk = new ListView<String>();
        ObservableList<String> itemsklassementmakkelijk = FXCollections.observableArrayList();
        ObservableList<String> itemsklassementnormaal = FXCollections.observableArrayList();
        ObservableList<String> itemsklassementmoeilijk = FXCollections.observableArrayList();

        List<String[]> klassementmakkelijk = klassement.get(0);
        List<String[]> klassementnormaal = klassement.get(1);
        List<String[]> klassementmoeilijk = klassement.get(2);

        for (int i = 0; i < klassementmakkelijk.size(); i++) {
            String[] get = klassementmakkelijk.get(i);
            String speler = String.format(" %d |   %s   %s", i, get[1], get[0]);
            itemsklassementmakkelijk.add(speler);

        }
        displayklassementmakkelijk.setItems(itemsklassementmakkelijk);
        for (int i = 0; i < klassementnormaal.size(); i++) {
            String[] get = klassementnormaal.get(i);
            String speler = String.format(" %d |  %s   %s", i, get[1], get[0]);
            itemsklassementnormaal.add(speler);

        }
        displayklassementnormaal.setItems(itemsklassementnormaal);
        for (int i = 0; i < klassementmoeilijk.size(); i++) {
            String[] get = klassementmoeilijk.get(i);
            String speler = String.format(" %d |  %s   %s", i, get[1], get[0]);
            itemsklassementmoeilijk.add(speler);

        }
        displayklassementMoeilijk.setItems(itemsklassementmoeilijk);

        add(displayklassementmakkelijk, 0, 1);
        add(displayklassementnormaal, 1, 1);
        add(displayklassementMoeilijk, 2, 1);

        add(new Label(Taal.getWoordUitBundle("guiKlasMak")), 0, 0);
        add(new Label(Taal.getWoordUitBundle("guiKlasNor")), 1, 0);
        add(new Label(Taal.getWoordUitBundle("guiKlasMoe")), 2, 0);

    }
}
