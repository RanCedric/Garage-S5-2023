package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generalisationArisaina.bdd.connexion.Connexion;
import model.Materiel;

/**
 * Servlet implementation class MaterielInsertController
 */
@WebServlet("/MaterielInsertController")
public class MaterielInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String materiel_label = request.getParameter("materiel_label");
		double materiel_pu = Double.parseDouble(request.getParameter("material_pu"));
		
		Materiel materiel = new Materiel();
		materiel.setMateriel_label(materiel_label);
		materiel.setMateriel_pu(materiel_pu);
		
		Connection c = Connexion.connectToPostgresDatabase();
		
		try {
			materiel.saveInDatabse(c);
			
			c.close();
			
			response.sendRedirect("./HomeController");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
