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
	<link rel="icon" type="image/gif" href="Mauticon.png">
</head>
<body>
	<header>
    <a class="ribbon" tabindex="-1" title="zur�ck zur Startseite!" href="Start.jsp">
	   <img src="logotransparent.png" alt="Logo" style="width:120px;height:100px;float:left;"> 
	   <h1 id="headline">Mautilus 18</h1>
       <p>Mercurius IT GmbH</span></p>
    </a>  
	</header>
	
	<main>
	
		<nav id="navigation">
		<ul>
			<li><a href="Start.jsp">Home</a></li>
		<li><a aria-current="page" href="LoginPage.jsp">Login</a></li>
		<li><a href="ProjectIntroduction.html">Projektvorstellung</a></li>
		<li><a href="Statistics.jsp">Statistik</a></li>
		<li><a href="PrivacyPolicy.html">Privacy Policy</a></li>
		<li><a href="Impressum.html">Impressum</a></li>
		</ul>
		</nav>
		
	<section>
		<h1>Login</h1>
		 <br/>
		 <form action="LoginCheck.jsp" method="post">
		 <br/>
		 <label>E-Mail:</label>
		 <br/>
		 <input type="text" name="username">
		 <br/>
		 <br/>
		 <label>Passwort:</label>
		 <br/>
		 <input type="password" name="password">
		 <br/>
		 <br/>
		 <input type="submit" value="Login">
		 </form> 
	</section>
	
	<section>
	<h1>Registrieren</h1>
	<br/>
	
		 <form action="Registration.jsp" method="post">
		 	<br/>
			<label for="name">Name: </label><br/><input type="text" name="name">
			<br/>
			<br/>
			<label for="surname">Nachname: </label><br/><input type="text" name="surname">
			<br/>
			<br/>
			<label for="street">Stra�e: </label><br/><input type="text" name="street">
			<br/>
			<br/>
			<label for="hnumber">Hausnummer: </label><br/><input type="text" name="hnumber">
			<br/>
			<br/>
			<label for="postcode">Postleitzahl: </label><br/><input type="text" name="postcode">
			<br/>
			<br/>
			<label for="city">Ort: </label><br/><input type="text" name="city">
			<br/>
			<br/>
			<label for="land">Land: </label><br/><input type="text" name="country">
			<br/>
			<br/>
			<label for="telephone">Telefon: </label><br/><input type="text" name="telephone">
			<br/>
			<br/>
			<label for="iscompany">Gewerblich: </label><br/><input type="checkbox" name="iscompany">
			<br/>
			<br/>
			<label for="email">E-Mail: </label><br/><input type="text" name="email">
			<br/>
			<br/>
			<label for="password">Passwort: </label><br/><input type="password" name="password">
			<br/>
			<br/>
		 	<input type="submit" value="Registrieren">
		 </form>
		  
	</section>
	</main>
</body>
</html>