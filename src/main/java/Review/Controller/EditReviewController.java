package Review.Controller;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import Review.Entity.Review;


@WebServlet ("/editReview")
public class EditReviewController extends HttpServlet{
    int reviewid = 0;
    String userid = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reviewid = Integer.parseInt(request.getParameter("reviewid"));
        userid = request.getParameter("userid");
        Review rev = new Review().getInfoforEdit(reviewid);
        request.setAttribute("reviewinfo", rev);
        RequestDispatcher dispatcher = request.getRequestDispatcher("EditReview.jsp");
        dispatcher.forward(request, response);
    }
    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Review rev = new Review();
        HttpSession session = request.getSession();
        // gets values of text fields
        String content = request.getParameter("review");
        int rating = Integer.parseInt(request.getParameter("rating"));

        
       boolean success = rev.editReview(reviewid,content,rating );
       if(success){
           
           session.setAttribute("message", "Successfully Edited");
           response.sendRedirect("ShowmyReviews?userid="+userid);
       }
       
       else {
           session.setAttribute("message", "Problem Editing");
           response.sendRedirect("ShowmyReviews?userid="+userid);
           
       }
   
    }
}