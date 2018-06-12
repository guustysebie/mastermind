/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.Spel;
import domein.Speler;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author guust
 */
public class SpelerMapper {

    /*------MYSQL statements-------*/
    
    /**
     * Voegt een speler toe aan de databank
     */
    private static final String INSERT_SPELER = "INSERT INTO ID222177_g38.Speler  (gebruikersnaam,wachtwoord )  values (? ,?)";

    /**
     * Vraagt een speler op aan de databank aan de hand van de gebruikersnaam
     */
    private static final String VRAAG_SPELER = "SELECT gebruikersnaam, wachtwoord, gewonnenSpellenMakkelijk, gewonnenSpellenNormaal, gewonnenSpellenMoeilijk from ID222177_g38.Speler where gebruikersnaam = ? ";

    /**
     * Update aantal gewonnen spellen met moeilijkheidsgraad makkelijk van speler
     */
    private static final String UPDATE_SPELER_MAKKELIJK = "UPDATE ID222177_g38.Speler SET GewonnenSpellenMakkelijk = GewonnenSpellenMakkelijk + 1 WHERE gebruikersnaam = ?";

    /**
     * Update aantal gewonnen spellen met moeilijkheidsgraad normaal van speler
     */
    private static final String UPDATE_SPELER_NORMAAL = "UPDATE ID222177_g38.Speler SET GewonnenSpellenNormaal = GewonnenSpellenNormaal + 1 WHERE gebruikersnaam = ?";

    /**
     * Update aantal gewonnen spellen met moeilijkheidsgraad moeilijk van speler
     */
    private static final String UPDATE_SPELER_MOEILIJK = "UPDATE ID222177_g38.Speler SET GewonnenSpellenMoeilijk = GewonnenSpellenMoeilijk + 1 WHERE gebruikersnaam = ?";

    /**
     * Geeft een alfabetisch geordende lijst van spelers
     */
    private static final String GEEF_LIJST_MET_SPELERS = "select * from ID222177_g38.Speler";

    /**
     * Geeft de spelers die in het klassement makkelijk staan geordend op aantal gewonnen spelletjes van hoog naar laag, bij gelijk aantal staat degene met 
     * procentueel de meeste gewonnen spelletjes bovenaan
     */
    private static final String GEEF_KLASSEMENT_MAKKELIJK = "select Speler.gebruikersnaam, Speler.puntenUitdagingenMakkelijk, Speler.gespeeldeUitdagingenMakkelijk "
            + "from ID222177_g38.Speler "
            + "order by puntenUitdagingenMakkelijk DESC, puntenUitdagingenMakkelijk/gespeeldeUitdagingenMakkelijk DESC";

    /**
     * Geeft de spelers die in het klassement normaal staan geordend op aantal gewonnen spelletjes van hoog naar laag, bij gelijk aantal staat degene met 
     * procentueel de meeste gewonnen spelletjes bovenaan
     */
    private static final String GEEF_KLASSEMENT_NORMAAL = "SELECT "
            + "    Speler.gebruikersnaam, "
            + "    Speler.puntenUitdagingenNormaal, "
            + "    Speler.gespeeldeUitdagingenNormaal "
            + "FROM "
            + "    ID222177_g38.Speler "
            + "ORDER BY puntenUitdagingenNormaal DESC , puntenUitdagingenNormaal / gespeeldeUitdagingenNormaal DESC";

    /**
     * Geeft de spelers die in het klassement moeilijk staan geordend op aantal gewonnen spelletjes van hoog naar laag, bij gelijk aantal staat degene met 
     * procentueel de meeste gewonnen spelletjes bovenaan
     */
    private static final String GEEF_KLASSEMENT_MOEILIJK = "SELECT "
            + "    Speler.gebruikersnaam, "
            + "    Speler.puntenUitdagingenMoeilijk, "
            + "    Speler.gespeeldeUitdagingenMoeilijk "
            + "FROM "
            + "    ID222177_g38.Speler "
            + "ORDER BY puntenUitdagingenMoeilijk DESC , puntenUitdagingenMoeilijk / gespeeldeUitdagingenMoeilijk DESC";

    /**
     * Update de punten van de uitdaging van de winnaar als de uitdaging makkelijk is
     */
    private static final String UPDATE_PUNTEN_UITDAGING_MAKKELIJK_WINNAAR = "UPDATE ID222177_g38.Speler "
            + "set puntenUitdagingenMakkelijk = puntenUitdagingenMakkelijk +3 "
            + " where gebruikersnaam = ?  ";

