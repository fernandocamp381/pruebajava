<%@page import="java.math.BigDecimal"%>
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

<% List<Libro> listatotal = (List<Libro>)session.getAttribute("LibrosCliente"); %>
<%double suma=0; %>
<% for(Libro libro : listatotal){%>
<%suma+=libro.getPrecioUnitario().doubleValue(); %>
<%} %>
<table>
<tr><td>Total:</td><td><%=suma%></td></tr>
</table>

<table style="border:solid black 2px">
<tr>
<th style="border:solid black 2px">Titulo</th>
<th style="border:solid black 2px">autor</th>
<th style="border:solid black 2px">precio</th>
</tr>


<% for(Libro libro : listatotal){%>
<tr><td style="border:solid black 2px"><%=libro.getTitulo()%></td> <td style="border:solid black 2px"> <%=libro.getAutor()%></td>
<td style="border:solid black 2px"> <%=libro.getPrecioUnitario()%></td> <td><a href="GestionNormal?opcion=eliminar&libroEliminar=<%=libro.getIsbn()%>">Eliminar</a></td></tr>

<%} %>

</table>

<br>
<a href="GestionNormal?opcion=vaciar">Vaciar</a><br>
<a href="GestionNormal?opcion=cerrarSession">Cerrar Sesion</a><br>
<a href="GestionNormal?opcion=volver">Volver a opciones</a><br>
<a href="GestionNormal?opcion=comprar">Comprar</a><br>




</body>
</html>