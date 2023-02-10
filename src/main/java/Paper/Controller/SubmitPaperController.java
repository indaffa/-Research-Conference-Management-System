package Paper.Controller;

import java.io.IOException;
import Paper.Entity.*;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Author.DAO.*;
import Author.Entity.Author;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.*;

@WebServlet ("/SubmissionPaper")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class SubmitPaperController extends HttpServlet{
    String username = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        listAuthor(request, response);
        username = request.getParameter("username");
        
    }
 
    private void listAuthor(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Author dao = new Author();
 
        List<String> dropdown = dao.dropDownList();
        request.setAttribute("listAuthor", dropdown);
        RequestDispatcher dispatcher = request.getRequestDispatcher("Author_SubmissionPaper.jsp");
        dispatcher.forward(request, response);

    }
    
    @Override
    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException{
        
        // gets values of text fields
        String filename = request.getParameter("fileName");
        String co_author = request.getParameter("authors");
        
        HttpSession session = request.getSession();
        
        // obtains the upload file part in this multipart request
        Part filePart = request.getPart("paper");
        InputStream inputStream = filePart.getInputStream();// input stream of the upload file
         
        
        /*
        // check if filename/File is empty
        if(filename.isEmpty()) {
            session.setAttribute("haveEmptyField", "One of the field is empty");
            return;
        }
        else {
            
        }
        */
       
        
        ArrayList<String> authors = new ArrayList<String>();
        authors.add(co_author);
        authors.add(username);
        
        Paper paper = new Paper();
        boolean success = paper.createSubmission(filename, authors, inputStream);
       
        if(success){
            
            session.setAttribute("message", "Successfully submitted");
            response.sendRedirect("ShowMyPapers?username="+username);
        }
        
        else {
            session.setAttribute("message", "Submission Failed");
            response.sendRedirect("ShowMyPapers?username="+username);
            
        }
       
    }

}