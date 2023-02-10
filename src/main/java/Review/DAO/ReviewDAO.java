package Review.DAO;

import java.sql.Connection;
import Review.Entity.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bid.Entity.Bid;
import Reviewer.Entity.Reviewer;
import dbconnection.DbConnection;
import general.Entity.User;

public class ReviewDAO{
    
    public boolean submitReview(String review, int rating, int paperid, int reviewerid){
        
        String insertreview = "Update reviews set reviewContent = ? , rating = ? where paperidfk = ? and reviewer = ?;";
        String updatebid = "Update bid set allocateStatus = ? where reviewName = ? and paperidfk = ?;";
 try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(insertreview);
                PreparedStatement preparedStatement2 = connection.prepareStatement(updatebid);)
        {
                 preparedStatement.setString(1, review);
                 preparedStatement.setInt(2, rating);
                 preparedStatement.setInt(3, paperid);
                 preparedStatement.setInt(4, reviewerid);
                 preparedStatement.executeUpdate();
                 preparedStatement2.setString(1, "complete");
                 preparedStatement2.setInt(2, reviewerid);
                 preparedStatement2.setInt(3, paperid);
                 preparedStatement2.executeUpdate();
                 return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public boolean allocatePaper(int bidID) {
        String allocate_paper = "insert into reviews (`bid_id`, `paperidfk`, `reviewer`) values (?, ?, ?);";
        Bid myBid = new Bid().getBidInfoByID(bidID);
        Reviewer tempReviewer = new Reviewer();
        if (!tempReviewer.checkReviewerReachMaxPaper(myBid.getReviewer_id()) ) {
            return false;
        }
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(allocate_paper))
        {
            preparedStatement.setInt(1, bidID);
            preparedStatement.setInt(2, myBid.getPaper_id());
            preparedStatement.setInt(3, myBid.getReviewer_id());
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        
        
    }
  
    public ArrayList<Review> getPaperReview(int paperID){
        ArrayList<Review> allReview = new ArrayList<Review>();
        
        String getPaperReview = "select * from paperinfo inner join bid on paperinfo.paperidfk = bid.paperidfk "
                + "inner join reviews on reviews.bid_id = bid.bid_id where paperinfo.paper_id = ? "
                + "and bid.allocateStatus = 'complete' ;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getPaperReview))
        {
            preparedStatement.setInt(1, paperID);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int reviewID = rs.getInt("reviews.review_id");
                Bid tempBid = new Bid().getBidInfoByID(rs.getInt("bid.bid_id"));
                String reviewerComment = rs.getString("reviews.reviewContent");
                int rating = rs.getInt("reviews.rating");
                
                Review tempReviw = new Review(reviewID, tempBid, rating, reviewerComment);
                
                allReview.add(tempReviw);
                
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        if (allReview.size() != 0 )
            return allReview;
        else
            return null;
    }
    
    public Review getReviewInfoByID(int reviewID) {
        Review temp = new Review();
        String getBidInfoByID = "select * from reviews where review_id = ?;";
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getBidInfoByID))
        {

            preparedStatement.setInt(1, reviewID);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int reviewid = rs.getInt("review_id");
                Bid tempBid = new Bid(rs.getInt("bid_id"));
                
                temp = new Review(reviewid, tempBid);
                
                
            }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        return temp;
        
    }
    
