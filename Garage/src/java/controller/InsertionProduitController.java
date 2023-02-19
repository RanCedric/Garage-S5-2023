/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import generalisationArisaina.bdd.connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produits;

/**
 *
 * @author itu
 */
@WebServlet(name = "InsertionProduitController", urlPatterns = {"/InsertionProduitController"})
public class InsertionProduitController extends HttpServlet {


    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String produitLabel = request.getParameter("produit-label");
            double produitPu = Math.abs(Double.parseDouble(request.getParameter("produit-pu")));
            
            Produits produit = new Produits();
            produit.setProduit_label(produitLabel);
            produit.setProduit_pu(produitPu);
            
            Connection c = Connexion.connectToPostgresDatabase();
            
            produit.saveInDatabse(c);
            
            c.close();
            
            response.sendRedirect("./HomeController");
        } catch (SQLException ex) {
            Logger.getLogger(InsertionProduitController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
