/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DAO.BDD;
import java.sql.ResultSet;
import model.Admin;

/**
 *
 * @author Cédric
 */
public class Service_Admin {
    public Admin login(String name,String mdp){
        Admin a = new Admin();
        
        String requet = "select * from admin where admin_name = '"+name+"' and admin_mdp = '"+mdp+"'";
        BDD bdd = new BDD();
        try{
            ResultSet res = bdd.response(requet);
            while(res.next()){
                a = new Admin(res.getString(1),res.getString(2),res.getString(3));
            }
        }
        catch(Exception e){}
        
        return a;
    }
}
