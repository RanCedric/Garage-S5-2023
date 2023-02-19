package controller;

import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import generalisationArisaina.bdd.connexion.Connexion;
import java.sql.Date;
import java.util.Map;
import model.FactureDetails;
import model.Materiel;
import model.Produit_qt;
import model.Produits;
import model.Service;
import model.Service_qt;

/**
 * Servlet implementation class FacturationDetailsInsert
 */
@WebServlet("/FacturationDetailsInsert")
public class FacturationDetailsInsert extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Services
		
		Connection c = Connexion.connectToPostgresDatabase();
		
                Map<String, String[]> servicesParameters = request.getParameterMap();
                
		try {
                    ArrayList<Service_qt> services = new ArrayList<>();
                    
                    for (Map.Entry<String, String[]> entry : servicesParameters.entrySet()) {
                        String name = entry.getKey();
                        String[] quantite_ = entry.getValue();
                        
                        if (!name.contains("quantite")) continue;
                        String serviceId = name.replace("quantite", "").trim();
                        double remise = Double.parseDouble(servicesParameters.get("remise" + serviceId)[0]);
                        
                        double quantite = Double.parseDouble(quantite_[0]);
                        
                        Service serviceInstance = new Service();

                        serviceInstance.setSelection("service_id", serviceId);
                        Service currentService = (Service) serviceInstance.getSelectionFromDatabase(c);

                        Service_qt serv_qt = new Service_qt();
                        serv_qt.setService(currentService);
                        serv_qt.setQuantite(quantite);
                        serv_qt.setRemise(remise);

                        services.add(serv_qt);
                    }
                    
                    FactureDetails factureDetails = new FactureDetails();
                    factureDetails.setDateFactureDetails(new Date(System.currentTimeMillis()));
                    factureDetails.setServices(services);
                    
                    ArrayList<FactureDetails> df = new ArrayList<>();
                    
                    if (request.getSession().getAttribute("details_facture") != null) {
                        df = (ArrayList<FactureDetails>) request.getSession().getAttribute("details_facture");
                    }
                    
                    df.add(factureDetails);
                    request.getSession().setAttribute("details_facture", df);
                    
                    response.sendRedirect("./FacturationController");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Materiel
		
		Connection c = Connexion.connectToPostgresDatabase();
			
		Map<String, String[]> servicesParameters = request.getParameterMap();
                
		try {
                    ArrayList<Produit_qt> produis = new ArrayList<>();
                    
                    for (Map.Entry<String, String[]> entry : servicesParameters.entrySet()) {
                        String name = entry.getKey();
                        String[] quantite_ = entry.getValue();
                        
                        if (!name.contains("quantite")) continue;
                        String serviceId = name.replace("quantite", "").trim();

                        double quantite = Double.parseDouble(quantite_[0]);
                        Produits produitInstance = new Produits();

                        produitInstance.setSelection("produit_id", serviceId);
                        Produits currentProduit = (Produits) produitInstance.getSelectionFromDatabase(c);

                        Produit_qt produits_qt = new Produit_qt();
                        produits_qt.setProduit(currentProduit);
                        produits_qt.setQuantite(quantite);

                        produis.add(produits_qt);
                    }
                    
                    FactureDetails factureDetails = new FactureDetails();
                    factureDetails.setDateFactureDetails(new Date(System.currentTimeMillis()));
                    factureDetails.setAchats(produis);
                    
                    ArrayList<FactureDetails> df = new ArrayList<>();
                    
                    if (request.getSession().getAttribute("details_facture") != null) {
                        df = (ArrayList<FactureDetails>) request.getSession().getAttribute("details_facture");
                    }
                    
                    df.add(factureDetails);
                    request.getSession().setAttribute("details_facture", df);
                    
                    response.sendRedirect("./FacturationController");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
