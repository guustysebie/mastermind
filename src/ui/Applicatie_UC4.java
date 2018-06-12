/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domein.CodePin;
import domein.DomeinController;
import domein.EvaluatiePin;
import domein.Spel;
import domein.Spelbord;
import java.util.List;
import java.util.Scanner;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class Applicatie_UC4 {

    private DomeinController dc;

    /**
     * Constructor van de klasse. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public Applicatie_UC4(DomeinController dc) {
        this.dc = dc;
    }
    
    /**
     * Laat de speler een opgeslagen spel laden
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#startLadenSpel() 
     * @see DomeinController#kiesSpel(java.lang.String) 
     * @see DomeinController#geefSpelbord() 
     * @see DomeinController#geefCode() 
     */
    public void startLadenSpel() {
        String keuze = "";
        Scanner input = new Scanner(System.in);
        boolean error = true;
        try {
            List<Spel> lijstMetTeLadenSpelletjes = dc.startLadenSpel();

            System.out.printf("%n%n-----------------------------%n  %s       %s%n", Taal.getWoordUitBundle("spelnaam"),
                    Taal.getWoordUitBundle("moeilijkheidsgraad"));
            for (int i = 0; i < lijstMetTeLadenSpelletjes.size(); i++) {

                System.out.printf("|  %-20s|   %-3d|%n-----------------------------%n",
                        lijstMetTeLadenSpelletjes.get(i).getNaam(), lijstMetTeLadenSpelletjes.get(i).getMoeilijkheidsgraad());

            }
        } catch (IllegalArgumentException e) {

            System.out.println(Taal.getWoordUitBundle("geenSpelBesch"));
            System.exit(0);
        }

        System.out.printf(Taal.getWoordUitBundle("naamGewensteSpel") + " ");

        do {
            keuze = input.next();
            try {
                
                dc.kiesSpel(keuze);
                error = false;
            } catch (RuntimeException e) {
                System.out.printf("%s%n%s", Taal.getWoordUitBundle("spelNietInLijst"), Taal.getWoordUitBundle("probeerOpnieuw"));

            }
        } while (error == true);

          if (dc.geefMoeilijkheidsgraad()==1 || dc.geefMoeilijkheidsgraad() == 2) {
            System.out.printf("%s", geefSpelbordMenN());
        } else {
            System.out.printf("%s", geefSpelbordM());
        }
        
        
       

    }

     public String geefSpelbordMenN() {

        Spelbord spelbord = dc.geefSpelbord();
        CodePin[][] codepinbord = spelbord.getBord();
        EvaluatiePin[][] evaluatiepinbord = spelbord.getEvaluatie();
        CodePin[] tekrakencode = dc.geefCode();
 
        
        
            System.out.printf("%n%n%s%n%-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s%n", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"));
     

        String retour = String.format("%n%n     CodePinnen                   EvaluatiePinnen%n");
        

            retour += String.format("%-10s%-10s%-10s%-10s -----> tekraken code", tekrakencode[0], tekrakencode[1], tekrakencode[2], tekrakencode[3]);
        
       
            String placeholder = "******";
            retour += String.format("%-10s%-10s%-10s%-10s -----> tekraken code", placeholder, placeholder, placeholder, placeholder);
        

        for (int j = 0; j < 12; j++) {

            retour += String.format("%n|");
            for (int i = 0; i < 4; i++) {

                if (codepinbord[j][i] == null) {
                    retour += String.format("%-10s", Taal.getWoordUitBundle("leeg"));
                } else {
                    String test = codepinbord[j][i].getKleur();
                    retour += String.format("%-10s", Taal.getWoordUitBundle(test));
                }

            }

            retour += "|| ";

            for (int i = 0; i < 4; i++) {

                if (evaluatiepinbord[j][i] == null) {
                    retour += String.format("%-7s", Taal.getWoordUitBundle("leeg"));
                } else {
                    String test = evaluatiepinbord[j][i].getKleur();
                    retour += String.format("%-7s", Taal.getWoordUitBundle(test));

                }

            }
            retour += "|";

        }
        retour += String.format("%n%n");

        return retour;

    }

    public String geefSpelbordM() {
        Spelbord spelbord = dc.geefSpelbord();
        CodePin[][] codepinbord = spelbord.getBord();
        EvaluatiePin[][] evaluatiepinbord = spelbord.getEvaluatie();
        CodePin[] tekrakencode = dc.geefCode();

        String retour = String.format("%n%n     CodePinnen                   EvaluatiePinnen");

          

             System.out.printf("%n%n%s%n%-10s %-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("leeg"));
        
        
        
      
            String placeholder = "******";
            retour += String.format("%-10s%-10s%-10s%-10s%-10s -----> tekraken code", placeholder, placeholder, placeholder, placeholder, placeholder);
        

        for (int j = 0; j < 12; j++) {

            retour += String.format("%n|");
            for (int i = 0; i < 5; i++) {

                if (codepinbord[j][i] == null) {
                    retour += String.format("%-10s", Taal.getWoordUitBundle("leeg"));
                } else {
                    String test = codepinbord[j][i].getKleur();
                    retour += String.format("%-10s", Taal.getWoordUitBundle(test));
                }

            }

            retour += "|| ";

            for (int i = 0; i < 5; i++) {

                if (evaluatiepinbord[j][i] == null) {
                    retour += String.format("%-7s", Taal.getWoordUitBundle("leeg"));
                } else {
                    String test = evaluatiepinbord[j][i].getKleur();
                    retour += String.format("%-7s", Taal.getWoordUitBundle(test));

                }

            }
            retour += "|";

        }
        retour += String.format("%n%n");

        return retour;

    }
    
    
    
}
