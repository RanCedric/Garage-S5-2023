/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import bdd.BDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author P15B-164-Arisaina
 */
public class ServiceSpecialite {
    public static boolean saveSpecialite(String[] idPostes, String employer_id) {
        BDD bdd = new BDD();
        
        boolean response = false;
        for (String idPoste : idPostes) {
            String insertQuery = "INSERT INTO specialites(employer_id_spec, poste_id_specialites) VALUES (";
            insertQuery += idPoste + ",";
            insertQuery += employer_id + ")";
            
            response = bdd.confirme(idPoste);
        }
        
        return response;
    }
    
    public static double estimation(Map<String, Double> postes_id) throws SQLException {
        BDD bdd = new BDD();
        
        double total = 0;
        for (Map.Entry<String, Double> poste_id : postes_id.entrySet()) {
            String getPosteQuery = "SELECT poste_label, poste_karama FROM poste WHERE poste_id = " + poste_id.getKey();
            
            ResultSet res = bdd.response(getPosteQuery);
            
            double karama = res.getDouble("poste_karama");
            
            total += karama * poste_id.getValue();
        }
         
        return total;
    }
}
