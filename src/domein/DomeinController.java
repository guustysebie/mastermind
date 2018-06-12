/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

import java.util.ArrayList;
import java.util.List;
import resourceBundles.Taal;

/**
 *
 * @author User
 */
public class DomeinController {

    /*--- ATTRIBUTEN --- */
    private final SpelerRepository spelerRepository = new SpelerRepository();
    private Speler aangemeldeSpeler;
    private Spel spel;
    private final SpelRepository spelrepo = new SpelRepository();
    private List<CodePin[]> pogingen = new ArrayList<CodePin[]>();
    private UitdagingRepository ur = new UitdagingRepository();
    private Uitdaging uitdaging;

    /*--- METHODEN UC 1 --- */
    /**
     * De methode verifieert gebruikersnaam en wachtwoord met de databank en
     * geeft een gepaste foutmelding als ze fout zijn ingevoerd
     *
     * @param gebruikersnaam  geeft de gebruikersnaam mee
     * @param wachtwoord    geeft het wachtwoord mee
     * @throws IllegalArgumentException wanneer de speler niet gevonden wordt.
     * Dit kan zijn omdat er een foute gebruikersnaam of wachtwoord wordt
     * ingevoerd
     * @see Taal#getWoordUitBundle(java.lang.String)
     */
    public void meldAan(String gebruikersnaam, String wachtwoord) {
        Speler gevondenSpeler = spelerRepository.geefSpeler(gebruikersnaam);
        Speler ingegevenSpeler = new Speler(gebruikersnaam, wachtwoord);

        if (gevondenSpeler == null) {
            throw new IllegalArgumentException(Taal.getWoordUitBundle("fouteGebnaam") + String.format("%n"));

        } else {

            if (gevondenSpeler.getGebruikersnaam().equals(ingegevenSpeler.getGebruikersnaam())) {

                if (gevondenSpeler.getWachtwoord().equals(ingegevenSpeler.getWachtwoord())) {

                    aangemeldeSpeler = gevondenSpeler;
                } else {

                    throw new IllegalArgumentException(Taal.getWoordUitBundle("foutWachtwoord") + String.format("%n"));
                }

            } else {

                throw new IllegalArgumentException(Taal.getWoordUitBundle("fouteGebnaam"));

            }
        }
    }

    /**
     * De methode registreer registreert en maakt een nieuwe speler aan met
     * gebruikersnaam en wachtrwoord megegeven als parameters.Als de speler al
     * bestaat in de databank dan wordt er een exception gegooid met correcte
     * foutmelding.
     *
     * @param gebruikersnaam de gebruikersnaam
     * @param wachtwoord    eerste invoer van het wachtwoord
     * @param bvwachtwoord tweede invoer van het wachtwoord
     * @throws IllegalArgumentException wordt gegooid als de speler al bestaat
     * in de databank
     * @throws IllegalArgumentException de gebruikersnaam is te lang
     * @throws IllegalArgumentException als het wachtwoord niet voldoet aan de
     * eisen
     * @throws IllegalArgumentException de wachtwoorden niet gelijk zijn
     * @throws IllegalArgumentException de gebruikersnaam te lang is
     */
    public void registreer(String gebruikersnaam, String wachtwoord, String bvwachtwoord) {
        Speler test = new Speler(gebruikersnaam, wachtwoord);
        Speler gevondenSpeler = spelerRepository.geefSpeler(gebruikersnaam);

        if (gevondenSpeler != null) {
            throw new IllegalArgumentException(Taal.getWoordUitBundle("GebnaamGebruikt") + String.format("%n"));
        } else if ((spelerRepository.controleerSpeler(gebruikersnaam) == true) && (spelerRepository.controleerWachtwoord(wachtwoord) == true) && (bvwachtwoord.equals(wachtwoord)) && (test.controleLengteGebruikersnaam(gebruikersnaam) == true) && (test.controleLengteWachtwoord(wachtwoord) == true)) {

            spelerRepository.voegToe(test);
            aangemeldeSpeler = test;

            // throw new IllegalArgumentException(Taal.getWoordUitBundle("GebnaamTeLang") + "%n%n");
            //throw new IllegalArgumentException(Taal.getWoordUitBundle("WwNietGelijk") + "%n%n");
            // throw new IllegalArgumentException(Taal.getWoordUitBundle("WwTeLang") + "%n%n");
            //  throw new IllegalArgumentException(Taal.getWoordUitBundle("WwEisenFout") + "%n%n");
        } else if ((spelerRepository.controleerSpeler(gebruikersnaam) == false)) {
            throw new IllegalArgumentException(Taal.getWoordUitBundle("GebnaamTeLang"));

        } else if (spelerRepository.controleerWachtwoord(wachtwoord) == false) {

            throw new IllegalArgumentException(Taal.getWoordUitBundle("WwEisenFout"));

        } else if (!(bvwachtwoord.equals(wachtwoord))) {
            throw new IllegalArgumentException(Taal.getWoordUitBundle("WwNietGelijk"));

        } else if ((test.controleLengteGebruikersnaam(gebruikersnaam) == false)) {
            throw new IllegalArgumentException(Taal.getWoordUitBundle("GebnaamTeLang"));
        } else {
            throw new IllegalArgumentException("guust heeft slecht geprogrameerd");
        }
    }

