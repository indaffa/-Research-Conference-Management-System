<html>
<head>
	<%@include file= "include/header.jsp" %>
</head>
<style>

.main{
	padding: 25px;
}


</style>
<body>

	<%@include file= "include/navbar.jsp" %>
	<div class="main">
	<form id="loginForm" action="login" method="post"  onsubmit="return loginValidationCheck();">
	     <h2>Login</h2>
	     <fieldset class="form-group">
	        <label>Username</label>
	        <input type="text" class="form-control" name="username" id="username">
	     </fieldset>
	     <fieldset class="form-group">
	        <label>Password</label>
	        <input type="password" class="form-control" name="password" id="password">
	     </fieldset>
	     <label for = "profile">Choose Profile: </label>
		 <select name = "profile" id = "profile" class="form-control"  >
			<option value = "systemadmin" >System Admin</option>
			<option value = "reviewer" >Reviewer</option>
			<option value = "conference" >Conference Chair</option>
			<option value = "author" >Author</option>
			
		 </select> 
		 <br/>
	     <div class="btn-toolbar pull-right">
	        <button type="submit" class="btn btn-outline-success my-2 my-sm-0 mr-3">Login</button>
	     </div>
	  </form>
	  </div>
</body>
</html>