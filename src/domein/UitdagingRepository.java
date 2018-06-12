/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import persistentie.UitdagingMapper;

/**
 *
 * @author guust
 */
public class UitdagingRepository {

    UitdagingMapper um = new UitdagingMapper();

    /**
     * De methode die een uitdaging aanmaakt en deze in de databank steekt.
     * @param uitdaging een uitdaging
     */
    public void maakUitdagingAan(Uitdaging uitdaging) {

        um.maakUitdagingAan(uitdaging);

    }

    /**
     * De methode die een Lijst van String arrays terug geeft die de uitdagingen voorstellen. De methode doet dit aan de hand van de gebruikersnaam.
     * Wanneer er op dat moment geen openstaande uitdagingen zijn dan wordt hiervoor ook een gepaste foutmelding gegeven.
     * @param gebruikersnaam gebruikersnaam van de speler
     * @throws IllegalArgumentException wanneer de gebruiker momenteel geen openstaande uitdagingen heeft.
     * @return de uitdagingen
     * @see UitdagingMapper#geefUitdagingen(java.lang.String) 
     */
    public List<String[]> geefUitdagingen(String gebruikersnaam) {

        if (um.geefUitdagingen(gebruikersnaam).isEmpty()) {
            throw new IllegalArgumentException("je hebt momenteel geen openstaande uitdagingen");
        }

        return um.geefUitdagingen(gebruikersnaam);

    }
/**
 * De methode controleert of de gebruiker een uitdaging mag aanmaken. De methode doet dit door te kijken in de databank via de mapper of de gebruiker al uitdagigen
 * heeft aangemaakt of niet.
 * @param gebruikersnaam gebruikersnaam van de speler
 * @return een boolean die aangeeft of de gebruiker een uitdaging mag aanmaken
 * @see UitdagingMapper#geefUitdagingUitdager(java.lang.String) 
 */
    public boolean magUitdagingAanmaken(String gebruikersnaam) {
        List<String> aantal = um.geefUitdagingUitdager(gebruikersnaam);
        if (aantal.isEmpty()) {
            return true;
        } else {
            return false;
        }

    }
    /**
     * De methode geeft de naam van de uitdaging die de uitdager heeft gekozen.
     * @param gebruikersnaam gebruikersnaam van de speler
     * @return de naam van de uitdaging
     * @see UitdagingMapper#geefUitdagingUitdager(java.lang.String) 
     */
   public String geefNaamUitdagingVanUitdager(String gebruikersnaam){
    List<String> aantal = um.geefUitdagingUitdager(gebruikersnaam);
   return aantal.get(0);
   } 

    /**
     * De methode geeft een uitdaging aan de hand van de naam van de uitdaging. Wanneer de uitdaging alleen nullen bevat
     * wordt hiervoor ook een gepaste foutmelding gegeven. 
     * @param naamUitdaging de gekozen naam van de uitdaging
     * @return de uitaging die de gebruiker heeft opgevraagd aan de hand van de uitdagingnaam
     * @throws IllegalArgumentException wanneer de uitdaging alleen nullen bevat
     * @see UitdagingMapper#geefUitdagingen(java.lang.String) 
     * @see UitdagingMapper#geefUitdaging(java.lang.String) 
     */
    public Uitdaging geefUitdaging(String naamUitdaging) {
        if (um.geefUitdagingen(naamUitdaging) == null) {
            throw new IllegalArgumentException("000000000");
        }
        return um.geefUitdaging(naamUitdaging);

    }

    /**
     * De methode zorgt er voor dat de pogingen van de uitdager kunnen upgedate worden. De methode doet dit adhv de aantal pogingen
     * en een uitdaging meegegeven als parameters.
     * @param pogingen het aantal pogingen van de uitdager wnr de code gekraakt werd
     * @param uitdaging de gespeeld uitdaging
     * @see UitdagingMapper#updatePogingenIndienGewonnenUitdager(int, domein.Uitdaging) 
     */
    public void updatePogingenUitdager(int pogingen, Uitdaging uitdaging) {

        um.updatePogingenIndienGewonnenUitdager(pogingen, uitdaging);

    }

