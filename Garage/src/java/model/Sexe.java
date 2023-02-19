/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.BDD;

/**
 *
 * @author itu
 */
public class Sexe extends BDD
{
    String sexe_id ;
    String sexe_label ;

    public String getSexe_id() {
        return sexe_id;
    }

    public void setSexe_id(String sexe_id) {
        this.sexe_id = sexe_id;
    }

    public String getSexe_label() {
        return sexe_label;
    }

    public void setSexe_label(String sexe_label) {
        this.sexe_label = sexe_label;
    }
 
}

