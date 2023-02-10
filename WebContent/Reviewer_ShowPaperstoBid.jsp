<!DOCTYPE html>
<html>
<head>
	<%@include file= "include/header.jsp" %>

</head>
<style>
table, td, th, tr{
	border: 2px solid black;
}

table{
	margin-left: auto;
	margin-right: auto;
	background-color: white;
	td  : {text-align: center;};

}

.main{
	align: center;
	width: 80%;
	background-color: white;
	margin: auto;
	min-height: 100%;
	opacity: 0.9;
}

body{
background-image: linear-gradient(rgba(0,0,0,0.6),rgba(0,0,0,0.6)), url("img/background.jpg");
height: 100vh;
-webkit-background-size: cover;
background-size: cover;
background-position: center center;
}

</style>
<body>
	<%@include file = "include/navbar.jsp" %>
	
		<div class="main">
        <h1 align="center">Papers Available</h1>
         <div align="center">
        <table border="1" cellpadding="5">
            <tr>
            	<th>ID</th>
                <th>PaperName</th>
                <th>Author</th>
                <th>Co-Author</th>
                <th>Bid</th>
                <th>Download</th>
                
            </tr>
            <c:forEach var="paper" items="${listPaper}">
                <tr>
                	<td><c:out value="${paper.id}" /></td>
                    <td><c:out value="${paper.papername}" /></td>
                    <td><c:out value="${paper.author}" /></td>
                    <td><c:out value="${paper.coauthor}" /></td>
            		
                    <td>
                     <a href="bidPaper?paperid=<c:out value = '${paper.id}'/>&userid=<c:out value = '${sessionScope.id}'/>">
						Bid
						</a>
					</td>
					
                    <td style="width:10%">
                     <a href="downloadPaper?paperid=<c:out value = '${paper.id}'/>&userid=<c:out value = '${sessionScope.id}'/>">
						Download
						</a>
					</td>
                </tr>
            </c:forEach>
        </table>
    </div>
    </div>

</body>
</html>