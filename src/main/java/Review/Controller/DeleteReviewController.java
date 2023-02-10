package Review.Controller;
import java.io.IOException;


import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bid.Entity.Bid;
import Review.Entity.Review;


@WebServlet ("/deleteReview")

public class DeleteReviewController extends HttpServlet{
    int revid = 0;
    String userid = "";
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        revid = Integer.parseInt(request.getParameter("reviewid"));
        userid = request.getParameter("userid");
        deleteReview(request, response);
    }
    
    private void deleteReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Review rev = new Review();
        HttpSession session = request.getSession();
        
        boolean result = rev.deleteReview(revid);
        if(result){
            rev = rev.getReviewInfoByID(revid);
            new Bid().updateBidStatus(rev.getBidfk().getBid_id(), "allocated");
            session.setAttribute("message", "Successfully Deleted");
            response.sendRedirect("ShowmyReviews?userid="+userid);
        }
        
        else {
            session.setAttribute("message", "Problem deleting");
            response.sendRedirect("ShowmyReviews?userid="+userid);
            
        }
    }
}