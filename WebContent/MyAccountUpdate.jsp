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
background-opacity: 0.1;
}

</style>
<body>
	<%@include file = "include/navbar.jsp" %>

	<div class="main">
	<form action= "updateMyAccount?id=<c:out value ='${sessionScope.id }'/>&profileName=
	<c:out value='${sessionScope.profileName}'/>" method = "post">
	
		<input name="oldusername" id = "oldusername" value =<c:out value = '${myAccount.username} }'/> hidden>
		<label> Username</label>
		<input class="input-group" id = "myusername" name="myusername" value =<c:out value = '${myAccount.username} }'/> required >


		<br>
		<label> Fullname</label>
		<input class="input-group" id = "myname" name="myname" value =<c:out value = '${myAccount.fullname} }'/> required>
		
		
		<br>
		<label> Password</label>
		<input class="input-group" id = "mypassword" name="mypassword" value =<c:out value = '${myAccount.password} }'/> required>
		
		<br>
		<label> Email</label>
		<input class="input-group" id = "myemail"  name="myemail" value =<c:out value = '${myAccount.email} }'/> required>
		
		<br>
		
		<c:if test="${sessionScope.profileName == 'reviewer'}">
			<label> Max Number of Papers</label>
			<input class="input-group" id = "maxPaper"  name="maxPaper" value =<c:out value = '${myAccount.max_no_papers} }'/> required>
		</c:if>
		
		<br>
		<button type = "submit">Submit</button>
	</form>
	</div>
</body>
</html>