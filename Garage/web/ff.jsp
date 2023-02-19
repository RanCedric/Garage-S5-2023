<%@page import="model.Produits"%>
<%@page import="model.Materiel"%>
<%@page import="model.Service"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
	    ArrayList<Object> services = (ArrayList<Object>) request.getAttribute("services");
		ArrayList<Object> materiels = (ArrayList<Object>) request.getAttribute("materiels");
		
		String nomClient = (String) session.getAttribute("nom");
		Date dateFacturation = (Date) session.getAttribute("date");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<div class="Informations">
			<h3>Facture du: <%= dateFacturation %></h3>
			<h5>Client: <%= nomClient %></h5>
		</div>
		
                <form method="get" action="FacturationDetailsInsert">
			<% for (Object o : services) { 
				Service service = (Service) o;
			%>
				<div>
					<label for="services"><%= service.getService_nom() %></label>
					<input type="number" placeholder="Quantite" name="quantite<%= service.getService_id() %>" value="0">
                                        <input type="number" placeholder="Remise" name="remise<%= service.getService_id() %>" value="0">

				</div>
			<% } %>
			
			<input type="submit" value="Valider">
		</form>
		
		<br>
		
                <form method="post" action="FacturationDetailsInsert">
			<% for (Object o : materiels) { 
				Produits materiel = (Produits) o;
			%>
				<div>
					<label for="services"><%= materiel.getProduit_label() %></label>
					<input type="number" placeholder="quantite" name="quantite<%= materiel.getProduit_id() %>" value="0">				
                                        
                                </div>
			<% } %>
			
			<input type="submit" value="Valider">
		</form>
                <a href="ValidationFactureController">Facturer</a>
</body>
</html>