    public String geefAangemeldeSpelerNaam() {
        return aangemeldeSpeler.getGebruikersnaam();
    }

    /*--- METHODEN UC 2 --- */
    /**
     * De methode geefMoeilijkheidsgraadEnGewonnenSpellen geeft de
     * moeilijkheidsgraad en de gewonnen spellen terug.
     *
     * @return de moeilijkheidsgraad en de gewonnen spellen
     * @see SpelerRepository#geefMoeilijkheidsgraad(java.lang.String)
     * @see SpelerRepository#geefGewonnenSpellen(java.lang.String)
     * @see Taal#getWoordUitBundle(java.lang.String)
     */
    public String geefMoeilijkheidsgraadEnGewonnenSpellen() {
        int moeilijkheid = spelerRepository.geefMoeilijkheidsgraad(aangemeldeSpeler.getGebruikersnaam());
        //dit is de oproep van de array maar dit zijn int geen Strings
        int[] gewonnenSpellen = spelerRepository.geefGewonnenSpellen(aangemeldeSpeler.getGebruikersnaam());
        String retour = "";
        switch (moeilijkheid) {
            case 1:
                //retour += String.format("%nDe moeilijkheidsgraden waaruit je kan kiezen is makkelijk(1)%naantal gewonnenSpellen is: %d%n", gewonnenSpellen[0]);
                retour += String.format("%s %s%n%s %d%n", Taal.getWoordUitBundle("moeilijkheidsgraad1"), Taal.getWoordUitBundle("makkelijk"),
                        Taal.getWoordUitBundle("aantalGewonnenMak"), gewonnenSpellen[0]);
                break;

            case 2:
//                retour += String.format("%nDe moeilijkheidsgraden waaruit je kan kiezen is makkelijk(1) en normaal(2)%naantal gewonnen spellen makkelijk: "
//                        + "%d%naantal gewonnen spellen normaal: %d%n", gewonnenSpellen[0], gewonnenSpellen[1]);
                retour += String.format("%s %s en %s%n%s %d%n%s %d%n", Taal.getWoordUitBundle("moeilijkheidsgraad2"), Taal.getWoordUitBundle("makkelijk"),
                        Taal.getWoordUitBundle("normaal"), Taal.getWoordUitBundle("aantalGewonnenMak"), gewonnenSpellen[0],
                        Taal.getWoordUitBundle("aantalGewonnenNor"), gewonnenSpellen[1]);
                break;

            case 3:
//                retour += String.format("%nDe moeilijkheidsgraden waaruit je kan kiezen is makkelijk(1),normaal(2) en moeilijk(3)%naantal gewonnen spellen "
//                        + "makkelijk: %d%naantal gewonnen spellen normaal: %d%naantal gewonnen spellen moeilijk: %d%n", 
//                        gewonnenSpellen[0], gewonnenSpellen[1], gewonnenSpellen[2]);
                retour += String.format("%s %s, %s en %s%n%s %d%n%s %d%n%s %d%n", Taal.getWoordUitBundle("moeilijkheidsgraad2"),
                        Taal.getWoordUitBundle("makkelijk"), Taal.getWoordUitBundle("normaal"), Taal.getWoordUitBundle("moeilijk"),
                        Taal.getWoordUitBundle("aantalGewonnenMak"), gewonnenSpellen[0], Taal.getWoordUitBundle("aantalGewonnenNor"), gewonnenSpellen[1],
                        Taal.getWoordUitBundle("aantalGewonnenMoe"), gewonnenSpellen[2]);
                break;

            default:
                retour += String.format(Taal.getWoordUitBundle("niksGespeeld") + "%n");

        }
        return retour;

    }

