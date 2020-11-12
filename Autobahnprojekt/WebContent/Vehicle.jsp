<!DOCTYPE html>
<%@ page import="Backend.*" %>
<%@ page import="database.*" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
<% //@ page import ="static org.apache.commons.lang.StringEscapeUtils.escapeHtml" %>
<html>
<head>
	<meta charset="utf-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="Beispiel-Design Nr. 03 von SelfHTML.org" />
    <link rel="stylesheet" href="style.css">
	<title>Autobahnprojekt</title>
</head>
<body>
<header>
    <a class="ribbon" tabindex="-1" title="zurück zur Startseite!" href="index.html">
	   <img src="logotransparent.png" alt="Logo" style="width:120px;height:100px;float:left;"> 
	   <h1 id="headline">Mautilus 18</h1>
       <p>Mercurius IT GmbH</span></p>
    </a>  
</header>

<main>
<%
	session.getAttribute("username");
	System.out.println(session.getAttribute("username"));
	DatabaseConnection database = new DatabaseConnection();
	User currentUser = database.getUserData(session.getAttribute("username").toString());
	%>
	<nav id="navigation">
	<ul>
		<li><a href="Start.jsp">Home</a></li>
		<li><a href="LoginPage.jsp">Login</a></li>
		<li><a href="ProjectIntroduction.html">Projektvorstellung</a></li>
		<li><a href="Statistics.jsp">Statistik</a></li>
		<li><a href="PrivacyPolicy.html">Privacy Policy</a></li>
		<li><a href="Impressum.html">Impressum</a></li>
	</ul>
	</nav>
	<section>
	<h2>Benutzer-Bereich</h2>
<nav id="navigation">
	<table >
			<tr>
			<% 
			out.print("<td  align=\"center\"> <a href=\"UserDataLink.jsp?username=" + currentUser.geteMail() + "&content=start"+"\" ><font size=\"4\"> <span style=\"margin-left:2em\">Start</span></font></a></td>");
			out.print("<td  align=\"center\"> <a href=\"UserDataLink.jsp?username=" + currentUser.geteMail() + "&content=profile"+"\" ><font size=\"4\"> <span style=\"margin-left:2em\">Profil</span></font></a></td>");
			out.print("<td  align=\"center\"> <a href=\"UserDataLink.jsp?username=" + currentUser.geteMail() + "&content=bill"+"\" ><font size=\"4\"> <span style=\"margin-left:2em\">Rechnung</span></font></a></td>");
			out.print("<td  align=\"center\"> <a href=\"UserDataLink.jsp?username=" + currentUser.geteMail() + "&content=vehicle"+"\" ><font size=\"4\"> <span style=\"margin-left:2em\">Fahrzeuge</span></font></a></td>");
			out.print("<td  align=\"center\"> <a href=\"UserDataLink.jsp?username=" + currentUser.geteMail() + "&content=transit"+"\" ><font size=\"4\"> <span style=\"margin-left:2em\">Strecken</span></font></a></td>");
			out.print("<td  align=\"center\"> <a href=\"UserDataLink.jsp?username=" + currentUser.geteMail() + "&content=logout"+"\" ><font size=\"4\"> <span style=\"margin-left:2em\">Logout</span></font></a></td>");
				%>
			</tr>
		</table>
		<br/>
	</nav>
	<p font size="3"><b>Meine Fahrzeuge </b></p>
	<table border="1" id="AddressBookTable" class="table table-hover">
	   <tr id="headline" class="table"> <!--  class=table makes row unselectable -->
	      <td><b><span style=\"margin-left:2em,margin-right:2em \">Beschreibung</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Landeszeichen</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Kennzeichen</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Letzte Position</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Aktuelle Position</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">mautpflichtige Strecke</span></b></td>
	   </tr>
	   
	   <% 
	   List<Vehicle> vehicles = database.getVehicle(currentUser);
	  /* for(Vehicle v: vehicles) {
		   //out.print("<td>" + escapeHtml(v.getDescription())+ "</td>");
		   out.print("<td>" + escapeHtml(v.getOrigin().toString())+ "</td>");
		   out.print("<td>" + escapeHtml(v.getRegistrationNumber())+ "</td>");
		   //out.print("<td>" + escapeHtml(v.getLastPos().get)+ "</td>");
		   //out.print("<td>" + escapeHtml(v.getAcuallPos())+ "</td>");
		   out.print("<td>" + escapeHtml(Double. toString(v.getKm()))+ "</td>");}
		*/   
		for(Vehicle v: vehicles) {
			   //out.print("<td>" + v.getDescription()+ "</td>");
			   out.print("<td>" + "test"+ "</td>");
			   out.print("<td>" + v.getOrigin().toString()+ "</td>");
			   out.print("<td>" + v.getRegistrationNumber()+ "</td>");
			   out.print("<td>" + v.getLastPos().toString()+ "</td>");
			   out.print("<td>" + v.getAcuallPos().toString()+ "</td>");
			   out.print("<td>" + Double.toString(v.getKm())+ "</td>");   
	   }
		
	 
	  %> 
	</table>
	
	
	</section>
	</main>
	</body>
</html>