/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.CodePin;
import domein.Uitdaging;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static java.util.Collections.list;
import java.util.List;

/**
 *
 * @author guust
 */
public class UitdagingMapper {

    /**
     * Maakt een uitdaging aan
     */
    private static final String MAAK_UITDAGING_AAN = "insert into ID222177_g38.Uitdaging(CodePin1, Codepin2, CodePin3, CodePin4, CodePin5, moeilijkheidsgraad,uitdager,uitgedaagde, naamuitdaging)values(?,?,?,?,?, ?, ?,?,?)";

    /**
     * Maakt een uitdaging aan voor de uitdager
     */
    private static final String MAAK_SPEL_AAN_UITDAGER = "insert into ID222177_g38.spel (CodePin1,CodePin2, CodePin3, CodePin4, CodePin5 , moeilijkheidsgraad, naamspel,naamspeler, isuitdaging , uitdagingid)"
            + "values ("
            + "(select CodePin1 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin2 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin3 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin4 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin5 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select moeilijkheidsgraad from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "concat(?,' ',?),"
            + "(select uitdager from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + " 1, "
            + " (select uitdagingid from ID222177_g38.Uitdaging where naamuitdaging =?))";

    /**
     * Voegt een uitdaging toe voor de uitgedaagde
     */
    private static final String MAAK_SPEL_AAN_UITGEDAAGDE = "insert into ID222177_g38.spel (CodePin1,CodePin2, CodePin3, CodePin4, CodePin5 , moeilijkheidsgraad, naamspel,naamspeler,isuitdaging, uitdagingid)"
            + "values ("
            + "(select CodePin1 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin2 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin3 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin4 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select CodePin5 from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "(select moeilijkheidsgraad from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + "concat(?,' ',?),"
            + "(select uitgedaagde from ID222177_g38.Uitdaging where naamuitdaging =?),"
            + " 1, "
            + " (select uitdagingid from ID222177_g38.Uitdaging where naamuitdaging =?))";

    /**
     * Geeft een lijst meet uitdagingen
     */
    private static final String GEEF_LIJST_MET_UITDAGINGEN = "SELECT uitdager, naamuitdaging, moeilijkheidsgraad, gewonnenSpellenMakkelijk, gewonnenSpellenNormaal, gewonnenSpellenMoeilijk  "
            + " FROM ID222177_g38.Uitdaging ,ID222177_g38.Speler"
            + " where Uitdaging.uitgedaagde = ? and Uitdaging.uitdager = Speler.gebruikersnaam"
            + " order by uitdager ";

    /**
     * Update de pogingen van de uitdager
     */
    private static final String UPDATE_POGINGEN_UITDAGER = "update ID222177_g38.Uitdaging"
            + " set Uitdaging.pogingenUitdager = ?"
            + " where Uitdaging.naamuitdaging = ? and Uitdaging.uitdager = ?"
            + " limit 1;";

    /**
     * Update de pogingen van de uitgedaagde
     */
    private static final String UPDATE_POGINGEN_UITGEDAAGDE = "update ID222177_g38.Uitdaging"
            + " set Uitdaging.pogingenUitgedaagde = ?"
            + " where Uitdaging.naamuitdaging = ? and Uitdaging.uitgedaagde = ?"
            + " limit 1;";

    /**
     * Vraagt een uitdaging op aan de databank
     */
    private static final String GEEF_SPEL = "select * from ID222177_g38.Uitdaging where naamuitdaging = ?";

    /**
     * Geeft een lijst met alle uitdagingen die door beide spelers zijn afgewerkt
     */
    private static final String GEEF_AFGEWERKTE_UITDAGINGEN = "SELECT * FROM ID222177_g38.Uitdaging "
            + "where pogingenUitdager != 0 and pogingenUitgedaagde != 0";

    /**
     * Vraagt een spel op uit de databank aan de hand van de uitdagingID
     */
    private static final String GEEF_SPEL_MET_UITDAGINGID = "select * FROM ID222177_g38.Uitdaging where uitdagingID = ? ";

    /*---------MYSQL Statement: geen idee wat dees doet wordt ook niet gebruikt----*/
    private static final String GEEF_LIJST_MET_OPENSTAANDE_UITDAGINGEN = "SELECT * FROM ID222177_g38.Uitdaging "
            + "where uitdagingID = ?";

    /**
     * Verwijdert een uitdaging
     */
    private static final String VERWIJDER_VOLTOOIDE_SPELLEN = "delete from ID222177_g38.Uitdaging where uitdagingID = ? ";

    /**
     * Geeft alle uitdagingen van een uitdager weer
     */
    private static final String GEEF_ALLE_UITDAGINGEN_VAN_EEN_UITDAGER_WEER = "SELECT * FROM ID222177_g38.Uitdaging where uitdager = ?  and pogingenUitdager is  null";

    /**
     * Vraagt de uitdager op aan de databank
     * @param gebruikersnaam naam van de gebruiker
     * @return geeft een lijst met de namen van de uitdagingen die de speler heeft
     */
    public List<String> geefUitdagingUitdager(String gebruikersnaam) {
        
        List<String> uitdagingen = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GEEF_ALLE_UITDAGINGEN_VAN_EEN_UITDAGER_WEER)) {                      //voert het voorafgemaakte statement uit
            query.setString(1, gebruikersnaam);                                                                     //zet de string in het statement op de gebruikersnaam
            try (ResultSet rs = query.executeQuery()) {                                                             //maakt nieuwe resultset aan en exucutes het statement
                while (rs.next()) {
                    uitdagingen.add(rs.getString("naamuitdaging"));                                                                //voegt bovenstaande waarden toe aan de retourstring
                    
                }
            }
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return uitdagingen;

    }


