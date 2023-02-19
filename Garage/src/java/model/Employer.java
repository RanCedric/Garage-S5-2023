/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import DAO.BDD;
import java.sql.Date;

/**
 *
 * @author P15B-164-Arisaina
 */
public class Employer extends BDD{
    String employer_id;
    String employer_name;
    String employer_forname;
    String ref_sexe_id;
    Date employer_date;
    String employer_numero;
    String ref_poste_id;

    public Employer(String employer_id, String employer_name, String employer_forname, String ref_sexe_id, String employer_date, String employer_numero, String ref_poste_id) {
        this.employer_id = employer_id;
        this.employer_name = employer_name;
        this.employer_forname = employer_forname;
        this.ref_sexe_id = ref_sexe_id;
        this.employer_date = Date.valueOf(employer_date) ;
        this.employer_numero = employer_numero;
        this.ref_poste_id = ref_poste_id;
    }

    public Employer() {
    }

    public String getEmployer_id() {
        return employer_id;
    }

    public void setEmployer_id(String employer_id) {
        this.employer_id = employer_id;
    }

    public String getEmployer_name() {
        return employer_name;
    }

    public void setEmployer_name(String employer_name) {
        this.employer_name = employer_name;
    }

    public String getEmployer_forname() {
        return employer_forname;
    }

    public void setEmployer_forname(String employer_forname) {
        this.employer_forname = employer_forname;
    }

    public String getRef_sexe_id() {
        return ref_sexe_id;
    }

    public void setRef_sexe_id(String ref_sexe_id) {
        this.ref_sexe_id = ref_sexe_id;
    }

    public Date getEmployer_date() {
        return employer_date;
    }

    public void setEmployer_date(Date employer_date) {
        this.employer_date = employer_date;
    }

    public String getEmployer_numero() {
        return employer_numero;
    }

    public void setEmployer_numero(String employer_numero) {
        this.employer_numero = employer_numero;
    }

    public String getRef_poste_id() {
        return ref_poste_id;
    }

    public void setRef_poste_id(String ref_poste_id) {
        this.ref_poste_id = ref_poste_id;
    }
}
