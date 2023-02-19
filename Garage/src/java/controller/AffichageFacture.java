package controller;

import java.io.IOException;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generalisationArisaina.bdd.connexion.Connexion;
import model.Facture;

/**
 * Servlet implementation class AffichageFacture
 */
@WebServlet("/AffichageFacture")
public class AffichageFacture extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		
		Connection c = Connexion.connectToPostgresDatabase();
		
		Facture factureInstance = new Facture();
		try {
			factureInstance.setSelection("facture_id", id);
			
			Facture currentFacture = (Facture) factureInstance.getSelectionFromDatabase(c);
			
			request.setAttribute("facture", currentFacture);
			
			request.getRequestDispatcher("./completeFacture.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
