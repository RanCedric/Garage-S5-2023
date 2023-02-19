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

/**
 *
 * @author aris
 */
public class FactureDAO {
    public static String insert(Connection c, Facture facture) throws SQLException {
        String query = "insert into facture values (default, ?, ?, ?) RETURNING facture_id";
        
        PreparedStatement pstmt = c.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        pstmt.setString(1, facture.getFacture_client());
        pstmt.setString(2, facture.getClient_id());
        pstmt.setDate(3, facture.getFacture_date());
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
