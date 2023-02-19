/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import generalisationArisaina.bdd.objects.DatabaseObject;


/**
 *
 * @author P15B-164-Arisaina
 */
public class Prixpo extends DatabaseObject{
    String prixpo_id;
    double prixpo_min;
    double prixpo_max;
    double prixpo_pourcent;
    
    public Prixpo() {
        
    }

    public String getPrixpo_id() {
        return prixpo_id;
    }

    public void setPrixpo_id(String prixpo_id) {
        this.prixpo_id = prixpo_id;
    }

    public double getPrixpo_min() {
        return prixpo_min;
    }

    public void setPrixpo_min(double prixpo_min) {
        this.prixpo_min = prixpo_min;
    }

    public double getPrixpo_max() {
        return prixpo_max;
    }

    public void setPrixpo_max(double prixpo_max) {
        this.prixpo_max = prixpo_max;
    }

    public double getPrixpo_pourcent() {
        return prixpo_pourcent;
    }

    public void setPrixpo_pourcent(double prixpo_pourcent) {
        this.prixpo_pourcent = prixpo_pourcent;
    }
    
    
}
