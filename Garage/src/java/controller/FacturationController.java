package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generalisationArisaina.bdd.connexion.Connexion;
import model.Produits;
import model.Service;

/**
 * Servlet implementation class FacturationController
 */
@WebServlet("/FacturationController")
public class FacturationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Connection c = Connexion.connectToPostgresDatabase();
		
		try {
			ArrayList<Object> services = new Service().getFromDatabase(c);
			ArrayList<Object> materiels = new Produits().getFromDatabase(c);
			
			request.setAttribute("services", services);
			request.setAttribute("materiels", materiels);
			
			c.close();
			
			request.getRequestDispatcher("./ff.jsp").forward(request, response);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
