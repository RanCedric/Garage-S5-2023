/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author allan
 */
public class Admin {
     String admin_id  ;
     String admin_name ;
     String admin_mdp  ;

    public Admin() {
    }

    public Admin(String admin_id, String admin_name, String admin_mdp) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_mdp = admin_mdp;
    }

    public String getAdmin_id() {
        return admin_id;
    }

    public void setAdmin_id(String admin_id) {
        this.admin_id = admin_id;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public void setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
    }

    public String getAdmin_mdp() {
        return admin_mdp;
    }

    public void setAdmin_mdp(String admin_mdp) {
        this.admin_mdp = admin_mdp;
    }
     
}