/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import domein.CodePin;
import domein.DomeinController;
import domein.EvaluatiePin;
import domein.Spelbord;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import javafx.geometry.Insets;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;

import javafx.scene.control.Tooltip;

import javafx.scene.layout.GridPane;

import javafx.stage.Stage;
import resourceBundles.Taal;

/**
 * FXML Controller class
 *
 * @author guust
 */
public class SpelbordController extends GridPane {

    private Spelbord spelbord;
    private EvaluatiePin[][] evaluatiepinbord;
    private CodePin[][] codepinbord;
    private int moeilijkheidsgraad;

    private DomeinController dc;

    /**
     * Constructor van de klasse SpelbordController. Initialiseert de DomeinController en stelt het spelbord, het codepinbord, het evaluatiepinbord en de 
     * moeilijkheidsgraad in
     * @param dc geeft de domeincontroler mee 
     * @see DomeinController#geefSpelbord() 
     * @see DomeinController#geefMoeilijkheidsgraad() 
     * @see Spelbord#getBord() 
     * @see Spelbord#getEvaluatie() 
     */
    public SpelbordController(DomeinController dc) {

        this.dc = dc;

        spelbord = dc.geefSpelbord();
        codepinbord = spelbord.getBord();
        evaluatiepinbord = spelbord.getEvaluatie();
        moeilijkheidsgraad = dc.geefMoeilijkheidsgraad();
        this.getStylesheets().add(getClass().getResource("GUI.css").toExternalForm());
        this.getStyleClass().add("SmoothBackground");
        buildGui();

    }

    /**
     * Bouwt het sherm met het spelbord waarop een spelletje Mastermind kan gespeeld worden
     */
    private void buildGui() {

        CodePin[] tekrakencode = dc.geefCode();

        ChoiceBox keuzecodepin1;
        ChoiceBox keuzecodepin2;
        ChoiceBox keuzecodepin3;
        ChoiceBox keuzecodepin4;
        ChoiceBox keuzecodepin5;
    
        if(moeilijkheidsgraad == 3)
        {System.out.printf("%s %s %s %s %s ",tekrakencode[0], tekrakencode[1], tekrakencode[2], tekrakencode[3], tekrakencode[4]);
        }else{
        
        System.out.printf("%s %s %s %s  ",tekrakencode[0], tekrakencode[1], tekrakencode[2], tekrakencode[3]);
        }
        
        System.out.println("");
        setVgap(10);
        setHgap(10);
        setPadding(new Insets(25));
        if (moeilijkheidsgraad == 3) {
            keuzecodepin1 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("leeg")));
            keuzecodepin2 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("leeg")));
            keuzecodepin3 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("leeg")));
            keuzecodepin4 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("leeg")));
            keuzecodepin5 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("leeg")));
           
        } else {
            keuzecodepin1 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart")));
            keuzecodepin2 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart")));
            keuzecodepin3 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart")));
            keuzecodepin4 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart")));
            keuzecodepin5 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("geel"), Taal.getWoordUitBundle("wit"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("paars"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("zwart")));
        }

        keuzecodepin1.getStyleClass().add("choice-boxSpelbord");
        keuzecodepin2.getStyleClass().add("choice-boxSpelbord");
        keuzecodepin3.getStyleClass().add("choice-boxSpelbord");
        keuzecodepin4.getStyleClass().add("choice-boxSpelbord");
        keuzecodepin5.getStyleClass().add("choice-boxSpelbord");
        
        
        

        keuzecodepin1.setTooltip(new Tooltip("Kies codepin 1"));
        keuzecodepin2.setTooltip(new Tooltip("Kies codepin 2"));
        keuzecodepin3.setTooltip(new Tooltip("Kies codepin 3"));
        keuzecodepin4.setTooltip(new Tooltip("Kies codepin 4"));
        keuzecodepin5.setTooltip(new Tooltip("Kies codepin 5"));

