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

@WebServlet("/unallocatePaper")
public class UnAllocatePaperController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        UnAllocatePaper (request, response);
    }
    
    protected void UnAllocatePaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Review tempReview = new Review();
        int bidID = Integer.parseInt(request.getParameter("bidID"));
        
        Bid temp = new Bid();

        if (tempReview.UnAllocatePaper(bidID)) {
            temp.updateBidStatus(bidID, "unallocated");
            session.setAttribute("message", "Successfully UnAllocate");
            response.sendRedirect("conferenceBidPage");
        }else {
            session.setAttribute("message", "Fail to Unallocate");
            response.sendRedirect("conferenceBidPage");
        }
        
        
    }
}