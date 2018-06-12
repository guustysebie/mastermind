/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/*-------IMPORTS----------------*/
import java.util.List;
import persistentie.SpelMapper;
import persistentie.SpelerMapper;

/*------------KLASSE-------------------*/
public class SpelRepository {

    /*---------------ATTRIBUTEN----------------------*/
    private final SpelMapper mapper = new SpelMapper();
    private Spel spel;

    /**
     * De methpde voegt een spel toe aan de databank.
     * @param spel spel 
     * @param gebruikersnaam naam van de gebruiker
     * @see SpelMapper#voegSpelToe(domein.Spel, java.lang.String) 
     */
  
    public void voegSpelToe(Spel spel, String gebruikersnaam) {
        mapper.voegSpelToe(spel, gebruikersnaam);        
    }

    /**
     * De methode geeft een lijst met spelobjecten terug aan de hand van de gebruikersnaam. De methode geeft ook een gepaste foutmelding wanneer er
     * op dat moment geen beschikbare spellen zijn
     * @param gebruikersnaam naam van de gebruiker
     * @return een lijst van spelobjecten 
     * @throws IllegalArgumentException wanneer er momenteel geen beschikbare spellen zijn
     * @see SpelMapper#geefSpelLijst(java.lang.String) 
     */
   
    public List<Spel> geefLijstMetSpellen(String gebruikersnaam) {

        if (mapper.geefSpelLijst(gebruikersnaam).isEmpty() == true) {
            throw new IllegalArgumentException("er zijn momenteel geen beschikbare spelletjes");
            
        } else {
            return mapper.geefSpelLijst(gebruikersnaam);
        }
    }

    /**
     * De methode geeft een spel terug aan de hand van de spelnaam
     * @param spelnaam naam van het spel
     * @return een spel
     * @see SpelMapper#geefSpel(java.lang.String) 
     */
    
    public Spel geefSpel(String spelnaam) {
        return mapper.geefSpel(spelnaam);
    }

    /**
     * De methode geeft een lijst met alle espeelde pogingen weer met andere woorden het spelbord zonder evaluatiepinnen.
     * @param spelnaam naam van het spel
     * @return een lijst van Codpinnen die de pogingen voorstellen
     * @see SpelMapper#updateSpelbord(java.lang.String) 
     */
    public List<CodePin[]> geefPogingen(String spelnaam) {
        return mapper.updateSpelbord(spelnaam);
    }

    /**
     * De methode verwijdert een spel uit de databank aan de hand van de spelnaam die wordt meegegeven als parameter.
     * @param spelnaam naam van het spel
     * @see SpelMapper#verwijderSpel(java.lang.String) 
     */
    public void verwijderSpel(String spelnaam) {
        mapper.verwijderSpel(spelnaam);
    }

    /**
     * De methode slaat een spel op aan de hand van de lijst met alle codepinnen en een spelnaam.
     * @param pogingen pogingen van  (het spelbord)
     * @param spelnaam naam van het spel
     * @see SpelMapper#slaSpelOp(java.util.List, java.lang.String) 
     */
    public void slaSpelOp(List<CodePin[]> pogingen, String spelnaam) {
        mapper.slaSpelOp(pogingen, spelnaam);
    }

    /**
     * De methode controleert of een spel een uitdaging is
     * @param spelnaam naam van het spel
     * @return een boolean die weergeeft of een spel een uitdaging is
     * @see SpelMapper#isUitdaging(java.lang.String) 
     */
    public boolean isuitdaging(String spelnaam) {
        return mapper.isUitdaging(spelnaam);
    }

    /**
    * De methode verwijdert de spellen die een uitdaging zijn en door de twee spelers voltooid zijn 
    * @param spel naam van het spel
    * @see SpelMapper#verwijderDeUitdagingen(int) 
     */
    /*----------FUNCTIE: verwijderd de spellen die een uitdaging zijn en door de 2---------*/
    public void verwijderSpellenDieTotEenVoltooideUitdagingBehoren(List<Integer> spel) {
        for (int teller = 0; teller < spel.size(); teller++) {
            mapper.verwijderDeUitdagingen(spel.get(teller));
        }
    }
}
