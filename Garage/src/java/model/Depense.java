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
 * @author Cédric
 */
public class Depense extends BDD{
    String depense_label;
    Date depense_date;
    double depense_montant;

    public Depense() {
    }

    public Depense(String depense_label, Date depense_date, double depense_montant) {
        this.depense_label = depense_label;
        this.depense_date = depense_date;
        this.depense_montant = depense_montant;
    }

    public String getDepense_label() {
        return depense_label;
    }

    public void setDepense_label(String depense_label) {
        this.depense_label = depense_label;
    }

    public Date getDepense_date() {
        return depense_date;
    }

    public void setDepense_date(Date depense_date) {
        this.depense_date = depense_date;
    }

    public double getDepense_montant() {
        return depense_montant;
    }

    public void setDepense_montant(double depense_montant) {
        this.depense_montant = depense_montant;
    }
    
    public double depenseTotal(Report report){
            double vola = 0.0;
            BDD bdd = new BDD();
            
            String requete = "";
            if (report == null) {
                requete = "select sum(depense_montant) from depense where depense_date <= now()";
            } else {
                requete = "select sum(depense_montant) from depense where depense_date <= now() and depense_date > '" + report.getDate_report() + "'";
            }
            vola = Double.valueOf(bdd.getOne(requete));
            return vola;    
    }
    
    public double depenseTotal(String datee, Report report){
            double vola = 0.0;
            BDD bdd = new BDD();
            
            String requete = "";
            if (report == null) {
                requete = "select sum(depense_montant) from depense where depense_date <= '"+datee+"'";
            } else {
                requete = "select sum(depense_montant) from depense where depense_date <= '"+datee+"' and depense_date > '" + report.getDate_report() + "'";
            }
            
            vola = Double.valueOf(bdd.getOne(requete));
            return vola;    
    }
}
