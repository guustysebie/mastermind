/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author User
 */
public class Verificatie {

    private static final SpelerRepository spelerRepository = new SpelerRepository();
    private static final SpelRepository spelRepository = new SpelRepository();
    private static final UitdagingRepository uitdagingRepository = new UitdagingRepository();

    /**
     * De methode controleert of de gebruikersnaam voldoet aan de eisen. Als dit niet zo is dan worden de correct foutmeldingen
     * gegeven. Dit is wanneer de gebruikersnaam al in gebruik is, de gebruikersnaam te lang is en/of de gebruikernsaam niet ingevuld is.
     * @param gebruikersnaam naam van de speler
     * @throws IllegalArgumentException wanneer de gebruikersnaam al in gebruik of te lang is of wanneer de gebruiker de gebruikersnaam niet heeft ingevuld.
     * @return de boolean die zegt of een gebruikersnaam geldig is of niet.
     * @see SpelerRepository#geefSpeler(java.lang.String) 
     */
    public static boolean controleerGebruikersnaamRegistreer(String gebruikersnaam) {
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if (speler != null) {
            throw new IllegalArgumentException("De gebruikersnaam is al in gebruik");
        } else if (gebruikersnaam.length() > 20) //Controleert of speler in de db zit (indien true zit speler nog niet in de databank) || controleert of gebruikersnaam minder dan 20 tekens bevat
        {
            throw new IllegalArgumentException("De gebruikersnaam is te lang");
        } else {
            if (gebruikersnaam.equals("")) {
                throw new IllegalArgumentException("De gebruikersnaam is verplicht in te vullen");
            }
            return true;
        }
    }

    /**
     * De methode controleert of de gebruikersnaam voldoet aan de eisen om aan te kunnen melden. Wanneer dat niet zo is wordt een gepaste foutboodschap gegooid.
     * @param gebruikersnaam naam van de gebruiker
     * @throws IllegalArgumentException wanneer de gebruiker zich nog niet heeft geregistreerd en de gebruikersnaam dus nog niet in de databank zit.
     * @return een boolean die laat weten of de gebruiker zich kan aanmelden met zijn gebruikersnaam of niet
     * @see SpelerRepository#geefSpeler(java.lang.String) 
     */
    public static boolean controleerGebruikersnaamAanmelden(String gebruikersnaam) {
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if (speler == null) {
            throw new IllegalArgumentException("Gelieve u eerst te regisreren");
        } else {
            return true;
        }
    }

    /**
     * De methode controleert of de gebruiker zich kan registreren met zijn wachtoord of niet. Als het wachtwoord niet voldoet aan de eisen en hij zich dus niet kan tegistreren krijgt hij de gepaste foutboodschappen.
     * @param wachtwoord wachtwoord van gebruiker
     * @throws IllegalArgumentException wanneer het wachtwoord te lang is, leeg is of niet de juiste criteria volgt om een kwalitatief wachtwoord te vormen.
     * @return een boolan die zegt of de gebruiker zich kan registreren met zijn opgegeven wachtwoord.
     */
    public static boolean controleerWachtwoordRegistreer(String wachtwoord) {
        if (wachtwoord.length() > 20) {
            throw new IllegalArgumentException("Het wachtwoord is te lang");
        } else {
            if (wachtwoord.equals("")) {
                throw new IllegalArgumentException("Het wachtwoord mag niet leeg zijn");
            } else {

                if (!wachtwoord.matches("\\d{1}\\S{6,20}\\d{1}")) {
                    throw new IllegalArgumentException("Het wachtwoord voldoet niet aan de vereisten");
                } else {
                    return true;
                }
            }
        }
    }

    /**
     * De methode controleert of de gebruiker zich kan aanmelden met het opgegevn wachtwoord. Als dat niet het geval is wordt een correcte foutboodschap gegeven.
     * @param gebruikersnaam naam van de gebruiker
     * @param wachtwoord wachtwoord van de gebruiker
     * @throws IllegalArgumentException als het ingevoerde wachtwoord verkeerd is
     * @return een boolean die zegt of de gebruiker zich kan aanmelden met het opgegeven wachtwoord.
     * @see Speler#getWachtwoord() 
    */
    public static boolean controleerWachtwoordAanmelden(String gebruikersnaam, String wachtwoord) {
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        if (speler.getWachtwoord().equals(wachtwoord)) {
            return true;
        } else {
            throw new IllegalArgumentException("Het ingevoerde wachtwoord is verkeerd");
        }
    }

