
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Poste;
import model.Specialite;
import service.ServicePoste;
import service.ServiceSpecialite;

/**
 *
 * @author P15B-164-Arisaina
 */
@WebServlet(name = "EstimationController", urlPatterns = {"/EstimationController"})
public class EstimationController extends HttpServlet {
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
        //ArrayList<Poste> allPostes = ServicePoste.getAllPoste();
        ArrayList<Specialite> specialite =  ServicePoste.getAllSpecialite();
                
        //request.setAttribute("postes", allPostes);
        request.setAttribute("specialites", specialite);
        request.getRequestDispatcher("./PageEstimation.jsp").forward(request, response);
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
        Map<String, String[]> parameterMap = request.getParameterMap();
        
        Map<String, Double> estimationParameter = new HashMap<>();
        
        ArrayList<Specialite> specialite =  ServicePoste.getAllSpecialite();
        
        //request.setAttribute("postes", allPostes);
        request.setAttribute("specialites", specialite);
        
        for (Map.Entry<String, String[]> parameter : parameterMap.entrySet()) {
        	estimationParameter.put(parameter.getKey(), Double.parseDouble(parameter.getValue()[0]));
        }
         
        try {
            double estimation = ServiceSpecialite.estimation(estimationParameter);
            
            request.setAttribute("estimation", estimation);
            
            request.getRequestDispatcher("./PageEstimation.jsp").forward(request, response);
        } catch (SQLException ex) {
            // TODO: Message erreur
        	response.getWriter().print(ex.getMessage());
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
