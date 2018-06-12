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
import java.util.ArrayList;
import java.util.Scanner;
import ui.Applicatie_UC1;

/**
 *
 * @author guust
 */
import main.StartUp;
import resourceBundles.Taal;

public class Applicatie_UC3 {

    private DomeinController dc;

    /**
     * Constructor van de klasse. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public Applicatie_UC3(DomeinController dc) {
        this.dc = dc;
    }

    /**
     * Laat de speler kiezen om het spel op te slaan of verder te spelen
     * @param keuze wat de speler wilt doen opslaan of verder speln
     */
    public void maakKeuze(int keuze) {

        if (keuze == 1) {

            slaSpelOp();

        } else {

            speelverder();

        }

    }
    
    /**
     * Slaat het spel op in de databank
     * @see DomeinController#isuitdaging(java.lang.String) 
     * @see DomeinController#geefspelnaam() 
     * @see DomeinController#slaSpelOpdieEenUitdagingIS() 
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#slaSpelOp(java.lang.String) 
     */
    public void slaSpelOp() {

        Scanner input = new Scanner(System.in);
        boolean error = false;
        if (dc.isuitdaging(dc.geefspelnaam()) == true) {
            //dc.verwijderSpel(dc.geefspelnaam());
            dc.slaSpelOpdieEenUitdagingIS();

            System.out.printf(Taal.getWoordUitBundle("opgeslagen") + "%n");
            System.exit(0);
        } else {

            String naamspel;
            System.out.println(Taal.getWoordUitBundle("geefNaamSpel"));

            do {

                try {
                    naamspel = input.next();
                    dc.slaSpelOp(naamspel);
                    error = false;
                } catch (RuntimeException e) {
                    System.out.printf("%s%n%s ", Taal.getWoordUitBundle("spelnaamGebruikt"), Taal.getWoordUitBundle("geefAndereSpelnaam"));

                    error = true;
                }
            } while (error == true);

            System.out.printf(Taal.getWoordUitBundle("opgeslagen") + "%n");
            System.exit(0);

        }
    }
    
