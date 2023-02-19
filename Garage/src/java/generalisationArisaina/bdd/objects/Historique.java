package generalisationArisaina.bdd.objects;

import java.sql.Time;
import java.sql.Timestamp;

public class Historique extends DatabaseObject{
	String id;
	String nomTable;
	String action;
	String datemodif;
	String valeur;
	
	public Historique() {
		super.setPrimaryKey("HST", "gethseq");
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNomTable() {
		return nomTable;
	}

	public void setNomTable(String nomTable) {
		this.nomTable = nomTable;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getDatemodif() {
		return datemodif;
	}
	
	public Timestamp getDateModifToTimestamp() {
		return new Timestamp(Timestamp.parse(getDatemodif()));
	}

	public void setDatemodif(String datemodif) {
		this.datemodif = datemodif;
	}

	public String getValeur() {
		return valeur;
	}

	public void setValeur(String valeur) {
		this.valeur = valeur;
	}
}
