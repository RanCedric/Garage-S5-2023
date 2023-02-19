<%-- 
    Document   : insertionEmployer
    Created on : 14 janv. 2023, 17:27:59
    Author     : allan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="service.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <title>admissionEmployer</title>
    </head>
    <% Service_Employer employer = new Service_Employer(); %>

    <body>
         <div>
           <form action="" method="post">
            <input type="text" name="nom" class="nom_insertion" class="input_insertion">
            <input type="text" name="prenom" class="prenom_insertion" class="input_insertion">
            <input type="text" name="sexe" class="sexe_insertion" class="input_insertion">
            <input type="datetime-local" name="datetime" class="input_insertion">
            <input type="tel" name="telephone" name="telephone" class="input_insertion">
            <div id="post_insertion" class="input_insertion">
            <%  for (int i= 0 ; i<employer.maka().size(); i++) {%>
            <ul>
            <li><% out.println(employer.maka().get(i)); %> <input Type="checkbox" value="<% out.println(employer.maka().get(i)); %>" checked></li> 
            </ul>
            <%  } %>
            </div>
            <button type="submit" name="submit_insertion" class="submit_insertion">
            valider
            </button>
        </form>
        </div>
    </body>
</html>
