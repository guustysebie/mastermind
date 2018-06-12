/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package resourceBundles;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author KriNi
 */
public class Taal {
    private static String naam;
    private static ResourceBundle resourceBundle;
    private static Locale l;
    
    public Taal()
    {
    }
    
    public Taal(String naam)
    {
        controleerNaam(naam);
        Taal.naam = naam;
    }

    public String getNaam()
    {
        return naam;
    }

    public void setNaam(String naam)
    {
        Taal.naam = naam;
    }

    public final void controleerNaam(String naam)
    {
        if (!"fr".equals(naam) || !"en".equals(naam) || !"nl".equals(naam))
        {
            throw new IllegalArgumentException(Taal.getWoordUitBundle("taalOngeldig"));
        }
    }
    public static void kiesTaal (String taal){
        l = new Locale(taal);
    }
    public static String getWoordUitBundle(String key) {
        resourceBundle = ResourceBundle.getBundle("resourceBundles/Bundle", l);
        return resourceBundle.getString(key);
    }
    
    public static String vertaalWoordNaarNederlands(String key){
     resourceBundle = ResourceBundle.getBundle("resourceBundles/Bundle", l);
       
     

     return resourceBundle.getString(key);
    
    }

    
    
    
  
}
