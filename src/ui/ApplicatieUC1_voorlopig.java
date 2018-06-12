/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 
package ui;

import domein.DomeinController;
import domein.Speler;
import persistentie.SpelerMapper;
import java.util.Scanner;
import resourceBundles.Taal;

/**
 *
 * @author KriNi
 *//*
public class ApplicatieUC1_voorlopig {

    private DomeinController dc;

    public ApplicatieUC1_voorlopig(DomeinController dc) {
        dc = new DomeinController();
    }

    public void startOp() {
        String taal = null;
        Scanner input = new Scanner(System.in);
        boolean ongeldigeTaal = false;
        do {
            if (ongeldigeTaal) {
                input.nextLine();
                System.out.printf("Geen geldige taal (typ nl/en/fr)%n"
                        + "Not a valid language (type nl/en/fr)%n"
                        + "Pas une langue valide (Tappez nl/en/fr)%n");
            } else {
                System.out.printf("Kies uw taal (NL)%nChoose your language (EN)%nChoisissez votre langue (FR)%n");
            }
            taal = input.next();
            taal = taal.toLowerCase();
            ongeldigeTaal = true;
        } while (!(taal.equals("nl") || taal.equals("en") || taal.equals("fr")));

        Taal.kiesTaal(taal);

        System.out.printf("Welkom bij mastermind%nlog in (druk1)%nregistreer(druk2)%ngeef uw keuze in:");

        //scanner voor selectie inlog proces of registratie proces
        int keuzelogin = input.nextInt(); //variable selectie inlog

        keuzeLogin(keuzelogin);
    }
    
   
    public static void keuzeLogin(int keuze) {
        if (keuze == 1) { // inlog sequentie (normaal verloop)

            meldAan();

            }
        else
        {
            registreer();
        }

            /*    if(usernamedb.equals(registratieusername)){
                
                System.out.println("speler al in  databank");
            
           }
             */
            // sp = testcase.geefSpeler(registratieusername);
            // usernamedb = sp.getUsername();
            //  sp.setUsername(registratieusername);
            /*      System.out.printf("geef wachtwoord in:");
                Scanner input = new Scanner(System.in);
                String wachtwoord;
            wachtwoord = input.next();
            int lengte = wachtwoord.length();
            char eerste = wachtwoord.charAt(0);
            char laatste = wachtwoord.charAt(lengte - 1);

            if (Character.isDigit(eerste) && Character.isDigit(laatste) && lengte >= 8 && lengte < 21) {
                System.out.println("wachtwoord voldoet aan de eisen");
            } else {
               
                System.out.printf("wachtwoord voldoet niet aan de vereisten probeer opnieuw%nwachtwoord:");
                wachtwoord=input.next();
                
                lengte = wachtwoord.length();
                eerste = wachtwoord.charAt(0);
                laatste= wachtwoord.charAt(lengte -1);
                
               
                while((( Character.isDigit(eerste) == false) || (Character.isDigit(laatste)==false) ) || (lengte < 8) || lengte > 20  ){
                System.out.printf("wachtwoord voldoet niet aan de vereisten probeer opnieuw%nwachtwoord:");
                wachtwoord=input.next();
                
                lengte = wachtwoord.length();
                eerste = wachtwoord.charAt(0);
                laatste= wachtwoord.charAt(lengte -1);
                
                }
                System.out.println("wachtwoord voldoet aant de eisen");
             
                
                
                
             
            }
             */
    //}
       /*      public static void registreer()
    {
         Speler sp = new Speler("", "");
         /*   SpelerMapper testcase = new SpelerMapper();
        /*    Scanner registratie = new Scanner(System.in);
         //   String registratieww;
          /*  String registratieusername;
           // String usernamedb = "controle";

           // System.out.printf("geef hier de gewenste username in:");
          //  registratieusername = registratie.next();

     //       sp = testcase.geefSpeler(registratieusername);
//
         //   if (sp == null) {

                // System.out.println("speler staat niet in db");
                Speler sp2 = new Speler("", "");

           *     if (registratieusername.length() > 21) {

                    System.out.printf("username te lang! probeer opnieuw%nusername");
              *      registratieusername = registratie.next();

               *     while ((registratieusername.length() > 21)) {
                        System.out.printf("username te lang! probeer opnieuw%nusername");
                *        registratieusername = registratie.next();

               *     }
              *      sp2.setGebruikersnaam(registratieusername);
              *  } else {

                    sp2.setGebruikersnaam(registratieusername);
                /*    System.out.printf("geef wachtwoord in:");
                    Scanner input = new Scanner(System.in);
                    String wachtwoord;
                    wachtwoord = input.next();
                    int lengte = wachtwoord.length();
                    char eerste = wachtwoord.charAt(0);
                    char laatste = wachtwoord.charAt(lengte - 1);

                    if (Character.isDigit(eerste) && Character.isDigit(laatste) && lengte >= 8 && lengte < 21) {
                        System.out.println("wachtwoord voldoet aan de eisen");
                    } else {

                        System.out.printf("wachtwoord voldoet niet aan de vereisten probeer opnieuw%nwachtwoord:");
                        wachtwoord = input.next();

                        lengte = wachtwoord.length();
                        eerste = wachtwoord.charAt(0);
                        laatste = wachtwoord.charAt(lengte - 1);

                        while (((Character.isDigit(eerste) == false) || (Character.isDigit(laatste) == false)) || (lengte < 8) || lengte > 20) {
                            System.out.printf("wachtwoord voldoet niet aan de vereisten probeer opnieuw%nwachtwoord:");
                            wachtwoord = input.next();

                            lengte = wachtwoord.length();
                            eerste = wachtwoord.charAt(0);
                            laatste = wachtwoord.charAt(lengte - 1);

                        }
                        System.out.println("wachtwoord voldoet aant de eisen");

                    }

                    System.out.printf("verifieer wachtwoord: ");
                    String bevestegingswachtwoord;

                    bevestegingswachtwoord = input.next();

                    if (bevestegingswachtwoord.equals(wachtwoord)) {

                        System.out.println("u bent geregistreerd");
                        sp2.setWachtwoord(wachtwoord);
                    } else {
                        System.out.printf("bevestegingspaswoord komt niet overeen probeeropnieuw%nverifieer wachtwoord: ");
                        bevestegingswachtwoord = input.next();

                        while (!bevestegingswachtwoord.equals(wachtwoord)) {
                            System.out.printf("bevestegingspaswoord komt niet overeen probeeropnieuw%nverifieer wachtwoord:");
                            bevestegingswachtwoord = input.next();

                        }

                        System.out.println("u bent geregistreerd");
                        sp2.setWachtwoord(wachtwoord);

                    }
                    testcase.voegToe(sp2);

                }
            } else {
                Speler sp2 = new Speler("", "");

                System.out.printf("username al in gebruik gelieve een andere te gebruikenqqq %nusername:");
                registratieusername = registratie.next();
                sp = testcase.geefSpeler(registratieusername);
                String controle;
                controle = sp.getGebruikersnaam();

                while (controle.equals(registratieusername)) {
                    System.out.printf("username al in gebruik gelieve een andere te gebruiken %nusername:");

                    registratieusername = registratie.next();

                    sp = testcase.geefSpeler(registratieusername);

                    if (sp == null) {

                        sp2.setGebruikersnaam(registratieusername);

                    } else {

                        controle = sp.getGebruikersnaam();

                    }

                }

                sp2.setGebruikersnaam(registratieusername);
                System.out.printf("geef wachtwoord in:");
                Scanner input = new Scanner(System.in);
                String wachtwoord;
                wachtwoord = input.next();
                int lengte = wachtwoord.length();
                char eerste = wachtwoord.charAt(0);
                char laatste = wachtwoord.charAt(lengte - 1);

                if (Character.isDigit(eerste) && Character.isDigit(laatste) && lengte >= 8 && lengte < 21) {
                    System.out.println("wachtwoord voldoet aan de eisen");
                } else {

                    System.out.printf("wachtwoord voldoet niet aan de vereisten probeer opnieuw%nwachtwoord:");
                    wachtwoord = input.next();

                    lengte = wachtwoord.length();
                    eerste = wachtwoord.charAt(0);
                    laatste = wachtwoord.charAt(lengte - 1);

                    while (((Character.isDigit(eerste) == false) || (Character.isDigit(laatste) == false)) || (lengte < 8) || lengte > 20) {
                        System.out.printf("wachtwoord voldoet niet aan de vereisten probeer opnieuw%nwachtwoord:");
                        wachtwoord = input.next();

                        lengte = wachtwoord.length();
                        eerste = wachtwoord.charAt(0);
                        laatste = wachtwoord.charAt(lengte - 1);

                    }
                    System.out.println("wachtwoord voldoet aant de eisen");

                }

                System.out.printf("verifieer wachtwoord: ");
                String bevestegingswachtwoord;

                bevestegingswachtwoord = input.next();

                if (bevestegingswachtwoord.equals(wachtwoord)) {

                    System.out.println("u bent geregistreerd");
                    sp2.setWachtwoord(wachtwoord);
                } else {
                    System.out.printf("bevestegingspaswoord komt niet overeen probeeropnieuw%nverifieer wachtwoord: ");
                    bevestegingswachtwoord = input.next();

                    while (!bevestegingswachtwoord.equals(wachtwoord)) {
                        System.out.printf("bevestegingspaswoord komt niet overeen probeeropnieuw%nverifieer wachtwoord:");
                        bevestegingswachtwoord = input.next();

                    }

                    System.out.println("u bent geregistreerd");
                    sp2.setWachtwoord(wachtwoord);

                }
                testcase.voegToe(sp2);

            }
    
    }     

    public static void meldAan()
    {
        String username;
            String wachtwoord;

            Scanner input = new Scanner(System.in); //scanner voor username en ww in te scannen

            System.out.printf("%n%ngeef username in: "); //vraagt username en slaat op in variabele
            username = input.next();

            System.out.printf("%ngeef wachtwoord in: "); // vraagt wachtwoord en slaat op in variabele
            wachtwoord = input.next();

            Speler controlesp = new Speler("", "");
            SpelerMapper controle = new SpelerMapper();

            controlesp = controle.geefSpeler(username);

            if (controlesp == null) {

                System.out.printf("username niet herkent%nusername:");
                username = input.next();
                System.out.printf("%ngeef wachtwoord in: "); // vraagt wachtwoord en slaat op in variabele
                wachtwoord = input.next();
                controlesp = controle.geefSpeler(username);

                while (controlesp == null) {
                    System.out.printf("username niet herkent%nusername:");
                    username = input.next();
                    System.out.printf("%ngeef wachtwoord in: "); // vraagt wachtwoord en slaat op in variabele
                    wachtwoord = input.next();
                    controlesp = controle.geefSpeler(username);

                }

                if (username.equals(controlesp.getGebruikersnaam()) && wachtwoord.equals(controlesp.getWachtwoord())) {

                    System.out.printf("welkom %s%nopties", controlesp.getGebruikersnaam());

                } else {
                    System.out.printf("wachtwoord fout probeer opnieuw%nwachtwoord:");
                    wachtwoord = input.next();

                    if (wachtwoord.equals(controlesp.getWachtwoord())) {
                        System.out.printf("welkom %s%nopties", controlesp.getGebruikersnaam());
                    } else {

                        for (int counter = 0; counter < 4; counter++) {
                            System.out.printf("wachtwoord fout probeer opnieuw%nwachtwoord:");
                            wachtwoord = input.next();
                            if (wachtwoord.equals(controlesp.getWachtwoord())) {
                                System.out.printf("welkom %s%nopties", controlesp.getGebruikersnaam());
                                break;
                            }
                        }

                        System.out.printf("teveel onjuist wachtwoord gegeven! systeem wordt afgesloten");

                    }

                }

                //moet nog loop schrijven voor blijven herhalen 
            } else {

                if (username.equals(controlesp.getGebruikersnaam()) && wachtwoord.equals(controlesp.getWachtwoord())) {

                    System.out.printf("welkom %s%nopties", controlesp.getGebruikersnaam());

                } else {

                    System.out.printf("wachtwoord fout probeer opnieuw%nwachtwoord:");
                    wachtwoord = input.next();
                    if (wachtwoord.equals(controlesp.getWachtwoord())) {
                        System.out.printf("welkom %s%nopties", controlesp.getGebruikersnaam());
                    } else {

                        for (int counter = 0; counter < 4; counter++) {
                            System.out.printf("wachtwoord fout probeer opnieuw%nwachtwoord:");
                            wachtwoord = input.next();
                            if (wachtwoord.equals(controlesp.getWachtwoord())) {
                                System.out.printf("welkom %s%nopties ", controlesp.getGebruikersnaam());
                                break;
                            }
                        }

                        System.out.printf("teveel onjuist wachtwoord gegeven! systeem wordt afgesloten");

                    }

                }

            }
    }
}
*/


