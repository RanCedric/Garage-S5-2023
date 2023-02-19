package model;

import generalisationArisaina.bdd.objects.DatabaseObject;

public class Service_spec_montant extends DatabaseObject{
	String service_id;
	double montant;
	
	public Service_spec_montant() {
		
	}

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public double getMontant() {
		return montant;
	}

	public void setMontant(double montant) {
		this.montant = montant;
	}
}