    /**
     * De methode controleert of de gebruiker de bevestiging van het wachtwoord correct heeft ingegeven. Als dit niet het geval is wordt een correcte foutboodschap gegeven.
     * @param wachtwoord wachtwoord van de gebruiker
     * @param bevestigWachtwoord 2de wachtwoord ingevoerd door de gebruiker
     * @throws IllegalArgumentException als de wachtwoorden niet gelijk zijn aan elkaar.
     * @return een boolean die zegt of de gebruiker dezelfde wachtwoorden heeft ingegeven
   
     */
    public static boolean bevestigWachtwoord(String wachtwoord, String bevestigWachtwoord) {
        if (bevestigWachtwoord.equals(wachtwoord)) {
            return true;
        } else {
            throw new IllegalArgumentException("De wachtwoorden zijn niet aan elkaar gelijk");
        }
    }

    /**
     *
     * @param naam naam van het spel 
     * @throws IllegalArgumentException dat de naam al in gebrui is
     * @return returnt of de naam al in gebruik is ofniet
     */
    public static boolean controleerSpelNaam(String naam) {
        Spel spel = spelRepository.geefSpel(naam);
        if (spel != null) {
            throw new IllegalArgumentException("De naam van het spel is al in gebruik");
        } else {
            return true;
        }
    }

    public static boolean controleerBezigMetUitdaging(String gebruikersnaam, String uitdagingNaam) {
        Speler speler = spelerRepository.geefSpeler(gebruikersnaam);
        Uitdaging uitdaging = uitdagingRepository.geefUitdaging(uitdagingNaam);

        if (uitdaging.getNaamUitgedaagde().equals(speler.getGebruikersnaam()) && uitdaging.getPogingenUitgedaagde() > 0) {
            if (uitdaging.getNaamUitdager().equals(speler.getGebruikersnaam()) && uitdaging.getPogingenUitdager() > 0) {
                return true;
            } else {
                throw new IllegalArgumentException("U bent al bezig met een uitdaging (uitdager)");
            }
        } else {
            throw new IllegalArgumentException("U bent al bezig met een uitdaging (uitgedaagde)");
        }

    }

    public static boolean controleerNaamUitdaging(String naam) {
        Uitdaging uitdaging = uitdagingRepository.geefUitdaging(naam);
        if (naam.equals("")) {
            throw new IllegalArgumentException("De naam mag niet leeg zijn");
        } else {
            if (naam.length() < 26) {
                throw new IllegalArgumentException("De ingevoerde naam is leeg");
            } else {
                if (uitdaging != null) {
                    throw new IllegalArgumentException("De naam is al in gebruik");
                } else {
                    return true;
                }
            }
        }
    }
    
    public static boolean controleerNaamUitgedaagde(String naam, int moeilijkheidsgraad)
    {
        
        if(naam.equals("")){
            throw new IllegalArgumentException("De naam mag niet leeg zijn");
        }
        else
        {
            if(spelerRepository.geefMoeilijkheidsgraad(naam)==1)
            {
                switch(moeilijkheidsgraad)
                {
                    case 2: 
                        throw new IllegalArgumentException("Deze speler heeft moeilijkheidsgraad 2 nog niet vrij gespeeld");
                    case 3: 
                        throw new IllegalArgumentException("Deze speler heeft moeilijkheidsgraad 3 nog niet vrij gespeeld");
                    default:
                        return true;
                }
            }
            else
            {
                if(spelerRepository.geefMoeilijkheidsgraad(naam)==2)
                {
                    if(moeilijkheidsgraad == 3)
                    {
                        throw new IllegalArgumentException("Deze speler heeft moeilijkheidsgreaad 3 nog niet vrij gespeeld");
                    }
                    else
                    {
                        return true;
                    }
                }
                else
                {
                    if(spelerRepository.geefMoeilijkheidsgraad(naam)==3)
                    {
                        return true;
                    }
                    else{
                        throw new IllegalArgumentException("Gelieve een geldige moeilijkheidsgraad in te geven");
                    }
                }
            }
        }
        
    }

}
