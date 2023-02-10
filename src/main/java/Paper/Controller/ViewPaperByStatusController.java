package Paper.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Paper.Entity.Paper;

@WebServlet ("/ViewPaperByStatus")
public class ViewPaperByStatusController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws   ServletException, IOException {
        ViewPaperByStatus (request, response);
    }
    
    protected void ViewPaperByStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paper temp = new Paper();
        String Status = request.getParameter("status");
        ArrayList<Paper> allPaper = temp.getAllPapersByStatus(Status);
        request.setAttribute("paperInfo", allPaper);
        RequestDispatcher dis = request.getRequestDispatcher("/ConferenceChair_ViewPaper.jsp");
        dis.forward(request, response);
        
    }
}
