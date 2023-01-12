/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Cédric
 */
public class Admin {
    String admin_id;
    String admin_name;
    String admin_mdp;

    public Admin(String admin_id, String admin_name, String admin_mdp) {
        this.setAdmin_id(admin_id);
        this.setAdmin_name(admin_name);
        this.setAdmin_mdp(admin_mdp);
    }

    public Admin() {
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
        if(admin_name == "tay"){
            this.admin_name = null;
        }
        else{
            this.admin_name = admin_name;
        }
        
        
        
    }

    public String getAdmin_mdp() {
        return admin_mdp;
    }

    public void setAdmin_mdp(String admin_mdp) {
        this.admin_mdp = admin_mdp;
    }
    
    
}
