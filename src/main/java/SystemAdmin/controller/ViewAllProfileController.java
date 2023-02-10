package SystemAdmin.controller;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SystemAdmin.entity.SystemAdmin;
import general.Entity.User;
import SystemAdmin.entity.*;
import java.io.IOException;

import java.util.List;


@WebServlet("/viewUserProfile")

public class ViewAllProfileController extends HttpServlet{
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			viewAllProfile(request, response);
	}
	
	
	protected void viewAllProfile(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		    SystemAdmin temp = new SystemAdmin();
			List<User> userProf = temp.viewAllProfile();
			request.setAttribute("userProfileList", userProf);
			RequestDispatcher dis = request.getRequestDispatcher("/SysAdmin_UserProfilePage.jsp");
			dis.forward(request, response);
		}catch (Exception ex) {
			log(ex.toString());
		}
		
	}
	
	
}
