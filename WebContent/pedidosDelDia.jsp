<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.beans.Pedido"%>
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
<th style="border:solid black 2px">Id pedido</th>
<th style="border:solid black 2px">Id usuario</th>
<th style="border:solid black 2px">Direccion de entrega</th>
<th style="border:solid black 2px">Estado del pedido</th>
<th style="border:solid black 2px">Fecha de alta</th>
</tr>

<% List<Pedido> listatotal = (List<Pedido>)request.getAttribute("pedidosdia"); %>
<% for(Pedido pedido : listatotal){%>
<tr><td style="border:solid black 2px"><%=pedido.getIdPedido()%></td> <td style="border:solid black 2px"> <%=pedido.getUsuario().getIdUsuario()%></td>
<td style="border:solid black 2px"> <%=pedido.getDireccionEntrega()%></td> <td style="border:solid black 2px"><%=pedido.getEstado()%></td><td style="border:solid black 2px"><%=pedido.getFechaAlta()%></td></tr>

<%} %>

</table>
<a href="GestionAdmin?opcion=volverAlMenu">Volver</a>
</body>
</html>