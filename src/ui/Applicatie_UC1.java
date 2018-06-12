/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import java.util.Scanner;
import domein.DomeinController;
import java.util.InputMismatchException;
import resourceBundles.Taal;

/**
 *
 * @author KriNi
 */
public class Applicatie_UC1 {

    private DomeinController dc;

    /**
     * Constructor van de klasse. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public Applicatie_UC1(DomeinController dc) {
        this.dc = dc;
    }

    /**
     * Start de applicatie en toont de taalselectie
     */
    public void startOp() {
        String taal = null;
        Scanner sc = new Scanner(System.in);
        boolean ongeldigeTaal = false;
        do {
            if (ongeldigeTaal) {
                sc.nextLine();
                System.out.printf("%nGeen geldige taal (typ nl/en/fr/de)%n"
                        + "Not a valid language (type nl/en/fr/de) %n"
                        + "Pas une langue valide (tappez nl/en/fr/de)%n"
                        + "Keine gültige Sprache (typ nl/en/fr/de)%n");
            } else {
                System.out.printf("Kies uw taal (Nl)%nChoose your language (En)%nChoisissez votre langue (Fr)%nWählen Sie Ihre Sprache (DE)%n");
            }
            taal = sc.next();
            taal = taal.toLowerCase();
            ongeldigeTaal = true;

        } while (!(taal.equals("nl") || taal.equals("en") || taal.equals("fr") || taal.equals("de")));

        Taal.kiesTaal(taal);
        
        maakKeuze();
        //  kiesOptie();
    }

    /**
     * Toont de mogelijkheid om aan te melden of te registreren. Afhankelijk van deze keuze wordt respectievelijk het aanmeldscherm of registratiescherm getoond. 
     * Hierna krijgt de speler de keuze om een spel te starten, een spel te laden, iemand uit dagen, uitdagingen te aanvaarden of het klassement bekijken.
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#meldAan(java.lang.String, java.lang.String) 
     * @see DomeinController#geefAangemeldeSpelerNaam() 
     * @see DomeinController#registreer(java.lang.String, java.lang.String, java.lang.String) 
     * @see Applicatie_UC2#Applicatie_UC2(domein.DomeinController) 
     * @see Applicatie_UC3#Applicatie_UC3(domein.DomeinController) 
     * @see Applicatie_UC4#Applicatie_UC4(domein.DomeinController) 
     * @see Applicatie_UC5#Applicatie_UC5(domein.DomeinController) 
     * @see Applicatie_UC6#Applicatie_UC6(domein.DomeinController) 
     * @see Applicatie_UC7#Applicatie_UC7(domein.DomeinController) 
     * @see Applicatie_UC2#speel(java.lang.String) 
     * @see Applicatie_UC3#speelverder() 
     * @see Applicatie_UC4#startLadenSpel() 
     * @see Applicatie_UC5#main() 
     * @see Applicatie_UC6#main() 
     * @see Applicatie_UC7#geefKlassement() 
     */
    public void maakKeuze() {
        String wachtwoord;
        String gebruikersnaam = "";
        String bvwachtwoord;
        int keuze = 0;
        char keuzeChar = (char) keuze;
        Scanner input = new Scanner(System.in);
        boolean ongeldigNummer = false;
        boolean error = true;

        do {
           
            if (ongeldigNummer) {
                System.out.printf("%n" + Taal.getWoordUitBundle("nummer1of2") + "%n");
                input.nextLine();
            }
            System.out.printf("%n" + Taal.getWoordUitBundle("welkomMastermind") + "%n" + Taal.getWoordUitBundle("kies") + "%n" + Taal.getWoordUitBundle("meldAan") + "%n"
                    + Taal.getWoordUitBundle("registreer") + "%n" + Taal.getWoordUitBundle("geefKeuze") + " ");
            ongeldigNummer = false;
            try {
                keuze = input.nextInt();             
                keuzeChar = (char) keuze;
                if(Character.isDigit(keuzeChar)||Character.isLetter(keuzeChar)==false)
                    ongeldigNummer = true;
            } catch (InputMismatchException e) //invoer is geen getal
            {
                input.nextLine();
                System.out.printf("%n" + Taal.getWoordUitBundle("invoerGetal") + "%n");
            }
        } while (!(keuze == 1 || keuze == 2));

        switch (keuze) {
            case 1:
                System.out.printf("%n" + Taal.getWoordUitBundle("welkomAanmelden") + "%n");
                do {
                    try {
                        System.out.printf(Taal.getWoordUitBundle("geefGebruikersnaam") + " ");
                        gebruikersnaam = input.next();
                        System.out.printf(Taal.getWoordUitBundle("geefWachtwoord") + " ");
                        wachtwoord = input.next();
                        dc.meldAan(gebruikersnaam, wachtwoord);
                        error = false; //alle invoer klopt
                    } catch (IllegalArgumentException e) {
                        System.out.printf(e.getMessage());
                        input.nextLine();
                    }
                } while (error);

                System.out.printf("%n" + Taal.getWoordUitBundle("welkom") + " %s%n" + Taal.getWoordUitBundle("opties") + "%n%n",
                        dc.geefAangemeldeSpelerNaam());
//                System.out.printf(Taal.getWoordUitBundle("startSpel") + "%n" + Taal.getWoordUitBundle("laadSpel") + "%n" +
//                         Taal.getWoordUitBundle("daagUit") + "%n" + Taal.getWoordUitBundle("aanvaardUitdaging") + "%n" +
//                         Taal.getWoordUitBundle("klassement") + "%n");

                break;
            case 2:
                System.out.printf("%n" + Taal.getWoordUitBundle("welkomRegistreren") + "%n");
                do {
                    try {
                        System.out.print(Taal.getWoordUitBundle("geefGebruikersnaam") + " ");
                        gebruikersnaam = input.next();
                        System.out.printf(Taal.getWoordUitBundle("geefWachtwoord") + " ");
                        wachtwoord = input.next();
                        System.out.printf(Taal.getWoordUitBundle("verifieerWachtwoord") + " ");
                        bvwachtwoord = input.next();
                        dc.registreer(gebruikersnaam, wachtwoord, bvwachtwoord);
                        error = false;
                    } catch (IllegalArgumentException e) {
                        System.out.printf(e.getMessage());
                        input.nextLine();
                    }
                } while (error);

                System.out.printf("%n" + Taal.getWoordUitBundle("registratieCompleet") + "%n");
                System.out.printf("%n" + Taal.getWoordUitBundle("welkom") + " %s%n" + Taal.getWoordUitBundle("opties") + "%n%n",
                        dc.geefAangemeldeSpelerNaam());

                break;
            default:

                throw new IllegalArgumentException("stond niet tussen de keuze oetlul");
        }

        boolean ongeldigNr = false;

        do {
            if (ongeldigNr) {
                System.out.printf("%n" + Taal.getWoordUitBundle("nummerTussen1en5") + "%n%n");
                input.nextLine();
            }
            System.out.printf(Taal.getWoordUitBundle("startSpel") + "%n" + Taal.getWoordUitBundle("laadSpel") + "%n"
                    + Taal.getWoordUitBundle("daagUit") + "%n" + Taal.getWoordUitBundle("aanvaardUitdaging") + "%n"
                    + Taal.getWoordUitBundle("klassement") + "%n" + Taal.getWoordUitBundle("geefKeuze") + " ");
            ongeldigNr = false;
            try {
                keuze = input.nextInt();
                ongeldigNr = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.printf("%n%s%n%n", Taal.getWoordUitBundle("invoerGetal"));
            }
        } while (!(keuze == 1 || keuze == 2 || keuze == 3 || keuze == 4 || keuze == 5));

        Applicatie_UC3 app3 = new Applicatie_UC3(dc);
        Applicatie_UC4 app4 = new Applicatie_UC4(dc);

        switch (keuze) {
            case 1:
                Applicatie_UC2 app2 = new Applicatie_UC2(dc);
                app2.speel(gebruikersnaam);
                break;
            case 2:

                app4.startLadenSpel();
                

                app3.speelverder();
                break;

            case 3:
                Applicatie_UC5 app5 = new Applicatie_UC5(dc);
                app5.main();
                app3.speelverder();

                //Applicatie_UC5 app5 = new Applicatie_UC5(dc);
                //...
                break;
            case 4:

                Applicatie_UC6 app6 = new Applicatie_UC6(dc);
                app6.main();
                app3.speelverder();
                break;
            case 5:
                Applicatie_UC7 app7 = new Applicatie_UC7(dc);
                app7.geefKlassement();
                break;
            //...
            //break;
        }

    }

