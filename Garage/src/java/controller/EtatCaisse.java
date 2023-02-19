/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.BDD;
import generalisationArisaina.bdd.connexion.Connexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Depense;
import model.Facture;
import model.Report;

/**
 *
 * @author Cédric
 */
@WebServlet("/EtatCaisse")
public class EtatCaisse extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EtatCaisse</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EtatCaisse at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

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
        Depense dep = new Depense();
        Facture fac = new Facture();
        
        
        Connection c = Connexion.connectToPostgresDatabase();
        
        ArrayList<Report> reports = null;
        try {
            reports = Report.getFromDatabaseFrom(null, c);
        } catch (Exception ex) {
            Logger.getLogger(EtatCaisse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Report report = null;
        if (reports.size() > 0) {
            report = reports.get(0);
        }
        
        double total_depense = dep.depenseTotal(report);
        double total_vole_niditra = 0.0;
        
        
        BDD bdd = new BDD();
        ArrayList<Object> factures = new ArrayList<>();
        try{
            factures = new Facture().getFromDatabase(bdd.getConnection());
        }catch(Exception e){}
        
        
        for(int i=0;i<factures.size();i++){
            Facture f =(Facture) factures.get(i);
            total_vole_niditra += f.efaVoaloha(report);
        }
        
        double tombony = total_vole_niditra - total_depense;
        if (report != null) {
            tombony += report.getMontant_report();
        }
        
        request.getSession().setAttribute("report", report);
        request.getSession().setAttribute("tombony", tombony);
        request.getSession().setAttribute("total_vole_niditra", total_vole_niditra);
        request.getSession().setAttribute("total_depense", total_depense);
        
        response.sendRedirect("caisse.jsp");
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
        String date = request.getParameter("datee");
        
        Depense dep = new Depense();
        Facture fac = new Facture();
        
        Connection c = Connexion.connectToPostgresDatabase();
        
        ArrayList<Report> reports = null;
        try {
            reports = Report.getFromDatabaseFrom(null, c);
        } catch (Exception ex) {
            Logger.getLogger(EtatCaisse.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Report report = null;
        if (reports.size() > 0) {
            report = reports.get(0);
        }
        
        double total_depense = dep.depenseTotal(date, report);
        
        BDD bdd = new BDD();
        ArrayList<Object> factures = new ArrayList<>();
        try{
            factures = new Facture().getFromDatabase(bdd.getConnection());
        }catch(Exception e){}
        
        double total_vole_niditra = 0.0;
        
        for(int i=0;i<factures.size();i++){
            Facture f =(Facture) factures.get(i);
            total_vole_niditra += f.efaVoaloha(date, report);
        }
        
        double tombony = total_vole_niditra - total_depense;
        if (report != null) {
            tombony += report.getMontant_report();
        }
        
        request.getSession().setAttribute("report", report);
        request.getSession().setAttribute("tombony", tombony);
        request.getSession().setAttribute("total_vole_niditra", total_vole_niditra);
        request.getSession().setAttribute("total_depense", total_depense);
        
        response.sendRedirect("caisse.jsp");
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
