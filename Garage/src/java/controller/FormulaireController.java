/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Employer;
import model.Poste;
import model.Sexe;
import model.SpecialEmp;
import model.Specialite;
import service.ServicePoste;
import service.ServiceSpecialite;
import service.Service_Employer;

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
        ArrayList<Poste> allPostes = ServicePoste.getAllPoste();
        ArrayList<Specialite> allSpecialite=ServicePoste.getAllSpecialite();
        ArrayList<Sexe> allSexe=ServicePoste.getAllSexe();
        for(int i=0;i<allSexe.size();i++)
        {
            response.getWriter().print(allSexe.get(i).getSexe_label());
        }
        
        request.getSession().setAttribute("allsexe", allSexe);
        request.getSession().setAttribute("allposte", allPostes);
        request.getSession().setAttribute("allspecialite",allSpecialite);
        
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
        String idGenre = request.getParameter("sexe").trim();
        String employerNum = request.getParameter("telephone"); 
        String idPoste = request.getParameter("poste");
        
        Map<String, String[]> specialites = request.getParameterMap();
        ArrayList<String> specialiteIndiv = new ArrayList<>();
        
        for (Map.Entry<String, String[]> specialtie : specialites.entrySet()) {
            if (specialtie.getKey().contains("specialite")) {
                specialiteIndiv.add(specialtie.getValue()[0]);
            }
        }
        
//        String[] postes = request.getParameterValues("postes[]");
//         ArrayList<Poste> p = ServicePoste.getAllPoste();
//         ArrayList<Poste> ptrue=new ArrayList<Poste>();
//         
//         for (int i= 0 ; i<p.size(); i++)
//         {
//             String val=request.getParameter(String.valueOf(p.get(i).getPoste_id()));
//             if(val.equals("true")) ptrue.add(p.get(i));
//         }
//         String[]PosteValide=new String[ptrue.size()];
//         for(int i=0;i<PosteValide.length;i++)
//         {
//             PosteValide[i]=ptrue.get(i).getPoste_id();
//         }
             
          
           
          
        Employer employer = new Employer();
        employer.setEmployer_name(nom);
        employer.setEmployer_forname(prenom);
        employer.setEmployer_date(dateNaissance);
        employer.setEmployer_numero(employerNum);
        employer.setRef_sexe_id(idGenre);
        employer.setRef_poste_id(idPoste);
        
        String emp_id = Service_Employer.saveEmployer(employer);
        
        for (int i = 0; i < specialiteIndiv.size(); i++) {
            SpecialEmp se = new SpecialEmp();
            se.setEmployer_id_spec(emp_id);
            se.setSpecialite_id_specialites(specialiteIndiv.get(i));
            
            se.save();
        }
        
        
        
//        if (s.checkEmployer(employer.getEmployer_name(),employer.getEmployer_forname())) {
//            String emp_id = Service_Employer.saveEmployer(employer);
//            
//            ServiceSpecialite.saveSpecialite(PosteValide, emp_id);
//        }
        
        response.sendRedirect("./accueil.jsp"); // TODO: A changer
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
