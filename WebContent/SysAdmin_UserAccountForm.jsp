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
	<c:if test="${userAccount != null}">
	<%
   		String clientusername = request.getParameter("cusername").toString();
		String clientprofilename = request.getParameter("cprofilename").toString();
		
	%>
		<form id = "updateUserAccount" action = "updateUserAccount?cusername=<%=clientusername %>&cprofilename=<%=clientprofilename %>" method ="post" >
	</c:if>
	<c:if test="${userAccount == null}">
		<form id="createNewUser" action = "createNewUser" method="post"  >	
	</c:if>

	
		<label>Fullname</label>
		<input class="input-group" type="text" name="name" id="name" value="<c:out value='${userAccount.fullname}' />" required>
		<br/>
		<label>Username</label>
		<input class="input-group" type="text" name="username" id="username" value="<c:out value='${userAccount.username}' />" required>
		<br/>
		<label>E-mail</label>
		<input class="input-group" type="text" name="email" id="email" value="<c:out value='${userAccount.email}' />" required>

		<br/>
		
		<br/>
		<label>Password</label>
		<input class="input-group" type="password" name="password" id="password" value="<c:out value='${userAccount.password}' />" required>
		
		<br/>
		
		<label for = "profile">Choose Profile: </label>
		<select id = "profilename" name = "profilename" class="form-control" >
        	<c:forEach var="userProfile" items="${userProfileList}">
        		<option value ="<c:out value='${userProfile.profileName }' />" >
           			<c:out value = "${userProfile.profileName }" />
           		</options>
        	</c:forEach>
       	</select>
       	<br/>		
		<button type= "submit">Submit</button>
	</form>
	</div>
</body>
</html>