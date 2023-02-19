package generalisationArisaina.bdd.connexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connexion {
	public static Connection connectToOracleDatabase() {
	    try {
	    	Class.forName("oracle.jdbc.driver.OracleDriver");
	    	Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","star","usine");
	    	
	    	return con;
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	    
	    return null;
	}
	
	public static Connection connectToPostgresDatabase() {
		try {
			Class.forName("org.postgresql.Driver");
			Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/garage1", "postgres", "root");
			
			return con;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
}