    /**
     *De methode zorgt er voor dat de pogingen van de uitdaagde kunnen upgedate worden. De methode doet dit adhv de aantal pogingen
     * en een uitdaging meegegeven als parameters.
     * @param poging het aantal pogingen van de uitgedaagde wnr de code gekraakt werd
     * @param uitdaging de gespeelde uitdaging
     * @see UitdagingMapper#updatePogingenIndienGewonnenUitgedaagde(int, domein.Uitdaging) 
     */
    public void updatePogingenUitgedaagde(int poging, Uitdaging uitdaging) {

        um.updatePogingenIndienGewonnenUitgedaagde(poging, uitdaging);

    }

    /**
     * De methode geeft een Lijst van integers terug die de lijst met voltooide uitdagingen terug geeft.
     * @return de lijst met voltooide uitdagingen
     */
    public List<Integer> geefLijstvoltooideUitdagingen() {

        return um.geefLijstvoltooideUitdagingen();

    }

    /**
     * De methode bepaalt de winnaar en verliezer
     * @return een Lijst van String arrays die de winnaar en verliezer voorstellen.
     * @see UitdagingMapper#geefLijstvoltooideUitdagingen() 
     * @see UitdagingMapper#geefUitdagingMetSpelID(int) 
     * @see Uitdaging#getNaamUitdager() 
     * @see Uitdaging#getNaamUitgedaagde() 
     * @see Uitdaging#getMoeilijkheidsgraad() 
     */
    public List<String[]> bepaalWinnaarEnverliezer() {
        List<String[]> resultaten = new ArrayList<String[]>();
        List<Integer> voltooideUitdagingen = um.geefLijstvoltooideUitdagingen();
        for (int teller = 0; teller < voltooideUitdagingen.size(); teller++) {
            String[] resultaat = new String[3];
            Uitdaging uitdaging = um.geefUitdagingMetSpelID(voltooideUitdagingen.get(teller));

            if (uitdaging.getPogingenUitdager() <= uitdaging.getPogingenUitgedaagde()) {

                resultaat[0] = uitdaging.getNaamUitdager();
                resultaat[1] = uitdaging.getNaamUitgedaagde();
                resultaat[2] = String.format("%d", uitdaging.getMoeilijkheidsgraad());
            } else {
                resultaat[1] = uitdaging.getNaamUitdager();
                resultaat[0] = uitdaging.getNaamUitgedaagde();
                resultaat[2] = String.format("%d", uitdaging.getMoeilijkheidsgraad());
            }

            resultaten.add(resultaat);

        }
        return resultaten;
    }
/**
 * De methode verwijdert de voltooide uitdagingen door de lijst met voltooide uitdagingen op te vragen via da uitdagingmapper en ze dan 1 voor 1 wegschrijven.
 * @see UitdagingMapper#geefLijstvoltooideUitdagingen() 
 */
    public void verwijderVoltooideUitdagingen() {

        List<Integer> voltooideUitdagingen = um.geefLijstvoltooideUitdagingen();
        // System.out.println(voltooideUitdagingen.get(0));
        for (int i = 0; i < voltooideUitdagingen.size(); i++) {
            um.verwijderVoltooideUitdaging(voltooideUitdagingen.get(i));

        }

    }

    /* public static void main(String[] args) {
   
        UitdagingMapper test = new UitdagingMapper();
        UitdagingRepository lel = new UitdagingRepository();
        List<Integer> tatat = test.geefLijstvoltooideUitdagingen();
        System.out.println(tatat.toString());
        lel.verwijderVoltooideUitdagingen();
        System.out.println(tatat.toString());
        
        
       
        
        
        
       
        
    }
    
     */
}
