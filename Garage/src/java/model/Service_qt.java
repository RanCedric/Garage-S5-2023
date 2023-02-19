package model;

import java.sql.Connection;

public class Service_qt {
	Service service;
	double quantite;
        double remise;

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }
        
        
	public Service getService() {
		return service;
	}
	public void setService(Service service) {
		this.service = service;
	}
	public double getQuantite() {
		return quantite;
	}
	public void setQuantite(double quantite) {
		this.quantite = quantite;
	}
	
	public double totalPrix(Connection c) throws Exception {
		return getService().getMontant(c) * getQuantite();
	}
}