    /**
     * Update de punten van de uitdaging van de verliezer als de uitdaging makkelijk is
     */
    private static final String UPDATE_PUNTEN_UITDAGING_MAKKELIJK_VERLIEZER = "UPDATE ID222177_g38.Speler "
            + "set puntenUitdagingenMakkelijk = puntenUitdagingenMakkelijk -1 "
            + " where gebruikersnaam = ? and puntenUitdagingenMakkelijk > 0 ;";

    /**
     * Update de punten van de uitdaging van de winnaar als de uitdaging normaal is
     */
    private static final String UPDATE_PUNTEN_UITDAGING_NORMAAL_WINNAAR = "update ID222177_g38.Speler "
            + "set puntenUitdagingenNormaal = puntenUitdagingenNormaal +3 "
            + "where gebruikersnaam = ? ; ";

    /**
     * Update de punten van de uitdaging van de verliezer als de uitdaging normaal is
     */
    private static final String UPDATE_PUNTEN_UITDAGING_NORMAAL_VERLIEZER = "update ID222177_g38.Speler "
            + "set puntenUitdagingenNormaal = puntenUitdagingenNormaal -1 "
            + "where gebruikersnaam = ? and puntenUitdagingenNormaal > 0;";

    /**
     * Update de punten van de uitdaging van de winnaar als de uitdaging moeilijk is
     */
    private static final String UPDATE_PUNTEN_UITDAGING_MOEILIJK_WINNAAR = "update ID222177_g38.Speler "
            + "set puntenUitdagingenMoeilijk  = puntenUitdagingenMoeilijk +3 "
            + "where gebruikersnaam = ?; ";

    /**
     * Update de punten van de uitdaging van de verliezer als de uitdaging moeilijk is
     */
    private static final String UPDATE_PUNTEN_UITDAGING_MOEILIJK_VERLIEZER = "update ID222177_g38.Speler "
            + "set puntenUitdagingenMoeilijk = puntenUitdagingenMoeilijk -1 "
            + "where gebruikersnaam = ? and puntenUitdagingenMoeilijk > 0;";

    /**
     * Update het aantal keer een persoon een makkelijke uitdaging heeft volbracht
     */
    private static final String UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_MAKKELIJK = "update ID222177_g38.Speler "
            + "set gespeeldeUitdagingenMakkelijk = gespeeldeUitdagingenMakkelijk+1 "
            + "where gebruikersnaam = ? ; ";

    /**
     * Update het aantal keer een persoon een normale uitdaging heeft volbracht
     */
    private static final String UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_NORMAAL = "update ID222177_g38.Speler "
            + "set gespeeldeUitdagingenNormaal = gespeeldeUitdagingenNormaal +1 "
            + "where gebruikersnaam = ? ; ";

    /**
     * Update het aantal keer een persoon een moeilijke uitdaging heeft volbracht
     */
    private static final String UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_MOEILIJK = "update ID222177_g38.Speler "
            + "set gespeeldeUitdagingenMoeilijk = gespeeldeUitdagingenMoeilijk +1 "
            + "where gebruikersnaam = ? ; ";

    /*----FUNCTIES---------*/
    
