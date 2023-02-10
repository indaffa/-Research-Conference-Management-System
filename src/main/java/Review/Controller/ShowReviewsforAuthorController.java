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


@WebServlet(urlPatterns="/ShowReviewsforAuthor")
public class ShowReviewsforAuthorController extends HttpServlet{
    
    int reviewer_id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reviewer_id = Integer.parseInt(request.getParameter("userid"));
        listReview(request,response);
    }
    
    
    private void listReview(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Review rev = new Review();
 
       
           
            ArrayList<Review> listReview = rev.showReviewsforAuthor(reviewer_id);
            request.setAttribute("listReview", listReview);
            RequestDispatcher dispatcher = request.getRequestDispatcher("ShowReviewsforAuthor.jsp");
            dispatcher.forward(request, response);
 
    }
    
    
    
}