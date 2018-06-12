/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domein;

/**
 *
 * @author guust
 */
public class EvaluatiePin {

    /*----------Attributen-------------*/
    private final String kleur;

    /**
     * De constructor voor EvaluatiePin met de parameter kleur
     * @param kleur kleur van de evaluatiepin zwart of wit of leeg
     */
    /*---------------CONSTRUCTOR----------*/
    public EvaluatiePin(String kleur) {
        this.kleur = kleur;
    }

    /**
     * De methode is een zuivere getter die de kleur van een EvaluatiePin terugggeeft
     * @return kleur van de EvaluatiePin
     */
    /*----------GETTERS---------------*/
    public String getKleur() {
        return kleur;
    }
    /*-------FUNCTIE: zet de string om naar juist output voor in commandline-----*/
    @Override
    public String toString() {
        return String.format(getKleur() + " ");
    }

}
