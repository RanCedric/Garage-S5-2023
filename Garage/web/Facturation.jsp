<%@page import="model.Client"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%
    ArrayList<Object> clients = (ArrayList<Object>) request.getAttribute("clients");
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<div class="container">
		<form method="post" action="./FactureController">
                    <select name="client">
                        <% for (Object o : clients) { 
                            Client client = (Client) o;
                        %>
                        <option value="<%= client.getClient_id() %>"><%= client.getClient_name() %></option>
                        <% } %>
                    </select>
                    
			<input type="text" name="nom_client">
			<input type="date" name="date_facturation">
			
			<input type="submit" value="Valider"> 
		</form>
	</div>
</body>
</html>