<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.beans.Libro"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Hola, <%=(String)session.getAttribute("usuario") %></h1>

<% List<Libro> listatotal = (List<Libro>)request.getAttribute("listaLibros"); %>
<form action="GestionNormal" method="post">
<% for(Libro libro : listatotal){%>
<input type="checkbox" id="<%=libro.getIsbn()%>" name="valorElejido" value="<%=libro.getIsbn()%>">
<label for="<%=libro.getIsbn()%>"><%=libro.getTitulo()%></label><br>
<%} %>
 <button type="submit" name=opcion value="addLibros">Enviar</button>
</form>

</body>
</html>