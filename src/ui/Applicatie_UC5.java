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
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class Applicatie_UC5 {

    private DomeinController dc;

    /**
     * Constructor van de klasse. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee  
     */
    public Applicatie_UC5(DomeinController dc) {
        this.dc = dc;
    }

    /**
     * De speler kiest de moeilijkheidsgraad van het spel waarmeer hij iemand wil uitdagen. Hij krijgt een lijst van spelers te zien waaruit hij kan kiezen. 
     * Daarna moet hij de uitdaging een naam geven 
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#geefMoeilijkheidsgraadEnGewonnenSpellen() 
     * @see DomeinController#geefLijstMetSpelers(int) 
     * @see DomeinController#maakUitdagingAan(java.lang.String, int, java.lang.String) 
     * @see DomeinController#geefSpelbord() 
     */
    public void main() {
        Scanner input = new Scanner(System.in);
        int keuze = 0;

        boolean ongeldigNr = false;
        boolean error = true;
        boolean magniet = false;
        do {
            do {
                if (ongeldigNr) {
                    System.out.printf("%n" + Taal.getWoordUitBundle("nummerTussen1en3") + "%n");
                    input.nextLine();
                }
                System.out.printf("%n" + dc.geefMoeilijkheidsgraadEnGewonnenSpellen());
                System.out.printf(Taal.getWoordUitBundle("geefKeuze") + " ");
                ongeldigNr = false;
                try {
                    keuze = input.nextInt();
                    dc.maakSpelAan(keuze);
                    ongeldigNr = true;

                } catch (IllegalArgumentException e) {
                    System.out.printf(e.getMessage());
                    input.nextLine();
                } catch (InputMismatchException e) {
                    input.nextLine();
                    System.out.printf("%n" + Taal.getWoordUitBundle("invoerGetal") + "%n");
                }

                try {
                    dc.maakSpelAan(keuze);
                    magniet = true;
                } catch (IllegalArgumentException e) {
                    System.out.printf("%n%s", e.getMessage());

                }

            } while (magniet == false);

        } while (!(keuze == 1 || keuze == 2 || keuze == 3));

        try {
            List<String[]> lijstvanspelers = dc.geefLijstMetSpelers(keuze);

            System.out.printf("%s   %s", Taal.getWoordUitBundle("naamSpeler"), Taal.getWoordUitBundle("moeilijkheidsgraad"));
            for (int i = 0; i < lijstvanspelers.size(); i++) {

                System.out.printf("%n|%-23s %-5s|", lijstvanspelers.get(i)[0], lijstvanspelers.get(i)[1]);
            }
            boolean error2 = false;
            do {
                System.out.printf("%n%s", Taal.getWoordUitBundle("kiesSpelerUitdaging"));
                String naamuittedagenspeler = input.next();
                System.out.printf("%n%s ", Taal.getWoordUitBundle("geefNaamUitdaging"));
                String naamuitdaging = input.next();
                try {
                    dc.maakUitdagingAan(naamuittedagenspeler, keuze, naamuitdaging);
                    error2 = true;

                } catch (IllegalArgumentException de) {
                    System.out.println(de);
                } catch (RuntimeException a) {
                    System.out.println(Taal.getWoordUitBundle("naamUitdagingGebruikt"));
                }

            } while (error2 == false);

            if (dc.geefMoeilijkheidsgraad() == 1 || dc.geefMoeilijkheidsgraad() == 2) {
                System.out.printf("%s", geefSpelbordMenN());
            } else {
                System.out.printf("%s", geefSpelbordM());
            }

        } catch (IllegalArgumentException e) {
            System.out.println(Taal.getWoordUitBundle("uitdagingAfwerken"));

            if (dc.geefMoeilijkheidsgraad() == 1 || dc.geefMoeilijkheidsgraad() == 2) {
                System.out.printf("%s", geefSpelbordMenN());
            } else {
                System.out.printf("%s", geefSpelbordM());
            }

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
