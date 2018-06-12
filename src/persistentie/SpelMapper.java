/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistentie;

import domein.CodePin;
import domein.Spel;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 *
 * @author KriNi
 */
public class SpelMapper {


    /**
     * Voegt een spel toe aan de databank van de moeilijkheidsgraad makkelijk of normaal
     */
    private static final String INSERT_SPEL_MAKKELIJKENNORMAAL = "INSERT INTO ID222177_g38.spel  (codepin1,codepin2,codepin3,codepin4,naamspel,moeilijkheidsgraad, naamspeler) values (?,?,?,?,?,?,?)";

    /**
     * Voegt een spel toe aan de databank van de moeilijkheidsgraad moeilijk
     */
    private static final String INSERT_SPEL_MOEILIJK = "INSERT INTO ID222177_g38.spel  (codepin1,codepin2,codepin3,codepin4,codepin5,naamspel,moeilijkheidsgraad, naamspeler) values (?,?,?,?,?,?,?,?)";

    /**
     * Geeft een lijst met beschikbare spellen
     */
    private static final String GIVE_LIST_OF_GAMES = "select naamspel, moeilijkheidsgraad, naamspeler, isUitdaging from ID222177_g38.spel where naamspeler = ?  order by moeilijkheidsgraad";

    /**
     * Geeft een spel aan de hand van de unieke spelnaam
     */
    private static final String GEEF_SPEL = "select* from ID222177_g38.spel where naamspel = ? ";

    /**
     * Verwijdert een spel uit de databank aan de hand van de unieke spelnaam
     */
    private static final String DELETE_SPEL = "delete from ID222177_g38.spel where naamspel = ?";

    /**
     * Slaat een makkelijk spel op
     */
    private static final String SLA_SPEL_OP_MAKKELIJKNORMAAL = "insert into ID222177_g38.Spelbord(rij, naamspel )values (?,(select naamspel from ID222177_g38.spel where naamspel = ?));"/*insert into ID222177_g38.CodePin(codepin1, codepin2,codepin3, codepin4, spelbordid)values(?,?,?,?, (select spelbordid from ID222177_g38.Spelbord where rij = ? and naamspel = ?))"*/;

    /**
     * Slaat het spel op in het spelbord
     */
    private static final String SLA_SPELOP = "insert into ID222177_g38.CodePin(codepin1, codepin2,codepin3, codepin4,codepin5, spelbordid)values(?,?,?,?,?, (select spelbordid from ID222177_g38.Spelbord where rij = ? and naamspel = ?))";

    /**
     * Update de pogingen van het spelbord
     */
    private static final String UPDATE_SPELBORD = "select CodePin.codepin1, CodePin.codepin2,CodePin.codepin3,CodePin.codepin4,CodePin.codepin5, Spelbord.rij from ID222177_g38.CodePin, ID222177_g38.Spelbord where Spelbord.Spelbordid = CodePin.spelbordid and Spelbord.naamspel= ? ";

    /**
     * Evalueert of een spel een uitdaging is
     */
    private static final String IS_DIT_SPEL_UITDAGING = "SELECT * FROM ID222177_g38.spel where naamspel = ?";

    /**
     * Verwijdert alle spellen die een uitdaging zijn en voltooid zijn
     */
    private static final String VERWIJDER_UITDAGINGEN = "delete from ID222177_g38.spel where uitdagingid = ? limit 2 ";

