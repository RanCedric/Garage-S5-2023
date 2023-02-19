package model;

import java.sql.Connection;

public class Produit_qt {
	Produits produit;
	double quantite;
	public Produits getProduit() {
		return produit;
	}
	public void setProduit(Produits produit) {
		this.produit = produit;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
}
