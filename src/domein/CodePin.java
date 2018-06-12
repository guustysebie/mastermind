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
public class CodePin {
 
/*-------------Attributen----------------------*/    
    private String kleur;

    /**
     *
     * @param kleur
     */
    
/*-----------Constructor----------------------*/    
    /**
     * De constructor die een CodePin aanmaakt aan de hand van de kleur meegegeven via de parameter
     * @param kleur  stelt de kleur in
     */
    public CodePin(String kleur) {
        this.kleur = kleur;
    }

 
/*-----------getters-------------------------*/   
    /**
     * De methode geeft de kleur van een codepin terug
     * @return kleur van de codepin
     */
    public String getKleur() {
        return kleur;
    }

    /**
     * De methode stelt de kleur in van een CodePin
     * @param kleur stelt de kleur in
     */
    
/*------------setters-------------------------*/    
    public void setKleur(String kleur) {
        this.kleur = kleur;
    }
   
 /*----------FUNCTIE: zet waarde om in string------*/   
    @Override
    public String toString()
    {
        if(kleur==null){
        return "leeg";
        }else{
        return String.format(kleur);
        }
    }
        

}
