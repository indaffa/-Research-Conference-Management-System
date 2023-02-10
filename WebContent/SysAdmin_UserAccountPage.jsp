<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file= "include/header.jsp" %>
</head>


<body>
	<%@include file = "include/navbar.jsp" %>

	<div class="main">
	<br/>
	<div align="center">
	<h1>All User Account</h1>
	<br/>

	<form action = "searchUserAccount" method = "post">
		<label>Search </label>
		<input name = "searchbox" id = "searchbox" type = "text" placeholder = "search by username" > 
		<button type="submit">Find</button>
	</foohrm>
	</div>


	<table style="width:80%;height:80%;">
		<thead>
			<tr style="text-align: center">
				<th> Username </th>
				<th> Password</th>
				<th> Role </th>
				<th colspan=2> Edit or Read</th>
				

			</tr>

		</thead>
		<tbody style="text-align: center">
			<c:forEach var ="user" items = "${userAccountList}">
				<c:if test = "${user.ID != sessionScope.id || fn:toLowerCase(user.profileName) != fn:toLowerCase(sessionScope.profileName)}" > 
				<tr>
					<td>
						<c:out value="${user.username}" />
					</td>
					<td>
						<c:out value="${user.password}" />
					</td>
					
					<td>
						<c:out value="${user.profileName}" />
					</td>
					<td>
						<a href="updateUserAccount?cusername=<c:out value = '${ user.username}'  />&cprofilename=<c:out value = '${user.profileName }'/> " >
						Update
						</a>
					</td>
					<td>
						<a href="readAccountForm?username=<c:out value = '${user.username}'  />&profilename=<c:out value = '${user.profileName}'/> " >
						Read
						</a>
					</td>
				</tr>
				</c:if>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
</body>
</html>