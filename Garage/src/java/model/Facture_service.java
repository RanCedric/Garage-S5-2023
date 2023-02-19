package model;

import java.sql.Connection;
import java.sql.SQLException;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;

public class Facture_service extends DatabaseObject{
	String facture_id_service;
    String service_id;
    double quantite;
    double remise;
    
    public Facture_service() {
    	
    }

	public String getFacture_id_service() {
		return facture_id_service;
	}

	public void setFacture_id_service(String facture_id_service) {
		this.facture_id_service = facture_id_service;
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}

	public double getRemise() {
		return remise;
	}

	public void setRemise(double remise) {
		this.remise = remise;
	}
	
	public Service getCurrentService(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		Service serviceInstance = new Service();
		serviceInstance.setSelection("service_id", getService_id());
		
		Service currentService = (Service) serviceInstance.getSelectionFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return currentService;
	}
	
	public double getPrixUnitaire(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		Service serviceInstance = new Service();
		serviceInstance.setSelection("service_id", getService_id());
		
		Service currentService = (Service) serviceInstance.getSelectionFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return currentService.getMontant(c);
	}
	
	public double getMontantEnFacture(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		double pu = getPrixUnitaire(c);
		
		double prix = pu - (pu * (getRemise() / 100.0));
		
		prix *= getQuantite();
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return prix;
	}
}
