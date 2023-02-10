
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

table, td, th, tr{
	border: 2px solid black;
}

table{
	margin-left: auto;
	margin-right: auto;
	background-color: white;
}

.main{
	align: center;
	width: 60%;
	background-color: white;
	margin: auto;
	min-height: 100%;
	opacity: 0.9;
}

</style>
<body>
	<%@include file = "include/navbar.jsp" %>


	<div class="main">
	<br/>
	<h1 align="center">All User Profile</h1>
	<br/>

	<table>
		<thead>
			<tr>
				<th>Profile</th>
				<th colspan = 2>Edit And Read </th>
				

			</tr>

		</thead>
		<tbody>
			<c:forEach var ="user" items = "${userProfileList}">
				<tr>

					<td>
						<c:out value="${user.profileName}" />
					</td>
					
					<td>
						<a href="<%=request.getContextPath()%>/updateUserProfile?profilename=<c:out value = '${ user.profileName}'  />" >
						Edit
						</a>
					</td>
										<td>
						<a href="<%=request.getContextPath()%>/ReadProfileController?profilename=<c:out value = '${ user.profileName}'  />" >
						Read
						</a>
					</td>
					

				</tr>
			</c:forEach>
		</tbody>
	</table>
	</div>
		
</body>
</html>