package Paper.DAO;

import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DbConnection;
import general.Entity.User;
import Paper.Entity.*;

public class PaperDAO{
    
    public boolean AcceptPaper(int paperID) {
        String RatePaper = "update paper set Status = 'Accept' where paper_id = ?";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(RatePaper))
        {
            
            preparedStatement.setInt(1, paperID);
            
            preparedStatement.executeUpdate();
            
            return true;
            
            
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public boolean RejectPaper(int paperID) {
        String RatePaper = "update paper set Status = 'Reject' where paper_id = ?";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(RatePaper))
        {
            
            preparedStatement.setInt(1, paperID);
            
            preparedStatement.executeUpdate();
            
            return true;
            
            
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public boolean UpdatePaperStatus(int paperID, String paperStatus) {
        String RatePaper = "update paper set Status = ? where paper_id = ?";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(RatePaper))
        {
            
            preparedStatement.setString(1, paperStatus);
            preparedStatement.setInt(2, paperID);
            
            preparedStatement.executeUpdate();
            
            return true;
            
            
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
    
    public ArrayList<Paper> getAllPapersByStatus(String Status){
        ArrayList<Paper> temp = new ArrayList<Paper>();
        String getAllPapers = new String();
        if (Status.equalsIgnoreCase("All")) {
            getAllPapers = "select * from paper inner join paperinfo on paper.paper_id = paperinfo.paperidfk "
                    + "inner join author on paperinfo.Author = author.id ;";            
        }else {
            getAllPapers = "select * from paper inner join paperinfo on paper.paper_id = paperinfo.paperidfk "
                    + "inner join author on paperinfo.Author = author.id "
                    + "where paper.Status = '" + Status + "' ;";                 
        }

        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getAllPapers))
        {
            
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
                int authorID = rs.getInt("paperinfo.Author");
                User tempAuthor = new User().getInfoByID(authorID, "author");
                
                int paperID = rs.getInt("paperinfo.paper_id");
                String papername = rs.getString("papername");
                String authorName = rs.getString("author.fullname");
                String paperStatus = rs.getString("paper.status"); 
                
                Paper tempPaper = new Paper(papername ,paperID, authorName, paperStatus);
                temp.add(tempPaper);
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return temp;
        
        
    }
    
    public boolean createSubmission(String filename, ArrayList<String>authors, InputStream inputStream ) {
        int rs = 0;
        int result = 0;
        
        String Submission_Paper = "INSERT INTO paper (paperName, fileContent, Status) values (?, ?, ?);";
        String Submission_PaperInfo = "INSERT INTO paperinfo (paperidfk, Author, Coauthor) values (?, ?, ?);";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(Submission_Paper);
                PreparedStatement preparedStatement2 = connection.prepareStatement(Submission_PaperInfo))
        {
            
     
            preparedStatement.setString(1, filename);
            preparedStatement.setBlob(2, inputStream);
            preparedStatement.setString(3, "Pending");
            rs = preparedStatement.executeUpdate();
           
           // Submission_PaperInfo_success = rs.next();
            Statement statement1 = connection.createStatement();
            Statement statement2 = connection.createStatement();
            Statement statement3 = connection.createStatement();

            String getAuthorID = "Select id from author where username = '"+authors.get(1)+"';";
            String getCoAuthorID = "Select id from author where username = '"+authors.get(0)+"';";
            ResultSet id = statement1.executeQuery(getAuthorID);
            id.next();
            ResultSet id2 = statement3.executeQuery(getCoAuthorID);
            id2.next();
            String getPaperID = "Select paper_id from paper where paperName = '"+filename+"';";
            ResultSet paperid = statement2.executeQuery(getPaperID);
            paperid.next();
            preparedStatement2.setInt(1, paperid.getInt("paper_id"));
            preparedStatement2.setInt(2, id.getInt("id"));
            preparedStatement2.setInt(3, id2.getInt("id"));
            result = preparedStatement2.executeUpdate();
                //Submission_Paper_success= result.next();
            
        }catch (SQLException e) {
            printSQLException (e);
        }
        
        if(rs == 1 && result == 1){
            return true;
        }
        
        else {
            return false;
        }
        
    }
    
    
    public ArrayList<Paper> showAllPaperstoBid(int reviewer_id){
        ArrayList<Paper> listPaper = new ArrayList<>();
        String paperinfo = "Select paperidfk, Author, Coauthor from paperinfo where paperidfk not in (Select paperidfk from bid where reviewName ='"+String.valueOf(reviewer_id)+"');";
        
        try (Connection connection = DbConnection.init();
                Statement statement1 = connection.createStatement();){
            
            ResultSet paper = statement1.executeQuery(paperinfo);
            while(paper.next()) {
                String papername = "Select paperName from paper where paper_id = '"+paper.getInt("paperidfk")+"';";
                String authorname = "Select fullname from author where id = '"+paper.getInt("Author")+"';";
                String coauthorname = "Select fullname from author where id = '"+paper.getInt("Coauthor")+"';";
                
                Statement statement2 = connection.createStatement();
                Statement statement3 = connection.createStatement();
                Statement statement4 = connection.createStatement();
                
                ResultSet paper_name = statement2.executeQuery(papername);
                paper_name.next();
                ResultSet author_name = statement3.executeQuery(authorname);
                author_name.next();
                ResultSet coauthor_name = statement4.executeQuery(coauthorname);
                coauthor_name.next();
                
                listPaper.add(new Paper(paper.getInt("paperidfk"),  paper_name.getString("paperName"), author_name.getString("fullname"),  coauthor_name.getString("fullname")));
                
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return listPaper;
        
    }
    
    
    
    
    public ArrayList<Paper> showMyPapers(String username){
            
            ArrayList<Paper> listPaper = new ArrayList<>();
             
            try (Connection connection = DbConnection.init()) {
                
                String getauthorid = "Select id from author where username = '"+username+"';";
                Statement statement1 = connection.createStatement();
                ResultSet authid = statement1.executeQuery(getauthorid);
                authid.next();
                String paperidfk = "SELECT paperidfk, Author, Coauthor FROM paperinfo where Author = '"+authid.getInt("id")+"' or Coauthor = '"+authid.getInt("id")+"';";
                Statement statement2 = connection.createStatement();
                ResultSet papidfk = statement2.executeQuery(paperidfk);
                
                while(papidfk.next()) { 
                    String getPaper = "Select paper_id, paperName, Status from paper where paper_id='"+papidfk.getInt("paperidfk")+"';";
                    Statement statement3 = connection.createStatement();
                    ResultSet paper = statement3.executeQuery(getPaper);
                    paper.next();
                    String name = paper.getString("paperName");
                    String Status = paper.getString("Status");
                    int paper_id = paper.getInt("paper_id");
                    int idauthor = papidfk.getInt("Author");
                    int idcoauthor = papidfk.getInt("Coauthor");
                    String getauthor = "Select fullname from author where id = '"+String.valueOf(idauthor)+"';";
                    String getauthorusername = "Select username from author where id = '"+String.valueOf(idauthor)+"';";
                    String getcoauthor = "Select fullname from author where id = '"+String.valueOf(idcoauthor)+"';";
                    Statement statement4 = connection.createStatement();
                    Statement statement5 = connection.createStatement();
                    Statement statement6= connection.createStatement();
                    ResultSet authoruser = statement4.executeQuery(getauthor);
                    ResultSet coauthoruser = statement5.executeQuery(getcoauthor);
                    ResultSet authorusername = statement6.executeQuery(getauthorusername);
                    authoruser.next();
                    coauthoruser.next();
                    authorusername.next();
                    
                    listPaper.add(new Paper(paper_id, name, Status,authoruser.getString("fullname"),coauthoruser.getString("fullname"),authorusername.getString("username")));
                }
                 
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }      
             
            return listPaper;
        }
    
    
    public boolean deletePaper(int id){
        int rs1 = 0;
        int rs2 = 0;
        int rs3 = 0;
        int rs4 = 0;
        int rs5 = 0;
    
        String deletebid = "Delete from bid where paperidfk = ?;";
        String deletereviews = "Delete from reviews where paperidfk = ?;";
        String paperinfo = "Delete from paperinfo where paperidfk = ?;";
        String paper = "Delete from paper where paper_id = ?;";
        
        try (Connection connection = DbConnection.init();
                PreparedStatement preparedStatement2 = connection.prepareStatement(deletebid);
                PreparedStatement preparedStatement3 = connection.prepareStatement(deletereviews);
                PreparedStatement preparedStatement4 = connection.prepareStatement(paperinfo);
                PreparedStatement preparedStatement5 = connection.prepareStatement(paper);){
             
        
            preparedStatement2.setInt(1, id);
            preparedStatement3.setInt(1, id);
            preparedStatement4.setInt(1, id);
            preparedStatement5.setInt(1, id);
           
            rs2 = preparedStatement3.executeUpdate();
            rs3 = preparedStatement2.executeUpdate();
            rs4 = preparedStatement4.executeUpdate();
            rs5 = preparedStatement5.executeUpdate();
            
        }catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }      
        
   
            return true;
     
        
    }
    
    public boolean editPaper(int id, String papername, ArrayList<String> authors) {
        String changepapername = "Update paper set paperName = ? where paper_id = ?;";
        String authorid = "Select id from author where username = '"+authors.get(1)+"';";
        String coauthorid = "Select id from author where username = '"+authors.get(0)+"';";
        String changecoauthor = "Update paperinfo set Coauthor = ? where paperidfk = ? and Author=?;";

        int rs1=0;
        int rs2 = 0;
      
        
        try (Connection connection = DbConnection.init();
                PreparedStatement preparedStatement1 = connection.prepareStatement(changepapername);
                Statement statement1 = connection.createStatement();
                Statement statement2 = connection.createStatement();
                PreparedStatement preparedStatement2 = connection.prepareStatement(changecoauthor);){
            
            preparedStatement1.setString(1, papername);
            preparedStatement1.setInt(2, id);
            rs1 = preparedStatement1.executeUpdate();
            
            ResultSet getauthorid = statement1.executeQuery(authorid);
            ResultSet getcoauthorid = statement2.executeQuery(coauthorid);
            
            getauthorid.next();
            getcoauthorid.next();
            System.out.println(getcoauthorid.getInt("id"));
            System.out.println(getauthorid.getInt("id"));
            
            preparedStatement2.setInt(1, getcoauthorid.getInt("id"));
            preparedStatement2.setInt(2, id);
            preparedStatement2.setInt(3, getauthorid.getInt("id"));
            rs2 = preparedStatement2.executeUpdate();
            
        }
        catch (SQLException e) {
            e.printStackTrace();
           
        }
        return true;
    }
    
    public Paper getPaperInfoBySpecificAuthor (int id, String username) {
        Paper temp = new Paper();
        String getPaperInfoWithAuthor = ""
                + "select * from paperinfo inner join author on paperinfo.Author = "
                + "author.id inner join paper on paper.paper_id = paperinfo.paper_id "
                + "where paper.paper_id = ? and author.username = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getPaperInfoWithAuthor))
        {
            preparedStatement.setInt(1, id);
            preparedStatement.setString(2, username);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            
            while (rs.next()) {
                
                User tempAuthor = new User().getInfoByID(rs.getInt("paperinfo.Author"), "author");
                User coAuthor = new User().getInfoByID(rs.getInt("paperinfo.Coauthor"), "author");
                
                int paperID = rs.getInt("paperinfo.paper_id");
                String papername = rs.getString("paper.papername");
                String status = rs.getString("paper.Status");
                String authorName = tempAuthor.getFullname();
                String coAuthorName = coAuthor.getFullname();
                String authorUserName = tempAuthor.getUsername();
                
                temp = new Paper(paperID, papername,status, authorName, coAuthorName, authorUserName);
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return temp;
        
    }
 
    
    public Paper getpaperInfo(int id, String username){
        int rs1 = 0;
        int rs2 = 0;
        Paper paper;
        
        String getpapername = "Select paperName from paper where paper_id ='"+String.valueOf(id)+"';";
        String getauthors = "Select username from Author where id in (Select Author from paperinfo where paperidfk = '"+String.valueOf(id)+"');";
        String co_author = "";
       
        try (Connection connection = DbConnection.init();
                Statement preparedStatement1 = connection.createStatement();
                Statement preparedStatement2 = connection.createStatement();){
             
                ResultSet papername = preparedStatement1.executeQuery(getpapername);  
                ResultSet authorsname = preparedStatement2.executeQuery(getauthors); 
                papername.next();
                while(authorsname.next()) {
                    if(username != authorsname.getString("username")) {
                        co_author = authorsname.getString("username");
                    }
                }
                
                paper = new Paper(papername.getString("Papername"), id, co_author);
                
        }
        
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }      
     
        return paper;
        
        
    }
    
 public Paper downloadPaper(int paperid) {
     Paper paper = null;
        String getpaper = "SELECT paperName, fileContent FROM paper WHERE paper_id = ?;";
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getpaper);)
        {
            preparedStatement.setInt(1, paperid);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                String fileName = result.getString("paperName");
                Blob blob = result.getBlob("fileContent");
                InputStream inputStream = blob.getBinaryStream();
                paper = new Paper(fileName, inputStream);
            }
                    
        }catch (SQLException ex) {
             ex.printStackTrace();
                  
        }      
        
        return paper;
    }
    
    private void printSQLException(SQLException ex) 
    {
        for (Throwable e : ex) 
        {
            if (e instanceof SQLException) 
            {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) 
                {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}