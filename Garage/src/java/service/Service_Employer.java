/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.BDD;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Employer;
import controller.ControllerLogin ;
import java.util.ArrayList;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employer;
import model.Poste;
import model.Sexe;
/**
 *
 * @author allan
 */
public class Service_Employer {
    public Boolean checkEmployer (String name , String username)
    {
        Boolean Checkemploye = false ;
        int count=0 ;
        BDD bdd = new BDD () ;
        Employer employe  ;
        if (!name.equals("") && !username.equals(""))
        {
            String requette = "select * from employe where employer_name= '"+name+"'and employer_forname='"+username+"'" ; 
            Checkemploye = true ;
            try{
            ResultSet set =  bdd.response(requette) ;
            while (set.next())
            {
                count++;
            }
            }catch (Exception e){
                
            }
            
        }
        return (count ==0) ;
    }
    public Boolean chekValeur (String name , String username ,String date){
     Boolean bol =false;
     if(!name.equals("") && !username.equals("") && !date.equals("") ){
        bol=true ;
     }else{
         
     }
     return bol ;
    }
    
     public Boolean chekInput (String name , String password){
     Boolean bol =false;
     if(name.equals("") && password.equals("")  ){
     bol=false ;
     }
     return bol ;
    }
     
        public ArrayList<String[]> getAllSexe()
    {
        Sexe sexe=new Sexe();
        ArrayList<String[]> s=sexe.select();
        return s;
    }
    
    public ArrayList<String[]> getAllPoste()
    {
        Poste poste=new Poste();
        ArrayList<String[]> p=poste.select();
        return p;
    }
    
        public static String saveEmployer(Employer emp) {
        String emp_id = null;
        
        String requete = "INSERT INTO employer(employer_name, employer_forname, ref_sexe_id, employer_date, employer_numero, ref_poste_id) VALUES (";
        requete += "'" + emp.getEmployer_name() + "',";
        requete += "'" + emp.getEmployer_forname() + "',";
        requete += "'" + emp.getRef_sexe_id() + "',";
        requete += "'" + emp.getEmployer_date() + "',";
        requete += "'" + emp.getEmployer_numero() + "',";
        requete += "'" + emp.getRef_poste_id() + "')";


        BDD bdd = new BDD();
        boolean response = bdd.confirme(requete);
        
        
            requete = "SELECT * FROM employer WHERE ";
            requete += " employer_name = '" + emp.getEmployer_name() + "' and ";
            requete += " employer_forname = '" + emp.getEmployer_forname() + "' and ";
            requete += " ref_sexe_id = '" + emp.getRef_sexe_id() + "' and ";
            requete += " employer_date = '" + emp.getEmployer_date() + "' and ";
            requete += " employer_numero = '" + emp.getEmployer_numero() + "' and ";
            requete += " ref_poste_id = '" + emp.getRef_poste_id() + "'";
            
            System.out.println(requete);
            
            ResultSet res;
            try {
                res = bdd.response(requete);
                
                while (res.next()) {
                    emp_id = res.getString("employer_id");
                }
            } catch (SQLException ex) {
            }
        
        
        return emp_id;
    }
}
