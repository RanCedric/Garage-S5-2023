<%@page import="model.Report"%>
<%
    double depense = (double)session.getAttribute("total_depense");
    double niditra =(double)session.getAttribute("total_vole_niditra");
    double ambiny =(double)session.getAttribute("tombony");
    Report report = (Report) session.getAttribute("report");
%>
<% if (report != null){ %>
<h5>Report du: <%= report.getDate_report() %> </h5>
<% } %>

<label>vole niditra : <%= niditra %></label><br>
<label>vole depense : <%= depense %></label><br>
<label>vole ambiny : <%= ambiny %></label><br>

<form action="EtatCaisse" method="post">
    <label>etat du caisse pour la date</label><br>
    <input type="date" name="datee"><br>
    <input type="submit">
</form>

</hr>
<h3>Reporter</h3>
<form action="EtatCaisse" method="post">
    <label>Reporter</label><br>
    <input type="date" name="date"><br>
    <input type="submit">
</form>