<%@page contentType="text/html" pageEncoding="UTF-8"%> 
<%@ page import="Backend.*" %>
<html>
	<head> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"> 
		<title>JSP Page</title> 
	</head> 
	<body> 
		<% 
		String username="";
		String password="";
		username=request.getParameter("username"); 
		password=request.getParameter("password"); 
		LoginCheck logincheck = new LoginCheck();
		//if((username.equals("anurag") && password.equals("jain"))) { //hier muss die Funktion zum Nutzerdaten prüfen aufgerufen werden
		if (!(request.getParameter("username").equals("")||request.getParameter("password").equals(""))){
			if (logincheck.checkPassword(username, password)){
				session.setAttribute("username",username); response.sendRedirect("User.jsp"); } 
			else response.sendRedirect("LoginFailed.html");
		}
		else {
			response.sendRedirect("LoginFailed.html");
		}
		
		%> 
	</body> 
</html>
