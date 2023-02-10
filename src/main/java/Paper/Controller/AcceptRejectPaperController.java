package Paper.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Paper.Entity.Paper;


@WebServlet ("/AcceptRejectPaper")
public class AcceptRejectPaperController extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AcceptRejectPaper (request, response);
    }
    
    protected void AcceptRejectPaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paper tempPaper = new Paper();
        
        String paperStatus = request.getParameter("ratePaper");
        int paperID = Integer.parseInt(request.getParameter("paperID"));
        HttpSession session = request.getSession();
        
        if (paperStatus.equalsIgnoreCase("Accept")) {
            if (tempPaper.AcceptPaper(paperID)) 
                session.setAttribute("message", "Successfully Accept the Paper");                    
            else
                session.setAttribute("message", "Fail to Rate the Paper");
            
        }else if (paperStatus.equalsIgnoreCase("Reject")) {
            if ( tempPaper.RejectPaper(paperID))
                session.setAttribute("message", "Successfully Reject the Paper");
            else
                session.setAttribute("message", "Fail to Rate the Paper");
        }
        
        response.sendRedirect("ViewPaperByStatus?status=All");
        
    }
}
