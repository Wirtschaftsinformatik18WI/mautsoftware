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
    <a class="ribbon" tabindex="-1" title="zur¸ck zur Startseite!" href="index.html">
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
	<h1>Registrieren</h1>
	</br>
 <center>
		 <form action="Registration.jsp" method="post"><br/>
		Name:<input type="text" name="name"><br/>
		Nachname:<input type="text" name="surname"> <br/>
		Straﬂe:<input type="text" name="street"> <br/>
		Hausnummer:<input type="text" name="hnumber"> <br/>
		Postleitzahl:<input type="text" name="postcode"> <br/>
		Ort:<input type="text" name="city"> <br/>
		Land:<input type="text" name="country"> <br/>
		Telefon:<input type="text" name="telephone"> <br/>
		Gewerbe?:<input type="checkbox" name="iscompany"> <br/>
		E-Mail:<input type="text" name="email"> <br/>
		Passwort:<input type="password" name="password"> <br/>
		 <input type="submit" value="Registrieren">
		  </form> 
		  </center>
	</section>
	</main>
</body>
</html>