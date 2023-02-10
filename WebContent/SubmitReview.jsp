<!DOCTYPE html>
<html>
<head>
<%@include file= "include/header.jsp" %>

</head>
<body>
	<%@include file = "include/navbar.jsp" %>
	<div class = "main">
		<h1 align= "center"> My Review </h1>
         <form method="post" action="submitReview">
          <table border="0">
          <tr>
               <td>Your Review </td>
                  <td><textarea style = "width:100%" id="review" name="review" rows="4" cols="50" required></textarea></td>
                 </tr>
                 <tr>
                    <td>Rating </td>
                    <td>
                    	<select name="rating" id="rating">
			  				<option value="-3">-3</option>
			  				<option value="-2">-2</option>
			  				<option value="-1">-1</option>
			  				<option value="0">0</option>  				
			  				<option value="1">1</option>
			  				<option value="2">2</option>
			 				 <option value="3">3</option>
					     </select>
			     	</td>
                </tr>
                <tr>
                <tr>
                    <td colspan="2">
                        <input class = "btn-block" type="submit" value="Submit">
                    </td>
                </tr>
			</table>
         </form>
         </div>

</body>
</html>