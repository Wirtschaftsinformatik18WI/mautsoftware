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
    <a class="ribbon" tabindex="-1" title="zur¸ck zur Startseite!" href="Start.jsp">
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
<<<<<<< HEAD
		 <br/>
		 <form action="LoginCheck.jsp" method="post">
		 <br/>
		 <label>E-Mail:</label><br/><input type="text" name="username">
		 <br/>
		 <br/>
		 <label>Passwort:</label>
		 <br/>
		 <input type="password" name="password">
		 <br/>
		 <br/>
		 <input type="submit" value="Login">
		 </form> 
		  
=======
		 <center>
		 <form action="LoginCheck.jsp" method="post"><br/>
		 E-Mail:<input type="text" name="username"><br/>
		 Passwort:<input type="password" name="password"> <br/>
		 <input type="submit" value="Login">
		  </form> 
		  </center>
		  <a href="RegistrationPage.jsp">Registrieren</a></li>
>>>>>>> refs/remotes/origin/master
	</section>
	
<<<<<<< HEAD
	<section>
	<h1>Registrieren</h1>
	<br/>
	<form> 
				<br/>
				<label for="name">Name: </label><br/><input id="name" name="name">  
				<br/>
				<br/>
				<label for="surname">Nachname: </label><br/><input id="surname" name="surname">  
				<br/>
				<br/>
				<label for="street">Straﬂe: </label><br/><input id="street" name="street">  
				<br/>
				<br/>
				<label for="hnumber">Hausnummer: </label><br/><input id="hnumber" name="hnumber">  
				<br/>
				<br/>
				<label for="postcode">Postleitzahl: </label><br/><input id="postcode" name="postcode">  
				<br/>
				<br/>
				<label for="city">Ort: </label><br/><input id="city" name="city">  
				<br/>
				<br/>
				<label for="telephone">Telefon: </label><br/><input id="telephone" name="telephone">
				<br/>
				<br/>
				<label for="email">E-Mail: </label><br/><input id="email" name="email"> 
				<br/>
				<br/>
				<label for="iscompany">Gewerblich: </label><br/><input id="iscompany" name="iscompany" type="checkbox"> 
				<br/>
				<br/>
				<label for="password">Passwort: </label><br/><input id="password" name="password" type="password">  
				<br/>
				<br/>
				<button id="submitbutton"  onclick="submitFunction(event)"> Registrieren  </button>  <br/>
			</form>
	</section>
=======
>>>>>>> refs/remotes/origin/master
	</main>
</body>
</html>