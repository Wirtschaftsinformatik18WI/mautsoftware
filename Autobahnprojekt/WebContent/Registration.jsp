<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@ page import="Backend.*" %>
<%@ page import="database.*" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
User newUser= null;
boolean iscompany=Boolean.valueOf(request.getAttribute("iscompany").toString());
if(iscompany){
	newUser = new User(request.getAttribute("email").toString(),request.getAttribute("name").toString(),request.getAttribute("street").toString(),request.getAttribute("postcode").toString(),request.getAttribute("hnumber").toString(),request.getAttribute("city").toString(),request.getAttribute("telephone").toString(),request.getAttribute("password").toString(),request.getAttribute("country").toString());
}
else{
	newUser = new User(request.getAttribute("email").toString(),request.getAttribute("name").toString(),request.getAttribute("surname").toString(),request.getAttribute("street").toString(),request.getAttribute("postcode").toString(),request.getAttribute("hnumber").toString(),request.getAttribute("city").toString(),request.getAttribute("telephone").toString(),request.getAttribute("password").toString(),request.getAttribute("country").toString());
}
DatabaseConnection database = new DatabaseConnection();
database.createNewUser(newUser);
%>
</body>
</html>