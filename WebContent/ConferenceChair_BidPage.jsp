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

table, td, th, tr{
	border: 2px solid black;
}

table{
	margin-left: auto;
	margin-right: auto;
	background-color: white;
	td  : {text-align: center;};

}


</style>
<body>
	<%@include file = "include/navbar.jsp" %>
	
	<div class="main">
	<h1 align="center">Current Bid from reviewer</h1>
	
	<div align="center">
	<a href = "<%=request.getContextPath()%>/autoAllocatePaper" onclick = "return confirmUserAction('Auto Allocate?');">
		<button >
			Auto Allocate
		</button>
	</a>
	</div>
	
	<table>
		<thead>
			<tr>
				<th>BidID</th>
				<th>PaperName</th>
				<th>ReviewerName</th>
				<th>AuthorName </th>
				<th>CoAuthor </th>
				<th> </th>
			</tr>

		</thead>
		<tbody>
			<c:forEach var ="bidInfo" items = "${bidInfo}">
			<c:if test= "${bidInfo.allocateStatus != 'complete' }"> 
				<tr>
					
					<td>
						<c:out value="${bidInfo.bid_id}" />
					</td>
					<td>
						<c:out value="${bidInfo.paper.papername}" />
					</td>
					
					<td>
						<c:out value="${bidInfo.reviewer.fullname}" />
					</td>
					
					<td>
						<c:out value="${bidInfo.paper.author}" />
					</td>
					<td > 	
						<c:if test = "${bidInfo.paper.coauthor != null }"> 
							<c:out value="${bidInfo.paper.coauthor}" />
		
						</c:if>
						<c:if test = "${bidInfo.paper.coauthor == null }"> 
							Null
						</c:if>
						
					</td>
					
					<td>
					<c:if test = "${bidInfo.allocateStatus == 'unallocated'}">
						<a href="allocatePaper?bidID=<c:out value = '${bidInfo.bid_id}'  />" >
						Allocate
						</a>
						<a onclick = "return confirmUserAction('Are you Sure');" href="RejectBid?bidID=<c:out value = '${bidInfo.bid_id}'  />" >
						Reject
						</a>
					 </c:if>
					 
					 <c:if test = "${bidInfo.allocateStatus == 'allocated'}" >
						<a type= "submit" onclick = "return confirmUserAction('Are you Sure');" href="unallocatePaper?bidID=<c:out value = '${bidInfo.bid_id}'  />" >
						UnAllocate
						</a>
					 </c:if>
					
						
					</td>
	
				</tr>
			</c:if>
			</c:forEach>
		</tbody>
	</table>
	</div>
	
</body>
</html>