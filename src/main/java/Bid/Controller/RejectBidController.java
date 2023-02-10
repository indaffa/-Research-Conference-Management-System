package Bid.Controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bid.Entity.Bid;

@WebServlet("/RejectBid")
public class RejectBidController extends HttpServlet{
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        rejectBid(request, response);
    }
    
    
    protected void rejectBid(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int bidID = Integer.parseInt(request.getParameter("bidID"))  ;
        Bid temp = new Bid();
        
        HttpSession session = request.getSession();

        if (temp.RejectBid(bidID)) {
            session.setAttribute("message", "Successfully Reject the bid");
            response.sendRedirect("conferenceBidPage");
        }else {
            session.setAttribute("message", "Successfully Reject the bid");
            response.sendRedirect("conferenceBidPage");
        }
        
        
    }
}
