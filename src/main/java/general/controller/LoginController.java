package general.controller;


import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SystemAdmin.entity.SystemAdmin;
import general.Entity.User;

import java.io.IOException;
import java.util.List;


@WebServlet ("/login")

public class LoginController extends HttpServlet{
	protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		loginUser(request, response);
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
//	    loginProfile(request, response);
//	    
//	}
	
//	protected void loginProfile(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        
//        try {
//            List<User> userProf = SystemAdmin.getAllProfile();
//            request.setAttribute("userProfileList", userProf);
//            RequestDispatcher dis = request.getRequestDispatcher("/login");
//            dis.forward(request, response);
//        }catch (Exception ex) {
//            log(ex.toString());
//        }
//        
//    }
	
	protected void loginUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		String username = request.getParameter("username");
		String password= request.getParameter("password");
		String profileName= request.getParameter("profile") ;
		
		User temp = new User();

		
		HttpSession session = request.getSession();
		
		if (temp.login(username, password ,profileName)) {
		    User tempT = temp.getMyInfo(username, profileName);
		    session.setAttribute("id", tempT.getID());
		    session.setAttribute("username", username);
		    session.setAttribute("profileName", profileName);
            
		    response.sendRedirect("HomePage.jsp");

		}else {
            session.setAttribute("message", "Login failed. Incorrect username or password.");
            response.sendRedirect("index.jsp");
        }	
	}
	
	
}
