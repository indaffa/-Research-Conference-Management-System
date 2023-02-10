package Paper.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Paper.Entity.Paper;


@WebServlet ("/UpdatePaperStatus")
public class UpdatePaperStatusController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UpdatePaperStatus (request, response);
    }
    
    protected void UpdatePaperStatus(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paper tempPaper = new Paper();
        
        String paperStatus = request.getParameter("ratePaper");
        int paperID = Integer.parseInt(request.getParameter("paperID"));
        HttpSession session = request.getSession();
        
        if (tempPaper.UpdatePaperStatus(paperID, paperStatus)) {
            session.setAttribute("message", "Successfully Update the Paper");  
        }else {
            session.setAttribute("message", "Fail Update the Paper");   
        }
        response.sendRedirect("ViewPaperByStatus?status=All");
        
    }
}
