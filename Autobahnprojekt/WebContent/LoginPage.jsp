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
		<h1>Login</h1>
		 <center>
		 <form action="LoginCheck.jsp" method="post"><br/>E-Mail:<input type="text" name="username">
		  <br/>Passwort:<input type="password" name="password"> <br/><input type="submit" value="Login">
		  </form> 
		  </center>
	</section>
	<section>
	<h1>Registrieren</h1>
	</br>
	<form> 
				<br/>
				<label for="name">Name: <input id="name" name="name"> </label> 
				<br/>
				<br/>
				<label for="surname">Nachname: <input id="surname" name="surname"> </label> 
				<br/>
				<br/>
				<label for="street">Straﬂe: <input id="street" name="street"> </label> 
				<br/>
				<br/>
				<label for="hnumber">Hausnummer: <input id="hnumber" name="hnumber"> </label> 
				<br/>
				<br/>
				<label for="postcode">Postleitzahl: <input id="postcode" name="postcode"> </label> 
				<br/>
				<br/>
				<label for="city">Ort: <input id="city" name="city"> </label> 
				<br/>
				<br/>
				<label for="telephone">Telefon: <input id="telephone" name="telephone"> </label> 
				<br/>
				<br/>
				<label for="email">E-Mail: <input id="email" name="email"> </label> 
				<br/>
				<br/>
				<label for="iscompany">Gewerblich: <input id="iscompany" name="iscompany" type="checkbox"> </label> 
				<br/>
				<br/>
				<label for="password">Passwort: <input id="password" name="password" type="password"> </label> 
				<br/>
				<br/>
				<button id="submitbutton"  onclick="submitFunction(event)"> Registrieren  </button>  <br/>
			</form>
	</section>
	</main>
	<script>
function submitFunction(event) { 
	let name=document.getElementById("firstname").value;
	let surname=document.getElementById("surname").value;
	let street=document.getElementById("street").value;
	let hnumber=document.getElementById("hnumber").value;
	let postcode=document.getElementById("postcode").value;
	let city=document.getElementById("city").value;
	let telephone=document.getElementById("telephone").value;
	let email=document.getElementById("email").value;
	let password=document.getElementById("password").value;
	let iscompany=document.getElementById("iscompany").value;
	
	
//Request:------------------------
	
	var xhttp = new XMLHttpRequest();
	 var url = "Registration.jsp?name=" + name + "&surname=" + surname + "&street=" + street +"&city=" + city +"&telephone=" + telephone +"&email=" + email+"&postcode="+ postcode +"&hnumber=" + hnumber+"&password=" + password+"&iscompany=" + iscompany ;
	 xhttp.open("POST", url, true);
	 xhttp.send(); 
	 
	}
	
</script>
</body>
</html>