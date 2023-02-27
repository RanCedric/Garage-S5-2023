package model;

import DAO.BDD;
import java.lang.reflect.Array;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;
import java.sql.ResultSet;
import java.sql.Statement;

public class Facture extends DatabaseObject{
	String facture_id;
    String facture_client;
    Date facture_date;
    String client_id;
    
    public Facture() {
    	
    }

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }
    

	public String getFacture_id() {
		return facture_id;
	}

	public void setFacture_id(String facture_id) {
		this.facture_id = facture_id;
	}

	public String getFacture_client() {
		return facture_client;
	}

	public void setFacture_client(String facture_client) {
		this.facture_client = facture_client;
	}

	public Date getFacture_date() {
		return facture_date;
	}

	public void setFacture_date(Date facture_date) {
		this.facture_date = facture_date;
	}
	
	public ArrayList<Object> getDetailsFacture(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		FactureDetails factureDetail = new FactureDetails();
		factureDetail.setSelection("factureId_origine", getFacture_id());
		
		ArrayList<Object> factureDetails = factureDetail.getFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return factureDetails;
	}
	
	public double getTotalPrix(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		ArrayList<Object> f_services = getDetailsFacture(c);
		
		double somme = 0;
		
		for (Object o : f_services) {
			FactureDetails details = (FactureDetails) o;
			
			somme += details.getTotalPrix(c);
		}
				
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
                
                somme = somme - (somme * getRemise());
		
		return somme;
	}
        
        public boolean estPremierFacture() throws SQLException {
            Connection c = Connexion.connectToPostgresDatabase();
            
            String query = "SELECT min(facture_id) as id FROM facture WHERE facture_date <= '31-01-" + this.getFacture_date().getYear() + "' AND facture_date >= '01-01-" + this.getFacture_date().getYear() + "'";
            
            Statement stat = c.createStatement();
            
            ResultSet res = stat.executeQuery(query);
            
            boolean isFirst = false;
            
            while (res.next()) {
                if (this.getFacture_id().equals(res.getString("id"))) isFirst = true;
            }
            
            c.close();
            
            return isFirst;
        }
        
        public double getRemise() throws Exception {
            Connection c = Connexion.connectToPostgresDatabase();
            
            Client clientInstance = new Client();
            clientInstance.setSelection("client_id", this.getClient_id());
            
            Client client = (Client) clientInstance.getSelectionFromDatabase(c);
            
            double remise = 0;
            
            if (client.getDate_naissance().getDay() == this.getFacture_date().getDay() && client.getDate_naissance().getMonth() == this.getFacture_date().getMonth()) {
                remise = Remises.getRemiseof("anniversaire").getRemise_montant(); // A changer
            }
            
            if (estPremierFacture()) {
                double tempRemise = Remises.getRemiseof("premier").getRemise_montant();
                
                remise = tempRemise > remise ? tempRemise : remise;
            }
            
            
            c.close();
            
            return remise;
        }
        
        public double efaVoaloha(Report report){
            double vola = 0.0;
            BDD bdd = new BDD();
            
            String requete = "";
            if (report == null) {
                requete = "select sum(montant_paye) from facture_payer where paye_date <= now()";
            } else {
                requete = "select sum(montant_paye) from facture_payer where paye_date <= now() and paye_date > '" + report.getDate_report() + "'";
            }
        
            vola = Double.valueOf(bdd.getOne(requete));
            return vola;
        }
        
        public double getReste() throws Exception {
            return getTotalPrix(null) - efaVoaloha(null);
        }
        
        public double efaVoaloha(String datee, Report report){
            double vola = 0.0;
            BDD bdd = new BDD();
            String requete = "";
            
            if (report == null) {
                requete = "select sum(montant_paye) from facture_payer where paye_date <='"+datee+"'";
            } else {
                requete = "select sum(montant_paye) from facture_payer where paye_date <='"+datee+"' and paye_date > '" + report.getDate_report() + "'";
            }
            vola = Double.valueOf(bdd.getOne(requete));
            return vola;
        }
}
