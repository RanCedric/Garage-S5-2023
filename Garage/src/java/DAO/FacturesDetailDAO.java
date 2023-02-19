/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import model.Facture;
import model.FactureDetails;

/**
 *
 * @author aris
 */
public class FacturesDetailDAO {
    public static String insert(Connection c, FactureDetails facture) throws SQLException {
        String query = "insert into factureDetails(factureId_origine, dateFactureDetails) values (?, ?) RETURNING factureDetails_id";
        
        PreparedStatement pstmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, facture.getFactureId_origine());
        pstmt.setDate(2, facture.getDateFactureDetails());
        // ... set other parameters ...
        pstmt.executeUpdate();
        ResultSet rs = pstmt.getGeneratedKeys();
        
        String id = "";
        
        if (rs.next()) {
          id = rs.getString(1);
          // Use the generated id in your code
        }
        
        return id;
    }
}
