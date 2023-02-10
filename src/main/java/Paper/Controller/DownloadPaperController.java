package Paper.Controller;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import Paper.Entity.*;


@WebServlet ("/downloadPaper")
@MultipartConfig(maxFileSize = 16177215)    // upload file's size up to 16MB
public class DownloadPaperController extends HttpServlet{
    int id = 0;
    String username = "";
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        id = Integer.parseInt(request.getParameter("paperid"));
        username = request.getParameter("username");
        downloadPaper(request, response);
    }
    
    private void downloadPaper(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
     
      
 
        Paper paper = new Paper().downloadPaper(id);
        ServletContext context = getServletContext();
        
        // sets MIME type for the file download
        String mimeType = context.getMimeType(paper.getPapername());
        if (mimeType == null) {        
            mimeType = "application/octet-stream";
        }              
        // set content properties and header attributes for the response
        response.setContentType("text/plain");
        String headerKey = "Content-Disposition";
        String headerValue = String.format("attachment; filename=\"%s\"", paper.getPapername()+".pdf");
        response.setHeader(headerKey, headerValue);
        
     // writes the file to the client
        OutputStream outStream = response.getOutputStream();
         
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
         
        while ((bytesRead = paper.getInputstream().read(buffer)) != -1) {
            outStream.write(buffer, 0, bytesRead);
        }
         
        paper.getInputstream().close();
        outStream.close();             

    }
}