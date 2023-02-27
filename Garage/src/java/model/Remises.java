/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aris
 */
public class Remises extends DatabaseObject{
    String remise_id;
    String remise_nom;
    double remise_montant;

    public Remises() {
    }

    public String getRemise_id() {
        return remise_id;
    }

    public void setRemise_id(String remise_id) {
        this.remise_id = remise_id;
    }

    public String getRemise_nom() {
        return remise_nom;
    }

    public void setRemise_nom(String remise_nom) {
        this.remise_nom = remise_nom;
    }

    public double getRemise_montant() {
        return remise_montant;
    }

    public void setRemise_montant(double remise_montant) {
        this.remise_montant = remise_montant;
    }
    
    public static Remises getRemiseof(String remise_nom){
        Remises current = null;
        
        try {
            Connection c = Connexion.connectToPostgresDatabase();
            
            ArrayList<Object> allRemise = new Remises().getFromDatabase(c);
            
            for (Object o : allRemise) {
                Remises remise = (Remises) o;
                
                if (remise.getRemise_nom().toLowerCase().contains(remise_nom.toLowerCase())) {
                    current = remise;
                }
            }
            
            c.close();
        } catch (SQLException ex) {
            Logger.getLogger(Remises.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return current;
    }
}
