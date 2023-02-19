/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.BDD;

/**
 *
 * @author P15B-164-Arisaina
 */
public class Poste extends BDD{
    int poste_id;
    String poste_label;
    
    public Poste(){
        
    }
    
    // Getters - Setters
    public int getPoste_id() {
        return poste_id;
    }

    public void setPoste_id(int poste_id) {
        this.poste_id = poste_id;
    }

    public String getPoste_label() {
        return poste_label;
    }

    public void setPoste_label(String poste_label) {
        this.poste_label = poste_label;
    }
}
