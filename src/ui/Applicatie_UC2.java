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
import java.util.Scanner;
import resourceBundles.Taal;

/**
 *
 * @author robinbekaert
 */
public class Applicatie_UC2 {

    private DomeinController dc;

    public Applicatie_UC2(DomeinController dc) {
        this.dc = dc;
    }

    /**
     * Laat de speler een moeilijkheidsgraad selecteren en start daarna een spel op met de gekozen moeilijkheidsgraad
     * @param gebruikersnaam  de gebruikersnaam van de speler die aangemeld is 
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#geefMoeilijkheidsgraadEnGewonnenSpellen() 
     * @see DomeinController#maakSpelAan(int) 
     * @see Applicatie_UC3#Applicatie_UC3(domein.DomeinController) 
     * @see Applicatie_UC3#maakKeuze(int) 
     */
    public void speel(String gebruikersnaam)
    {
//        System.out.printf(dc.geefMoeilijkheidsgraadEnGewonnenSpellen(gebruikersnaam));
//        
//        System.out.printf("%nmaak keuze: ");
//        
//        int keuze ;
//        
//        Scanner input = new Scanner(System.in);
//        keuze = input.nextInt();
//        
//        do{
//        
//        if(keuze <= dc.geefmoeilijkheidsgraad(gebruikersnaam) && keuze >0){
//        
//            System.out.printf("%ndikke pitta");
//            break;
//           // dc.maakSpelAan(keuze);
//        }else{
//        
//        System.out.printf("maak geldige keuze:");
//         keuze = input.nextInt();
//        }
//        }while(keuze >= dc.geefmoeilijkheidsgraad(gebruikersnaam) || keuze <0);
        int keuze = 0;
        Scanner input = new Scanner(System.in);
        boolean ongeldigNr = false;
        boolean error = true;
        boolean magniet = false;
        do{
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
                System.out.printf("%n" + Taal.getWoordUitBundle("nummerTussen1en3") + "%n");
                input.nextLine();
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.printf("%n" + Taal.getWoordUitBundle("invoerGetal") + "%n");
            }

           

        } while ((!(keuze == 1 || keuze == 2 || keuze == 3)));
        
         try {
                dc.maakSpelAan(keuze);
                magniet= true;
            } catch (IllegalArgumentException e) {
                System.out.printf("%n%s", e.getMessage());
                
            }
        
        }while(magniet == false);
        
        
        
        
        if (keuze == 1 || keuze == 2) {
            System.out.printf("%s", geefSpelbordMenN());
        } else {
            System.out.printf("%s", geefSpelbordM());
        }

        Applicatie_UC3 app3 = new Applicatie_UC3(dc);

        System.out.printf(Taal.getWoordUitBundle("verderSpelen") + "%n" + Taal.getWoordUitBundle("kies") + " ");
        int keuze2 = 0;
        do {
            try{
            keuze2 = input.nextInt();
            }catch(InputMismatchException e){
                input.nextLine();
                System.out.println("het getal moet 1 of 2 zijn probeeropnieuw:");
               
            }
        } while (!(keuze2 == 1 || keuze2 == 2));

        app3.maakKeuze(keuze2);

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
