/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/*------IMPORTS--------*/
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * @author User
 */
public class Spel {

    /*--- ATTRIBUTEN ---*/
    private final int moeilijkheidsgraad;                                       //moeilijkheidsgraad van het spel
    private String naam = "";                                                   //string -> naam van het spel
    private final CodePin[] teKrakenCode;                                       //code pin array de code die moet gekraat worden
    private final Spelbord spelbord;                                            //spelbord -> klasse waarin de rest zich bevindt
    private EvaluatiePin[] laatsteEvaluatie = null;                             //evaluatiepin array deze evalueert de gedane pogingen in het spel
    private List<CodePin[]> pogingen = new ArrayList<CodePin[]>();
    private String naamSpeler;                                                                            //slaat alle pogingen op die gedaan zijn in een unlinked list

    /**
     * De constructor met 2 parameter die een spel aanmaakt aan de hand van de
     * naam en de moeilijkheidsgraad
     *
     * @param naam naam van het spel 
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     * @see Spel#teKrakenCode
     * @see Spel#willekeurigeCode()
     */
    public Spel(String naam, int moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;                           //stelt de meegegeven parameter in van moeilijkheidsgraad
        this.naam = naam;                                                       //stelt de meegegeven parameter in op naam
        teKrakenCode = willekeurigeCode();                                      //genereerd een willerkeurige code met codepinnen
        spelbord = new Spelbord(moeilijkheidsgraad);                            //maakt een nieuw spelbord
    }

