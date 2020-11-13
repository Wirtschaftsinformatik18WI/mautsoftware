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
		Origin originx =  Origin.D;
		String description="";
		String origin="";
		String regnumber="";
		String email = "";
		description=request.getParameter("description"); 
		origin=request.getParameter("origin");
		regnumber=request.getParameter("regnumber");
		email=request.getParameter("email");
		
		DatabaseConnection database = new DatabaseConnection();
		database.createVehicle(description, originx.changeToCorrectOrigin(origin) , regnumber, email);
		System.out.println(description+ " - " + origin + " - " + regnumber);
		response.sendRedirect("vehicleadded.html");
		%> 
	</body> 
</html>