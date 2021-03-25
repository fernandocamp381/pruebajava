<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.beans.Tema"%>
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

<% List<Tema> listatotal = (List<Tema>)request.getAttribute("listaTemas"); %>
<form action="GestionNormal" method="post">
<% for(Tema tema : listatotal){%>
<input type="radio" id="<%=tema.getIdTema()%>" name="valorElejido" value="<%=tema.getIdTema()%>">
<label for="<%=tema.getIdTema()%>"><%=tema.getDescTema()%></label><br>
<%} %>
 <button type="submit" name=opcion value="librosTemas">Enviar</button>
</form>


</body>
</html>