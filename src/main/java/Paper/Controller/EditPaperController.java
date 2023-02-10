package Paper.Controller;
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

import Author.Entity.Author;
import Paper.Entity.*;


@WebServlet ("/EditPaper")
public class EditPaperController extends HttpServlet{
    int id = 0;
    String username = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("paperid"));
        username = request.getParameter("username");
        listAuthor(request, response);
    }
    
    
    private void listAuthor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Author author = new Author();
        Paper paper = new Paper();
 
            List<String> dropdown = author.dropDownList();
            Paper newpaper = paper.getpaperInfo(id, username);
            request.setAttribute("listAuthor", dropdown);
            request.setAttribute("paperinfo", newpaper);
            RequestDispatcher dispatcher = request.getRequestDispatcher("Author_EditPaper.jsp");
            dispatcher.forward(request, response);
 
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paper paper = new Paper();
        HttpSession session = request.getSession();
        // gets values of text fields
        String filename = request.getParameter("fileName");
        String co_author = request.getParameter("authors");
        ArrayList<String> authors = new ArrayList<String>();
        authors.add(co_author);
        authors.add(username);
        
        boolean result = paper.editPaper(id, filename, authors);
        if(result){
            
            session.setAttribute("message", "Successfully Edited");
            response.sendRedirect("ShowMyPapers?username="+username);
        }
        
        else {
            session.setAttribute("message", "Problem Editing");
            response.sendRedirect("ShowMyPapers?username="+username);
            
        }
 
    }
}