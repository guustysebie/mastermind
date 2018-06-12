/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/*---------IMPORT------------*/
import resourceBundles.Taal;

/**
 *
 * @author User
 */
public class Spelbord {

    /*--------- ATTRIBUTEN ----------*/
    private CodePin[][] bord;                                                   //maakt een 2 dimensionaal array met alle rijen, alle codepinnnen 
    private EvaluatiePin[][] evaluatie;                                         //
    private int nmrpoging = 0;

    /*--- GETTER ---*/
    /**
     * De methode geeft het nummer van de poging terug
     * @return nmrpoging
     */
    public int getNmrpoging() {
        return nmrpoging;
    }

    /**
     * De methode geeft het bord bestaande uit een dubbele array van CodePinnen terug
     * @return het spelbord
     */
    public CodePin[][] getBord() {
        return bord;
    }
/**
 * De methode geeft een dubbele array van EvaluatiePinnen terug die een evaluatie voorstellen
 * @return een evaluatie
 */
    public EvaluatiePin[][] getEvaluatie() {
        return evaluatie;
    }

    /*----Setters----*/

    /**
     * De methode stelt het pogingnummer in
     * @param nmrpoging aan welke zet de speler zit
     */
    public void setNmrpoging(int nmrpoging) {
        this.nmrpoging = nmrpoging;
    }

    /*--- CONSTRUCTOR ---*/
    /**
     * De methode maakt een spelbord aan de hand van de moeilijkheidsgraad aan en geeft een foutmelding wanneer Guust depressief is :p
     * @param moeilijkheidsgraad moeilijkheidsgraad van het gekozen spel
     * @throws IllegalArgumentException wanneer guust op zoektocht is achter coke
     */
    public Spelbord(int moeilijkheidsgraad) {
        switch (moeilijkheidsgraad) {
            case 1:
                bord = new CodePin[12][4];

                //lege rijen aanmaken
                for (CodePin[] rij : bord) {
                    for (CodePin pin : rij) {
                        pin = null;
                    }
                }

                evaluatie = new EvaluatiePin[12][4];
                //lege evaluaitepinnen aanmaken
                for (EvaluatiePin[] rij : evaluatie) {
                    for (EvaluatiePin pin : rij) {
                        pin = null;
                    }

                }
                break;
                
            case 2:
                bord = new CodePin[12][4];
                
                //lege rijen aanmaken
                for (CodePin[] rij : bord) {
                    for (CodePin pin : rij) {
                        pin = null;
                    }
                }

                evaluatie = new EvaluatiePin[12][4];
                //lege evaluaitepinnen aanmaken
                for (EvaluatiePin[] rij : evaluatie) {
                    for (EvaluatiePin pin : rij) {
                        pin = null;
                    }

                }
                break;
            case 3:
                bord = new CodePin[12][5];

                //lege rijen aanmaken
                for (CodePin[] rij : bord) {
                    for (CodePin pin : rij) {
                        pin = null;
                    }
                }

                evaluatie = new EvaluatiePin[12][5];
                //lege evaluaitepinnen aanmaken
                for (EvaluatiePin[] rij : evaluatie) {
                    for (EvaluatiePin pin : rij) {
                        pin = null;
                    }

                }
                break;

            default:
                throw new IllegalArgumentException("i have crippeling depression");
        }
    }

    /*--- METHODEN UC 3 ---*/
    /**
     * De methode vult het spelbord verder op met evaluatiepinnen
     * @param poging    de poging die de sper gedaan heeft
     * @param evaluatie evaluatiepin array 
     */
    public void doePoging(CodePin[] poging, EvaluatiePin[] evaluatie) {
        int legeRijen = 0;
        for (CodePin[] rij : bord) {
            if (rij[0] == null || rij[1] == null || rij[2] == null || rij[3] == null) {
                legeRijen++;
            }
        }
        bord[bord.length - legeRijen] = poging;

        this.evaluatie[this.evaluatie.length - legeRijen] = evaluatie;
        nmrpoging++;
    }

    /*--- OVERRIDES ---*/
    @Override
    public String toString() {
        String uitvoer = String.format(Taal.getWoordUitBundle("spelbord") + "%n");

        for (CodePin[] rij : bord) {
            for (CodePin pin : rij) {
                if (pin != null) {
                    uitvoer += String.format(pin.getKleur() + " ");
                } else {
                    uitvoer += String.format(Taal.getWoordUitBundle("leeg") + " ");

                }
            }
            uitvoer += String.format("%n");
        }

        uitvoer += String.format("%n%n" + Taal.getWoordUitBundle("evaluatiepinnen") + "%n");

        for (EvaluatiePin[] rij : evaluatie) {
            for (EvaluatiePin pin : rij) {
                if (pin != null) {
                    uitvoer += String.format(pin.getKleur() + " ");
                } else {
                    uitvoer += String.format(Taal.getWoordUitBundle("leeg") + " ");

                }
            }
            uitvoer += String.format("%n");
        }

        return uitvoer;
    }
}
