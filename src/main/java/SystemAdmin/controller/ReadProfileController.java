package SystemAdmin.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SystemAdmin.entity.SystemAdmin;
import general.Entity.User;

@WebServlet("/ReadProfileController")
public class ReadProfileController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    ReadProfile(request, response);
	}
	
	
	protected void ReadProfile(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try {
            String profileName = request.getParameter("profilename");
            SystemAdmin temp = new SystemAdmin();
            User tempUser = temp.getProfile(profileName);
            request.setAttribute("userProfile", tempUser);
            RequestDispatcher dis = request.getRequestDispatcher("/SysAdmin_UserProfileRead.jsp");
            dis.forward(request, response);
        }catch (Exception ex) {
            log(ex.toString());
        }
        
    }

}
