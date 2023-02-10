package Review.Controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bid.Entity.Bid;
import Review.Entity.Review;
import Reviewer.Entity.Reviewer;


@WebServlet("/autoAllocatePaper")
public class AutoAllocatePaperController extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AutoAllocatePaper (request, response);
    }
    
    
    protected void AutoAllocatePaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        Review tempReview = new Review();

        ArrayList<Bid> temp = new Bid().getBidThatUnallocated();
        for (int i = 0 ; i < temp.size(); i++) {
            if (tempReview.AllocatePaper(temp.get(i).getBid_id()) ) {
                new Bid().updateBidStatus(temp.get(i).getBid_id(), "allocated");
            }
        }
        
        session.setAttribute("message", "Successfully Auto Allocated");
        response.sendRedirect("conferenceBidPage");
        

    }
}
