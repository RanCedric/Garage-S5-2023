/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.BDD;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Employer;
import model.Poste;
import model.Sexe;
import model.Specialite;

/**
 *
 * @author P15B-164-Arisaina
 */
public class ServicePoste 
{
    public static ArrayList<Poste> getAllPoste()
    {
        BDD bdd = new BDD();
        String query = "select * from poste";
        ArrayList<Poste> postes = new ArrayList<>();
        ResultSet res;
        try {
              res = bdd.response(query);
              while(res.next())
              {
                Poste p = new Poste();
                  p.setPoste_id(res.getInt("poste_id"));
                  p.setPoste_label(res.getString("poste_label"));
                  postes.add(p);
              }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ServicePoste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return postes;
    }

    public static ArrayList<Specialite> getAllSpecialite() {
     BDD bdd = new BDD();
        String query = "select * from specialite";
        ArrayList<Specialite> specialite = new ArrayList<>();
        ResultSet res;
        try {
              res = bdd.response(query);
              while(res.next())
              {
                Specialite p = new Specialite();
                  p.setSpecialite_id(res.getString("specialite_id"));
                  p.setSpecialite_label(res.getString("specialite_label"));
                  p.setKarama(res.getDouble("specialite_karama"));
                  specialite.add(p);
              }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ServicePoste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return specialite;
    }
    
        public static ArrayList<Sexe> getAllSexe() {
     BDD bdd = new BDD();
        String query = "select * from sexe";
        ArrayList<Sexe> sexe = new ArrayList<>();
        ResultSet res;
        try {
              res = bdd.response(query);
              while(res.next())
              {
               Sexe s = new Sexe();
                  s.setSexe_id(res.getString("sexe_id"));
                  s.setSexe_label(res.getString("sexe_label"));
                  sexe.add(s);
              }
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(ServicePoste.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return sexe ;
    }

    public static ArrayList<Employer> getAllemployer() {
     BDD bdd = new BDD();
     Employer emp=new Employer();
        ArrayList<String[]> listEmp = emp.select();
        ArrayList<Employer> list=new ArrayList<Employer>();
       for(int i=0;i<listEmp.size();i++)
        {
              Employer cache=new Employer(listEmp.get(i)[0],listEmp.get(i)[1],listEmp.get(i)[2],listEmp.get(i)[3],listEmp.get(i)[4],listEmp.get(i)[5],listEmp.get(i)[6]);
              list.add(cache);
        }   
        return list;
        }
}
