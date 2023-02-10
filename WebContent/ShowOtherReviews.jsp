<!DOCTYPE html>
<html>
<head>
<%@include file= "include/header.jsp" %>

</head>
</head>
<body>
	<%@include file = "include/navbar.jsp" %>

        <div align="center" class = "main">
        <h1>Other Reviewer's review</h1>
        <table border="1" cellpadding="5">
            <caption><h2>List of reviews</h2></caption>
            <tr>
            	<th>Reviewer</th>
                <th>Review</th>
                <th>Rating</th>
            </tr>
            <c:forEach var="review" items="${listReview}">
                <tr>
                	
                    <td><c:out value="${review.reviewername}" /></td>
                    <td><textarea style = "width:100%" id="review" name="review" rows="4" cols="50">${review.review}</textarea></td>
                    <td><c:out value="${review.rating}" /></td>
   
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>