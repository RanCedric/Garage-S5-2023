<%@page import="model.Specialite"%>
<%@page import="model.Poste"%>
<%@page import="java.awt.Point"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	ArrayList<Poste> allPoste = (ArrayList<Poste>) request.getAttribute("postes");
    	ArrayList<Specialite> allSpecialite = (ArrayList<Specialite>) request.getAttribute("specialites");
    %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Estimation</title>
</head>
<body>
	<div class="container">
		<form class="estimation-form" method="post">
			<% for (Specialite specialite : allSpecialite) { %>
				<label for="duree"><%= specialite.getSpecialite_label() %></label>
				<input type="number" name="<%= specialite.getSpecialite_id() %>" id="duree" value="0">
			<% } %>
			
			<input type="submit" value="Estimer">
		</form>
		
		<% if (request.getAttribute("estimation") != null){ 
			double estimation = (Double) request.getAttribute("estimation");
		%>
			<p>Estimation du service: <%= estimation %></p>
		<% } %>
	</div>
</body>
</html>