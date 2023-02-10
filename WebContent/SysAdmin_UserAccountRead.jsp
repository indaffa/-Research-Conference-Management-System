
<!DOCTYPE html>
<html>
<head>
<%@include file= "include/header.jsp" %>
</head>
<style>
.main{
	align: center;
	width: 60%;
	background-color: white;
	margin: auto;
	min-height: 100%;
	opacity: 0.9;
	padding: 30px;
}

body{
background-image: linear-gradient(rgba(0,0,0,0.6),rgba(0,0,0,0.6)), url("img/background.jpg");
height: 100vh;
-webkit-background-size: cover;
background-size: cover;
background-position: center center;
}

</style>
<body>
	<%@include file = "include/navbar.jsp" %>

	
	<div class="main">
	<br/>
		<label>Fullname</label>
		<input type="text" name="name" id="name" value="<c:out value='${userAccount.fullname}' />" readonly>
		
		<br/>
		
		<label>Username</label>
		<input type="text" name="username" id="username" value="<c:out value='${userAccount.username}' />" readonly>
		
		
		<br/>
		
		
		<label>E-mail</label>
		<input type="text" name="email" id="email" value="<c:out value='${userAccount.email}' />" readonly>

		<br/>
		
		<br/>
		<label>Password</label>
		<input type="password" name="password" id="password" value="<c:out value='${userAccount.password}' />" readonly >
		
		<br/>
		
		<label for = "profile">Current Profile: </label>
		<input type="text" name="prof" id="prof" value="<c:out value='${userAccount.profileName}' />"  readonly>
		
		
		
       
       <br/>
       </div>
		
</body>
</html>