/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domein.CodePin;
import domein.DomeinController;
import domein.EvaluatiePin;
import domein.Spelbord;
import java.util.List;
import java.util.Scanner;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class Applicatie_UC6 {

    private DomeinController dc;

    /**
     * Constructor van de klasse. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public Applicatie_UC6(DomeinController dc) {
        this.dc = dc;
    }

    /**
     * Toont een lijst met openstaande uitdagingen waaruit de speler kan kiezen
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#geefLijstmetUidagingen() 
     * @see DomeinController#startUitdaging(java.lang.String) 
     * @see DomeinController#geefSpelbord() 
     */
    public void main() {

        Scanner input = new Scanner(System.in);
        String naamuitdaging;
        System.out.printf("%s%n", Taal.getWoordUitBundle("openstaandeUitdagingen"));

        try {
            List<String[]> lijstmetbeschikbareuitdagingen = dc.geefLijstmetUidagingen();

            for (int i = 0; i < lijstmetbeschikbareuitdagingen.size(); i++) {
                String[] uitdaging = lijstmetbeschikbareuitdagingen.get(i);
                System.out.printf("|%-25s %-20s %-10s %-10s|%n", uitdaging[0], uitdaging[1], uitdaging[2], uitdaging[3]);
            }

        } catch (IllegalArgumentException e) {
            System.out.println(Taal.getWoordUitBundle("geenUitdagingen"));
            System.exit(0);
        }

        System.out.println(Taal.getWoordUitBundle("kiesUitdaging"));
        boolean arror = true;
        do {
            naamuitdaging = input.next();
            try {
                dc.startUitdaging(naamuitdaging);
                arror = false;
            } catch (IllegalArgumentException e) {

                System.out.println(Taal.getWoordUitBundle("uitdagingNietInLijst"));
            }

        } while (arror == true);

        if (dc.geefMoeilijkheidsgraad() == 1 || dc.geefMoeilijkheidsgraad() == 2) {
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

        String retour = String.format("%n%n     CodePinnen                   EvaluatiePinnen%n");

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

        String placeholder = "******";
        retour += String.format("%n%-10s%-10s%-10s%-10s%-10s -----> tekraken code", placeholder, placeholder, placeholder, placeholder, placeholder);

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
