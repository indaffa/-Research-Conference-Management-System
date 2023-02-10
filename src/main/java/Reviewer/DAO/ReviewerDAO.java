package Reviewer.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import Paper.Entity.Paper;
import Reviewer.Entity.Reviewer;
import dbconnection.DbConnection;
import general.Entity.User;

public class ReviewerDAO {
    
    public boolean checkReviewerReachMaxPaper(int reviewerID) {
        String checkReviewerMaxPaper = "select * from reviewer "
                + "inner join reviews on reviewer.id = reviews.reviewer "
                + "inner join bid on bid.bid_id = reviews.bid_id "
                + "where reviewer.id = ? and bid.allocateStatus = 'allocated' ;";
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(checkReviewerMaxPaper))
        {
            User temp = new User().getInfoByID(reviewerID, "reviewer");
            preparedStatement.setInt(1, reviewerID);
            int currentPaper = 0;
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                currentPaper++;
            }
            
            if (currentPaper >= ((Reviewer)temp).getMax_no_papers()) {
                return false;
            }else
                return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    
    public boolean updateReviewer(int myID, String username, String password, String name ,String email,int maxPapers ,String profileName) {
        String updateMyAccount = "update "+ profileName +" set username = ?, fullname = ?, password = ?, email = ?,max_no_paper = ? where id = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(updateMyAccount))
        {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, maxPapers);
            preparedStatement.setInt(6, myID);
            
            preparedStatement.executeUpdate();
            return true;
            
            
        }catch (SQLException e) {
            return false;
        }

    }
    
    public ArrayList<Paper> papersToReview(int reviewer_id) throws SQLException{
        ArrayList<Paper> rev = new ArrayList<Paper>();
 String getPaperid =  "Select paperidfk from reviews where rating is null and reviewer ='"+String.valueOf(reviewer_id)+"';";     
 String paperinfo = "Select paperidfk, Author, Coauthor from paperinfo where paperidfk = ?;";
        
        try (Connection connection = DbConnection.init();
                Statement statement2 = connection.createStatement();){
            
            ResultSet paperid = statement2.executeQuery(getPaperid);
            
            while(paperid.next()) {
                PreparedStatement preparedStatement = connection.prepareStatement(paperinfo);
                preparedStatement.setInt(1, paperid.getInt("paperidfk"));
                ResultSet paper = preparedStatement.executeQuery();
                while(paper.next()) {
                    String papername = "Select paperName from paper where paper_id = '"+paper.getInt("paperidfk")+"';";
                    String authorname = "Select fullname from author where id = '"+paper.getInt("Author")+"';";
                    String coauthorname = "Select fullname from author where id = '"+paper.getInt("Coauthor")+"';";
                    
                    Statement statement3 = connection.createStatement();
                    Statement statement4 = connection.createStatement();
                    Statement statement5 = connection.createStatement();
                    
                    ResultSet paper_name = statement3.executeQuery(papername);
                    paper_name.next();
                    ResultSet author_name = statement4.executeQuery(authorname);
                    author_name.next();
                    ResultSet coauthor_name = statement5.executeQuery(coauthorname);
                    coauthor_name.next();
                    
                    rev.add(new Paper(paper.getInt("paperidfk"),  paper_name.getString("paperName"), author_name.getString("fullname"),  coauthor_name.getString("fullname")));
            }
           
                
            }
        }
        return rev;
        
    }
    
    
}
