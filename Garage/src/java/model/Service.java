package model;

import java.sql.Connection;
import java.util.ArrayList;

import generalisationArisaina.bdd.objects.DatabaseObject;

public class Service extends DatabaseObject{
	String service_id;
    String service_nom;
    double service_prix;
    
    public Service() {
    	
    }

	public String getService_id() {
		return service_id;
	}

	public void setService_id(String service_id) {
		this.service_id = service_id;
	}

	public String getService_nom() {
		return service_nom;
	}

	public void setService_nom(String service_nom) {
		this.service_nom = service_nom;
	}

	public double getService_prix() {
		return service_prix;
	}

	public void setService_prix(double service_prix) {
		this.service_prix = service_prix;
	}
	
	public double getMontant(Connection c) throws Exception {
		double prix = 0;
		
		Service_spec_montant ssminstance = new Service_spec_montant();
		ssminstance.setSelection("service_id", getService_id());
		
		Service_mat_montant smmInstance = new Service_mat_montant();
		smmInstance.setSelection("service_id", getService_id());
		
		ArrayList<Object> allSpec = ssminstance.getFromDatabase(c);
		ArrayList<Object> allMat = smmInstance.getFromDatabase(c);
		
		double mat_prix = 0, spec_prix = 0;
		for (Object o : allSpec) {
			Service_spec_montant spec = (Service_spec_montant) o;
			
			spec_prix += spec.getMontant();
		}
		
		for (Object o : allMat) {
			Service_mat_montant mat = (Service_mat_montant) o;
			
			mat_prix += mat.getMontant();
		}
		
		prix = (mat_prix + spec_prix) * (getService_prix() / 100.0);
		
		return prix;
	}
}
