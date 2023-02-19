/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import com.sun.org.apache.bcel.internal.generic.AALOAD;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Facture_payer;

/**
 *
 * @author aris
 */
@WebServlet(name = "PayerFactureController", urlPatterns = {"/PayerFactureController"})
public class PayerFactureController extends HttpServlet {

    
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
        double prixEntree = Double.parseDouble(request.getParameter("prix"));
        String idFacture = request.getParameter("facture");
        Date date = request.getParameter("date") == null ? new Date(System.currentTimeMillis()) : Date.valueOf(request.getParameter("date")); 
        
        Facture_payer fp = new Facture_payer();
        fp.setFacture_id_payer(idFacture);
        fp.setMontant_paye(prixEntree);
        fp.setPaye_date(date);
        
        fp.save();
        
        response.sendRedirect("AffichageFacture?id=" + idFacture);
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
