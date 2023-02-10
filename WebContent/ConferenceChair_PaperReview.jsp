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
	<c:if test="${PaperReview == null }" >
		<h1 align="center">Currently No Review yet</h1>
	</c:if>
	
	
	<c:if test="${PaperReview != null }" >
		<c:forEach var ="PaperReview" items = "${PaperReview}">
			<label >Reviewer Name </label>
			<input type= "text" id = "reviewerName" value ="<c:out value="${PaperReview.bidfk.reviewer.fullname}"/>" readonly>
			
			<br>
			
			<label >Reviewer Rating </label>
			<input type= "text" id = "reviewerRating" value ="<c:out value="${PaperReview.rating}"/>" readonly>
			
			<br>
			
			<label for="reviewerComment">Reviewer Comment</label>
			<br>
			<textarea class="form-control" rows="3" name="reviewerComment" id="reviewerComment"  readonly>
<c:out value="${PaperReview.review}"/>
			</textarea>
			<br/>
			
			<hr/>
		
		</c:forEach>
		
		
		<hr/>
		<c:if test ="${param.currentStatus == 'Pending' }"> 
			<form method = "post" action = "AcceptRejectPaper?paperID=<c:out value = '${paperID}'/>">
		
		</c:if>
		<c:if test ="${param.currentStatus != 'Pending' }"> 
			<form method = "post" action = "UpdatePaperStatus?paperID=<c:out value = '${paperID}'/>">
		
		</c:if>
		
			<label>Rate Paper </label>
			<br>
			<select id ="ratePaper"  name ="ratePaper">
				<option value = "Accept" >Accept</option>
				<option value = "Reject" >Reject</option>
			
			</select>
			
			<br/> <br/> <br/>
			
			<button id = "submitButton" type= "submit"> Rate Paper</button>
		
		</form>
				
	</c:if>
	</div>
	
	
	
	
</body>
</html>