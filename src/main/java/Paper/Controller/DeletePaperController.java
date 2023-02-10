package Paper.Controller;
import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Paper.Entity.*;


@WebServlet ("/deletePaper")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class DeletePaperController extends HttpServlet{
    int id = 0;
    String username = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("paperid"));
        username = request.getParameter("username");
        deletePaper(request, response);
    }
    
    private void deletePaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Paper paper = new Paper();
        HttpSession session = request.getSession();
 
       
            
            boolean result = paper.deletePaper(id);
            if(result){
                
                session.setAttribute("message", "Successfully Deleted");
                response.sendRedirect("ShowMyPapers?username="+username);
            }
            
            else {
                session.setAttribute("message", "Problem deleting");
                response.sendRedirect("ShowMyPapers?username="+username);
                
            }
 
        
    }
}