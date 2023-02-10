package SystemAdmin.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import SystemAdmin.entity.*;
import general.Entity.User;

@WebServlet (urlPatterns="/updateUserProfile")
public class UpdateProfileController extends HttpServlet{
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        updateProfileForm(request, response);
    }
    
    protected void doPost (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        try {
            UpdateProfile(request, response);
        } catch (SQLException | IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    protected void updateProfileForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String profileName = request.getParameter("profilename");
        
        User temp = new SystemAdmin().getProfile(profileName);
        request.setAttribute("userProfile", temp);
        RequestDispatcher dis = request.getRequestDispatcher("SysAdmin_UserProfileForm.jsp");
        dis.forward(request, response);
    }
    
    protected void UpdateProfile(HttpServletRequest request, HttpServletResponse response)  throws SQLException, IOException {
        int profileID = Integer.parseInt(request.getParameter("id"));
        String profileName = request.getParameter("profilename");
        String description = request.getParameter("description");   
        
        SystemAdmin temp = new SystemAdmin();
        
        HttpSession session = request.getSession();
        
        if (temp.updateProfile(profileID, profileName, description)) {
            session.setAttribute("message", "Successfully Update User Account");
            response.sendRedirect("HomePage.jsp");
        }else {
            session.setAttribute("message", "Fail to Update User ");
            response.sendRedirect("HomePage.jsp");
        }
    }
    
    
}
