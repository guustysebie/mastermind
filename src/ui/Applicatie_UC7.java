/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import domein.DomeinController;
import java.util.List;
import resourceBundles.Taal;

/**
 *
 * @author guust
 */
public class Applicatie_UC7 {

    private DomeinController dc;

    /**
     * Constructor van de klasse. Initialiseert de DomeinController
     * @param dc geeft de domeincontroler mee 
     */
    public Applicatie_UC7(DomeinController dc) {
        this.dc = dc;
    }

    /**
     * Geeft het klassement weer
     * @see Taal#getWoordUitBundle(java.lang.String) 
     * @see DomeinController#updateKlassement() 
     * @see DomeinController#geefKlassement() 
     */
    public void geefKlassement() {
        dc.updateKlassement();
        List<List<String[]>> klassement = dc.geefKlassement();
       List<String[]> klassementmakkelijk = klassement.get(0);
       List<String[]> klassementnormaal = klassement.get(1);
       List<String[]> klassementmoeilijk = klassement.get(2);
        System.out.println("");
        
        System.out.printf("                          KLASSEMENT                        %n%n");
        System.out.printf("  %s                         %s                         %s", Taal.getWoordUitBundle("guiMakkelijk"),
                Taal.getWoordUitBundle("guiNormaal"), Taal.getWoordUitBundle("guiMoeilijk"));
        
        for (int i = 0; i < klassementmakkelijk.size(); i++) {
            String outputmakkelijk = "";
            String outputnormaal = "";
            String outputmoeilijk = "";
            String[] spelermakkelijk = klassementmakkelijk.get(i);
            String[] spelernormaal = klassementnormaal.get(i);
            String[] spelermoeilijk = klassementmoeilijk.get(i);
            
            
            outputmakkelijk = String.format("%-3d%-5s%-25s", i+1,spelermakkelijk[1],spelermakkelijk[0]);
            outputnormaal = String.format("%-3d%-5s%-25s", i+1,spelernormaal[1],spelernormaal[0]);
            outputmoeilijk = String.format("%-3d%-5s%-25s", i+1,spelermoeilijk[1],spelermoeilijk[0]);
            
            System.out.println(outputmakkelijk + outputnormaal+ outputmoeilijk);
            
        }
        
        System.exit(0);
        
        
        

    }

}
