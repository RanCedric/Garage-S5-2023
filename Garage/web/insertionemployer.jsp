<%-- 
    Document   : insertionmployer
    Created on : Jan 12, 2023, 3:55:48 PM
    Author     : tafit
--%>

<%@page import="java.util.ArrayList"%>
<%
    ArrayList<String[]> listsexe =(ArrayList<String[]>) session.getAttribute("listeSexe");
    ArrayList<String[]> listpost =(ArrayList<String[]>) session.getAttribute("listePoste");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Insertion</title>
    </head>
    <body>
        <div class ="container">
            <div class="container-form-emp">
                <form action="Control" method="post">
                    <input type="text" name="employer_name" placeholder="Nom"><br>
                    <input type="text" name="employer_forname" placeholder="Prenom"><br>
                    <select name="ref_sexe_id">
                        <% for(int i=0;i<listsexe.size();i++){ %>
                        <option value="<% out.print(listsexe.get(i)[0]); %>" ><% out.print(listsexe.get(i)[1]); %></option>
                        <% } %>
                    </select><br>
                    <input type="date" name="employer_date" palceholder="Date de naissance"><br>
                    <input type="text" name="employer_numero" palceholder="numero de telephone"><br>
                    <select name="ref_poste_id">
                        <% for(int i=0;i<listpost.size();i++){ %>
                        <option value="<% out.print(listpost.get(i)[0]); %>" ><% out.print(listpost.get(i)[1]); %></option>
                        <% } %>
                    </select><br><br>
                    <button type="submit" name="">Inscrire</button>
                </form>
            </div>
        </div>
                </form>
            </div>
        </div>
    </body>
</html>
