/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.BDD;
import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author P15B-164-Arisaina
 */
public class Produits extends DatabaseObject{
    private String produit_id;
    String produit_label;
    double produit_pu;
    
    public Produits() {
        
    }

    public String getProduit_id() {
        return produit_id;
    }

    public void setProduit_id(String produit_id) {
        this.produit_id = produit_id;
    }

    public String getProduit_label() {
        return produit_label;
    }

    public void setProduit_label(String produit_label) {
        this.produit_label = produit_label;
    }

    public double getProduit_pu() {
        return produit_pu;
    }

    public void setProduit_pu(double produit_pu) {
        this.produit_pu = produit_pu;
    }
    
    // Fonctions 
    
    public double getMarge(Connection c) throws SQLException {
        boolean ouvert = false;
        if (c == null) {
            c = Connexion.connectToPostgresDatabase();
            ouvert = true;
        }   
        
        double marge = 0;
        ArrayList<Object> fourchettes = new Prixpo().getFromDatabase(c);
        
        for (Object o : fourchettes) {
            Prixpo fourchette = (Prixpo) o;
            
            if (this.getProduit_pu() >= fourchette.getPrixpo_min() && this.getProduit_pu() <= fourchette.getPrixpo_max()) {
                marge = fourchette.getPrixpo_pourcent();
            }
        }
        
        if (ouvert) {
            c.close();
        }
        
        return marge;
    }
    
    public double getPrixConseiller(Connection c) throws SQLException {
        boolean ouvert = false;
        if (c == null) {
            c = Connexion.connectToPostgresDatabase();
            ouvert = true;
        }   
        
        double prix = 0;
        ArrayList<Object> fourchettes = new Prixpo().getFromDatabase(c);
        
        for (Object o : fourchettes) {
            Prixpo fourchette = (Prixpo) o;
            
            if (this.getProduit_pu() >= fourchette.getPrixpo_min() && this.getProduit_pu() <= fourchette.getPrixpo_max()) {
                prix = (this.getProduit_pu() * (fourchette.getPrixpo_pourcent() / 100)) + this.getProduit_pu();
            }
        }
        
        if (ouvert) {
            c.close();
        }
        
        return prix;
    }
    
    public double getBenefince() throws SQLException {
        return getPrixConseiller(null) - getProduit_pu();
    }
}
