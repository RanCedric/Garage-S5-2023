package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import generalisationArisaina.bdd.connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
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
 * @author P15B-164-Arisaina
 */
@WebServlet(urlPatterns = {"/PrixConseillerProduitController"})
public class PrixConseillerProduitController extends HttpServlet {
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        Connection c = Connexion.connectToPostgresDatabase();
        
        try {
            ArrayList<Object> materiels = new Produits().getFromDatabase(c);
            request.setAttribute("materiels", materiels);
        } catch (SQLException ex) {
            // TODO: Message erreur
        }
        
        try {
            String id = request.getParameter("id");
            
            Produits materielInstance = new Produits();
            materielInstance.setSelection("produit_id", id);
            
            Produits materiel = (Produits) materielInstance.getSelectionFromDatabase(c);
            
            request.setAttribute("mat", materiel);
            
            c.close();
            
            request.getRequestDispatcher("./AchatProduit.jsp").forward(request, response);
        } catch (SQLException ex) {
            response.getWriter().println(ex.getMessage() + "haha");
        } catch (Exception ex) {
            response.getWriter().println(ex.getMessage() + " hehe");
        }
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
