<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
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
		<h1>Login</h1>
		 <center>
		 <form action="LoginCheck.jsp" method="post"><br/>
		 E-Mail:<input type="text" name="username"><br/>
		 Passwort:<input type="password" name="password"> <br/>
		 <input type="submit" value="Login">
		  </form> 
		  </center>
		  <a href="RegistrationPage.jsp">Registrieren</a></li>
	</section>
	
	</main>
</body>
</html>