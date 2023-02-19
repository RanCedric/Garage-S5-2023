<%-- 
    Document   : PrixConseillerProduit
    Created on : 26 janv. 2023, 13:06:54
    Author     : P15B-164-Arisaina
--%>


<%@page import="model.Produits"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Produits produit = (Produits) request.getAttribute("mat");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Prix conseiller</title>
    </head>
    <body>
        <div class="container" style="margin: 0 auto;">
            <div class="produit-container" style="margin: 0 auto;">
                <div class="produit" style="width: 20%;  margin: 0 auto;">
                    <h3>Produit: <%= produit.getProduit_label() %></h3>
                    <h5>Prix d'achat: <%= produit.getProduit_pu() %>Ar</h5>
                    <h5>Marge: <%= produit.getMarge(null) %>%</h5>
                    <h5>Prix conseiller: <%= produit.getPrixConseiller(null) %>Ar</h5>
                    <h5>Benefice: <%= produit.getBenefince() %>Ar</h5>
<a href="./AchatProduitController">Retour</a>
                </div>
                
                
            </div>
        </div>
    </body>
</html>
