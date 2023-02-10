package SystemAdmin.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import SystemAdmin.entity.SystemAdmin;
import general.Entity.User;

@WebServlet("/readAccountForm")
public class ReadUserController extends HttpServlet{
    protected void doGet (HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
        readUser (request, response);
    }
    
    protected void readUser(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            String username = request.getParameter("username");
            String profileName = request.getParameter("profilename");
            
            User temp = new SystemAdmin().readUser(username, profileName);
            request.setAttribute("userAccount", temp);
            RequestDispatcher dispatcher = request.getRequestDispatcher("SysAdmin_UserAccountRead.jsp");
            dispatcher.forward(request, response);
        
        }catch (Exception e) {
            throw new ServletException(e);
        }
        
        
        
    }
}
