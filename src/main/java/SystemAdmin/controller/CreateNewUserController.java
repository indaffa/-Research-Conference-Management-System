package SystemAdmin.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Author.Entity.Author;
import ConferenceChair.Entity.ConferenceChair;
import Reviewer.Entity.Reviewer;
import SystemAdmin.entity.SystemAdmin;
import general.Entity.User;
import SystemAdmin.entity.*;

@WebServlet (urlPatterns = {"/createNewUserForm", "/createNewUser"})
public class CreateNewUserController extends HttpServlet{
	protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
	    createNewUserForm(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		try {
		    createNewUser(request, response);
			
		}catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void createNewUserForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		SystemAdmin temp = new SystemAdmin();
	    List<User> userProf = temp.viewAllProfile();
		request.setAttribute("userProfileList", userProf);
		RequestDispatcher dis = request.getRequestDispatcher("SysAdmin_UserAccountForm.jsp");
		dis.forward(request, response);
	}
	
	protected void createNewUser(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException{
	    String username = request.getParameter("username");
		String password = request.getParameter("password");
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String profilename = request.getParameter("profilename");
		
		SystemAdmin temp = new SystemAdmin();
		
//		
		HttpSession session = request.getSession();
		
		if (temp.createNewUser(username, password, name, email, profilename)) {
		    session.setAttribute("message", "Successfully create a User");
            response.sendRedirect("viewUserAccount");
		}else {
            session.setAttribute("message", "Fail to create User ");
            response.sendRedirect("createNewUserForm");
		}
		
	}
}
