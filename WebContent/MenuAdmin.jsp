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
		<li><a href="GestionAdmin?opcion=altaTema">Alta nuevo Tema</a></li>
		<li><a href="GestionAdmin?opcion=altaLibro">Alta nuevo Libro</a></li>
		<li><a href="GestionAdmin?opcion=datosCliente">Datos de Clientes</a></li>
		<li><a href="GestionAdmin?opcion=pedidosDelDia">Pedidos del Dia</a></li>
</body>
</html>