//        ChoiceBox keuzecodepin1 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("guiGeel"),Taal.getWoordUitBundle("guiWit"),
//                Taal.getWoordUitBundle("guiOranje"), Taal.getWoordUitBundle("guiRood"), Taal.getWoordUitBundle("guiPaars"),
//                Taal.getWoordUitBundle("guiBlauw"), Taal.getWoordUitBundle("guiGroen"), Taal.getWoordUitBundle("guiZwart")));
//        ChoiceBox keuzecodepin2 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("guiGeel"), Taal.getWoordUitBundle("guiWit"),
//                Taal.getWoordUitBundle("guiOranje"), Taal.getWoordUitBundle("guiRood"), Taal.getWoordUitBundle("guiPaars"),
//                Taal.getWoordUitBundle("guiBlauw"), Taal.getWoordUitBundle("guiGroen"), Taal.getWoordUitBundle("guiZwart")));
//        ChoiceBox keuzecodepin3 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("guiGeel"), Taal.getWoordUitBundle("guiWit"),
//                Taal.getWoordUitBundle("guiOranje"), Taal.getWoordUitBundle("guiRood"), Taal.getWoordUitBundle("guiPaars"),
//                Taal.getWoordUitBundle("guiBlauw"), Taal.getWoordUitBundle("guiGroen"), Taal.getWoordUitBundle("guiZwart")));
//        ChoiceBox keuzecodepin4 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("guiGeel"), Taal.getWoordUitBundle("guiWit"),
//                Taal.getWoordUitBundle("guiOranje"), Taal.getWoordUitBundle("guiRood"), Taal.getWoordUitBundle("guiPaars"),
//                Taal.getWoordUitBundle("guiBlauw"), Taal.getWoordUitBundle("guiGroen"), Taal.getWoordUitBundle("guiZwart")));
//        ChoiceBox keuzecodepin5 = new ChoiceBox(FXCollections.observableArrayList(Taal.getWoordUitBundle("guiGeel"), Taal.getWoordUitBundle("guiWit"),
//                Taal.getWoordUitBundle("guiOranje"), Taal.getWoordUitBundle("guiRood"), Taal.getWoordUitBundle("guiPaars"),
//                Taal.getWoordUitBundle("guiBlauw"), Taal.getWoordUitBundle("guiGroen"), Taal.getWoordUitBundle("guiZwart")));
        
        Button btndoepoging = new Button(Taal.getWoordUitBundle("guiDoePoging"));
        btndoepoging.getStyleClass().add("OrangeButtonSpelbord");


        /*getColumnConstraints().addAll(col1, col2);*/
 /*add(new Label(tekrakencode[0].getKleur()), 0, 0);
        add(new Label(tekrakencode[1].getKleur()), 1, 0);
        add(new Label(tekrakencode[2].getKleur()), 2, 0);
        add(new Label(tekrakencode[3].getKleur()), 3, 0);*/
        if (moeilijkheidsgraad == 3) {
            add(new Label(tekrakencode[4].getKleur()), 4, 0);
            add(new Label("codePin5"), 4, 1);
            add(new Label("eval5"), 9, 1);
            add(keuzecodepin5, 4, 15);
        }

        add(new Label("codePin1"), 0, 1);
        add(new Label("codePin2"), 1, 1);
        add(new Label("codePin3"), 2, 1);
        add(new Label("codePin4"), 3, 1);
        add(new Label("eval1"), 5, 1);
        add(new Label("eval2"), 6, 1);
        add(new Label("eval3"), 7, 1);
        add(new Label("eval4"), 8, 1);
        add(keuzecodepin1, 0, 15);
        add(keuzecodepin2, 1, 15);
        add(keuzecodepin3, 2, 15);
        add(keuzecodepin4, 3, 15);

        Button slaspelopButton = new Button(Taal.getWoordUitBundle("guiSlaOp"));
        slaspelopButton.getStyleClass().add("OrangeButtonSpelbord");
        slaspelopButton.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
        btndoepoging.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

        //add(new Label(dc.geefAangemeldeSpelerNaam()),0,0);
        if (moeilijkheidsgraad == 1 || moeilijkheidsgraad == 2) {
            add(slaspelopButton, 5, 15, 4, 2);
            add(btndoepoging, 0, 16, 4, 1);
            printSpelbordMakkelijkEnNormaal();
        } else {
            add(slaspelopButton, 5, 15, 5, 2);
            add(btndoepoging, 0, 16, 5, 1);
            printMoeilijk();
        }

        btndoepoging.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt) {
                keuzecodepin1.getValue();
                String[] poging = new String[5];
              
                   // poging[0] = Taal.vertaalWoordNaarNederlands(keuzecodepin1.getValue().toString());
                    
              
                
                try {
                   
      
                   
                   
                    poging[0] = Taal.vertaalWoordNaarNederlands(keuzecodepin1.getValue().toString());
                    poging[1] = Taal.vertaalWoordNaarNederlands(keuzecodepin2.getValue().toString());
                    poging[2] = Taal.vertaalWoordNaarNederlands(keuzecodepin3.getValue().toString());
                    poging[3] = Taal.vertaalWoordNaarNederlands(keuzecodepin4.getValue().toString());
                    
                    
                  /*  poging[0] = keuzecodepin1.getValue().toString();
                    poging[1] = keuzecodepin2.getValue().toString();
                    poging[2] = keuzecodepin3.getValue().toString();
                    poging[3] = keuzecodepin4.getValue().toString();*/
                    poging[4] = null;

                    if (moeilijkheidsgraad == 3) {
                         poging[4] = Taal.vertaalWoordNaarNederlands(keuzecodepin5.getValue().toString());
                     //   poging[4] = keuzecodepin5.getValue().toString();
                    }

                    try {

                        dc.doePoging(poging);
                        poging= new String[5];
                        
                    } catch (IllegalArgumentException e) {
                        Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("MasterMind");
                    alert.setHeaderText(null);

                    alert.setContentText(e.toString());
                    alert.showAndWait();
                        
                    }

                    if (dc.isGewonnen() == true) {

                        Label label1 = new Label(tekrakencode[0].getKleur());
                        Label label2 = new Label(tekrakencode[1].getKleur());
                        Label label3 = new Label(tekrakencode[2].getKleur());
                        Label label4 = new Label(tekrakencode[3].getKleur());
                        label1.setMaxSize(15, 15);
                        label2.setMaxSize(15, 15);
                        label3.setMaxSize(15, 15);
                        label4.setMaxSize(15, 15);
                        label1.setMinSize(15, 15);
                        label2.setMinSize(15, 15);
                        label3.setMinSize(15, 15);
                        label4.setMinSize(15, 15);
                        add(label1, 0, 0);
                        add(label2, 1, 0);
                        add(label3, 2, 0);
                        add(label4, 3, 0);
                        geefLabelsKleur(label1);
                        geefLabelsKleur(label2);
                        geefLabelsKleur(label3);
                        geefLabelsKleur(label4);
                        Alert alert = new Alert(AlertType.INFORMATION);
                        alert.setTitle("MasterMind");
                        alert.setHeaderText(null);
                        alert.setContentText(dc.geefInfoGewonnenSpel());
                        alert.showAndWait();

                        Stage stage = (Stage) (getScene().getWindow());
                        stage.close();

                    }

                    getChildren().clear();
                    buildGui();
                } catch (Exception e) {
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("MasterMind");
                    alert.setHeaderText(null);

                    alert.setContentText(String.format(Taal.getWoordUitBundle("guiGeldigePoging")));
                    alert.showAndWait();
                }

            }
        }
        );

        slaspelopButton.setOnAction(
                new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent evt
            ) {

                if (dc.isuitdaging(dc.geefspelnaam())) {
                    dc.slaSpelOpdieEenUitdagingIS();
                    Alert alert = new Alert(AlertType.INFORMATION);
                    alert.setTitle("MasterMind");
                    alert.setHeaderText(null);
                    alert.setContentText(Taal.getWoordUitBundle("guiOpgeslagen"));
                    alert.showAndWait();
                    Stage stage = (Stage) (getScene().getWindow());
                    stage.close();

                } else {

                    laadSlaspelop();
                }
            }
        }
        );

    }

    /**
     * Print het spelbord voor de moeilijkheidsgraad makkelijk en normaal
     */
    public void printSpelbordMakkelijkEnNormaal() {

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 4; j++) {

                if (codepinbord[i][j] == null) {

                    Label codePinLable = new Label("hallo");
                    codePinLable.setMaxSize(20, 20);
                    codePinLable.setMinSize(20, 20);
                    codePinLable.setStyle("-fx-padding: 10;"
                            + "-fx-border-style: solid ;"
                            + "-fx-border-width: 2px;"
                            + "-fx-border-radius: 50%;"
                            + "-fx-border-color: white;");
                    add(codePinLable, j, i + 2);

                    Label EvaluatiePinLable = new Label();
                    EvaluatiePinLable.setMaxSize(14, 14);
                    EvaluatiePinLable.setMinSize(14, 14);
                    EvaluatiePinLable.setStyle("-fx-padding: 10;"
                            + "-fx-border-style: solid ;"
                            + "-fx-border-width: 2px;"
                            + "-fx-border-radius: 50%;"
                            + "-fx-border-color: white;");

                    add(EvaluatiePinLable, j + 5, i + 2);
                } else {
                    Label codepinLabel = new Label(codepinbord[i][j].getKleur());
                    geefLabelsKleur(codepinLabel);
                    codepinLabel.setMaxSize(20, 20);
                    codepinLabel.setMinSize(20, 20);
                    add(codepinLabel, j, i + 2);
                    Label evalpinLabel = new Label(evaluatiepinbord[i][j].getKleur());
                    geefLabelsKleur(evalpinLabel);
                    evalpinLabel.setMaxSize(15, 15);
                    evalpinLabel.setMinSize(15, 15);
                    add(evalpinLabel, j + 5, i + 2);
                }

            }

        }

    }

    /**
     * Print het spelbord voor de moeilijkheidsgraad moeilijk 
     */
    public void printMoeilijk() {

        for (int i = 0; i < 12; i++) {
            for (int j = 0; j < 5; j++) {

                if (codepinbord[i][j] == null) {

                    Label codePinLable = new Label("hallo");
                    codePinLable.setMaxSize(15, 15);
                    codePinLable.setMinSize(15, 15);
                    codePinLable.setStyle("-fx-padding: 10;"
                            + "-fx-border-style: solid ;"
                            + "-fx-border-width: 2px;"
                            + "-fx-border-radius: 50%;"
                            + "-fx-border-color: white;");
                    add(codePinLable, j, i + 2);

                    Label EvaluatiePinLable = new Label();
                    EvaluatiePinLable.setMaxSize(10, 10);
                    EvaluatiePinLable.setMinSize(10, 10);
                    EvaluatiePinLable.setStyle("-fx-padding: 10;"
                            + "-fx-border-style: solid ;"
                            + "-fx-border-width: 2px;"
                            + "-fx-border-radius: 50%;"
                            + "-fx-border-color: white;");

                    add(EvaluatiePinLable, j + 5, i + 2);

                } else {
                    /*    Label codepinLabel = new Label(codepinbord[i][j].getKleur());
                    geefLabelsKleur(codepinLabel);
                    add(codepinLabel, j, i + 2);
                    Label evalpinLabel = new Label(evaluatiepinbord[i][j].getKleur());
                    geefLabelsKleur(evalpinLabel);
                    add(evalpinLabel, j + 5, i + 2);
                     */

                    Label codepinLabel = new Label(codepinbord[i][j].getKleur());
                    geefLabelsKleur(codepinLabel);
                    codepinLabel.setMaxSize(15, 15);
                    codepinLabel.setMinSize(15, 15);
                    add(codepinLabel, j, i + 2);
                    Label evalpinLabel = new Label(evaluatiepinbord[i][j].getKleur());
                    geefLabelsKleur(evalpinLabel);
                    evalpinLabel.setMaxSize(10, 10);
                    evalpinLabel.setMinSize(10, 10);
                    add(evalpinLabel, j + 5, i + 2);

                }

            }

        }

    }

    /**
     * Laadt het scherm via de klasse SpelOpslaan
     * @see SpelOpslaan#SpelOpslaan(domein.DomeinController) 
     */
    public void laadSlaspelop() {

        SpelOpslaan test = new SpelOpslaan(dc);
        Scene scene = new Scene(test, 250, 150);
        Stage stage = (Stage) this.getScene().getWindow();
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

    }
    
    /**
     * Geeft de labels van de codepinnen de gewenste kleur
     * @param inTeKleurenLabel het label dat je wenst in te kleuren
     */
    public void geefLabelsKleur(Label inTeKleurenLabel) {

        switch (inTeKleurenLabel.getText()) {
            case "geel":
                inTeKleurenLabel.getStyleClass().add("SpelbordGeel");
                break;
            case "rood":
                inTeKleurenLabel.getStyleClass().add("SpelbordRood");
                break;
            case "groen":
                inTeKleurenLabel.getStyleClass().add("SpelbordGroen");
                break;
            case "blauw":
                inTeKleurenLabel.getStyleClass().add("SpelbordBlauw");
                break;
            case "oranje":
                inTeKleurenLabel.getStyleClass().add("SpelbordOranje");
                break;
            case "paars":
                inTeKleurenLabel.getStyleClass().add("SpelbordPaars");
                break;
            case "wit":
                inTeKleurenLabel.getStyleClass().add("SpelbordWit");
                break;
            case "zwart":
                inTeKleurenLabel.getStyleClass().add("SpelbordZwart");
                break;
            case "leeg":
                inTeKleurenLabel.getStyleClass().add("SpelbordLeeg");
                break;
        }
    }

}
