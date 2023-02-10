<!DOCTYPE html>
<html>
<head>
<%@include file= "include/header.jsp" %>

</head>
<body>
	<%@include file = "include/navbar.jsp" %>

        <div align="center" class = "main">
        <h1 align = center>Your Papers</h1>
        <table border="1" cellpadding="5">
            <tr>
            	<th>ID</th>
                <th>PaperName</th>
                <th>Author</th>
                <th>Co-Author</th>
                <th>Review</th>
                
            </tr>
            <c:forEach var="paper" items="${listPaper}">
                <tr>
                	<td><c:out value="${paper.id}" /></td>
                    <td><c:out value="${paper.papername}" /></td>
                    <td><c:out value="${paper.author}" /></td>
                    <td><c:out value="${paper.coauthor}" /></td>
            		
                    <td>
                     <a href="submitReview?paperid=<c:out value = '${paper.id}'/>&userid=<c:out value = '${sessionScope.id}'/> " >
						Review
						</a>
					</td>
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>