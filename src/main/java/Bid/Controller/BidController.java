package Bid.Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bid.Entity.Bid;



@WebServlet ("/bidPaper")
public class BidController extends HttpServlet{
    int paperid = 0;
    int userid = 0;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        paperid = Integer.parseInt(request.getParameter("paperid"));
        userid = Integer.parseInt(request.getParameter("userid"));
        bidPaper(request, response);
    }
    
    private void bidPaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Bid bid = new Bid();
        HttpSession session = request.getSession();
        boolean result = bid.bidPaper(paperid, userid);
        
        if(result){
            
            session.setAttribute("message", "Bid sent");
            response.sendRedirect("ShowAllPaperstoBid?userid="+String.valueOf(userid));
        }
        
        else {
            session.setAttribute("message", "Bid Failed to sent");
            response.sendRedirect("ShowAllPaperstoBid?userid="+String.valueOf(userid));
            
        }
 
    }
    
    
}