    /**
     * De methode maakt een spel aan aan de hand van de meegegeven
     * moeilijkheidsgraad
     *
     * @param moeilijkheidsgraad geeft de moeilijkheidsgraad van het spel mee
     */
    public void maakSpelAan(int moeilijkheidsgraad) {

        if ((aangemeldeSpeler.getGewonnenSpellen()[0] < 20 && moeilijkheidsgraad != 1)) {
            throw new IllegalArgumentException("je hebt deze moeilijkheidsgraad nog niet vrijgespeeld");
        } else if ((aangemeldeSpeler.getGewonnenSpellen()[1] < 20 && moeilijkheidsgraad > 2) ) {
            throw new IllegalArgumentException("je hebt deze moeilijkheidsgraad nog niet vrijgespeeld");
        } else {

            spel = new Spel(moeilijkheidsgraad);
        }
    }

    /**
     *
     * @return geeft een spelbord terug
     */
    public Spelbord geefSpelbord() {
        return spel.getSpelbord();
    }

    /*--- METHODEN UC 3 ---*/
    // HIER NOG NULLPOINTER KOMT DOORDAT INVOER OF POGING NIET CORR GEINTIALISEERD IS
    /**
     * De methode maakt nieuwe pogingen aan door een array van CodePinnen te
     * creeëren met lengte moilijkheidsgraad die wordt opgevraagd via
     * spel.getMoeilijkheidsgraad(). Er wordt ook een gepaste foutmelding
     * gegeven wanneer er geen geldige moeilijkheidsgraad is.
     *
     * @param invoer de poging in de vorm van een string array
     * @throws IllegalArgumentException wanneer er een ongeldige
     * moeilijkheidsgraad is
     * @see SpelerRepository#updateGewonnenSpellen(int, java.lang.String)
     * @see Spel#getMoeilijkheidsgraad()
     * @see Spel#doePoging(domein.CodePin[])
     * @see Uitdaging#getNaamUitdaging()
     * @see Uitdaging#getNaamUitdager()
     * @see Uitdaging#getNaamUitgedaagde()

     */
    public void doePoging(String[] invoer) {
        //void 
        CodePin[] poging;
        String retour = "";
        switch (spel.getMoeilijkheidsgraad()) {
            case 1:
                poging = new CodePin[4];
                break;
            case 2:
                poging = new CodePin[4];
                break;
            case 3:
                poging = new CodePin[5];
                break;
            default:
                throw new IllegalArgumentException("geen geldige moeilijkheidsgraad");
        }
        for (int i = 0; i < poging.length; i++) {
            poging[i] = new CodePin(invoer[i]);

        }

        spel.doePoging(poging);
        pogingen.add(poging);//s

        if (isGewonnen() == true) {
            spelerRepository.updateGewonnenSpellen(spel.getMoeilijkheidsgraad(), aangemeldeSpeler.getGebruikersnaam());
            //als aantal spelletjes niet mag upgedate worden als het ook een uitdaging is
            String spelnaam= spel.getNaam();
            String[] arr = spelnaam.split(" ");
        
            if (isuitdaging(geefspelnaam()) == true && ur.geefUitdaging(arr[0]).getNaamUitdager().equals(aangemeldeSpeler.getGebruikersnaam())) {
                //update gewonnenspellen uitdager
                
                ur.updatePogingenUitdager(spel.geefAantalPogingen(), ur.geefUitdaging(arr[0]));
            }

            if (isuitdaging(geefspelnaam()) == true && ur.geefUitdaging(arr[0]).getNaamUitgedaagde().equals(aangemeldeSpeler.getGebruikersnaam())) {

                ur.updatePogingenUitgedaagde(spel.geefAantalPogingen(), ur.geefUitdaging(arr[0]));

            }
            pogingen = new ArrayList<CodePin[]>();
        }
    }

