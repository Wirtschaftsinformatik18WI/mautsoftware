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
		String description="";
		String origin="";
		String regnumber="";
		description=request.getParameter("description"); 
		origin=request.getParameter("origin");
		regnumber=request.getParameter("regnumber");
		
		DatabaseConnection database = new DatabaseConnection();
		
		System.out.println(description+ " - " + origin + " - " + regnumber);
		
		%> 
	</body> 
</html>