    public void kiesOptie() {
        int keuze = 0;
        Scanner input = new Scanner(System.in);
        boolean ongeldigNr = false;

        do {
            if (ongeldigNr) {
                System.out.printf("%n" + Taal.getWoordUitBundle("nummerTussen1en5") + "%n%n");
                input.nextLine();
            }
            System.out.printf(Taal.getWoordUitBundle("startSpel") + "%n" + Taal.getWoordUitBundle("laadSpel") + "%n"
                    + Taal.getWoordUitBundle("daagUit") + "%n" + Taal.getWoordUitBundle("aanvaardUitdaging") + "%n"
                    + Taal.getWoordUitBundle("klassement") + "%n" + Taal.getWoordUitBundle("geefKeuze") + " ");

            try {
                keuze = input.nextInt();
                ongeldigNr = true;
            } catch (InputMismatchException e) {
                input.nextLine();
                System.out.printf("%n%s%n", Taal.getWoordUitBundle("invoerGetal"));
            }
        } while (!(keuze == 1 || keuze == 2 || keuze == 3 || keuze == 4 || keuze == 5));

        /*switch(keuze)
        {
            case 1:
                Applicatie_UC2 app2 = new Applicatie_UC2(dc);
               
                break;
            case 2:
                //Applicatie_UC4 app4 = new Applicatie_UC4(dc);
                //...
                //break;
            case 3:
                //Applicatie_UC5 app5 = new Applicatie_UC5(dc);
                //...
                //break;
            case 4:
                //Applicatie_UC6 app6 = new Applicatie_UC6(dc);
                //...
                //break;
            case 5:
                //Applicatie_UC7 app7 = new Applicatie_UC7(dc);
                //...
                //break;
        }
    
    }*/
    }
}
