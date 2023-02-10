package SystemAdmin.controller;

import javax.servlet.*;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import general.Entity.User;
import SystemAdmin.entity.*;

import java.io.IOException;
import java.util.List;


@WebServlet("/viewUserAccount")

public class ViewAllUserController extends HttpServlet{
	protected void doGet (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			viewAllUser(request, response);
	}
	
	
	protected void viewAllUser(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		try {
		    
		    SystemAdmin temp = new SystemAdmin();
			List<User> userAcc = temp.viewAllUser();
			request.setAttribute("userAccountList", userAcc);
			RequestDispatcher dis = request.getRequestDispatcher("/SysAdmin_UserAccountPage.jsp");
			dis.forward(request, response);
		}catch (Exception ex) {
			log(ex.toString());
		}
		
	}
	
	
}