    /**
     * De methode geeft de info van het gewonnenspel bestaande uit aantal
     * zetten, aantal sterren, de te kraken code, aantal pogingen en de aantal
     * spellen tot de volgende ster
     *
     * @return info gewonnen spel
     * @see Taal#getWoordUitBundle(java.lang.String)
     * @see Spel#geefTekrakenCodeString()
     * @see Spel#geefTekrakenCodeString()
     * @see Spel#getMoeilijkheidsgraad()
     */
    public String geefInfoGewonnenSpel() {
        String retour = "";
        retour += String.format(Taal.getWoordUitBundle("gewonnen") + " %s%n" + Taal.getWoordUitBundle("aantalZetten") + " %s%n"
                + Taal.getWoordUitBundle("aantalSterren") + " %s%n" + Taal.getWoordUitBundle("aantalSpellen") + " %d%n",
                spel.geefTekrakenCodeString(), spel.geefAantalPogingen(), aangemeldeSpeler.geefAantalSterren(spel.getMoeilijkheidsgraad()),
                aangemeldeSpeler.geefAantalSpellenTotVolgendeSter(spel.getMoeilijkheidsgraad()));

        return retour;
    }

    /**
     * De methode geeft terug of de speler gewonnen is aan de hand van de
     * moeilijkheidsgraad en zijn laatste evaluatie. Aan de hand van de lengte
     * van zijn laatste evalutaie kunnen we controleren of deze lengte gelijk is
     * aan de benodigde moeilijkheidsgraad.
     *
     * @return een boolean die zegt of de speler gewonnen is of niet
     * @see Spel#getMoeilijkheidsgraad()
     * @see Spel#getLaatsteEvaluatie()
     */
    public boolean isGewonnen() {
        int count = 0;
        for (int teller = 0; teller < spel.getLaatsteEvaluatie().length; teller++) {

            if ("zwart".equals(spel.getLaatsteEvaluatie()[teller].getKleur())) {
                count++;
            }
        }
        switch (spel.getMoeilijkheidsgraad()) {
            case 1:
                if (count == 4) {
                    return true;
                }

            case 2:
                if (count == 4) {
                    return true;
                }
            case 3:
                if (count == 5) {
                    return true;
                }
            default:
                return false;
        }

    }

    /**
     * De methode geeft aan de speler een string van zijn laatste evaluatiepin
     * terug.
     *
     * @return een string van de speler zijn laatste evaluatiepin
     * @see Spel#laatsteEvaluatie
     *
     */
    public String geefLaatsteEvaltiePin() {
        String laatsteEvaluatiePin = "";
        for (EvaluatiePin laatsteEvaluatie : spel.getLaatsteEvaluatie()) {
            laatsteEvaluatiePin += laatsteEvaluatie.toString() + " ";

        }
        return laatsteEvaluatiePin;
    }

    /**
     * De methode geeft de code terug bestaande uit een array van CodePinnen die
     * we uit spel haleb via de methode getTeKrakenCode
     *
     * @return code bestaande uit een array van Codepinnen
     * @see Spel#getTeKrakenCode()
     */
    public CodePin[] geefCode() {

        return spel.getTeKrakenCode();
    }

