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
public class SpecialEmp extends BDD{
    String employer_id_spec;
    String specialite_id_specialites;

    public String getEmployer_id_spec() {
        return employer_id_spec;
    }

    public void setEmployer_id_spec(String employer_id_spec) {
        this.employer_id_spec = employer_id_spec;
    }

    public String getSpecialite_id_specialites() {
        return specialite_id_specialites;
    }

    public void setSpecialite_id_specialites(String specialite_id_specialites) {
        this.specialite_id_specialites = specialite_id_specialites;
    }
    
    
}
