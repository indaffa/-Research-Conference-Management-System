package Paper.Controller;
import java.io.IOException;

import java.sql.SQLException;
import java.util.ArrayList;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Paper.Entity.*;


@WebServlet(urlPatterns="/ShowAllPaperstoBid")
public class ShowAllPaperstoBidController extends HttpServlet{
    int reviewer_id;
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        reviewer_id = Integer.parseInt(request.getParameter("userid"));
        listPaper(request,response);
    }
    
    
    private void listPaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paper paper = new Paper();
 
        ArrayList<Paper> listPaper = paper.showAllPaperstoBid(reviewer_id);
        request.setAttribute("listPaper", listPaper);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Reviewer_ShowPaperstoBid.jsp");
        dispatcher.forward(request, response);
 

    }
}