    public boolean UnAllocatePaper(int bidID) {
        String unallocate_paper = "delete from reviews where bid_id = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(unallocate_paper))
        {
            preparedStatement.setInt(1, bidID);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public ArrayList<Review> showMyReviews(int userid){
        ArrayList<Review> rev = new ArrayList<>();
        String getreview = "Select review_id, reviewContent, paperidfk, rating from reviews where reviewer = ? and reviewContent is not null;";
        String getpapername = "Select paperName from paper where paper_id = ?;";
        
  try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement1 = connection.prepareStatement(getreview);
                PreparedStatement preparedStatement2 = connection.prepareStatement(getpapername))
        {
                preparedStatement1.setInt(1, userid);
                ResultSet rs = preparedStatement1.executeQuery();
                
                while(rs.next()) {
                    preparedStatement2.setInt(1, rs.getInt("paperidfk"));
                    ResultSet rs2 = preparedStatement2.executeQuery();
                    rs2.next();
                    rev.add(new Review(rs.getInt("paperidfk"), rs.getInt("review_id"), rs.getInt("rating"), rs2.getString("paperName"), rs.getString("reviewContent")));
                }
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return rev;
    }
    
    public boolean deleteReview(int id) {
        String deletereview = "Update reviews set reviewContent = null , rating = null where review_id = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(deletereview))
        {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public boolean editReview(int id, String content, int rating) {
        String editReview = "Update reviews set reviewContent = ? , rating = ? where review_id = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(editReview))
        {
            preparedStatement.setString(1, content);
            preparedStatement.setInt(2, rating);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public Review getInfoforEdit(int id) {
        String getinfo = "Select reviewContent, rating from reviews where review_id = ?;";
        Review rev = null;
 try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getinfo))
        {
         
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            rs.next();
             rev =  new Review(rs.getString("reviewContent"), rs.getInt("rating"));
           
        }catch (SQLException e) {
            e.printStackTrace();
           
        }
       return rev;
 
    }
    
    public ArrayList<Review> showOtherReviews(int paperid, int userid){
        ArrayList<Review> rev = new ArrayList<>();
        
        String getreviews = "Select reviewContent, rating, reviewer from reviews where paperidfk = ? and not reviewer = ? and reviewContent is not null;";
        String getreviewername = "Select fullname from reviewer where id = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement1 = connection.prepareStatement(getreviews);
                PreparedStatement preparedStatement2 = connection.prepareStatement(getreviewername);)
        {
            preparedStatement1.setInt(1, paperid);
            preparedStatement1.setInt(2, userid);
            ResultSet rs = preparedStatement1.executeQuery();
            while(rs.next()) {
                preparedStatement2.setInt(1, rs.getInt("reviewer"));
                ResultSet rs4 = preparedStatement2.executeQuery();
                rs4.next();
                
                rev.add(new Review(rs4.getString("fullname"), rs.getString("reviewContent"), rs.getInt("rating")));
            }
    
        }catch (SQLException e) {
            e.printStackTrace();
    
        }
        
        return rev;
        
        
    }
    
    public ArrayList<Review> showReviewsforAuthor(int authorid){
        ArrayList<Review> rev = new ArrayList<>();
        String getpaperid = "Select paperidfk from paperinfo where Author = ? or Coauthor = ?;";
        String getreviewinfo = "Select * from reviews where paperidfk = ? and reviewContent is not null;";
        String getreviewername = "Select fullname from reviewer where id = ? ;";
        String getpapername = "Select paperName from paper where paper_id = ?;";
        
  try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement1 = connection.prepareStatement(getpaperid);
                PreparedStatement preparedStatement2 = connection.prepareStatement(getreviewinfo);
                PreparedStatement preparedStatement3 = connection.prepareStatement(getreviewername);
                PreparedStatement preparedStatement4 = connection.prepareStatement(getpapername);)
        {
                preparedStatement1.setInt(1, authorid);
                preparedStatement1.setInt(2, authorid);
                ResultSet rs = preparedStatement1.executeQuery();
                
                while(rs.next()) {
                    preparedStatement4.setInt(1, rs.getInt("paperidfk"));
                    ResultSet rs4 = preparedStatement4.executeQuery();
                    rs4.next();
                    String papername = rs4.getString("paperName");
                    
                    preparedStatement2.setInt(1, rs.getInt("paperidfk"));
                    ResultSet rs2 = preparedStatement2.executeQuery();
                    
                    while(rs2.next()) {
                        preparedStatement3.setInt(1, rs2.getInt("reviewer"));
                        ResultSet rs3 = preparedStatement3.executeQuery();
                        rs3.next();
                        rev.add(new Review(rs2.getInt("paperidfk"), papername, rs2.getString("reviewContent"), rs2.getInt("rating"), rs3.getString("fullname")));
                    }
                
                }
        }catch (SQLException e) {
            e.printStackTrace();
           
        }
        return rev;
    }
    
    
}



