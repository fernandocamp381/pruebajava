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
<form action="GestionAdmin" method="post">
<input type="text" maxlength="15" name="ISBN" placeholder="ISBN" required>
<br>
<input type="text" maxlength="200" name="titulo" placeholder="Titulo" required>
<br>
<input type="text" maxlength="200" name="autor" placeholder="Autor" required>
<br>
<input type="text" maxlength="200" name="precio" placeholder="Precio de la Unidad" required>
<br>
<input type="text" maxlength="200" name="stock" placeholder="Stock" required>
<br>

<% List<Tema> listatotal = (List<Tema>)request.getAttribute("temas"); %>
<select name="temaelegido">
<% for(Tema tema : listatotal){%>
<option value="<%=tema.getIdTema()%>"><%=tema.getDescTema()%></option>
<%} %>
</select>
<br>
   <button type="submit" name="opcion" value="procesarAltaLibro">Enviar</button>
</form>
</body>
</html>