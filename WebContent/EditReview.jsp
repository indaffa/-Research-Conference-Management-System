<!DOCTYPE html>
<html>
<head>
<%@include file= "include/header.jsp" %>

</head>
<body>
	<%@include file = "include/navbar.jsp" %>
	<div class = "main">
	<h1 align= center> Edit my Review</h1>
         <form method="post" action="editReview">
          <table border="0">
          <tr>
               <td>Your Review </td>
                  <td colspan = 2><textarea style = "width: 100%" id="review" name="review" rows="4" cols="50" >${reviewinfo.review}</textarea></td>
                 </tr>
                 <tr>
                    <td>Rating </td>
                    <td><input type="text" value="old Rating : <c:out value='${reviewinfo.rating}'/>"></td>
                    <td>
                    	<select name="rating" id="rating">
			  				<option value="-3">3	</option>
			  				<option value="-2">-2	</option>
			  				<option value="-1">-1	</option>
			 				<option value="0">0		</option>
 				  			<option value="1">1		</option>
							<option value="2">2		</option>
   				  			<option value="3">3		</option>
			     		</select>
		     		</td>
                </tr>
                <tr>
                <tr>
                    <td colspan="3">
                        <input class = "btn-block" type="submit" value="Submit">
                    </td>
                </tr>
			</table>
         </form>
		</div>
</body>
</html>