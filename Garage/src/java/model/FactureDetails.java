package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;

public class FactureDetails extends DatabaseObject{
	int factureDetails_id;
    String factureId_origine;
    Date dateFactureDetails;
    private ArrayList<Service_qt> services;
    private ArrayList<Produit_qt> achats;
    
    public FactureDetails() {
    	
    }

    public ArrayList<Service_qt> getServices() {
        return services;
    }

    public void setServices(ArrayList<Service_qt> services) {
        this.services = services;
    }

    public ArrayList<Produit_qt> getAchats() {
        return achats;
    }

    public void setAchats(ArrayList<Produit_qt> achats) {
        this.achats = achats;
    }
    
    
    
	public int getFactureDetails_id() {
		return factureDetails_id;
	}
	
	public void setFactureDetails_id(int factureDetails_id) {
		this.factureDetails_id = factureDetails_id;
	}
	
	public String getFactureId_origine() {
		return factureId_origine;
	}
	
	public void setFactureId_origine(String factureId_origine) {
		this.factureId_origine = factureId_origine;
	}
	
	public Date getDateFactureDetails() {
		return dateFactureDetails;
	}
	
	public void setDateFactureDetails(Date dateFactureDetails) {
		this.dateFactureDetails = dateFactureDetails;
	}
	
	public ArrayList<Object> getProduits(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		Facture_produit f_produit = new Facture_produit();
		f_produit.setSelection("facture_id_produit", String.valueOf(getFactureDetails_id()));
		
		ArrayList<Object> f_produits = f_produit.getFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return f_produits;
	}
	
	public ArrayList<Object> getServices(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		Facture_service f_service = new Facture_service();
		f_service.setSelection("facture_id_service", String.valueOf(getFactureDetails_id()));
		
		ArrayList<Object> f_services = f_service.getFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return f_services;
	}
	
	public double getTotalPrix(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		ArrayList<Object> f_services = getServices(c);
		ArrayList<Object> f_produits = getProduits(c);
		
		double somme = 0;
		
		for (Object o : f_services) {
			Facture_service service = (Facture_service) o;
			
			somme += service.getMontantEnFacture(c);
		}
		
		for (Object o : f_produits) {
			Facture_produit produits = (Facture_produit) o;
			
			somme += produits.getMontantEnFacture(c);
		}
				
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return somme;
	}
}
