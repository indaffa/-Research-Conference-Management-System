<!DOCTYPE html>
<html>
<head>
	<%@include file= "include/header.jsp" %>

</head>

<style>

body{
background-image: linear-gradient(rgba(0,0,0,0.6),rgba(0,0,0,0.6)), url("img/background.jpg");
height: 100vh;
-webkit-background-size: cover;
background-size: cover;
background-position: center center;
background-opacity: 0.1;
}

.main{
	align: center;
	width: 60%;
	background-color: white;
	margin: auto;
	min-height: 100%;
	opacity: 0.9;
	padding: 30px;
}

</style>

<body>
	<%@include file = "include/navbar.jsp" %>
	<div class="main">
	
		<label>Profile Name</label>
		<input type="text" name="profilename" id="profilename" value="<c:out value='${userProfile.profileName}' />">

		<br>
		
		<label for="description">Description</label>
		<br>
		<textarea class="form-control" rows="3" name="description" id="description" >
<c:out value='${userProfile.description}' />
		
		</textarea>
		<br/>
		
		
	</div>
</body>
</html>