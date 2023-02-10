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
        <div align="center" class="main">
   		<h1>Reviews</h1>
        <table border="1" cellpadding="5">
            <tr>
            	<th>Paper ID</th>
                <th>PaperName</th>
                <th>Review</th>
                <th>Rating</th>
                <th>Reviewer</th>
            </tr>
            <c:forEach var="review" items="${listReview}">
                <tr>
                	<td><c:out value="${review.paperid}" /></td>
                    <td><c:out value="${review.papername}" /></td>
                    <td><textarea id="review" name="review" rows="4" cols="50">${review.review}</textarea></td>
                    <td><c:out value="${review.rating}" /></td>
                    <td><c:out value="${review.reviewername}" /></td>
   
                </tr>
            </c:forEach>
        </table>
    </div>

</body>
</html>