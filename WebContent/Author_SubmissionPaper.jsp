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
        <h1>Upload your Paper</h1>
        <form method="post" action="SubmissionPaper" enctype="multipart/form-data">
            <table border="0">
                <tr>
                    <td>File Name: </td>
                    <td><input type="text" name="fileName" size="50" required/></td>
                </tr>
                <tr>
                    <td>File: </td>
                    <td><input type="file" name="paper" size="50" required/></td>
                </tr>
                <tr>
                	<td>Add Co-Author: </td>
                	<td >
                		 <select name="authors">
        					<c:forEach items="${listAuthor}" var="author">
            				<option value="${author}">${author}</option>
        					</c:forEach>
    						</select>
    					
                	</td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Upload">
                    </td>
                </tr>
            </table>
        </form>
        </div>

</body>
</html>