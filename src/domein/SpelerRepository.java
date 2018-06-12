/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import persistentie.SpelerMapper;
import java.util.*;
import domein.Speler;

/**
 *
 * @author robinbekaert
 */
public class SpelerRepository {

    private Speler aangemeldeSpeler;
    SpelerMapper testcase = new SpelerMapper();

    private final SpelerMapper mapper;
    private Collection<Speler> speler;
    //Speler sp = new Speler(gebruikersnaam, wachtwoord);

    /**
     * De constructor zonder parameter waarbij een nieuwe SpelerMapper wordt aangemaakt
     */
    public SpelerRepository() {

        mapper = new SpelerMapper();
    }

    /**
     * De methode die een Speler terug geeft aan de hand van de primaire sleutel gebruikersnaam
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return een speler die we opvragen in de databank aan de hand van de gebruikersnaam
     * @see SpelerMapper#geefSpeler(java.lang.String) 
     */
    public Speler geefSpeler(String gebruikersnaam) {

        return mapper.geefSpeler(gebruikersnaam);

    }

    /*  public int moeilijkheidsgraad(String gebruikersnaam){
     Speler speler =   mapper.geefSpeler(gebruikersnaam);
        if(speler.getGewonnenSpellen())
    
        
        
    }*/
    /**
     * De methode die een Speler toevoegt aan de databank
     * @param speler de speler die het spel speelt
     */
    public void voegToe(Speler speler) {

        mapper.voegToe(speler);

    }

    /**
     * De methode die controleert of een speler al in de databank zit of niet.
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return de boolean die zegt of de speler reeds in de databank zit
     */
    public boolean controleerSpeler(String gebruikersnaam) {

        Speler speler = geefSpeler(gebruikersnaam);

        return speler == null; //true speler zit er niet in

    }

    /**
     * De methode die controleert of een wachtwoord voldoet aan de eisen
     * @param wachtwoord het wachtwoord van de speler
     * @return een boolean die zegt of het wachtwoord voldoet aan de eisen
     */
    public boolean controleerWachtwoord(String wachtwoord) {

        return Character.isDigit(wachtwoord.charAt(0)) && Character.isDigit(wachtwoord.charAt(wachtwoord.length() - 1)) && wachtwoord.length() >= 8 && wachtwoord.length() < 21;

    }

    /**
     * De methode die een speler terug geeft aan de hand van de gebruikersnaam en wachtwoord
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param wachtwoord wachtwoord van de speler
     * @return een speler die we opvragen uit de databank
     */
    public Speler geefSpeler(String gebruikersnaam, String wachtwoord) {
        // TODO - implement SpelerRepository.geefSpeler
        throw new UnsupportedOperationException();
    }

    /**
     * De methode die de gewonnen spellen teruggeeft aan de hand van de gebruikersnaam.
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return een integer array van de gewonnen spellen
     * @see SpelerRepository#geefSpeler(java.lang.String) 
     */
    public int[] geefGewonnenSpellen(String gebruikersnaam) {

        int[] gewonnenSpellen = testcase.geefSpeler(gebruikersnaam).getGewonnenSpellen();

        return gewonnenSpellen;

    }

    /**
     * De methode die de moeilijkheidsgraad teruggeeft die behoort tot een bepaalde speler die we kunnen doorgeven via de primaire sleutel gebruikersnaam. 
     * We doen dit aan de hand van de aantal gewonnen spellen.
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return de moeilijkheidsgraad
     * @see SpelerRepository#geefGewonnenSpellen(java.lang.String) 
     */
    public int geefMoeilijkheidsgraad(String gebruikersnaam) {
        // TODO - implement SpelerRepository.geefMoeilijkheidsgraad

        if (geefGewonnenSpellen(gebruikersnaam)[0] < 20) {
            return 1;
        } else if (geefGewonnenSpellen(gebruikersnaam)[1] < 20 && geefGewonnenSpellen(gebruikersnaam)[0] >= 20) {
            return 2;
        } else if (geefGewonnenSpellen(gebruikersnaam)[0] >= 20 && geefGewonnenSpellen(gebruikersnaam)[1] >= 20) {
            return 3;
        }
        return 1;
    }

    /**
     * De methode die de aantal gewonnen spelle update aan de hand van de moeilijkheidsgraad en de gebruikersnaam van de speler.
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     * @param gebruikersnaam gebruikersnaam van de speler
     * @see SpelerRepository#updateGewonnenSpellen(int, java.lang.String) 
     */
    public void updateGewonnenSpellen(int moeilijkheidsgraad, String gebruikersnaam) {

        testcase.updateAantalGewonnenSpellen(moeilijkheidsgraad, gebruikersnaam);

    }

    /**
     * De methode die een Lijst van Stringarrays terug geeft die de Spelers voorstelt. De methode doet dit aan de hand van de gebruikersnaam en de moeilijkheidsgraad.
     * @param gebruikersnaam gebruikersnaam van de speler
     * @param moeilijkheidsgraad de moilijkheidsgraad van een speler
     * @return een lijst van spelers
     */
    public List<String[]> geefLijstmetSpelers(String gebruikersnaam, int moeilijkheidsgraad) {
        
        return testcase.geefLijstMetSpelers(gebruikersnaam, moeilijkheidsgraad);

    }

    /**
     * De methode die het klassement teruggeeft.
     * @return een Lijst van een lijst van String arrays die het klassement voorstellen.
     */
    public List<List<String[]>> geefKlassement() {

        String output = "";
        List<List<String[]>> volledigklassemnet = new ArrayList<>();

         volledigklassemnet.add(testcase.geefKlassementMakkelijk());
            volledigklassemnet.add(testcase.geefKlassementNormaal());
        volledigklassemnet.add(testcase.geefKlassementMoeilijk());
      

        return volledigklassemnet;

    }

    /**
     * De methode die de punten en pogingen van uitdagiingen update.
     * @param uitdagingen een array van uitdagingen waarvan de punten van de uitdager en uitgedaagde moeten upgedate worden
     * @see  SpelerRepository#updatePuntenEnPogingenUitdagingen(java.util.List) 
     */
    public void updatePuntenEnPogingenUitdagingen(List<String[]> uitdagingen) {

        for (int teller = 0; teller < uitdagingen.size(); teller++) {

            testcase.uptdatePuntenUitdagingenEnpogingen(uitdagingen.get(teller));

        }

    }

}
