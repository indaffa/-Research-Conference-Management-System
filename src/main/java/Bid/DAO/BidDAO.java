package Bid.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Author.Entity.Author;
import Bid.Entity.Bid;
import Paper.Entity.Paper;
import Reviewer.Entity.Reviewer;
import dbconnection.DbConnection;
import general.Entity.User;

public class BidDAO{
    
    public boolean bidPaper(int paper_id, int reviewer_id){
        String bid = "Insert into bid (reviewName, paperidfk) values  (?, ?);";
        int rs = 0;
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(bid);)
        {

            preparedStatement.setInt(1, reviewer_id);
            preparedStatement.setInt(2, paper_id);
            rs = preparedStatement.executeUpdate();
            return true;
        }
        
        catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }      
     
    }
    
    public ArrayList<Bid> getBidThatUnallocated(){
        String getBid = "select * from bid inner join reviewer on reviewer.id = bid.reviewName "
                + "inner join paperinfo on paperinfo.paper_id = bid.paperidfk "
                + "inner join author on paperinfo.Author = author.id "
                + "where bid.allocateStatus = 'unallocated' ;";
        ArrayList<Bid> allBid = new ArrayList<Bid>();
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getBid);)
        {
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int bidID = rs.getInt("bid_id");
                int reviewerID = rs.getInt("reviewName");
                int paperIDFK = rs.getInt("paperidfk");
                String allocatedStatus = rs.getString("allocateStatus");
                
                //Reviewer
                User tempReviewer = new Reviewer().getInfoByID(rs.getInt("reviewer.id"), "reviewer");
                User tempAuthor = new Author().getInfoByID(rs.getInt("author.id"), "author");
                
                Paper paper = new Paper().getPaperInfoBySpecificAuthor(paperIDFK, tempAuthor.getUsername());
                Bid temp = new Bid(bidID, paperIDFK, reviewerID, allocatedStatus, tempReviewer, paper);
                
                allBid.add(temp);
            }
            
        }catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
        
        return allBid;
    }
    
    public  ArrayList<Bid> getAllBid() {
        
        String getBid = "select * from bid inner join reviewer on reviewer.id = bid.reviewName "
                + "inner join paperinfo on paperinfo.paper_id = bid.paperidfk "
                + "inner join author on paperinfo.Author = author.id ;";
        ArrayList<Bid> allBid = new ArrayList<Bid>();
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getBid);)
        {
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int bidID = rs.getInt("bid_id");
                int reviewerID = rs.getInt("reviewName");
                int paperIDFK = rs.getInt("paperidfk");
                String allocatedStatus = rs.getString("allocateStatus");
                
                //Reviewer
                User tempReviewer = new Reviewer().getInfoByID(rs.getInt("reviewer.id"), "reviewer");
                User tempAuthor = new Author().getInfoByID(rs.getInt("author.id"), "author");
                
                Paper paper = new Paper().getPaperInfoBySpecificAuthor(paperIDFK, tempAuthor.getUsername());
                Bid temp = new Bid(bidID, paperIDFK, reviewerID, allocatedStatus, tempReviewer, paper);
                
                allBid.add(temp);
            }
            
        }catch (SQLException e) {
            return null;
        }
        
        
        return allBid;
    }
    
    public boolean RejectBid(int bidID) {
        String REJECTBID = "delete from bid where bid_id = ?";
        try(Connection connection = DbConnection.init();
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(REJECTBID))
        {
            preparedStatement.setInt(1, bidID);
            preparedStatement.executeUpdate();
            return true;
            
        }catch (SQLException e ) {
            e.printStackTrace();
            return false;
        }
        
    }
    
    public void updateBidStatus(int bidID, String status) {
        String updateBidStatus = "update bid "
                + "set allocateStatus = ?  where bid_id = ?;";
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(updateBidStatus))
        {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, bidID);
            preparedStatement.executeUpdate();
            
            
        }catch (SQLException e ) {
            e.printStackTrace();
        }
        
    }
    
    public Bid getBidInfoByID(int bidID) {
        Bid temp = new Bid();
        String getBidInfoByID = "select * from bid where bid_id = ?;";
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getBidInfoByID))
        {

            preparedStatement.setInt(1, bidID);
            
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int bid_id = rs.getInt("bid_id");
                int reviewerID = rs.getInt("reviewName");
                int paperID = rs.getInt("paperidfk");
                String allocateStatus = rs.getString("allocateStatus");
                User tempU = new User().getInfoByID(reviewerID, "reviewer");
                
                temp = new Bid(bid_id, paperID, reviewerID, allocateStatus, tempU);
                return temp;
                
            }
        }
        
        catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }      
        
        return temp;
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