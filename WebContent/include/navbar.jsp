<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %> 
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
   <div class="container">
      <p class="navbar-brand"></p>
      <c:if test="${sessionScope.username != null}">
         <ul class="navbar-nav">
         	<li>
         		<a class="nav-link" href="<%=request.getContextPath()%>/HomePage.jsp">Home</a>
        	</li>
            <c:if test="${sessionScope.profileName == 'systemadmin'}">
			   
               <li>
                  <a class="nav-link" href="<%=request.getContextPath()%>/viewUserAccount">User Accounts</a>
               </li>
               <li>
                  <a class="nav-link" href="<%=request.getContextPath()%>/viewUserProfile">User Profiles</a>
               </li>
               <li>
                  <a class="nav-link" href="<%=request.getContextPath()%>/createNewUserForm">Add User Accounts</a>
               </li>
               <li>
                  <a class="nav-link" href="<%=request.getContextPath()%>/createNewUserProfileForm" >Add New User Profile</a>
               </li>
               
            </c:if>
            <c:if test="${sessionScope.profileName == 'conference'}">
               <li>
                  <a class="nav-link" href="<%=request.getContextPath()%>/conferenceBidPage">Current Bid</a>
               </li>
               
             	<li>
               	    <a class="nav-link" href="<%=request.getContextPath()%>/ViewPaperByStatus?status=All" > View Paper </a>
             	</li>
            </c:if>
            
            <c:if test="${sessionScope.profileName == 'reviewer'}">
               <li>
                  <a class="nav-link" href="PaperstoReview?userid=<c:out value = '${sessionScope.id}'/>">Papers to be Reviewed</a>
               </li>
               <li>
               	   <a class="nav-link" href="ShowmyReviews?userid=<c:out value = '${sessionScope.id}'/>"> Show my Reviews</a> 
               </li>
               <li>
                  <a class="nav-link" href="ShowAllPaperstoBid?userid=<c:out value = '${sessionScope.id}'/>">Bid Papers</a>
               </li>

            </c:if>
            <c:if test="${sessionScope.profileName == 'author'}">
               <li>
                  <a class="nav-link" href="SubmissionPaper?username=<c:out value = '${sessionScope.username}'/>">Create Submissions</a>
               </li>
               <li>
                  <a class="nav-link" href="ShowMyPapers?username=<c:out value = '${sessionScope.username}'/>">View My Papers</a>
               </li>
               <li>
                  <a class="nav-link" href="ShowReviewsforAuthor?userid=<c:out value = '${sessionScope.id}'/>">Show Reviews</a>
               </li>
            </c:if>
         </ul>
      </c:if>
      <ul class="navbar-nav ml-auto">
         <c:if test="${sessionScope.username == null}">
            

         </c:if>
         <c:if test="${sessionScope.username != null}">
            <li class="nav-item">
               <a class="nav-link disabled" href="">${profile}</a>
            </li>
            <li class="nav-item dropdown">
               <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">${username}  &nbsp; <i class="fas fa-user"> </i></a>
               <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                  	<a class="dropdown-item" href="<%=request.getContextPath()%>/updateMyAccount?username=
						<c:out value ='${sessionScope.username}'/>&profileName=<c:out value = '${sessionScope.profileName}'/>" class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink" class="btn btn-outline-success my-2 my-sm-0"> Update my Profile </a>
               		<a class="dropdown-item" id="logout_btn" href="<%=request.getContextPath()%>/logout" onclick="return logout2();" class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</a>
                  
               </div>
               <div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                 	 
               </div>
            </li>
         </c:if>
      </ul>
   </div>
</nav>