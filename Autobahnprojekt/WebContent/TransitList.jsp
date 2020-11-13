<!DOCTYPE html>
<%@ page import="Backend.*" %>
<%@ page import="database.*" %>
<%@ page import ="java.util.ArrayList"%>
<%@ page import ="java.util.List"%>
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
    <a class="ribbon" tabindex="-1" title="zurück zur Startseite!" href="Start.jsp">
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
	<p font size="3"><b>Meine Strecken </b></p>
	<table border="1" id="AddressBookTable" class="table table-hover">
	   <tr id="headline" class="table"> <!--  class=table makes row unselectable -->
	      <td><b><span style=\"margin-left:2em,margin-right:2em \">Fahrzeug</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Startpunkt</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Startzeit</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Endpunkt</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">Ankunftszeit</span></b></td>
	      <td><b><span style=\"margin-left:2em,margin-right:2em\">mautpflichtige Strecke</span></b></td>
	   </tr>
	   </table>
	   <% 
	   MainMethodes mainm = new MainMethodes();
	   ArrayList<Vehicle> allvehicleFromAUser = new ArrayList<>();
	   allvehicleFromAUser.addAll(database.getVehicle(currentUser)); 
	   ArrayList<FinishedTransits> allTransitsFromVehicle= new ArrayList<>();
	   for(Vehicle v: allvehicleFromAUser) {
		   out.print(v.getDescription());
		 out.print("  <tr id=\"headline\" class=\"table\">");
		      out.print("<td><b><span style=\"margin-left:2em,margin-right:2em \">Startpunkt</span></b></td>");
		      out.print("<td><b><span style=\"margin-left:2em,margin-right:2em \">Startzeit</span></b></td>");
		      out.print("<td><b><span style=\"margin-left:2em,margin-right:2em \">Endpunkt</span></b></td>");
		      out.print("<td><b><span style=\"margin-left:2em,margin-right:2em \">Ankunftszeit</span></b></td>");
		      out.print("<td><b><span style=\"margin-left:2em,margin-right:2em \">mautflichtige Strecke</span></b></td>");
		     out.print("</tr>");
		   out.print("</table>");
		   
		   allTransitsFromVehicle.addAll(database.getAllTransitFromVehicle(v, 11));
		   for(FinishedTransits t: allTransitsFromVehicle) {
			   out.print("<td>" + t.getStartPosition()+ "</td>");
			   out.print("<td>" + t.getStartDate()+ "</td>");
			   out.print("<td>" + t.getEndPosition()+ "</td>");
			   out.print("<td>" + t.getEndDate()+ "</td>");
			   out.print("<td>" + Double.toString(t.getKm())+ "</td>");   
	   }
		   allTransitsFromVehicle.clear();  
	   }
	   
	  
		%>
	</section>
	</main>
	</body>
</html>