    /**
     * De methode geeft de moeilijkheidsgraad terug die we uit de klasse spel
     * halen
     *
     * @return moeilijkheidsgraad
     * @see Spel#getMoeilijkheidsgraad()
     */
    public int geefMoeilijkheidsgraad() {

        return spel.getMoeilijkheidsgraad();
    }

    /**
     * De methode slaat het spel op in de databank aan de hand van de String
     * spelnaam die de primaire sleutel is van de klasse spel
     *
     * @param spelnaam naam van het spel
     * @see Spel#setNaam(java.lang.String)
     * @see SpelRepository#voegSpelToe(domein.Spel, java.lang.String)
     * @see SpelRepository#slaSpelOp(java.util.List, java.lang.String)
     * @see Spel#setNaam(java.lang.String)
     */
    public void slaSpelOp(String spelnaam) {
        spel.setNaam(spelnaam);
        spelrepo.voegSpelToe(spel, aangemeldeSpeler.getGebruikersnaam());
        spelrepo.slaSpelOp(pogingen, spelnaam);
        pogingen = new ArrayList<CodePin[]>();
    }

    /**
     * De methode slaat een spel op die een uitdaging moet zijn
     *
     * @see SpelRepository#slaSpelOp(java.util.List, java.lang.String)
     */
    public void slaSpelOpdieEenUitdagingIS() {
        spelrepo.slaSpelOp(pogingen, geefspelnaam());
    }

    /*-----------METHODES UC4--------------*/
    /**
     * De methode start het laden van een spel door een lijst van spellen terug
     * te geven die we uit de spelRepository halen aan de hand van de
     * gebruikersnaam van de reeds aangemelde speler.
     *
     * @return een lijst van spellen
     * @see SpelRepository#geefLijstMetSpellen(java.lang.String)
     */
    public List<Spel> startLadenSpel() {
        return spelrepo.geefLijstMetSpellen(aangemeldeSpeler.getGebruikersnaam());
    }

    /**
     * De methode laat de speler toe een spel te kiezen aan de hand van de
     * primaire sleutel van spel uit onze databank, namelijk spelnaam. Deze
     * methode geeft ook een gepaste foutmelding wanneer het spel niet in de
     * databank zit en dusdanig ook niet in de lijst van spellen.
     *
     * @param spelnaam de naam van het spel
     * @throws IllegalArgumentException wanneer het spel niet in de databank zit
     * @see SpelRepository#geefSpel(java.lang.String)
     * @see Spel#getNaamSpeler()
     * @see Speler#getGebruikersnaam()
     * @see Spel#doePoging(domein.CodePin[])
     * @see SpelRepository#verwijderSpel(java.lang.String)
     * @see SpelRepository#geefPogingen(java.lang.String)
     */
    public void kiesSpel(String spelnaam) {
        //void 
        /*  if (!spelrepo.geefSpel(spelnaam).getNaamSpeler().equals(aangemeldeSpeler.getGebruikersnaam())) {
            throw new IllegalArgumentException("spel staat niet in de lijst");
        }*/

        //spel = new Spel(spelrepo.geefSpel(spelnaam).getNaam(), spelrepo.geefSpel(spelnaam).getMoeilijkheidsgraad(), spelrepo.geefSpel(spelnaam).getTeKrakenCode());      
        spel = spelrepo.geefSpel(spelnaam);
        if(spel == null){
        throw new IllegalArgumentException("spel staat niet in de lijst");
        }
        
        List<CodePin[]> test = spelrepo.geefPogingen(spelnaam);

        CodePin[] poging = new CodePin[5];
        for (int h = 0; h < test.size(); h++) {

            CodePin[] HALLO = test.get(h);
            pogingen.add(test.get(h));
            spel.doePoging(test.get(h));

        }

        if (isuitdaging(spelnaam) == false) {
            spelrepo.verwijderSpel(spelnaam);
        }

    }

