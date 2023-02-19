<%-- 
    Document   : complete
    Created on : 2 févr. 2023, 11:51:04
    Author     : itu
--%>

<%@page import="model.FactureDetails"%>
<%@page import="model.Facture_produit"%>
<%@page import="model.Facture_service"%>
<%@page import="model.Facture"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<%
	Facture currentFacture = (Facture) request.getAttribute("facture");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        f i c t r
        <label>FACTURE du <%= currentFacture.getFacture_date() %></label><br>
         <label>CLIENT: <%= currentFacture.getFacture_client() %></label><br>
         <% for (Object o1 : currentFacture.getDetailsFacture(null)) { 
            FactureDetails fact = (FactureDetails) o1;
         %>
         <h5>Detail du: <%= fact.getDateFactureDetails() %></h5>
         <label>SERVICE</label>
            <ul>
            <% for (Object o : fact.getServices(null)){ 
            	Facture_service fs = (Facture_service) o;
            %>
                <li><%= fs.getCurrentService(null).getService_nom() %> : <%= fs.getMontantEnFacture(null) %> <%
                	if (fs.getRemise() != 0) out.print("| Avec remise de " + fs.getRemise());
                %></li>
            <% } %>
            </ul>
            
         <label>PRODUIT</label>
         	<% for (Object o : fact.getProduits(null)){ 
            	Facture_produit fs = (Facture_produit) o;
            %>
            <ul>
                <li><%= fs.getCurrentProduit(null).getProduit_label() %> : <%= fs.getMontantEnFacture(null) %> </li>
            </ul>
            <% } %>
         <% } %>
         <label>TOTAL: <%= currentFacture.getTotalPrix(null) %></label><br>
         <label>Deja payer: <%= currentFacture.efaVoaloha() %></label><br>
         <label>Reste payer: <%= currentFacture.getReste() %></label><br>

         
         <form action="PayerFactureController" method="post">
             <input type="hidden" name="facture" value="<%= currentFacture.getFacture_id() %>">
             <input type="date" name="date">
             <input type="number" name="prix" placeholder="Montant ici">
             <input type="submit" value="Payer montant">
         </form>
    </body>
</html>
