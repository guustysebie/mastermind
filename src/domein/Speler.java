/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import resourceBundles.Taal;

/**
 *
 * @author KriNi
 */
//test github
public class Speler {

    private String gebruikersnaam; //de 2 private attributen van de klasse speler
    private String wachtwoord;
    private int[] gewonnenSpellen;

    /**
     * De constructor maakt een speler aan de hand van de gebruikersnaam en wachtwoord
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @see Speler#setGebruikersnaam(java.lang.String) 
     * @see Speler#setWachtwoord(java.lang.String) 
     */
    public Speler(String gebruikersnaam, String wachtwoord) {
        // TODO - implement Speler.Speler
        setGebruikersnaam(gebruikersnaam);
        setWachtwoord(wachtwoord);
    }

    /**
     * De constructor met 3 parameters maakt een speler aan met de gebruikersnaam, wachtwoord en gewonnen spellen
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @param gewonnenSpellen een int array van de gewonnen spelletjes die de speler gewonnen heeft per klasse
     * @see Speler#setGebruikersnaam(java.lang.String) 
     * @see Speler#setWachtwoord(java.lang.String) 
     * @see Speler#setGewonnenSpellen(int[]) 
     */
    public Speler(String gebruikersnaam, String wachtwoord, int[] gewonnenSpellen) { //de constructor van de klas speler

        setGebruikersnaam(gebruikersnaam);
        setWachtwoord(wachtwoord);
        setGewonnenSpellen(gewonnenSpellen);
    }
/**
 * De methode stelt de aantal gewonnen spellen in
 * @param gewonnenSpellen  een int array van de gewonnen spelletjes die de speler gewonnen heeft per klasse
 */
    private void setGewonnenSpellen(int[] gewonnenSpellen) {
        this.gewonnenSpellen = gewonnenSpellen;
    }

    /**
     * De methode stelt de gebruikersnaam in
     * @param gebruikersnaam de naam van de gebruiker
     */
    public final void setGebruikersnaam(String gebruikersnaam) {

        this.gebruikersnaam = gebruikersnaam;

    }

    /**
     * De methode stelt het wachtwoord in
     * @param wachtwoord het ingevoerde wachtwoord
     */
    public final void setWachtwoord(String wachtwoord) {

        this.wachtwoord = wachtwoord;

    }

    /**
     * De methode geeft het wachtwoord terug
     * @return het wachtwoord
     */
    public String getWachtwoord() {

        return wachtwoord;

    }

    /**
     * De methode geeft de gebruikersnaam terug
     * @return gebruikersnaam
     */
    public String getGebruikersnaam() {
        return gebruikersnaam;
    }

    /**
     * De methode geeft de gewonnen spellen terug
     * @return een int array van de gewonnen spellen
     */
    public int[] getGewonnenSpellen() {
        return gewonnenSpellen;
    }

    /**
     * De methode controleert of de lengte van het wachtwoord correct is en kent hier een boolean aan toe.
     * @param wachtwoord het ingevoerde wachtwoord
     * @return een boolean die uitdrukt of de lengte van het wachtwoord correct is
     */
    public boolean controleLengteWachtwoord(String wachtwoord) {

        return wachtwoord.length() < 21;

    }

    /**
     * De methode controleert of de lengte van de gebruikersnaam correct is en kent hier een boolean aan toe.
     * @param gebruikersnaam gebruikersnaam van de aangemelde speler
     * @return een boolean die uitdrukt of de lengte van de gebruikersnaam correct is
     */
    public boolean controleLengteGebruikersnaam(String gebruikersnaam) {

        return gebruikersnaam.length() < 21;

    }

    /**
     * De methode geeft het aantal sterren terug uitgedrukt in een String die we bepalen aan de hand van de moeilijkheidsgraad. We geven een correcte
     * foutboodschap wanneer er een foute ingave is van de moeilijkheidsgraad.
     * @param moeilijkheidsgraad de moeilijkheidsgraad van wat hij zijn sterren wil weten
     * @throws IllegalArgumentException wanner er zich een fout opdoet tijdens de invoer van de moeilijkheidsgraad.
     * @return het aantalSterren
     * @see Speler#getGewonnenSpellen() 
     * @see Speler#zetWinsOmNaarSterren(int) 
     */
    public String geefAantalSterren(int moeilijkheidsgraad) {

        int[] a = getGewonnenSpellen();//vraag array op en kijk op a[0] voor makkelijk enzo

        switch (moeilijkheidsgraad) {
            case 1:

                int aantalWinsMakkelijk = a[0];
                return zetWinsOmNaarSterren(aantalWinsMakkelijk);

            case 2:
                int aantalWinsNormaal = a[1];
                return zetWinsOmNaarSterren(aantalWinsNormaal);

            case 3:
                int aantalWinsMoeilijk = a[2];
                return zetWinsOmNaarSterren(aantalWinsMoeilijk);
            default:
                throw new IllegalArgumentException("iets fout gelopen met met ingave keuze moeilijkheidsgraad");

        }

    }

    /**
     * De methode zet de winst om naar sterren en cast deze naar een String. De methode doet dit aan de hand van het aantal gewonnen spellen.
     * @param aantalgewonnen het aantal gewonnen spelletjes
     * @return een Sting van sterren waar de lengte afhankelijk is van de aantal gewonnen spellen
     * @see Taal#getWoordUitBundle(java.lang.String) 
     */
    public String zetWinsOmNaarSterren(int aantalgewonnen) {

        if (aantalgewonnen < 10) {

            return Taal.getWoordUitBundle("nietGenoegSpellen");

        } else if (aantalgewonnen > 10 && aantalgewonnen < 20) {

            return "*";

        } else if (aantalgewonnen > 20 && aantalgewonnen < 50) {

            return "**";

        } else if (aantalgewonnen > 50 && aantalgewonnen < 100) {
            return "***";

        } else if (aantalgewonnen > 100 && aantalgewonnen < 250) {

            return "****";

        } else {

            return "*****";

        }
    }

    /**
     * De methode geeft het aantal spellen tot de volgende ster terug. De methode berekent dit aan de hand van de moeilijkheidsgraad
     * @param moeilijkheidsgraad de moeilijkheidsgraad van wat je het aantal sterretjes tot volgende ster wilt weten
     * @return heet aantal spellen tot de volgende ster.
     */
    public int geefAantalSpellenTotVolgendeSter(int moeilijkheidsgraad) {
        int[] a = getGewonnenSpellen();
        
        int aantalMoeilijkheid = a[moeilijkheidsgraad-1];
        
        if(aantalMoeilijkheid<10)
        {
            return 10-aantalMoeilijkheid;
        }
        else
        {
            if(aantalMoeilijkheid>10&&aantalMoeilijkheid<20)
            {
                return 20-aantalMoeilijkheid;
            }
            else
            {
                if(aantalMoeilijkheid>20&&aantalMoeilijkheid<50)
                {
                    return 50-aantalMoeilijkheid;
                }
                else
                {
                    if(aantalMoeilijkheid>50&&aantalMoeilijkheid<100)
                    {
                        return 100-aantalMoeilijkheid;
                    }
                    else
                    {
                        if(aantalMoeilijkheid>100&&aantalMoeilijkheid<250)
                        {
                            return 250-aantalMoeilijkheid;
                        }
                        else
                        {
                           return 251;
                        }
                    }
                }
            }
        }

    }

    
    
    

   
}
