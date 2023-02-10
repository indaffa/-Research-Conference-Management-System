package Review.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bid.Entity.Bid;
import Review.Entity.Review;
import Reviewer.Entity.Reviewer;

@WebServlet("/allocatePaper")
public class AllocatePaperController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AllocatePaper (request, response);
    }
    
    protected void AllocatePaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        Review tempReview = new Review();
        int bidID = Integer.parseInt(request.getParameter("bidID"));
        
        Bid temp = new Bid();

        if (tempReview.AllocatePaper(bidID)) {
            temp.updateBidStatus(bidID, "allocated");
            session.setAttribute("message", "Successfully Allocated");
            
        }else {
            session.setAttribute("message", "Reviewer have max paper already");
            
        }
        
        response.sendRedirect("conferenceBidPage");
        
        
    }
}
