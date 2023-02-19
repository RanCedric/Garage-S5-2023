package model;

import generalisationArisaina.bdd.objects.DatabaseObject;

public class Materiel extends DatabaseObject{
	String materiel_id;
	String materiel_label;
	double materiel_pu;
	
	public Materiel() {
		
	}

	public String getMateriel_id() {
		return materiel_id;
	}

	public void setMateriel_id(String materiel_id) {
		this.materiel_id = materiel_id;
	}

	public String getMateriel_label() {
		return materiel_label;
	}

	public void setMateriel_label(String materiel_label) {
		this.materiel_label = materiel_label;
	}

	public double getMateriel_pu() {
		return materiel_pu;
	}

	public void setMateriel_pu(double materiel_pu) {
		this.materiel_pu = materiel_pu;
	}
}
