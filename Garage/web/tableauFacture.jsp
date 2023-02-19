<%-- 
    Document   : tableau
    Created on : 2 févr. 2023, 11:59:31
    Author     : itu
--%>

<%@page import="model.Facture"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	ArrayList<Object> factures = (ArrayList<Object>) request.getAttribute("factures");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <table border="1">
             <tr>
                <th>Facture</th>
                <th>Date</th>
                <th>Client</th>
                <th>Total</th>
                <th>Deja payer</th>
            </tr>
            
            <%
            	for (Object o : factures) {
            		Facture facture = (Facture) o;
            %>
            <tr>
                <td><%= facture.getFacture_id() %></td>
                <td><%= facture.getFacture_date() %></td>
                <td><%= facture.getFacture_client() %></td>
                <td><%= facture.getTotalPrix(null) %></td>
                <td><%= facture.efaVoaloha() %></td>
                <td><a href="./AffichageFacture?id=<%= facture.getFacture_id() %>">Afficher</a></td>
            </tr>
            <%
            	}
            %>
        </table>
    </body>
</html>
