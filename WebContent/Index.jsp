<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<form action="Login" method="post">
<input type="text" name="usuarioid" placeholder="usuario" required>
<br>
<input type="password" name="password" placeholder="password" required>
<br>
   <button type="submit" name=login value=validar>Enviar</button>
</form>
<a href="registro.jsp">Registrese</a>
</body>
</html>