    /**
     * Toont het spelbord aan de hand van de gekozen moeilijkheidsgraad en laat de speler het spel spelen
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#geefCode() 
     * @see DomeinController#geefspelnaam() 
     * @see DomeinController#geefMoeilijkheidsgraad() 
     * @see DomeinController#doePoging(java.lang.String[]) 
     * @see DomeinController#geefSpelbord() 
     * @see DomeinController#geefInfoGewonnenSpel() 
     * @see DomeinController#isGewonnen() 
     */
    public void speelverder() {

        Scanner input = new Scanner(System.in);
        int nummerpoging = 0;

        if (dc.geefMoeilijkheidsgraad() == 1 || dc.geefMoeilijkheidsgraad() == 2) {
            System.out.printf("%n%s%n%-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s%n", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"));
        } else {

             System.out.printf("%s%n%-10s %-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("leeg"));
        }

        ArrayList<String> poging = new ArrayList<>();

        do {

            System.out.printf("%n" + Taal.getWoordUitBundle("geefPoging") + " " + Taal.getWoordUitBundle("stop") + "%n");

            switch (dc.geefMoeilijkheidsgraad()) {
                case 1: {
                    String[] test = new String[4];
                    String tes = "";
                    String inputtaal;
                    for (int tellerpoging = 1; tellerpoging <= test.length; tellerpoging++) {

                        System.out.printf(Taal.getWoordUitBundle("pin") + " " + tellerpoging + ": ");
                        inputtaal = input.next();
                        tes = Taal.getWoordUitBundle(inputtaal);
                        
                        if ("stop".equals(inputtaal)) {
                            slaSpelOp();

                        } else {

                            poging.add(tes);
                        }
                    }

                    for (int i = 0; i < poging.size(); i++) {
                        test[i] = poging.get(i);
                    }
                    dc.doePoging(test);
                    System.out.printf("%s", geefSpelbordMenN());

                    if (dc.isGewonnen() == true) {

                        System.out.println(dc.geefInfoGewonnenSpel());
                        System.exit(0);
                    }
                    poging.clear();

                    break;
                }
                case 2: {
                    String[] test = new String[4];
                    String tes = "";
                    String inputtaal;
                    for (int tellerpoging = 1; tellerpoging <= test.length; tellerpoging++) {

                        System.out.printf(Taal.getWoordUitBundle("pin") + " " + tellerpoging + ": ");
                        inputtaal = input.next();
                        tes = Taal.getWoordUitBundle(inputtaal);

                        if ("stop".equals(inputtaal)) {
                            slaSpelOp();

                        } else {

                            poging.add(tes);
                        }
                    }

                    for (int i = 0; i < poging.size(); i++) {
                        test[i] = poging.get(i);
                    }
                    dc.doePoging(test);
                    System.out.printf("%s", geefSpelbordMenN());
                    if (dc.isGewonnen() == true) {
                        System.out.println(dc.geefInfoGewonnenSpel());
                        System.exit(0);
                    }
                    poging.clear();

                    break;
                }

                case 3: {

                    String[] test = new String[5];
                    String tes = "";
                    String inputtaal;
                    for (int tellerpoging = 1; tellerpoging <= test.length; tellerpoging++) {

                        System.out.printf(Taal.getWoordUitBundle("pin") + " " + tellerpoging + ": ");
                    inputtaal = input.next();
                        tes = Taal.getWoordUitBundle(inputtaal);

                        if ("stop".equals(inputtaal)) {
                            slaSpelOp();

                        } else {

                            poging.add(tes);
                        }
                    }

                    for (int i = 0; i < poging.size(); i++) {
                        test[i] = poging.get(i);
                    }
                    dc.doePoging(test);
                    System.out.printf("%s", geefSpelbordM());
                    if (dc.isGewonnen() == true) {
                        System.out.println(dc.geefInfoGewonnenSpel());
                        System.exit(0);
                    }
                    poging.clear();

                    break;

       
                }
                default:
                    throw new IllegalArgumentException("zware fout ergens");
            }
            nummerpoging++;

        } while (nummerpoging < 12 && dc.isGewonnen() == false);
        System.out.println("je hebt de code niet gekraakt probeer opnieuw");
        System.exit(0);
        

    }

    public String geefSpelbordMenN() {

        Spelbord spelbord = dc.geefSpelbord();
        CodePin[][] codepinbord = spelbord.getBord();
        EvaluatiePin[][] evaluatiepinbord = spelbord.getEvaluatie();
        CodePin[] tekrakencode = dc.geefCode();
 
        
        if (dc.geefMoeilijkheidsgraad() == 1 || dc.geefMoeilijkheidsgraad() == 2) {
            System.out.printf("%n%n%s%n%-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s%n", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"));
        } else {

             System.out.printf("%n%n%s%n%-10s %-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("leeg"));
        }
        

        String retour = String.format("%n%n     CodePinnen                   EvaluatiePinnen%n");
        if (dc.isGewonnen() == true) {

            retour += String.format("%-10s%-10s%-10s%-10s -----> tekraken code", tekrakencode[0], tekrakencode[1], tekrakencode[2], tekrakencode[3]);
        
        } else {
            String placeholder = "******";
            retour += String.format("%-10s%-10s%-10s%-10s -----> tekraken code", placeholder, placeholder, placeholder, placeholder);
        }

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

          if (dc.geefMoeilijkheidsgraad() == 1 || dc.geefMoeilijkheidsgraad() == 2) {
            System.out.printf("%n%n%s%n%-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s%n", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"));
        } else {

             System.out.printf("%n%n%s%n%-10s %-10s %-10s %-10s %-10s%n%-10s %-10s %-10s %-10s", Taal.getWoordUitBundle("mogelijkeKleuren"), Taal.getWoordUitBundle("geel"),
                    Taal.getWoordUitBundle("rood"), Taal.getWoordUitBundle("oranje"), Taal.getWoordUitBundle("paars"),
                     Taal.getWoordUitBundle("zwart"), Taal.getWoordUitBundle("wit"),
                     Taal.getWoordUitBundle("groen"), Taal.getWoordUitBundle("blauw"), Taal.getWoordUitBundle("leeg"));
        }
        
        
        if (dc.isGewonnen() == true) {

            retour += String.format("%n%-10s%-10s%-10s%-10s%-10s -----> tekraken code", tekrakencode[0], tekrakencode[1], tekrakencode[2], tekrakencode[3], tekrakencode[4]);
       
        } else {
            String placeholder = "******";
            retour += String.format("%n%-10s%-10s%-10s%-10s%-10s -----> tekraken code", placeholder, placeholder, placeholder, placeholder, placeholder);
        }

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
