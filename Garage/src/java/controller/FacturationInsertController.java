package controller;

import generalisationArisaina.bdd.connexion.Connexion;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Client;

/**
 * Servlet implementation class FactureController
 */
@WebServlet("/FactureController")
public class FacturationInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            Connection c = Connexion.connectToPostgresDatabase();
            
            try {
                ArrayList<Object> clients = new Client().getFromDatabase(c);
                
                request.setAttribute("clients", clients);
                
                c.close();
            } catch (SQLException ex) {
                Logger.getLogger(FacturationInsertController.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            request.getRequestDispatcher("./Facturation.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_client = request.getSession().getAttribute("nom") != null ? (String) request.getSession().getAttribute("nom") :request.getParameter("nom_client");
                String id_client = request.getSession().getAttribute("idClient") != null ? (String) request.getSession().getAttribute("idClient") : request.getParameter("client");
                
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date parse = null;
                
                String dateFacture = request.getParameter("date_facturation") == null || request.getParameter("date_facturation") == "" ? new Date(System.currentTimeMillis()).toString() : request.getParameter("date_facturation");
                
                if (request.getSession().getAttribute("date") != null) {
                    dateFacture = (String) request.getSession().getAttribute("date");
                }
                
                
		try {
                    parse = dateFormat.parse(dateFacture);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Date date_facturation = new Date(parse.getTime());
		
		request.getSession().setAttribute("nom", nom_client);
		request.getSession().setAttribute("date", date_facturation);
		request.getSession().setAttribute("idClient", id_client);
		
		response.sendRedirect("./FacturationController");
	}

}
