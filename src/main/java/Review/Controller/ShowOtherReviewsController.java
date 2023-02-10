package Review.Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Review.Entity.*;


@WebServlet(urlPatterns="/ShowOtherReviews")
public class ShowOtherReviewsController extends HttpServlet{
    
    int paper_id;
    int user_id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        paper_id = Integer.parseInt(request.getParameter("paperid"));
        user_id = Integer.parseInt(request.getParameter("userid"));
        listReview(request,response);
    }
    
    
    private void listReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Review rev = new Review();
 
        
           
            ArrayList<Review> listReview = rev.showOtherReviews(paper_id, user_id);
            request.setAttribute("listReview", listReview);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ShowOtherReviews.jsp");
            dispatcher.forward(request, response);
 
     
    }
    
    
    
}