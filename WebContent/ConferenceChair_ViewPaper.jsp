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
	<h1 align="center"> <c:out value = "${param.status }"> </c:out> Paper</h1>
	
	<div align="center">
	<a href="<%=request.getContextPath()%>/ViewPaperByStatus?status=All" > <button> View All </button> </a>
	<a href="<%=request.getContextPath()%>/ViewPaperByStatus?status=Pending" > <button> View Pending </button> </a>
	<a href="<%=request.getContextPath()%>/ViewPaperByStatus?status=Accept"> <button> View Accept </button> </a>	
	<a href="<%=request.getContextPath()%>/ViewPaperByStatus?status=Reject"> <button> View Reject </button> </a>
	</div>
	<div align = center>
		<table border = 1 cellpadding = "10">
			<thead>
				<tr>
					<th>PaperID</th>
					<th>Paper Name</th>
					<th>Author Name </th>
					<th>Status </th>
					<th>Review Status </th>
			
				</tr>

			</thead>
		
			<tbody> 
				<c:forEach var ="paperInfo" items = "${paperInfo}">

				<tr>
					<td>
						<c:out value="${paperInfo.id}" />
					</td>
					<td>
						<c:out value="${paperInfo.papername}" />
					</td>
					<td>
						<c:out value="${paperInfo.author}" />
					</td>
					
					<td>
						<c:if test="${paperInfo.status == 'Accept' }" > 
							<p class="text-success"> <c:out value="${paperInfo.status}" /> </p> 
						</c:if>
						<c:if test="${paperInfo.status == 'Reject' }" > 
							<p class="text-danger"> <c:out value="${paperInfo.status}" /> </p> 
						</c:if>
						<c:if test="${paperInfo.status == 'Pending' }" > 
							<p class="text-dark"> <c:out value="${paperInfo.status}" /> </p> 
						</c:if>
							
					</td>
					<td>
						<c:if test="${paperInfo.status == 'Pending'}"> 
							<a href="<%=request.getContextPath()%>/GetPaperReviewAndRating?paperID=<c:out value = '${paperInfo.id}'  />
							&currentStatus=<c:out value = '${paperInfo.status}'/>" >
							Accept/Reject Paper
						</a>
						</c:if>
						<c:if test="${paperInfo.status != 'Pending'}"> 
							<a href="<%=request.getContextPath()%>/GetPaperReviewAndRating?paperID=<c:out value = '${paperInfo.id}'  />
							&currentStatus=<c:out value = '${paperInfo.status}'/>" >
							Update Paper Status
						</a>
						</c:if>
					
					</td>
				
				</tr>
			
			
			</c:forEach>
		
		
		</tbody>
	</table>
</div>
</div>
</body>
</html>