    /**
     * Geeft een speler weer adhv de unieke gebruikersnaam in de databank
     * @param gebruikersnaam vraagt de gebruikersnaam
     * @return returnt een speler
     */
    public Speler geefSpeler(String gebruikersnaam) {
        Speler speler = null;/* maakt een speler aan die wordt ingesteld op NULL*/
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); /*maakt connectie met de databank adhv de connectie string*/
                PreparedStatement query = conn.prepareStatement(VRAAG_SPELER)) {/*voert het vooraf opgestelde statement uit*/
            query.setString(1, gebruikersnaam);                                          //zet de ontbrekende parameter in het statement met ge meegegeven gebruikersnaam
            try (ResultSet rs = query.executeQuery()) {                                  //maakt een resultset aan
                if (rs.next()) {                                                         //als er een resultaat terugkomt dan doet hij het volgende
                    String naam = rs.getString("gebruikersnaam");                        //haalt gebruikersnaam uit db 
                    String wachtwoord = rs.getString("wachtwoord");                      //haalt ww uit db
                    int gewonnenSpellenMakkelijk = rs.getInt("gewonnenSpellenMakkelijk");//haalt spellen uit db
                    int gewonnenSpellenNormaal = rs.getInt("gewonnenSpellenNormaal");    //haalt spellen uit db
                    int gewonnenSpellenMoeilijk = rs.getInt("gewonnenSpellenMoeilijk");  //haalt spellen uit db
                    int[] gewonnenSpellen = new int[3];                                 //steekt de spellen in een array
                    gewonnenSpellen[0] = gewonnenSpellenMakkelijk;
                    gewonnenSpellen[1] = gewonnenSpellenNormaal;
                    gewonnenSpellen[2] = gewonnenSpellenMoeilijk;
                    speler = new Speler(naam, wachtwoord, gewonnenSpellen);             //maakt de speler aan met de parameters gehaald uit db
                }
            }
        } catch (SQLException ex) {                                                     //indien iets misloopt met de db catch hij de foutmelding
            throw new RuntimeException(ex);
        }
        return speler;                                                                  // returnt speler
    }

    /**
     * Geeft een lijst met alle spelers terug
     * @param gebruikersnaam de naam van de aangemelde speler
     * @param moeilijkheidsgraad de moeilijkheidsgraad van het spel
     * @return returnt een string array met de waarden op plaats 0 de naam van de speler en op plaats 1 het aantal gewonnen spelletjes in de gekozenmoeilikheidsgraad
     */
    public List<String[]> geefLijstMetSpelers(String gebruikersnaam, int moeilijkheidsgraad) {              //methode die alle spelers in de db weergeeft adhv naam en moeilijkheidsgraad
        String retour = " naam       moeilijkheidsgraad ";
        List<String[]> lijstMetSpelers = new ArrayList<>();
       // String[] Speler = new String[2];                                                                                    //maakt een string aan die te gebruiken is door de string.format
        switch (moeilijkheidsgraad) {                                                               //switch case aanmaken moeilijkheidsgraad
            case 1:                                                                                 // optie 1 voor moeilijkheidsgraad makkelijk
                try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                        PreparedStatement query = conn.prepareStatement(GEEF_LIJST_MET_SPELERS)) {  //voert het voorafgemaakte statement uit
                    try (ResultSet rs = query.executeQuery()) {                                     //maak een resultset aan
                        while (rs.next()) {
                            String[] Speler = new String[2];//while loop die blijft gaan zolang er nog een resultaat is 
                            String naam = rs.getString("gebruikersnaam");
                            int gewonnenmakkelijk = rs.getInt("gewonnenSpellenMakkelijk");
                            Speler[0] = naam;
                            Speler[1] = String.format("%d", gewonnenmakkelijk);

                            if (naam.equals(gebruikersnaam)) {  
                                //kijken of de persoon in de lijst mag staan
                            } else {
                                
                                lijstMetSpelers.add(Speler);

                            }
                          
                        }

                    }
                } catch (SQLException ex) {                                                         //indien iets fout loopt catcht hij de exception
                    throw new RuntimeException(ex);
                }
                break;
            case 2:
                try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //zelfde als de case maar nu voor normaal
                        PreparedStatement query = conn.prepareStatement(GEEF_LIJST_MET_SPELERS)) {
                    try (ResultSet rs = query.executeQuery()) {
                        while (rs.next()) {
                            String[] Speler = new String[2];
                            String naam = rs.getString("gebruikersnaam");
                            int gewonnenMakkelijk = rs.getInt("gewonnenSpellenMakkelijk");
                            if (gewonnenMakkelijk < 20 || naam.equals(gebruikersnaam)) {
                            } else {
                                Speler[0] = naam;
                                Speler[1] = String.format("%d", gewonnenMakkelijk);
                                lijstMetSpelers.add(Speler);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case 3:
                try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //zelfde als de case maar nu voor normaal
                        PreparedStatement query = conn.prepareStatement(GEEF_LIJST_MET_SPELERS)) {
                    try (ResultSet rs = query.executeQuery()) {
                        while (rs.next()) {
                            String[] Speler = new String[2];
                            String naam = rs.getString("gebruikersnaam");
                            int gewonnennormaal = rs.getInt("gewonnenSpellenNormaal");
                            if (gewonnennormaal < 20 || naam.equals(gebruikersnaam)) {
                            } else {
                                Speler[0] = naam;
                                Speler[1] = String.format("%d", gewonnennormaal);
                                lijstMetSpelers.add(Speler);
                            }
                        }
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            default:
                throw new IllegalArgumentException("fout met moeilijkheidsgraad");               //extra veiligheid voor als er iets mis is met moeilijkheidsgraad
        }
        return lijstMetSpelers;      //retourneert de string met alle spelers en hun gewonnen spellen
    }

    /**
     * Voegt een nieuwe speler toe aan de databank
     * @param speler geeft de speler mee
     */
    public void voegToe(Speler speler) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(INSERT_SPELER)) {               //voert het voorafgemaakte statement uit
            query.setString(1, speler.getGebruikersnaam());                                     //zet de  eerste parameter van het mysql statement in op gebruikersnaam
            query.setString(2, speler.getWachtwoord());                                         //zet de  tweede parameter van het mysql statement in op wachtwoord
            query.executeUpdate();                                                              //voert het statement uit
        } catch (SQLException ex) {                                                             //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
    }

    /**
     * Update het aantal gewonnen spellen
     * @param moeilijkheidsgraad geeft de moeilijkheidsgraad mee
     * @param speler    geeft de spe mee zodat we de juist persoon updaten
     */
    public void updateAantalGewonnenSpellen(int moeilijkheidsgraad, String speler) {
        switch (moeilijkheidsgraad) {                                                               //switch case adhv de moeilijkheidsgraad
            case 1:                                                                                 //case 1 = makkelijk
                try (
                        Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                        PreparedStatement query = conn.prepareStatement(UPDATE_SPELER_MAKKELIJK)) { //voert het voorafgemaakte statement uit
                    query.setString(1, speler);                                                     //zet de  eerste parameter van het mysql statement in op gebruikersnaam

                    query.executeUpdate();                                                          //voert het statement uit
                    break;
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);                                                 //indien iets fout loopt in de db catcht hij de exception
                }
            case 2:                                                                                 //case 2 = normaal
                try (
                        Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                        PreparedStatement query = conn.prepareStatement(UPDATE_SPELER_NORMAAL)) {   //voert het voorafgemaakte statement uit
                    query.setString(1, speler);                                                     //zet de  eerste parameter van het mysql statement in op gebruikersnaam
                    query.executeUpdate();                                                          //voert het statement uit
                    break;
                } catch (SQLException ex) {                                                         //indien iets fout loopt in de db catcht hij de exception
                    throw new RuntimeException(ex);
                }
            case 3:                                                                                 //case 3 = moeilijk
                try (
                        Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de datbank
                        PreparedStatement query = conn.prepareStatement(UPDATE_SPELER_MOEILIJK)) {  //voert het voorafgemaakte statement uit
                    query.setString(1, speler);                                                     //zet de  eerste parameter van het mysql statement in op gebruikersnaam
                    query.executeUpdate();                                                          //voert het statement uit
                    break;
                } catch (SQLException ex) {                                                         //indien iets fout loopt in de db catcht hij de exception
                    throw new RuntimeException(ex);
                }
        }
    }

    /**
     * Geeft het klassement met moeilijkheidsgraad makkelijk
     * @return geeft klassement makkelijk terug
     */
    public List<String[]> geefKlassementMakkelijk() {
        List<String[]> test = new ArrayList<>();                                                    //maakt nieuwe string array lijst om de spelers in op te slaan
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GEEF_KLASSEMENT_MAKKELIJK)) {       //voert het voorafgemaakte statement uit
            try (ResultSet rs = query.executeQuery()) {                                             //maakt resultset aan en voert de query uit
                while (rs.next()) {                                                                 //while loop zolang er resultaten zijn blijft hij gaan
                    String naam = rs.getString("gebruikersnaam");                                   //vraagt de gebruikersnaam op uit de db
                    String[] lol = new String[2];                                                   //maakt nieuwe String array aan van 2
                    lol[0] = naam;
                    lol[1] = String.format("%d", rs.getInt("puntenUitdagingenMakkelijk"));          //voegt het aantal punten toe doormiddel van een string.format
                    test.add(lol);                                                                  //voegt de array toe aan de lijst
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);                                                         //indien iets fout loopt in de db catcht hij de exception
        }
        return test;                                                                                //returnt de lijst van string arrays
    }

    /**
     * Geeft het klassement met moeilijkheidsgraad normaal
     * @return geeft klassement normaal terug in de vorm van een array
     */
    public List<String[]> geefKlassementNormaal() {                                                 //logica is het zelfde als geefklassementMakkelijk
        List<String[]> test = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_KLASSEMENT_NORMAAL)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("gebruikersnaam");
                    String[] lol = new String[2];
                    lol[0] = naam;
                    lol[1] = String.format("%d", rs.getInt("puntenUitdagingenNormaal"));
                    test.add(lol);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return test;
    }

    /**
     * Geeft het klassement met moeilijkheidsgraad moeilijk
     * @return geeft klassement moeilijk terug in de vorm van een array
     */
    public List<String[]> geefKlassementMoeilijk() {                                                //logica is het zelfde als geefklassementMakkelijk
        List<String[]> test = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_KLASSEMENT_MOEILIJK)) {
            try (ResultSet rs = query.executeQuery()) {
                while (rs.next()) {
                    String naam = rs.getString("gebruikersnaam");
                    String[] lol = new String[2];
                    lol[0] = naam;
                    lol[1] = String.format("%d", rs.getInt("puntenUitdagingenMoeilijk"));
                    test.add(lol);
                }
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return test;
    }

    /**
     * Update de punten van elke speler in de uitdaging
     * @param uitdaging geeft een string [] mee van van alle spelletjes die voltooid zijn zodat de punten kunnen bijgeteld worden bij de winnaar en de verliezer op plek 0 staat winnaar op plek 1 de verliezer
     */
    public void uptdatePuntenUitdagingenEnpogingen(String[] uitdaging) {
        if (null != uitdaging[2]) {                                             //kijkt of de string array NULL is
            switch (uitdaging[2]) {                                             //uit de string array haalt hij de moeilijkheidsgraad en gebruikt deze als case
                case "1":                                                       //moeilijkheidsgraad = makkelijk
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);//maakt connectie met de databank
                            PreparedStatement query = conn.prepareStatement(UPDATE_PUNTEN_UITDAGING_MAKKELIJK_WINNAAR)) {//update de punten winnaar
                        query.setString(1, uitdaging[0]);                       //zet de parameter in het statement naar uitdaging[0] dit is de winnaar
                        query.executeUpdate();                                  //execute het mysql statement
                    } catch (SQLException ex) {                                 //indien iets fout loopt in de db catcht hij de exception
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);//maakt connectie met de databank
                            PreparedStatement query = conn.prepareStatement(UPDATE_PUNTEN_UITDAGING_MAKKELIJK_VERLIEZER)) {//update de punten verliezer
                        query.setString(1, uitdaging[1]);                       //zet de parameter in het statement naar uitdaging[1] dit is de verliezer
                        query.executeUpdate();                                  //execute het MYQSL statement
                    } catch (SQLException ex) {                                 //indien iets fout loopt in de db catcht hij de exception
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);//maakt connectie met de databank
                            PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_MAKKELIJK)) {//update de gespeelde voltooide uitdagingen
                        query.setString(1, uitdaging[0]);                       //zet de parameter in het MYSQl statement in de op de winnaar
                        query.executeUpdate();                                  //voert het statement uit
                    } catch (SQLException ex) {                                 //indien iets fout loopt in de db catcht hij de exception
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);//maakt connectie met de databank
                            PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_MAKKELIJK)) {//update de gespeelde voltooide uitdagingen
                        query.setString(1, uitdaging[1]);                       //zet de parameter in het MYSQl statement in de op de naam van de verliezer
                        query.executeUpdate();                                  //voert het statement uit
                    } catch (SQLException ex) {                                 //indien iets fout loopt in de db catcht hij de exception
                        throw new RuntimeException(ex);
                    }
                    break;                                                      //einde switch case moeilijkheidsgraad makkelijk
                case "2":                                                       //vanaf hier juit het zelfde al hierboven maar met normaal
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_PUNTEN_UITDAGING_NORMAAL_WINNAAR)) {
                        query.setString(1, uitdaging[0]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_PUNTEN_UITDAGING_NORMAAL_VERLIEZER)) {
                        query.setString(1, uitdaging[1]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_NORMAAL)) {
                        query.setString(1, uitdaging[0]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_NORMAAL)) {
                        query.setString(1, uitdaging[1]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                case "3":                                                       //vanaf hier juit het zelfde al hierboven maar met normaal
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_PUNTEN_UITDAGING_MOEILIJK_WINNAAR)) {
                        query.setString(1, uitdaging[0]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_PUNTEN_UITDAGING_MOEILIJK_VERLIEZER)) {
                        query.setString(1, uitdaging[1]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_MOEILIJK)) {
                        query.setString(1, uitdaging[0]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    try (
                            Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                            PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_GESPEELDE_UITDAGINGEN_MOEILIJK)) {
                        query.setString(1, uitdaging[1]);
                        query.executeUpdate();
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                    break;
                default:
                    break;
            }
        }

    }

}