    /*-------Methodes_UC5------------*/
    /**
     * De methode geeft een lijst met alle beschikbare spelers waartegen de
     * speler kan spelen. We bepalen dit aan de hand van de speler zijn
     * moeilijkheidsgraad en deze kan iedereen uitdagen die dezelfde
     * moeilijkheidsgraad heeft of lager.
     *
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     * @return een List van Strings met de spelers tegen wie de speler kan
     * spelen
     * @throws IllegalArgumentException wanneer de gebruiker al een uitdaging
     * heeft en hij deze eerst moet voltooien
     * @see UitdagingRepository#magUitdagingAanmaken(java.lang.String)
     * @see UitdagingRepository#geefNaamUitdagingVanUitdager(java.lang.String)
     * @see Speler#getGebruikersnaam()
     * @see Uitdaging#naamUitdaging
     * @see SpelerRepository#geefLijstmetSpelers(java.lang.String, int)
     */
    public List<String[]> geefLijstMetSpelers(int moeilijkheidsgraad) {
      
        if (ur.magUitdagingAanmaken(aangemeldeSpeler.getGebruikersnaam()) == false) {
            String naamuitdaging = ur.geefNaamUitdagingVanUitdager(aangemeldeSpeler.getGebruikersnaam());
            
            naamuitdaging += String.format(" %s", aangemeldeSpeler.getGebruikersnaam());
      
            System.out.println(naamuitdaging);
            kiesSpel(naamuitdaging);
        
            throw new IllegalArgumentException("Je hebt al een uitdaging, gelieve deze eerst te voltooien.");
        } else {

            return spelerRepository.geefLijstmetSpelers(aangemeldeSpeler.getGebruikersnaam(), moeilijkheidsgraad);
        }
    }

    /**
     * De methode maakt een uitdaging aan de hand van de naam van de uitgedaagde
     * speler, de moeilijkheidsgraad en we geven hier een naam van de uitdaging
     * mee. Dit wordt gedaan door een lijst met de lijst van spelers terug te
     * geven tegen wie de speler kan spelen aan de hand van de
     * moeilijkheidsgraad van de gebruiker. Na de creatie van de uitdaging
     * plaatsen we deze in de uitdagingrepository. Als de gebruiker zijn
     * uitgedaagde speler heeft gekozen controleren we ook dat deze wel degelijk
     * in de databank of lijst van spelers zit. Zo niet dan geeft de methode een
     * gepaste foutmelding.
     *
     * @param uitgedaagdespeler naam van de speler waartegen je wenst te spelen
     * @param moeilijkheidsgraad moeilijkheidsgraad van de uitdaging
     * @param naamuitdaging naam van de uitdaging
     * @throws IllegalArgumentException wanneer de naam van de uitgedaagde niet
     * in de lijst van spelers staat
     * @see SpelerRepository#geefLijstmetSpelers(java.lang.String, int)
     * @see UitdagingRepository#maakUitdagingAan(domein.Uitdaging)
     * @see DomeinController#kiesSpel(java.lang.String)
     * @see Uitdaging#getMoeilijkheidsgraad()
     * @see Uitdaging#getNaamUitdaging()
     * @see Speler#getGebruikersnaam()
     */
    public void maakUitdagingAan(String uitgedaagdespeler, int moeilijkheidsgraad, String naamuitdaging) {
        List<String[]> lijstmetspelers = geefLijstMetSpelers(moeilijkheidsgraad);
        int count = 0;
        for (int i = 0; i < lijstmetspelers.size(); i++) {
            String[] speler = lijstmetspelers.get(i);
            if (speler[0].equals(uitgedaagdespeler)) {
                count++;
            }
        }
        if (count < 1) {
            throw new IllegalArgumentException("naam staat niet in de lijst");
        }

        uitdaging = new Uitdaging(moeilijkheidsgraad, aangemeldeSpeler.getGebruikersnaam(), uitgedaagdespeler, naamuitdaging);
        ur.maakUitdagingAan(uitdaging);
        //spelreposetory.maakspeluitdagerAan();
        //TODO
        String spelnaam = "";
        spelnaam += String.format( naamuitdaging + " " + aangemeldeSpeler.getGebruikersnaam());
        System.out.println(spelnaam); 
        kiesSpel(spelnaam);

    }

