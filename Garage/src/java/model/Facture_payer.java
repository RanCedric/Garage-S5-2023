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
public class Facture_payer extends BDD{
    String facture_id_payer;
    Date paye_date;
    double montant_paye;

    public Facture_payer() {
    }

    public Facture_payer(String facture_id_payer, Date paye_date, double montant_paye) {
        this.facture_id_payer = facture_id_payer;
        this.paye_date = paye_date;
        this.montant_paye = montant_paye;
    }

    public String getFacture_id_payer() {
        return facture_id_payer;
    }

    public void setFacture_id_payer(String facture_id_payer) {
        this.facture_id_payer = facture_id_payer;
    }

    public Date getPaye_date() {
        return paye_date;
    }

    public void setPaye_date(Date paye_date) {
        this.paye_date = paye_date;
    }

    public double getMontant_paye() {
        return montant_paye;
    }

    public void setMontant_paye(double montant_paye) {
        this.montant_paye = montant_paye;
    }
    
    
}