    /**
     * Vraagt een uitdaging op aan de databank
     * @param naamuitdaging naam van de uitdaging
     * @return geeft een uitdaging terug
     */
    public Uitdaging geefUitdaging(String naamuitdaging) {
        Uitdaging uitdaging = null;                                                                                 //maakt een uitdaging aan en zet deze op NULL
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GEEF_SPEL)) {                                       //voert het voorafgemaakte statement uit
            query.setString(1, naamuitdaging);                                                                      //zet de parameter in het statement op de naam van de uitdaging
            try (ResultSet rs = query.executeQuery()) {                                                             //maakt resultset aan en voert het statement uit
                if (rs.next()) {                                                                                    //zolang er nog een uitdaging is
                    String naam = rs.getString("naamuitdaging");                                                    //vraagt de naam van de uitdaging aan de db
                    String codepin1 = rs.getString("codepin1");                                                     //vraagt de codepin aan de DB
                    String codepin2 = rs.getString("codepin2");                                                     //vraagt de codepin aan de DB
                    String codepin3 = rs.getString("codepin3");                                                     //vraagt de codepin aan de DB
                    String codepin4 = rs.getString("codepin4");                                                     //vraagt de codepin aan de DB
                    int moeilijkheidsgraad = rs.getInt("moeilijkheidsgraad");                                       //vraagt de moeilijkheidsgraad op aan de db
                    String uitdager = rs.getString("uitdager");                                                     //vraagt de naam van de uitdager op aan de db
                    String uitgedaagde = rs.getString("uitgedaagde");                                               //vraagt de naam van de uitgedaagde op aan db
                    CodePin[] teKrakencode = new CodePin[4];                                                        //maakt nieuwe codepin array aan
                    teKrakencode[0] = new CodePin(codepin1);
                    teKrakencode[1] = new CodePin(codepin2);
                    teKrakencode[2] = new CodePin(codepin3);
                    teKrakencode[3] = new CodePin(codepin4);
                    uitdaging = new Uitdaging(moeilijkheidsgraad, uitdager, uitgedaagde, naam, teKrakencode);       //maakt een uitdaging aan met de opgehaalde parrameters
                }
            }
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return uitdaging;                                                                                           //returnt de uitdaging
    }

    /**
     * Update de pogingen van de uitdager
     * @param aantalpogingen hoeveel pogingen de speler nodig had om te code te kraken
     * @param uitdaging  geeft de uitdaging om te zien welke uitdaging voltooid was
     */
    public void updatePogingenIndienGewonnenUitdager(int aantalpogingen, Uitdaging uitdaging) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_UITDAGER)) {                        //voert het voorafgemaakte statement uit
            query.setInt(1, aantalpogingen);                                                                        //zet de parameter van het statement op het aantal pogingen
            query.setString(2, uitdaging.getNaamUitdaging());                                                       //zet de parameter van het statement op de naam van de uitdaging
            query.setString(3, uitdaging.getNaamUitdager());                                                        //zet de parameter van het statement op de naam van de uitdager            
            query.executeUpdate();                                                                                  //voert het mysql statement uit
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
    }

    /**
     * Update de pogingen van de uitgedaagde
     * @param aantalpogingen aantal pogingen die de uitgedaagde nodig had om de code te kraken 
     * @param uitdaging  naam van de uitdaging
     */
    public void updatePogingenIndienGewonnenUitgedaagde(int aantalpogingen, Uitdaging uitdaging) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank              
                PreparedStatement query = conn.prepareStatement(UPDATE_POGINGEN_UITGEDAAGDE)) {                     //voert het voorafgemaakte statement uit
            query.setInt(1, aantalpogingen);                                                                        //zet de parameter in het statement op aantal pogingen
            query.setString(2, uitdaging.getNaamUitdaging());                                                       //zet de paramet in het statement op naam van de uitdaging
            query.setString(3, uitdaging.getNaamUitgedaagde());                                                     //zet de parameter in het statement op de naam van de uitgedaagde
            query.executeUpdate();                                                                                  //voert het statement uit
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception  
            throw new RuntimeException(ex);
        }
    }

    /**
     * Voegt een uitdaging toe aan de databank
     * @param uitdaging  de uitdaging
     */
    public void maakUitdagingAan(Uitdaging uitdaging) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(MAAK_UITDAGING_AAN)) {                              //voert het voorafgemaakte statement uit
            query.setString(1, uitdaging.getTekrakenCode()[0].getKleur());                                          //vraagt codepin op aan db
            query.setString(2, uitdaging.getTekrakenCode()[1].getKleur());                                          //vraagt codepin op aan db
            query.setString(3, uitdaging.getTekrakenCode()[2].getKleur());                                          //vraagt codepin op aan db
            query.setString(4, uitdaging.getTekrakenCode()[3].getKleur());                                          //vraagt codepin op aan db
     

if(uitdaging.getMoeilijkheidsgraad() ==3){
  query.setString(5, uitdaging.getTekrakenCode()[4].getKleur());                                          //vraagt codepin op aan db
}
  query.setString(5, "leeg");                                          //vraagt codepin op aan db
  query.setInt(6, uitdaging.getMoeilijkheidsgraad());                                                     //vraagt moeilijkheidsgraad op aan db
            query.setString(7, uitdaging.getNaamUitdager());                                                        //vraagt naam uitdager op aan de dn
            query.setString(8, uitdaging.getNaamUitgedaagde());                                                     //vraagt naam uitgedaagde op aan de db
            query.setString(9, uitdaging.getNaamUitdaging());                                                       //vraagt naam uitdaging op aan de db
            query.executeUpdate();                                                                                  //update het statement
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(MAAK_SPEL_AAN_UITDAGER)) {                          //voert het voorafgemaakte statement uit
            query.setString(1, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(2, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(3, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(4, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(5, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(6, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(7, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(8, uitdaging.getNaamUitdager());                                                        //zet parameter in het statement op naam van de uitdager
            query.setString(9, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(10, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.executeUpdate();                                                                                  //update het statement
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(MAAK_SPEL_AAN_UITGEDAAGDE)) {                       //voert het voorafgemaakte statement uit
            query.setString(1, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(2, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(3, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(4, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(5, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(6, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(7, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(8, uitdaging.getNaamUitgedaagde());                                                     //zet parameter in het statement op naam van de uitgedaage
            query.setString(9, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.setString(10, uitdaging.getNaamUitdaging());                                                       //zet parameter in het statement op naam van de uitdaging
            query.executeUpdate();                                                                                  //update het statement
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
    }

    /**
     * Geeft de beschikbare uitdagingen weer
     * @param gebruikersnaam gebruikersnaam van de speler die aangemeld is 
     * @return geeft een string array terug met op plek 0 naam van de uitdaging op 1 de naam van de uitdager, op 3 de moeilijkheidsgraad en op 4 het aantal gewonnen spelletjes
     */
    public List<String[]> geefUitdagingen(String gebruikersnaam) {
        List<String[]> beschikbareUitdagingen = new ArrayList<>();
        // String[] uitdaging = new String[4];                                                                                                              //geeft een titel aan de lijst
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GEEF_LIJST_MET_UITDAGINGEN)) {                      //voert het voorafgemaakte statement uit
            query.setString(1, gebruikersnaam);                                                                     //zet de string in het statement op de gebruikersnaam
            try (ResultSet rs = query.executeQuery()) {                                                             //maakt nieuwe resultset aan en exucutes het statement
                while (rs.next()) {
                    String[] uitdaging = new String[4];
                    uitdaging[0] = rs.getString("naamuitdaging");
                    uitdaging[1] = rs.getString("uitdager");                                                                         //voegt bovenstaande waarden toe aan de retourstring
                    switch (rs.getInt("moeilijkheidsgraad")) {                                                                         //switch case op moeilijkheidsgraad
                        case 1:
                            uitdaging[2] = String.format("%d", rs.getInt("moeilijkheidsgraad"));
                            uitdaging[3] = String.format("%d", rs.getInt("gewonnenSpellenMakkelijk"));
                            beschikbareUitdagingen.add(uitdaging);// voegt de overige waarden toe en een enter
                            break;
                        case 2:
                            uitdaging[2] = String.format("%d", rs.getInt("moeilijkheidsgraad"));
                            uitdaging[3] = String.format("%d", rs.getInt("gewonnenSpellenNormaal"));
                            beschikbareUitdagingen.add(uitdaging);// voegt de overige waarden toe en een enter
                            break;
                        case 3:
                            uitdaging[2] = String.format("%d", rs.getInt("moeilijkheidsgraad"));
                            uitdaging[3] = String.format("%d", rs.getInt("gewonnenSpellenMoeilijk"));
                            beschikbareUitdagingen.add(uitdaging);// voegt de overige waarden toe en een enter
                            break;
                        default:
                            break;
                    }

                }
            }
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return beschikbareUitdagingen;                                                                                              //returnt de lijst
    }

    /**
     * Geeft een lijst van alle voltooide uitdagingen met uitdaginID's weer
     * @return geeft een lijst met utidagings id's van de voltooide uitdagingen
     */
    public List<Integer> geefLijstvoltooideUitdagingen() {
        List<Integer> uitdagingidlijst = new ArrayList<>();                                                         //maakt een nieuwe lijst voor de uitdaging ID bij te houden
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                PreparedStatement query = conn.prepareStatement(GEEF_AFGEWERKTE_UITDAGINGEN)) {                     //voert het voorafgemaakte statement uit
            try (ResultSet rs = query.executeQuery()) {                                                             //maakt een resultset en voert het statemen uit
                while (rs.next()) {
                    uitdagingidlijst.add(rs.getInt("uitdagingID"));                                                 //vraagt uitdagingID op aan db en stopt het in lijst
                }
            }
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return uitdagingidlijst;                                                                                    //returnt de uitdagingenlijst
    }

    /**
     * Geeft een uitdaging aan de hand van de uitdagingID
     * @param uitdagingID uitdagingID dit is uniek
     * @return  geeft een uitdaging terug
     */
    public Uitdaging geefUitdagingMetSpelID(int uitdagingID) {
        Uitdaging uitdaging = null;                                                                                 //maakt een uitdaging aan en zet deze op NULL
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GEEF_SPEL_MET_UITDAGINGID)) {                       //voert het voorafgemaakte statement uit
            query.setInt(1, uitdagingID);                                                                           //zet de parameter in het statement op uitdagingID
            try (ResultSet rs = query.executeQuery()) {                                                             //maakt resultset en voert statement uit
                if (rs.next()) {
                    uitdaging = new Uitdaging(rs.getInt("moeilijkheidsgraad"), rs.getInt("pogingenUitdager"), rs.getInt("pogingenUitgedaagde"), rs.getString("Uitdager"), rs.getString("Uitgedaagde"));
                }                                                                                                   // ^- maakt uitdaging aan en vraagt de waardes op aan db
            }
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return uitdaging;                                                                                           //returnt de uitdaging
    }

    /**
     * Verwijdert een uitdaging aan de hand van de uitdagingID
     * @param uitdagingID de unieke code van de uitdaging
     */
    public void verwijderVoltooideUitdaging(int uitdagingID) {
        try (
                Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(VERWIJDER_VOLTOOIDE_SPELLEN)) {                     //indien iets fout loopt in de db catcht hij de exception
            query.setInt(1, uitdagingID);                                                                           //zet de parameter in het statement op uitdagingid
            query.executeUpdate();                                                                                  //voert het statement uit
        } catch (SQLException ex) {                                                                                 //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
    }
}
