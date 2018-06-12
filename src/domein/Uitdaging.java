/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author guust
 */
public class Uitdaging {
    
    /*-----Attributen-----*/
    
    private /*final*/ int moeilijkheidsgraad;
    private String naamUitdager = "";//persoon die is ingelogt
    private  String naamUitgedaagde ="";//persoon geselecteerd uit de lijst
    private /*final*/ CodePin[] tekrakenCode;
    private /*final*/ Spelbord spelbord;
    private  String naamUitdaging ="";
    private int pogingenUitdager;
    private int pogingenUitgedaagde;

    /*----Constructors----*/

    /**
     * De constructor die een uitdaging aanmaakt aan de hand van 4 parameters, de moeilijkheidsgraad, naamUitdager, naamUitgedaagde en de naam van de uitdaging
     * @param moeilijkheidsgraad moeilijkheidsgraad van de uitdaging
     * @param naamUitdager naam van de uitdager
     * @param naamUitgedaagde naam van de uit gedaagde
     * @param naamUitdaging naam van de uitdaging
     */


    public Uitdaging(int moeilijkheidsgraad, String naamUitdager, String naamUitgedaagde, String naamUitdaging) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.naamUitdager = naamUitdager;
        this.naamUitgedaagde = naamUitgedaagde;
        this.tekrakenCode = willekeurigeCode();
        this.spelbord = new Spelbord(moeilijkheidsgraad);
        this.naamUitdaging = naamUitdaging;
    }
    
    /**
     * De constructor die een uitdaging aanmaakt aan de hand van 5 parameters, de moeilijkheidsgraad, naamUitdager, naamUitgedaagde, de naam van de uitdaging
     * en de te kraken code
     * @param moeilijkheidsgraad moeilijkheidsgraad van de uitdaging
     * @param naamUitdager naam van de uitdager
     * @param naamUitgedaagde naam van de uitgedaagde
     * @param naamUitdaging naam van de uitdaging
     * @param tekrakencode de tekraken code
     */
    public Uitdaging(int moeilijkheidsgraad, String naamUitdager, String naamUitgedaagde, String naamUitdaging, CodePin[] tekrakencode) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.naamUitdager = naamUitdager;
        this.naamUitgedaagde = naamUitgedaagde;
        this.tekrakenCode = tekrakencode;
        this.spelbord = new Spelbord(moeilijkheidsgraad);
        this.naamUitdaging = naamUitdaging;
    }

    /**
     * Die constructor die een uitdaging aanmaakt aan de hand van 5 parameters, de moeilijkheidsgraad, pogingenUitdager, pogingenUitgedaagde
     * ,de naam van de uitdager en de naam van de uitgedaagde
     * @param moeilijkheidsgraad de moeilijkheidsgraad van de uitdaging
     * @param pogingenUitdager   de pogingen van de uitdager
     * @param pogingenUitgedaagde pogingen van de uitgedaage
     * @param naamUitdager naam van de uitdager
     * @param naamUitgedaagde naam van de uitgedaagde
     */
    public Uitdaging(int moeilijkheidsgraad, int pogingenUitdager, int pogingenUitgedaagde, String naamUitdager, String naamUitgedaagde) {
        this.pogingenUitdager = pogingenUitdager;
        this.pogingenUitgedaagde = pogingenUitgedaagde;
        this.naamUitdager = naamUitdager;
        this.naamUitgedaagde = naamUitgedaagde;
        this.moeilijkheidsgraad = moeilijkheidsgraad;
    }
    
    
    
    
    /*-------Getters-------*/

    /**
     * De methode die de moeilijkheidgraad van een uitdaging terug geeft
     * @return de interger moeilijkheidsgraad
     */


    public int getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    /**
     * De methode die de naam van de uitdager terug geeft
     * @return naam van de uitdager
     */
    public String getNaamUitdager() {
        return naamUitdager;
    }

    /**
     * De methode die een string van de naam van de uitgedaagde terug geeft
     * @return naam van de uitgedaagde
     */
    public String getNaamUitgedaagde() {
        return naamUitgedaagde;
    }

    /**
     * De methode die de te kraken code terug geeft bestaande uit een array van CodePinnen
     * @return de te kraken code
     */
    public CodePin[] getTekrakenCode() {
        return tekrakenCode;
    }

    /**
     * De methode die de naam van de uitdaging terug geeft
     * @return naam van de uitdaging
     */
    public String getNaamUitdaging() {
        return naamUitdaging;
    }

    /**
     * De methode die het aantal pogingen van de uitdager terug geeft
     * @return de pogingen van de uitdager
     */
    public int getPogingenUitdager() {
        return pogingenUitdager;
    }

    /**
     * De methode die het aantal pogingen van de uitgedaagde terug geeft
     * @return het aantal pogingen van de uitgedaagde
     */
    public int getPogingenUitgedaagde() {
        return pogingenUitgedaagde;
    }
    

    

    /*--------Setters--------*/

    /**
     * De methode die de naam van de uitdager instelt
     * @param naamUitdager naam van de uitdager
     */


    public void setNaamUitdager(String naamUitdager) {
        this.naamUitdager = naamUitdager;
    }

    /**
     * De methode die naam van de uitgedaagde instelt
     * @param naamUitgedaagde naam van de uitgedaagde
     */
    public void setNaamUitgedaagde(String naamUitgedaagde) {
        this.naamUitgedaagde = naamUitgedaagde;
    }
    
    //
    
    //
       
    /**
     * De methode die de willekeurige code bestaande uit een CodePin array aanmaakt
     * @throws IllegalArgumentException als er iets fout is gelopen door de moeilijkheidsgraad
     * @return de willekeurige code
     */
    public final CodePin[] willekeurigeCode() {
        int max = 8;
        int min = 1;
        Random rand = new Random();

        switch (moeilijkheidsgraad) {
            case 1: {
                CodePin[] cod = new CodePin[4];

                ArrayList<Integer> list = new ArrayList<>();

                for (int i = 1; i < 9; i++) {
                    list.add(i);
                }
                Collections.shuffle(list);
                for (int i = 0; i < 4; i++) {

                    cod[i] = new CodePin(getalNaarkleurNormaal(list.get(i)));
                }
                return cod;
            }
            case 2: {
                CodePin[] cod = new CodePin[4];
                for (int teller = 0; teller < 4; teller++) {

                    int nummer = rand.nextInt((max - min) + 1) + min;

                    cod[teller] = new CodePin(getalNaarkleurNormaal(nummer));

                }
                return cod;

            }
            case 3: {
                CodePin[] cod = new CodePin[5];
                int maxm = 8;
                int minm = 0;
                int nulc = 0;

                //ArrayList<Integer> nulcount = new ArrayList<>();
                for (int teller = 0; teller < 5; teller++) {

                    int nummer = rand.nextInt((maxm - minm) + 1) + minm;

                    cod[teller] = new CodePin(getalNaarkleurNormaal(nummer));

                    if (nummer == 0) {

                        nulc++;

                    }

                }

                if (nulc == 3 || nulc == 4 || nulc == 5) {
                    while (nulc > 2) {
                        for (int teller = 0; teller < 5; teller++) {

                            int nummer = rand.nextInt((maxm - minm) + 1) + minm;

                            cod[teller] = new CodePin(getalNaarkleurNormaal(nummer));

                        }
                        return cod;
                    }

                } else {
                    return cod;
                }

            }

            default:
                throw new IllegalArgumentException("");

        }
    } 
    
    /**
     * De methode die een getal naar een kleur omzet
     * @param nummer nummer om een getal om te zetten in een kleur
     * @return het kleur dat het getal voorstelt
     */
    public String getalNaarkleurNormaal(int nummer) {
        String kleur;
        switch (nummer) {

            case 1:
                kleur = "blauw";
                break;
            case 2:
                kleur = "rood";
                break;
            case 3:
                kleur = "oranje";
                break;
            case 4:
                kleur = "groen";
                break;
            case 5:
                kleur = "paars";
                break;
            case 6:
                kleur = "geel";
                break;
            case 7:
                kleur = "wit";
                break;
            case 8:
                kleur = "zwart";
                break;
            default:
                kleur = "leeg";
                break;
        }
        return kleur;

    }

   
   
   
   
   
}
