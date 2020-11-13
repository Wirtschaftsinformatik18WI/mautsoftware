<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="Backend.*" %>
<%@ page import="database.*" %>
<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<title>JSP Page</title> 
	</head> 
	<body> 
		<% 
		String name="";
		String surname="";
		String street="";
		String hnumber="";
		String postcode="";
		String city="";
		String country="";
		String telephone="";
		String email="";
		String password="";
		boolean iscompany=false;
		name=request.getParameter("name"); 
		surname=request.getParameter("surname");
		street=request.getParameter("street");
		hnumber=request.getParameter("hnumber");
		postcode=request.getParameter("postcode");
		city=request.getParameter("city");
		country=request.getParameter("country");
		telephone=request.getParameter("telephone");
		email=request.getParameter("email");
		password=request.getParameter("password");
		//iscompany=Boolean.valueOf(request.getParameter("iscompany").toString());
		
		DatabaseConnection database = new DatabaseConnection();
		
		if (database.doesUserExist(email)){
			response.sendRedirect("UserExists.html");
		}
		else {
			if (iscompany){
				User newUser = new User (email, name, street,postcode,hnumber,city,telephone,password,country);
				database.createNewUser(newUser);
				out.print("created: companyUser");
			}
			else {
				User newUser = new User (email, name, surname, street,postcode,hnumber,city,telephone,password,country);
				database.createNewUser(newUser);
				out.print("created: privateUser");
			}
		}
		
		%> 
	</body> 
</html>