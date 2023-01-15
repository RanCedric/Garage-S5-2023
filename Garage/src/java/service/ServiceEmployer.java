/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bdd.BDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employer;

/**
 *
 * @author P15B-164-Arisaina
 */
public class ServiceEmployer {
    public static String saveEmployer(Employer emp) {
        String emp_id = null;
        
        String requete = "INSERT INTO employer(employer_name, employer_forname, ref_sexe_id, employer_date, employer_numero) VALUES (";
        requete += "'" + emp.getEmployer_name() + "',";
        requete += "'" + emp.getEmployer_forname() + "',";
        requete += "'" + emp.getRef_sexe_id() + "',";
        requete += "'" + emp.getEmployer_date() + "',";
        requete += "'" + emp.getEmployer_numero() + "')";

        BDD bdd = new BDD();
        boolean response = bdd.confirme(requete);
        
        if (response) {
            requete = "SELECT FROM employer WHERE ";
            requete += " employer_name = '" + emp.getEmployer_name() + "',";
            requete += " employer_forname = '" + emp.getEmployer_forname() + "',";
            requete += " ref_sexe_id = '" + emp.getRef_sexe_id() + "',";
            requete += " employer_date = '" + emp.getEmployer_date() + "',";
            requete += " employer_numero = '" + emp.getEmployer_numero() + "')";
            
            ResultSet res;
            try {
                res = bdd.response(requete);
                
                while (res.next()) {
                    emp_id = res.getString("employer_id");
                }
            } catch (SQLException ex) {
                Logger.getLogger(ServiceEmployer.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        return emp_id;
    }
    
    public static boolean checkEmployer(Employer emp) {
        // Definir
        return true;
    }
}
