package model;

import java.sql.Connection;
import java.sql.SQLException;

import generalisationArisaina.bdd.connexion.Connexion;
import generalisationArisaina.bdd.objects.DatabaseObject;

public class Facture_produit extends DatabaseObject{
	String facture_id_produit;
    String produit_id;
    double quantite;
    
    public Facture_produit() {
    	
    }

	public String getFacture_id_produit() {
		return facture_id_produit;
	}

	public void setFacture_id_produit(String facture_id_produit) {
		this.facture_id_produit = facture_id_produit;
	}



	public String getProduit_id() {
		return produit_id;
	}

	public void setProduit_id(String produit_id) {
		this.produit_id = produit_id;
	}

	public double getQuantite() {
		return quantite;
	}

	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	
	public Produits getCurrentProduit(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		Produits serviceInstance = new Produits();
		serviceInstance.setSelection("produit_id", getProduit_id());
		
		Produits currentService = (Produits) serviceInstance.getSelectionFromDatabase(c);
		
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
		
		Produits serviceInstance = new Produits();
		serviceInstance.setSelection("produit_id", getProduit_id());
		
		Produits currentService = (Produits) serviceInstance.getSelectionFromDatabase(c);
		
		if (opened) {
			try {
				c.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return currentService.getPrixConseiller(c);
	}
	
	public double getMontantEnFacture(Connection c) throws Exception {
		boolean opened = false;
		
		if (c == null) {
			opened = true;
			
			c = Connexion.connectToPostgresDatabase();
		}
		
		double pu = getPrixUnitaire(c);
		double prix = pu;
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