    /**
     * De constructor met 1 paramater moeilijkheidsgraad
     *
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     */
    public Spel(int moeilijkheidsgraad) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        teKrakenCode = willekeurigeCode();
        spelbord = new Spelbord(moeilijkheidsgraad);
    }

    /**
     * De constructor met 4 parameters, naam, moeilijkheidsgraad, te kraken code
     * en de naam van de speler
     *
     * @param naam naam van het spel
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     * @param teKrakenCode de codepin array met de code die gekraakt moet worden
     * @param naamSpeler naam van de speler die het spelt speelt
     */
    public Spel(String naam, int moeilijkheidsgraad, CodePin[] teKrakenCode, String naamSpeler) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.naam = naam;
        this.teKrakenCode = teKrakenCode;
        this.naamSpeler = naamSpeler;
        spelbord = new Spelbord(moeilijkheidsgraad);
    }

    /**
     * De constructor met parameters naam van het spel, moeilijkheidsgraad en
     * een array van CodePinnen. Deze wordt gebruikt om een opgeslagen spel uit
     * de databank te laten genereren.
     *
     * @param naam naam van het spel 
     * @param moeilijkheidsgraad moeilijkheidsgraad van het spel
     * @param teKrakencode de code die gekraakt dient te worden
     */
    public Spel(String naam, int moeilijkheidsgraad, CodePin[] teKrakencode) {
        this.moeilijkheidsgraad = moeilijkheidsgraad;
        this.naam = naam;
        this.teKrakenCode = teKrakencode;
        spelbord = new Spelbord(moeilijkheidsgraad);
    }

    /**
     * De setter die de naam van het spel instelt
     *
     * @param naam naam van het spel
     */
    public void setNaam(String naam) {
        this.naam = naam;
    }

    /**
     * De setter die alle gedane pogingen instelt die we halen uit de databank
     *
     * @param pogingen alle pogingen die een speler gedaan heeft
     */
    public void setPogingen(List<CodePin[]> pogingen) {
        this.pogingen = pogingen;
    }

    /**
     * De methode geeft de naam van de speler terug
     *
     * @return naam van de speler
     */
    public String getNaamSpeler() {
        return naamSpeler;
    }

    /**
     * De methode stelt de naam van de speler in.
     *
     * @param naamSpeler naam van de speler
     */
    public void setNaamSpeler(String naamSpeler) {
        this.naamSpeler = naamSpeler;
    }

    /**
     * De methode geeft de moeilijkheidsgraad
     *
     * @return moeilijkheidsgraad van het spel
     */
    public int getMoeilijkheidsgraad() {
        return moeilijkheidsgraad;
    }

    /**
     * De methode geeft de naam terug
     *
     * @return naam
     */
    public String getNaam() {
        return naam;
    }

    /**
     * De methode geeft de te kraken code terug
     *
     * @return de te kraken code bestaande uit CodePinnen
     */
    public CodePin[] getTeKrakenCode() {
        return teKrakenCode;
    }

    /**
     * De methode geeft het spelbord terug
     *
     * @return spelbord
     */
    public Spelbord getSpelbord() {
        return spelbord;
    }

    /**
     * De methode geeft de array van evaluatiepinnen terug
     *
     * @return de evaluatiepinnen
     */
    public EvaluatiePin[] getLaatsteEvaluatie() {
        return laatsteEvaluatie;
    }

    /**
     * Deze methode geeft alle pogingen terug
     *
     * @return een List van CodePinnen die alle pogingen voorstellen
     */
    public List<CodePin[]> getPogingen() {
        return pogingen;
    }

    /*--- METHODEN UC 2 ---*/
    /**
     * De methode genereerd een willekeurig code bestaande uit een array van
     * CodePinnen
     *
     * @return een willekeurige code bestaande uit een array van Codepinnen
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
     * De methode zet de gegenereerde getallen om naar kleuren
     *
     * @param nummer het nummer dat je wilt omzetten
     * @return de kleur van het getal
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

    /*--- METHODEN UC 3 ----*/
    /**
     * De methode zet de te kraken code om naar een String.
     *
     * @return een String die de te kraken code voorstelt
     * @see Spel#getTeKrakenCode()
     */
    public String geefTekrakenCodeString() {
        String retour = "";
        for (int teller = 0; teller < getTeKrakenCode().length; teller++) {
            retour += String.format("%s ", getTeKrakenCode()[teller].getKleur());
        }
        return retour;
    }

    /**
     * De methode voegt een poging toe aan de lijst van CodePin arrays.
     *
     * @param poging poging die de speler gedaan had
     */
    public void slaPogingop(CodePin[] poging) {
        pogingen.add(poging);
    }

    /**
     * De methode doe poging laat de gebruiker een poging doen en valideert deze
     * aan de had van de codpinnen. De methode geeft ook een foutmelding als er
     * tijdens het runnen van het programma er zich problemen voordoen in
     * verband met de moeilijkheidsgraad.
     *
     * @param poging poging die de speler gedaan heeft
     * @throws IllegalArgumentException wanneer er tijdens het runnen van het
     * programma een fout optreed bij de moeilijkheidsgraad
     */
    public void doePoging(CodePin[] poging) {
        boolean isGeldig = false;

        spelbord.doePoging(poging, bepaalEvaluatiePin(poging));
        laatsteEvaluatie = bepaalEvaluatiePin(poging);

    }

    /**
     * De methode geeft het aantal pogingen die gespeeld werd terug
     *
     * @return het aantal pogingen
     * @see Spelbord#getNmrpoging()
     */
    public int geefAantalPogingen() {
        return spelbord.getNmrpoging();
    }

    /**
     * De methode creert de evaluatiepinnen en stelt hun kleur in aan de hand
     * van de poging die gedaan werd. De methode geeft een gepaste foutmelding
     * wanneer er ergens tijdens het runnen van het programma zich een fout
     * voordoet wegens de moeilijkheidsgraad.
     *
     * @param poging poging om te vergelijken met de tekraken code
     * @return een evaluatiepinarray die hints geeft wat de tekraken code is
     * @throws IllegalArgumentException wanneer men het programma runt en zich
     * een fout voordoet doordat de moeilijkheidsgraad incorrect is
     * @see Spel#teKrakenCode
     * @see CodePin#getKleur()
     */
    public EvaluatiePin[] bepaalEvaluatiePin(CodePin[] poging) {
        EvaluatiePin[] eval;
        switch (moeilijkheidsgraad) {
            case 1:
                eval = new EvaluatiePin[4];
                for (int tellerleeg = 0; tellerleeg < 4; tellerleeg++) {
                    if (!poging[tellerleeg].getKleur().equals(teKrakenCode[tellerleeg].getKleur())) {
                        eval[tellerleeg] = new EvaluatiePin("leeg");
                    }
                }
                for (int tellerwit = 0; tellerwit < 4; tellerwit++) {
                    if (poging[tellerwit].getKleur().equals(teKrakenCode[0].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[1].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[2].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[3].getKleur())) {
                        eval[tellerwit] = new EvaluatiePin("wit");
                    }
                }
                for (int teller = 0; teller < 4; teller++) {
                    if (poging[teller].getKleur().equals(teKrakenCode[teller].getKleur())) {
                        eval[teller] = new EvaluatiePin("zwart");
                    }
                }
                break;
            case 2:
                eval = new EvaluatiePin[4];

                int blackCounter = 0;
                int witCounter = 0;
                int leegCounter = 0;
                int counterkleur3 = 0;

                int test = 0;
                for (int i = 0; i < 4; i++) {

                    if (poging[i].getKleur().equals(teKrakenCode[i].getKleur())) {

                        blackCounter++;
                    } else {

                        if (poging[i].getKleur().equals(teKrakenCode[0].getKleur())) {

                            witCounter++;
                        } else if (poging[i].getKleur().equals(teKrakenCode[1].getKleur())) {

                            witCounter++;
                        } else if (poging[i].getKleur().equals(teKrakenCode[2].getKleur())) {

                            witCounter++;
                        } else if (poging[i].getKleur().equals(teKrakenCode[3].getKleur())) {

                            witCounter++;
                        } else {

                            leegCounter++;
                        }

                    }

                }

                int totaalwit = 0;
                int totaalzwart = 0;
                int totaalleeg = 0;
                totaalwit = witCounter - blackCounter;
                totaalzwart = blackCounter;
                
                

                
                System.out.println("");
                System.out.println("totaal wit: " +totaalwit);
                System.out.println("totaal zwart: "+totaalzwart);
                
               
           
                for (int i = 0; i < eval.length; i++) {
                    eval[i]= new EvaluatiePin("leeg");
                }
                
                for (int i = 0; i <totaalzwart; i++) {
                    eval[i]= new EvaluatiePin("zwart");
                
                    
                }
                
                for (int i = 0; i < totaalwit ; i++) {
                    
                    eval[i+totaalzwart] = new EvaluatiePin("wit");
                }
                
           
                
         
                

                
                
                
                
                
                /*
                List<String> list = new ArrayList();
                for (int j = 0; j < 4; j++) {

                    list.add(eval[j].getKleur());
                }
                Collections.sort(list);

                eval[0] = new EvaluatiePin(list.get(3));
                eval[1] = new EvaluatiePin(list.get(2));
                eval[2] = new EvaluatiePin(list.get(1));
                eval[3] = new EvaluatiePin(list.get(0));
*/
                /*
                int counterGemiddeld = 0;
                int aantalleeg = 0;
                for (int tellerleeg = 0; tellerleeg < 4; tellerleeg++) {
                    eval[tellerleeg] = new EvaluatiePin("leeg");
                }
                for (int tellerzwart = 0; tellerzwart < 4; tellerzwart++) {
                    if (poging[tellerzwart].getKleur().equals(teKrakenCode[tellerzwart].getKleur())) {
                        eval[counterGemiddeld] = new EvaluatiePin("zwart");
                        
                        if (counterGemiddeld < 3) {
                            counterGemiddeld++;
                        }
                    }
                }
                for (int tellerwit = 0; tellerwit < 4; tellerwit++) {

                    if (!poging[tellerwit].getKleur().equals(teKrakenCode[tellerwit].getKleur())) {
                        if (poging[tellerwit].getKleur().equals(teKrakenCode[0].getKleur())
                                || poging[tellerwit].getKleur().equals(teKrakenCode[1].getKleur())
                                || poging[tellerwit].getKleur().equals(teKrakenCode[2].getKleur())
                                || poging[tellerwit].getKleur().equals(teKrakenCode[3].getKleur())) {

                            if (counterGemiddeld == 0) {
                                eval[counterGemiddeld] = new EvaluatiePin("wit");
                                counterGemiddeld++;
                            } else {
                                if (counterGemiddeld < 3) {
                                    eval[counterGemiddeld] = new EvaluatiePin("wit");
                                    counterGemiddeld++;
                                } else {
                                    eval[3] = new EvaluatiePin("wit");
                                }
                            }
                        }
                    }
                }*/
                break;
            case 3:
                eval = new EvaluatiePin[5];
                int counterMoeilijk = 0;
                for (int tellerleeg = 0; tellerleeg < 5; tellerleeg++) {
                    eval[tellerleeg] = new EvaluatiePin("leeg");
                }
                for (int tellerzwart = 0; tellerzwart < 5; tellerzwart++) {
                    if (poging[tellerzwart].getKleur().equals(teKrakenCode[tellerzwart].getKleur())) {
                        eval[counterMoeilijk] = new EvaluatiePin("zwart");
                        if (counterMoeilijk < 4) {
                            counterMoeilijk++;
                        }
                    }
                }
                for (int tellerwit = 0; tellerwit < 5; tellerwit++) {
                    if (!poging[tellerwit].getKleur().equals(teKrakenCode[tellerwit].getKleur())) {
                        if (poging[tellerwit].getKleur().equals(teKrakenCode[0].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[1].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[2].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[3].getKleur()) || poging[tellerwit].getKleur().equals(teKrakenCode[4].getKleur())) {
                            if (counterMoeilijk == 0) {
                                eval[counterMoeilijk] = new EvaluatiePin("wit");
                                counterMoeilijk++;
                            } else {
                                if (counterMoeilijk < 4) {
                                    eval[counterMoeilijk] = new EvaluatiePin("wit");
                                    counterMoeilijk++;
                                } else {
                                    eval[4] = new EvaluatiePin("wit");
                                }

                            }
                        }
                    }
                }
                break;
            default:
                throw new IllegalArgumentException("Er is een fout met de moeilijkheidsgraad");
        }
        return eval;
    }
}
