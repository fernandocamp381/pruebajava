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
<input type="text" maxlength="50" name="nombreTema" placeholder="Nombre del tema" required>
<br>
<input type="text" maxlength="6" name="abreviaturaTema" placeholder="Abreviatura del tema" required>
<br>
   <button type="submit" name="opcion" value="procesarAltaTema">Enviar</button>
</form>
</body>
</html>