package generalisationArisaina.bdd.objects;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseObject {
	private String selection = "", attribut = "";
	private String selectionUpdate = "";
	
	private String prefix = "OBJ";
	private int primaryKeyLength = 7;
	private String nomSequenceFonction = "getSeq";
	
	public void setSelection(String _attribut, String _selection) throws Exception {
		this.selection = _selection;
		
		Field[] currentObjectFields = this.getClass().getDeclaredFields();
		
		for (Field field : currentObjectFields) {
			if (field.getName().toLowerCase().equals(_attribut.toLowerCase())) {
				this.attribut = _attribut;
				
				try {
					if (field.getType() == int.class) {
						int temp = Integer.parseInt(_selection);						
					} else if (field.getType() == double.class) {
						double temp = Double.parseDouble(_selection);
					}
				} catch (NumberFormatException e) {
					throw new Exception("Type de l'attribut et selection incompatible");
				}
				return;
			}
		}
		
		throw new Exception("Attribut " + _attribut + " introuvable");
	}
	
	public void setSelectionUpdate(String _attribut) throws Exception {
		Field[] currentObjectFields = this.getClass().getDeclaredFields();
		
		for (Field field : currentObjectFields) {
			if (field.getName().toLowerCase().equals(_attribut.toLowerCase())) {
				this.selectionUpdate = _attribut;
				return;
			}
		}
		
		throw new Exception("Attribut " + _attribut + " introuvable");
	}
	
	public void resetSelection() {
		this.selection = "";
		this.attribut = "";
	}
	
	public void resetUpdates() {
		this.selectionUpdate = "";
	}
	
	public String getSelection() {
		return this.selection;
	}
	
	public String getAttribut() {
		return this.attribut;
	}
	
	public String getSelectionUpdate() {
		return this.selectionUpdate;
	}
	
	public void setPrimaryKeyPrefix(String _prefix) {
		this.prefix = _prefix;
	}
	
	public void setNomSequenceFonction(String nomFonction) {
		this.nomSequenceFonction = nomFonction;
	}
	
	public String getNomSequenceFonction() {
		return this.nomSequenceFonction;
	}
	
	public String getPrefix() {
		return this.prefix;
	}
	
	public Type getAttributType(String _attribut) {
		Field[] currentObjectFields = this.getClass().getDeclaredFields();
		
		for (Field field : currentObjectFields) {
			if (field.getName().toLowerCase().equals(_attribut.toLowerCase())) {
				return field.getType();
			}
		}
		return null;
	}
	
	public void updateFromDatabase(Connection c, String value) throws Exception {
		if (getSelection() == "" || getAttribut() == "") {
			Field[] fields = getClass().getDeclaredFields();
			
			for(int i = 0; i < fields.length; i++) {
				if (fields[i].getName().toLowerCase().contains("id")) {
					setSelection(fields[i].getName(), (String) fields[i].get(this));
				}
			}
		}
		
		if (getSelection() == "" || getAttribut() == "") throw new Exception("Aucune valeur selectionee");
		
		String query = "UPDATE " + this.getClass().getSimpleName() + " SET ";
		
		try {
			if (getAttributType(this.getSelectionUpdate()) == String.class) {
				query += this.getSelectionUpdate() + " = '" + value + "' WHERE ";
			} else if (getAttributType(this.getSelectionUpdate()) == java.sql.Date.class){
				query += this.getSelectionUpdate() + " = '" + value + "' WHERE ";
			} else if (getAttributType(this.getSelectionUpdate()) == int.class){
				query += this.getSelectionUpdate() + " = " + value + " WHERE ";
			} else if (getAttributType(this.getSelectionUpdate()) == double.class){
				query += this.getSelectionUpdate() + " = " + Double.parseDouble(value) + " WHERE ";
			}
			
			if (getAttributType(this.getAttribut()) == String.class) {
				query += this.getAttribut() + " LIKE '%" + this.getSelection() + "%'";
			} else if (getAttributType(this.getSelectionUpdate()) == java.sql.Date.class){
				query += this.getAttribut() + " = '" + this.getSelection() + "'";
			} else {
				query += this.getAttribut() + " = " + this.getSelection();
			}
			
		} catch (NumberFormatException e) {
			System.out.println("Veuillez verifier la valeur entree");
		}
		
		Statement stat = c.createStatement();
		
//		System.out.println(query);
		
		stat.executeQuery(query);
	}
	
	public void deleteFromDatabase(Connection c) throws IllegalArgumentException, IllegalAccessException, Exception {
		if (getSelection() == "" || getAttribut() == "") {
			Field[] fields = getClass().getDeclaredFields();
			
			for(int i = 0; i < fields.length; i++) {
				if (fields[i].getName().toLowerCase().contains("id")) {
					setSelection(fields[i].getName(), (String) fields[i].get(this));
				}
			}
		}
		
		Statement stat = c.createStatement();
		
		if (getSelection() == "" || getAttribut() == "") throw new Exception("Aucune valeur selectionee");
		
		historiser(c, "delete");
		
		String query = "DELETE FROM " + this.getClass().getSimpleName() + " WHERE " + getAttribut();
		
		if (getAttributType(getAttribut()) == String.class) {
			query += " LIKE '%" + getSelection() + "%'";
		} else {
			query += " = " + getSelection();
		}
		
		stat.executeQuery(query);
	}
	
	public ArrayList<Object> getFromDatabase(Connection c) throws SQLException {
		if (c == null) return null;
		
		ArrayList<Object> databaseResults = new ArrayList<Object>();
		
		Statement stat = (Statement) c.createStatement();
		String query = "";
		if (getSelection() == "" || getAttribut() == "") {
			query = "SELECT * FROM " + this.getClass().getSimpleName();
		} else {
			if (getAttributType(this.getAttribut()) == String.class) {
				query = "SELECT * FROM " + this.getClass().getSimpleName() + " WHERE " + getAttribut() + " = '" + getSelection() + "'"; 				
			} else {
				query = "SELECT * FROM " + this.getClass().getSimpleName() + " WHERE " + getAttribut() + " = " + getSelection();
			}
		}
		
		System.out.println(query);
		ResultSet results = stat.executeQuery(query);
		
		try {
			while (results.next()) {
				Field[] rowFields = this.getClass().getDeclaredFields();
				Object newObj = this.getClass().getConstructor().newInstance();
				
				for (int i = 0; i < rowFields.length; i++) {
//					if (Modifier.isPrivate(rowFields[i].getModifiers())) continue;
					
					String fieldCapitalizedName = rowFields[i].getName().substring(0, 1).toUpperCase() + rowFields[i].getName().substring(1);
					Method setterMethod = newObj.getClass().getDeclaredMethod("set" + fieldCapitalizedName, rowFields[i].getType());
					
						if (rowFields[i].getType() == int.class) {
	//					debug.add(" huhu ");
							setterMethod.invoke(newObj, results.getInt(rowFields[i].getName()));
						} else if (rowFields[i].getType() == double.class) {
							setterMethod.invoke(newObj, results.getDouble(rowFields[i].getName()));
						} else if (rowFields[i].getType() == String.class) {
							setterMethod.invoke(newObj, results.getString(rowFields[i].getName()));
						} else if (rowFields[i].getType() == Date.class) {
							setterMethod.invoke(newObj, results.getDate(rowFields[i].getName()));
						} else if (rowFields[i].getType() == Timestamp.class) {
							setterMethod.invoke(newObj, results.getTimestamp(rowFields[i].getName()));
						}
				}
				
				databaseResults.add(newObj);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return databaseResults;
	}
	
	public Object getSelectionFromDatabase(Connection c) throws Exception {
		if (c == null) return null;
		
		if (this.getSelection() == "" || this.getAttribut() == "") throw new Exception("Veuillez selectionner");
		
		ArrayList<Object> bufferedObjects = getFromDatabase(c);
		
		if (bufferedObjects.size() > 0) {
			return bufferedObjects.get(0);
		} else {
			bufferedObjects.clear();
			
			throw new Exception("Aucune correspondance");
		}
	}
	
	public void saveInDatabse(Connection c) throws SQLException {
		if (c == null) return;
		
		Statement stat = c.createStatement();
		
		String requete = "INSERT INTO " + this.getClass().getSimpleName() + "(";
		
		Field[] objectFields = this.getClass().getDeclaredFields();
		
		for (int i = 0; i < objectFields.length; i++) {
			if (Modifier.isPrivate(objectFields[i].getModifiers())) continue;
			
			requete += objectFields[i].getName();
			
			if (i < objectFields.length - 1) requete += ",";
		}
		
		requete += ") VALUES (";
		
		for (int i = 0; i < objectFields.length; i++) {
                    if (Modifier.isPrivate(objectFields[i].getModifiers())) continue;
			try {
				String fieldCapitalizedName = objectFields[i].getName().substring(0, 1).toUpperCase() + objectFields[i].getName().substring(1);
				Method getterMethod = this.getClass().getDeclaredMethod("get" + fieldCapitalizedName);
				
				if (getterMethod.invoke(this) == null) {
					requete+= "NULL";
				} else {
					if (objectFields[i].getType() == int.class) {
						requete += getterMethod.invoke(this);
					} if (objectFields[i].getType() == String.class) {
						requete += "\'" + getterMethod.invoke(this) + "\'";
					} if (objectFields[i].getType() == double.class) {
						requete += getterMethod.invoke(this);
					} if (objectFields[i].getType() == Date.class) {
						String valeur = new SimpleDateFormat("dd-MM-YYYY").format(((Date) getterMethod.invoke(this)));
						requete += "'" + valeur + "'";
					} if (objectFields[i].getType() == Timestamp.class) {
						String valeur = new SimpleDateFormat("dd-MM-YY HH:mm:ss,SSSS").format(((Timestamp) getterMethod.invoke(this)));
						requete += "'" + valeur + "'";
					} 
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (NoSuchMethodException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if (i < objectFields.length - 1) requete += ",";
		}
		
		requete += ")";
		
		System.out.println(requete);
		
		try {
			ResultSet res = stat.executeQuery(requete);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void setPrimaryKey(String _prefix, String _nomFonction) {
		setPrimaryKeyPrefix(_prefix);
		setNomSequenceFonction(_nomFonction);
	}
	
	public String createPrimaryKey(Connection c) {
		if (c == null) return null;
		
		try {
			int sequence = getCurrentSequence(c);
			
			String withoutPrefix = createSequence(sequence, Math.abs(this.primaryKeyLength - this.getPrefix().length()));
			
			return this.getPrefix() + withoutPrefix;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "";
	}
	
	String createSequence(int sequence, int leftLength) {
		String seqString = Integer.toString(sequence);
		String wPrefix = "";
		
		for (int i = 0; i < Math.abs(leftLength - seqString.length()); i++) {
			wPrefix += "0";
		}
		
		wPrefix += seqString;
		
		return wPrefix;
		
	}
	
	public int parseId() {
		return -1;
	}
	
	int getCurrentSequence(Connection c) throws Exception {
		if (c == null) {
			throw new Exception("Äucune connexion");
		}
		
		int sequence = 0;
		
		Statement stat = c.createStatement();
		String query = "SELECT " + getNomSequenceFonction() + "() seq FROM DUAL";
		
		ResultSet res = stat.executeQuery(query);
		
		while (res.next()) {
			sequence = Integer.parseInt(res.getString("seq"));
		}
		
		return sequence;
	}
	public void historiser(Connection c, String action) {
		Field[] objectFields = getClass().getDeclaredFields();
		
		String nomId = "", id = "";
		
		try {
			for (int i = 0; i < objectFields.length; i++) {
				if (objectFields[i].getName().contains("id")) {
					nomId = objectFields[i].getName();
					id = (String) objectFields[i].get(this);
					
					break;
				}			
			}	
			
		setSelection(nomId, id);
		
		Statement stat = c.createStatement();
		System.out.println(getAttribut());
		
		ResultSet res = stat.executeQuery("SELECT * FROM " + this.getClass().getSimpleName() + " WHERE " + this.getAttribut() + " = '" + this.getSelection() + "'");
		
		Historique historique = new Historique();
		historique.setId(historique.createPrimaryKey(c));
		historique.setNomTable(this.getClass().getSimpleName());
		historique.setAction(action);
		historique.setDatemodif(new Timestamp(System.currentTimeMillis()).toString());
		historique.setValeur(this.valeursToString(res));
		
		historique.saveInDatabse(c);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	public String valeursToString(ResultSet res) {
		try {
			res.next();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Field[] objectFields = getClass().getDeclaredFields();
		
		String requete = "";
		
		for (int i = 0; i < objectFields.length; i++) {
//			try {
//				String fieldCapitalizedName = objectFields[i].getName().substring(0, 1).toUpperCase() + objectFields[i].getName().substring(1);
//				Method getterMethod = this.getClass().getDeclaredMethod("get" + fieldCapitalizedName);
//				
//				if (getterMethod.invoke(this) == null) {
//					requete+= "NULL";
//				} else {
//					if (objectFields[i].getType() == int.class) {
//						requete += objectFields[i].getName() + ":" + getterMethod.invoke(this);
//					} if (objectFields[i].getType() == String.class) {
//						requete += objectFields[i].getName() + ":" + getterMethod.invoke(this);
//					} if (objectFields[i].getType() == double.class) {
//						requete += objectFields[i].getName() + ":" + getterMethod.invoke(this);
//					} if (objectFields[i].getType() == Date.class) {
//						requete += objectFields[i].getName() + ":" + getterMethod.invoke(this);
//					} if (objectFields[i].getType() == Timestamp.class) {
//						requete += objectFields[i].getName() + ":" + getterMethod.invoke(this);
//					}
//				}
//			} catch (IllegalArgumentException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (NoSuchMethodException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (SecurityException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (IllegalAccessException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			} catch (InvocationTargetException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			try {
				requete += objectFields[i].getName() + ":" + res.getString(objectFields[i].getName());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			}
			
			if (i < objectFields.length - 1) requete += ";";
		}
		
		return requete;
	}
}
