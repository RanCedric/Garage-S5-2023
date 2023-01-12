/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.BDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employer;

/**
 *
 * @author allan
 */
public class Service_Employer {
    public Boolean chekEmployer (String name , String username ,String date) throws SQLException{
        Boolean Checkemploye = false ;
        int count=0 ;
        BDD bdd = new BDD () ;
        if (!name.equals("") && !username.equals("") && !date.equals("") ){
            String requette = "select * from employe where employer_name= '"+name+"'and employer_forname='"+username+"' and employer_date='"+date+"'" ; 
            Checkemploye = true ;
            try{
            ResultSet set =  bdd.response(requette) ;
            while (set.next()){
                count++;
            }
            }catch (Exception e){
                
            }
        }
        if (count == 0) Checkemploye = true;
        if (count !=0) Checkemploye = false;
        return Checkemploye;
    }
    
    public static String saveEmployer(Employer emp) {
        String emp_id = null;
        
        String requete = "INSERT INTO employer(employer_name, employer_forname, ref_sexe_id, employer_date, employer_numero, ref_poste_id) VALUES (";
        requete += "'" + emp.getEmployer_name() + "',";
        requete += "'" + emp.getEmployer_forname() + "',";
        requete += "'" + emp.getRef_sexe_id() + "',";
        requete += "'" + emp.getEmployer_date() + "',";
        requete += "'" + emp.getEmployer_numero() + "',";
        requete += "'" + emp.getRef_post_id() + "')";

        BDD bdd = new BDD();
        boolean response = bdd.confirme(requete);
        
        if (response) {
            requete = "SELECT FROM employer WHERE ";
            requete += " employer_name = '" + emp.getEmployer_name() + "',";
            requete += " employer_forname = '" + emp.getEmployer_forname() + "',";
            requete += " ref_sexe_id = '" + emp.getRef_sexe_id() + "',";
            requete += " employer_date = '" + emp.getEmployer_date() + "',";
            requete += " employer_numero = '" + emp.getEmployer_numero() + "',";
            requete += " ref_poste_id = '" + emp.getRef_post_id() + "')";
            
            ResultSet res;
            try {
                res = bdd.response(requete);
                
                while (res.next()) {
                    emp_id = res.getString("employer_id");
                }
            } catch (SQLException ex) {
            }
        }
        
        return emp_id;
    }
    
    public static boolean saveSalaire(double salaire, String emp_id) {
        String requete = "INSERT INTO salaire(ref_employer_id, salaire_value) VALUES ('"+ emp_id +"', "+ salaire +")";
        
        BDD bdd = new BDD();
        boolean response = bdd.confirme(requete);
        
        return response;
    }
}