    /**
     * De methode voegt een spel toe aan de databank
     * @param spel geeft een spel mee zodat we al het benodigde er uit kunnen halen
     * @param gebruikersnaam geeft de gebruikersnaam mee zodat het bij de juiste speler geupload word
     */
    public void voegSpelToe(Spel spel, String gebruikersnaam) {
        if (spel.getMoeilijkheidsgraad() == 1 || spel.getMoeilijkheidsgraad() == 2) {                               //kijkt of het makkelijk of normaal is
            try (
                    Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                    PreparedStatement query = conn.prepareStatement(SpelMapper.INSERT_SPEL_MAKKELIJKENNORMAAL)) {   //voert het voorafgemaakte statement uit
                query.setString(1, spel.getTeKrakenCode()[0].getKleur());                                           //zet codepin in de db
                query.setString(2, spel.getTeKrakenCode()[1].getKleur());                                           //zet codepin in de db
                query.setString(3, spel.getTeKrakenCode()[2].getKleur());                                           //zet codepin in de db
                query.setString(4, spel.getTeKrakenCode()[3].getKleur());                                           //zet codepin in de db
                query.setString(5, spel.getNaam());                                                                 //zet naam in de db
                query.setInt(6, spel.getMoeilijkheidsgraad());                                                      //zet moeilijkheidsgraad in de db
                query.setString(7, gebruikersnaam);                                                                 //zet de naam van de speler in de db 
                query.executeUpdate();                                                                              //voert het statement uit
            } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception
                throw new RuntimeException(ex);
            }
        } else {
            try (
                    Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //zelfde als boven maar voor moeilijkheidsgraad moeilijk
                    PreparedStatement query = conn.prepareStatement(SpelMapper.INSERT_SPEL_MOEILIJK)) {
                query.setString(1, spel.getTeKrakenCode()[0].getKleur());
                query.setString(2, spel.getTeKrakenCode()[1].getKleur());
                query.setString(3, spel.getTeKrakenCode()[2].getKleur());
                query.setString(4, spel.getTeKrakenCode()[3].getKleur());
                query.setString(5, spel.getTeKrakenCode()[4].getKleur());
                query.setString(6, spel.getNaam());
                query.setInt(7, spel.getMoeilijkheidsgraad());
                query.setString(8, gebruikersnaam);
                query.executeUpdate();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
    }

    /**
     * Slaat een spel op in de databank
     * @param pogingen is een lijst met codepin[] dit is het spelbord
     * @param spelnaam  de naam van het spel 
     */
    public void slaSpelOp(List<CodePin[]> pogingen, String spelnaam) {
        int counter = 0;                                                                                        //counter voor de rijen bijhoude
        //  CodePin[] pogingopslaan = new CodePin[5];                                                           //codepin array van 1 poging definieren
        for (int teller = 0; teller < pogingen.size(); teller++) {                                              //for loop voor het aantal rijen af te gaan
            CodePin[] pogingopslaan = new CodePin[5];
            pogingopslaan[0] = pogingen.get(teller)[0];                                                               //vraagt aan de lijst de rij die nu moet opgeslaan worden
            pogingopslaan[1] = pogingen.get(teller)[1];                                                               //vraagt aan de lijst de rij die nu moet opgeslaan worden
            pogingopslaan[2] = pogingen.get(teller)[2];                                                               //vraagt aan de lijst de rij die nu moet opgeslaan worden
            pogingopslaan[3] = pogingen.get(teller)[3];                                                               //vraagt aan de lijst de rij die nu moet opgeslaan worden

            if (pogingen.get(teller).length == 5) {
                pogingopslaan[4] = pogingen.get(teller)[4];
            }                                                           //vraagt aan de lijst de rij die nu moet opgeslaan worden
            try (
                    Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                    PreparedStatement query = conn.prepareStatement(SpelMapper.SLA_SPEL_OP_MAKKELIJKNORMAAL)) { //voert het voorafgemaakte statement uit
                query.setInt(1, counter);                                                                       //zet het nummer van de rij in de databank 
                query.setString(2, spelnaam);                                                                   //voegt de spelnaamtoe in de db
                query.executeUpdate();                                                                          //voert het statement uit
            } catch (SQLException ex) {                                                                         //indien iets fout loopt in de db catcht hij de exception
                throw new RuntimeException(ex);
            }
            try (
                    Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                    PreparedStatement query = conn.prepareStatement(SpelMapper.SLA_SPELOP)) {                   //voert het voorafgemaakte statement uit
                query.setString(1, pogingopslaan[0].getKleur());                                                //gaan haalt codepin en slaat deze op
                query.setString(2, pogingopslaan[1].getKleur());                                                //gaan haalt codepin en slaat deze op
                query.setString(3, pogingopslaan[2].getKleur());                                                //gaan haalt codepin en slaat deze op
                query.setString(4, pogingopslaan[3].getKleur());                                                //gaan haalt codepin en slaat deze op

                if (pogingen.get(teller).length == 5) {
                    query.setString(5, pogingopslaan[4].getKleur());
                }else{ 
                    query.setString(5, null);
                }
                //gaan haalt codepin en slaat deze op

                query.setInt(6, counter);                                                                       //slaat de rij op 
                query.setString(7, spelnaam);                                                                   //slaat spelnaam op in db
                query.executeUpdate();                                                                          //voert het statement uit
            } catch (SQLException ex) {                                                                         //indien iets fout loopt in de db catcht hij de exception
                throw new RuntimeException(ex);
            }
            pogingopslaan = null;                                                                               //zet de codepin array op null om zeker te zijn
            counter++;                                                                                          // telt 1 bij voor de rij
        }
    }

    /**
     * Geeft een lijst met spellen die geen uitdaging zijn
     * @param gebruikersnaam de naam zodat je jouw spellen meekrijgt
     * @return geeft een lijst met spellen terug
     */
    public List<Spel> geefSpelLijst(String gebruikersnaam) {
        List<Spel> spel = new ArrayList<>();                                                                    //maakt nieuwe arraylist van spellen aan om de spellen in op te slaan
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GIVE_LIST_OF_GAMES)) {                          //voert het voorafgemaakte statement uit      
            query.setString(1, gebruikersnaam);                                                                 //zet de parameter van het statement in op gebruikersnaam
            try (ResultSet rs = query.executeQuery()) {                                                         //maakt een resultset en execute de query
                while (rs.next()) {                                                                             //start while lus zolang er nog een spel in de opgevraagde lijst zit
                    String naam = rs.getString("naamspel");                                                     //vraagt string naam van het spel uit de db 
                    int moeilijkheidsgraad = rs.getInt("moeilijkheidsgraad");                                   //vraat moeilijkheidsgraad op uit de db
                    int isuitdaging = rs.getInt("isUitdaging");                                                 //vraagt op of het een uitdaging is
                    if (isuitdaging == 1) {                                                                     //indien het geen uitdaging is gaat het naar de else
                    } else {
                        spel.add(new Spel(naam, moeilijkheidsgraad));                                           //er wordt aan de lijst met spellen een nieuw spel toegevoegd met de parameters
                    }
                }
            }
        } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception 
            throw new RuntimeException(ex);
        }
        return spel;                                                                                            //return de lijst met spellen
    }

    /**
     * Vraagt de moeilijheidsgraad op aan de hand van de spelnaam
     * @param spelnaam geeft de naam van het spel mee
     * @return  returnt de moeilijkheidsgraad van dit spel
     */
    public int geefMoeilijkheidsGraad(String spelnaam) {
        int retour = 0;                                                                                         //initialiseerd een retour waarde int
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(GEEF_SPEL)) {                                   //voert het voorafgemaakte statement uit
            query.setString(1, spelnaam);                                                                       //zet de parameter van het statement als de spelnaam
            try (ResultSet rs = query.executeQuery()) {                                                         //maakt resultset aan en voert het statement uit
                if (rs.next()) {                                                                                //als er een resultaat is gaat hij door
                    int moeilijkheidsgraad = rs.getInt("moeilijkheidsgraad");                                   //vraagt de moeilijkheidsgraad op uit de db
                    retour = moeilijkheidsgraad;                                                                //zet de retour in op moeilijkheidsgraad
                }
            }
        } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return retour;                                                                                          // returnt de moeilijkheidsgraad
    }

    /**
     * Vraagt een spel op uit de databank
     * @param spelnaam de naam van het spel
     * @return  een spel van de speler
     */
    public Spel geefSpel(String spelnaam) {
        Spel spel = null;                                                                                       //maakt een spel aan met waarde NULL
        if (geefMoeilijkheidsGraad(spelnaam) == 1 || geefMoeilijkheidsGraad(spelnaam) == 2) {                   // als de moeilijkheidsgraad makkelijk of normaal is gaat hij in de clause
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                    PreparedStatement query = conn.prepareStatement(GEEF_SPEL)) {                               //indien iets fout loopt in de db catcht hij de exception
                query.setString(1, spelnaam);                                                                   //zet de parameter in het MYSQL statement in met de spelnaam 
                try (ResultSet rs = query.executeQuery()) {                                                     //maakt resultset aan en en voert het statement uit
                    if (rs.next()) {                                                                            //kijkt of er uit de datbank iets komt
                        String naam = rs.getString("naamspel");                                                 //vraagt naam van het spel op uit db
                        String codepin1 = rs.getString("codepin1");                                             //vraagt codepin op uit de db
                        String codepin2 = rs.getString("codepin2");                                             //vraagt codepin op uit de db
                        String codepin3 = rs.getString("codepin3");                                             //vraagt codepin op uit de db
                        String codepin4 = rs.getString("codepin4");                                             //vraagt codepin op uit de db
                        int moeilijkheidsgraad = rs.getInt("moeilijkheidsgraad");
                        String naamspeler = rs.getString("naamspeler");//vraagt moeilijkheidsgraad op uit db
                        CodePin[] teKrakencode = new CodePin[4];                                                //maakt array aan en voegt de tekraken codes toe
                        teKrakencode[0] = new CodePin(codepin1);
                        teKrakencode[1] = new CodePin(codepin2);
                        teKrakencode[2] = new CodePin(codepin3);
                        teKrakencode[3] = new CodePin(codepin4);
                        spel = new Spel(naam, moeilijkheidsgraad, teKrakencode, naamspeler);                                //maakt een spel aan met de parameters gehaalt uit de db
                    }
                }
            } catch (SQLException ex) {                                                                         //indien iets fout loopt in de db catcht hij de exception
                throw new RuntimeException(ex);
            }
        } else {                                                                                                //zelfde als hierboven maar voor moeilijkheidsgraad moeilijk
            try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL);
                    PreparedStatement query = conn.prepareStatement(GEEF_SPEL)) {
                query.setString(1, spelnaam);
                try (ResultSet rs = query.executeQuery()) {
                    if (rs.next()) {
                        String naam = rs.getString("naamspel");
                        String codepin1 = rs.getString("codepin1");
                        String codepin2 = rs.getString("codepin2");
                        String codepin3 = rs.getString("codepin3");
                        String codepin4 = rs.getString("codepin4");
                        String codepin5 = rs.getString("codepin5");
                        int moeilijkheidsgraad = rs.getInt("moeilijkheidsgraad");
                        CodePin[] teKrakencode = new CodePin[5];
                        teKrakencode[0] = new CodePin(codepin1);
                        teKrakencode[1] = new CodePin(codepin2);
                        teKrakencode[2] = new CodePin(codepin3);
                        teKrakencode[3] = new CodePin(codepin4);
                        teKrakencode[4] = new CodePin(codepin5);
                        spel = new Spel(naam, moeilijkheidsgraad, teKrakencode);
                    }
                }
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        }
        return spel;                                                                                            //returnt het gevonde spel
    }

    /**
     * Vraagt de pogingen op aan de databank
     * @param spelnaam naam van het spel
     * @return  het spelbord in lis codepinarray vorm
     */
    public List<CodePin[]> updateSpelbord(String spelnaam) {
        ArrayList<CodePin[]> spelbordpogingen = new ArrayList<>();                                              //maakt nieuwe array list aan voor de pogingen op te slaan
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(UPDATE_SPELBORD)) {                             //indien iets fout loopt in de db catcht hij de exception
            query.setString(1, spelnaam);                                                                       //zet de parameter van het statement in op spelnaam
            try (ResultSet rs = query.executeQuery()) {                                                         //maakt resultset aan en voert het mysql statement uit
                while (rs.next()) {   
                    //zolang er pogingen zijn blijft de while doorgaa
                    String codepin1 = rs.getString("codepin1");                                                 //vraagt codepin aan db
                    String codepin2 = rs.getString("codepin2");                                                 //vraagt codepin aan db
                    String codepin3 = rs.getString("codepin3");                                                 //vraagt codepin aan db
                    String codepin4 = rs.getString("codepin4");      
                    String codepin5 = rs.getString("codepin5");      
                    //vraagt codepin aan db
                    CodePin[] poging = new CodePin[5];                                                          //maakt codepin array aan 
                    poging[0] = new CodePin(codepin1);                                                          //steekt codepin in array
                    poging[1] = new CodePin(codepin2);                                                          //steekt codepin in array
                    poging[2] = new CodePin(codepin3);                                                          //steekt codepin in array
                    poging[3] = new CodePin(codepin4);                                                          //steekt codepin in array
                    poging[4] = new CodePin(codepin5);                                                          //steekt codepin in array
                    spelbordpogingen.add(poging);   
                       poging = null;             //stopt de codepin array in de lijst
                }
            }
        } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
        return spelbordpogingen;                                                                                //returnt de lijst met pogingen
    }

    /**
     * Verwijdert een spel uit de databank
     * @param spelnaam naam van het spel
     */
    public void verwijderSpel(String spelnaam) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank
                PreparedStatement query = conn.prepareStatement(DELETE_SPEL)) {                                 //voert het voorafgemaakte statement uit
            query.setString(1, spelnaam);                                                                       //zet de parameter van het statement als de naam van het spel
            query.executeUpdate();                                                                              //voert het statement uit 
        } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception                                 
            throw new RuntimeException(ex);
        }
    }

    /**
     * Evalueert of een spel een uitdaging is
     * @param spelnaam naam van het spel
     * @return of het spel een uitdaging is
     */
    public boolean isUitdaging(String spelnaam) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank   
                PreparedStatement query = conn.prepareStatement(IS_DIT_SPEL_UITDAGING)) {                       //voert het voorafgemaakte statement uit
            query.setString(1, spelnaam);                                                                       //zet de parameter van de string als de naam van spel
            try (ResultSet rs = query.executeQuery()) {                                                         //maakt een resultset aan en voert het statement uit
                if (rs.next()) {                                                                                //zolang er nog een resultaat is
                    int isuitdaging = rs.getInt("isUitdaging");                                                 //vraagt op het een uitdaging
                    if (isuitdaging == 1) {                                                                     //als het 1 is is het een uitdaging zo bepaald bij maken van een spel
                        return true;
                    }
                }
            }
        } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception   
            throw new RuntimeException(ex);
        }
        return false;
    }

    /**
     * Verwijdert de spellen die een uitdaging zijn aan de hand van de ID
     * @param uitdagingID het id van de uitdaging
     */
    public void verwijderDeUitdagingen(int uitdagingID) {
        try (Connection conn = DriverManager.getConnection(Connectie.JDBC_URL); //maakt connectie met de databank   
                PreparedStatement query = conn.prepareStatement(VERWIJDER_UITDAGINGEN)) {                       //voert het voorafgemaakte statement uit
            query.setInt(1, uitdagingID);                                                                       //zet de parameter van het statement op uitdaging id
            query.executeUpdate();                                                                              //voert het statement uit
        } catch (SQLException ex) {                                                                             //indien iets fout loopt in de db catcht hij de exception
            throw new RuntimeException(ex);
        }
    }

}
