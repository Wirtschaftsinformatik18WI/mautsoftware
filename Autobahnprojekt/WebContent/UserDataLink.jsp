<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<%
String username= request.getParameter("username");
String content= request.getParameter("content");
/*
switch(content){
case "start":
	session.setAttribute( "username", username );
	response.sendRedirect("User.jsp");
    break;
case "profile":
	session.setAttribute( "username", username );
	response.sendRedirect("Profile.jsp");
    break;
case "bill":
	session.setAttribute( "username", username );
	response.sendRedirect("Bill.jsp");
    break;
case "vehicle":
	session.setAttribute( "username", username );
	response.sendRedirect("Vehicle.jsp");
    break;
case "transit":
	session.setAttribute( "username", username );
	response.sendRedirect("TransitList.jsp");
    break;
case "logout":
	session.invalidate();
	response.sendRedirect("Start.jsp");
    break;
default:
    
    break;
}*/

if(content.equals("start")){
	session.setAttribute( "username", username );
	response.sendRedirect("User.jsp");
}
if(content.equals("profile")){
	session.setAttribute( "username", username );
	response.sendRedirect("Profile.jsp");
}
if(content.equals("bill")){
	session.setAttribute( "username", username );
	response.sendRedirect("Bill.jsp");
}
if(content.equals("vehicle")){
	session.setAttribute( "username", username );
	response.sendRedirect("Vehicle.jsp");
}
if(content.equals("transit")){
	session.setAttribute( "username", username );
	response.sendRedirect("TransitList.jsp");
}
if(content.equals("logout")){
	session.invalidate();
	response.sendRedirect("Start.jsp");
}

%>
</body>
</html>