    /**
     * De methode controleert aan de hand van de spelnaam of een spel een
     * uitdaging is. Deze spelnaam geven we mee als parameter en dan via deze
     * spelnaam controleren we in de databank via de methode isUitdaging()
     *
     * @param spelnaam naam van het spel
     * @return een boolean of het spel al dan niet een uitdaging is.
     * @see SpelRepository#isuitdaging(java.lang.String)
     */
    public boolean isuitdaging(String spelnaam) {
        return spelrepo.isuitdaging(spelnaam);
    }

    /*-------Methodes_UC6--------*/
    /**
     * De methode geeft een lijst met de uitdagingen die de aangemelde speler
     * heeft.
     *
     * @return een List van String arrays met alle uitdagingen die de gebruiker
     * heeft.
     * @see UitdagingRepository#geefUitdagingen(java.lang.String)
     */
    public List<String[]> geefLijstmetUidagingen() {
        return ur.geefUitdagingen(aangemeldeSpeler.getGebruikersnaam());
    }

    /**
     * De methode is de start van de Use case aanvaard uitdaging. Ze maakt een
     * List van String arrays die een lijst van de uitdagingen is die de
     * gebruiker heeft. De methode vraagt een bepaalde uitdaging op uit de
     * uitdagingrepository. De methode kan deze uitdaging opvragen via de
     * primaire sleutel naamuitdaging uit de databank. De methode geeft ook een
     * gepaste foutmelding wanneer de naam zich niet in de lijst en databank
     * bevindt.
     *
     * @param naamuitdaging  naam van de uitdaging die de speler wilt starten
     * @throws IllegalArgumentException wanner de naam van de uitdaging niet in
     * de lijst staat
     * @see DomeinController#geefLijstmetUidagingen()
     * @see UitdagingRepository#geefUitdaging(java.lang.String)
     * @see DomeinController#kiesSpel(java.lang.String)
     */
    public void startUitdaging(String naamuitdaging) {
        List<String[]> lijstmetuitdagingen = geefLijstmetUidagingen();
        int count = 0;
        for (int i = 0; i < lijstmetuitdagingen.size(); i++) {
            String[] speler = lijstmetuitdagingen.get(i);
            if (speler[0].equals(naamuitdaging)) {
                count++;

            }
        }
        if (count < 1) {
            throw new IllegalArgumentException("naam staat niet in de lijst");
        }

        uitdaging = ur.geefUitdaging(naamuitdaging);
        String naamspel = "";
        naamspel += String.format("%s %s", naamuitdaging, aangemeldeSpeler.getGebruikersnaam());
        //spelrepository.maakspelUitdaagdeAan();
        //TODO
        kiesSpel(naamspel);
    }

    /**
     * De methode geeft het klassement terug door deze op te vragen uit de
     * spelerRepository
     *
     * @return het klassement
     * @see SpelerRepository#geefKlassement()
     */
    public List<List<String[]>> geefKlassement() {
        return spelerRepository.geefKlassement();
    }

    /**
     * De methode zorgt er voor dat het klassement upgedate kan worden
     *
     * @see SpelerRepository#updatePuntenEnPogingenUitdagingen(java.util.List)
     * @see UitdagingRepository#bepaalWinnaarEnverliezer()
  
     * SpelRepository#verwijderSpellenDieTotEenVoltooideUitdagingBehoren(java.util.List)
     * @see UitdagingRepository#geefLijstvoltooideUitdagingen()
     * @see UitdagingRepository#verwijderVoltooideUitdagingen()
     */
    public void updateKlassement() {
        spelerRepository.updatePuntenEnPogingenUitdagingen(ur.bepaalWinnaarEnverliezer());
        spelrepo.verwijderSpellenDieTotEenVoltooideUitdagingBehoren(ur.geefLijstvoltooideUitdagingen());
        ur.verwijderVoltooideUitdagingen();

    }

    /**
     * De methode geeft de spelnaam terug
     *
     * @return spelnaam
     * @see Spel#getNaam()
     */
    public String geefspelnaam() {

        return spel.getNaam();
    }

}
