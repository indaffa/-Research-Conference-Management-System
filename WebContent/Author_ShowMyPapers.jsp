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
         <h1 align="center">Your Papers</h1>
        <table border="1" cellpadding="5" align="center">
            <tr>
            	<th>ID</th>
                <th>PaperName</th>
                <th>Status</th>
                <th>Author</th>
                <th>Co-Author</th>
            </tr>
            <c:forEach var="paper" items="${listPaper}">
                <tr>
                	<td><c:out value="${paper.id}" /></td>
                    <td><c:out value="${paper.papername}" /></td>
                    <td><c:out value="${paper.status}"/></td>
                    <td><c:out value="${paper.author}"/></td>
                    <td><c:out value="${paper.coauthor}"/></td>
            		
                    <td style="width:10%">
                    <c:choose>
    					<c:when test = "${paper.authorusername == sessionScope.username}">
        					<a href="EditPaper?paperid=<c:out value = '${paper.id}'/>&username=<c:out value = '${sessionScope.username}'/>">
						Edit
						</a>
        					
						</c:when>    
    					<c:otherwise>
        					
    					</c:otherwise>
					</c:choose>
        
					</td>
					<td style="width:10%">
					 <c:choose>
    					<c:when test = "${paper.authorusername == sessionScope.username}">
						<a href="deletePaper?paperid=<c:out value = '${paper.id}'/>&username=<c:out value = '${sessionScope.username}'/> " >
						Delete
						</a>
						</c:when>    
    					<c:otherwise>
        					
    					</c:otherwise>
					</c:choose>
					</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>