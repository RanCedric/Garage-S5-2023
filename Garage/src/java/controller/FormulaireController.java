/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employer;
import service.ServiceEmployer;
import service.ServiceSpecialite;

/**
 *
 * @author P15B-164-Arisaina
 */
@WebServlet(name = "FormulaireController", urlPatterns = {"/FormulaireController"})
public class FormulaireController extends HttpServlet {

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
        ArrayList<Poste> allPostes = ServicePoste.getAllPoste(); // Tsy mbola ananako le classe
        
        request.setAttribute("allposte", allPostes);
        request.getRequestDispatcher("./InsertionEmployer.jsp").forward(request, response);
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
        String nom = request.getParameter("nom");
        String prenom = request.getParameter("prenom");
        Date dateNaissance = Date.valueOf(request.getParameter("dateNaissance"));
        String idGenre = request.getParameter("genre").trim();
        String employerNum = request.getParameter("numero"); 
        String[] postes = request.getParameterValues("postes[]");
        
        Employer employer = new Employer();
        employer.setEmployer_name(nom);
        employer.setEmployer_forname(prenom);
        employer.setEmployer_date(dateNaissance);
        employer.setEmployer_numero(employerNum);
        employer.setRef_sexe_id(idGenre);
        
        if (ServiceEmployer.checkEmployer(employer)) {
            String emp_id = ServiceEmployer.saveEmployer(employer);
            
            ServiceSpecialite.saveSpecialite(postes, emp_id);
        }
        
        response.sendRedirect("./Acceuil.jsp"); // TODO: A changer
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
