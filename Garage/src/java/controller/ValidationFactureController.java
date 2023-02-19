package controller;

import DAO.FactureDAO;
import DAO.FacturesDetailDAO;
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
import model.Facture;
import model.FactureDetails;
import model.Facture_produit;
import model.Facture_service;
import model.Materiel;
import model.Produit_qt;
import model.Produits;
import model.Service;
import model.Service_qt;

/**
 * Servlet implementation class ValidationFactureController
 */
@WebServlet("/ValidationFactureController")
public class ValidationFactureController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nom_client = (String) request.getSession().getAttribute("nom");
		Date date_facturation = (Date) request.getSession().getAttribute("date");
                String id_client = (String) request.getSession().getAttribute("idClient");
                
		Facture facture = new Facture();
		facture.setFacture_client(nom_client);
		facture.setFacture_date(date_facturation);
                facture.setClient_id(id_client);
		
                ArrayList<FactureDetails> detFacture = (ArrayList<FactureDetails>) request.getSession().getAttribute("details_facture");
                
	
		Connection c = Connexion.connectToPostgresDatabase();
		
		try {
			String idfacture = FactureDAO.insert(c, facture);
			facture.setFacture_id(idfacture);
                        
                        for (FactureDetails details : detFacture) {
                            ArrayList<Service_qt> services = details.getServices();
                            ArrayList<Produit_qt> materiels = details.getAchats();
                            
                            details.setFactureId_origine(facture.getFacture_id());
                            
                            String detailId = FacturesDetailDAO.insert(c, details);
                            
                            if (services != null) {
                                for (Service_qt service : services) {
                                        Facture_service f_service = new Facture_service();

                                        f_service.setService_id(service.getService().getService_id());
                                        f_service.setFacture_id_service(detailId);
                                        f_service.setRemise(service.getRemise());
                                        f_service.setQuantite(service.getQuantite());

                                        f_service.saveInDatabse(c);
                                }
                            }
                            
                            if (materiels != null) {
                                for (Produit_qt produit : materiels) {
                                        Facture_produit f_produit = new Facture_produit();

                                        f_produit.setProduit_id(produit.getProduit().getProduit_id());
                                        f_produit.setFacture_id_produit(detailId);
                                        
                                        f_produit.setQuantite(produit.getQuantite());

                                        f_produit.saveInDatabse(c);
                                }
                            }
                        } 
			
			
			c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getSession().removeAttribute("nom");
		request.getSession().removeAttribute("date");
		request.getSession().removeAttribute("services_factures");
		request.getSession().removeAttribute("materiels_factures");
		
		response.sendRedirect("./ListeFactures");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
