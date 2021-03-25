<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
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


<table style="border:solid black 2px">
<tr>
<th style="border:solid black 2px">ID usuario</th>
<th style="border:solid black 2px">Cantidad libros comprados</th>
<th style="border:solid black 2px">Cuantos temas en total</th>
<th style="border:solid black 2px">Importe final</th>
</tr>

<% List<Object[]> listatotal = (List<Object[]>)request.getAttribute("informacionusuarios"); %>
<% for(Object[] objeto : listatotal){if(objeto[0]!=null){%>

<tr><td style="border:solid black 2px"><%=objeto[0]%></td> <td style="border:solid black 2px"> <%=objeto[1]%></td>
<td style="border:solid black 2px"> <%=objeto[3]%></td> <td style="border:solid black 2px"> <%=objeto[2]%></td></tr>

<%} else{%>
<tr><td style="border:solid black 2px">No ha hecho operaciones</td> <td style="border:solid black 2px">No ha hecho operaciones</td>
<td style="border:solid black 2px">No ha hecho operaciones</td> <td style="border:solid black 2px">No ha hecho operaciones</td></tr>
<%} }%>
</table>

<a href="GestionAdmin?opcion=volverAlMenu">Volver</a>


</body>
</html>