package general.DAO;

import dbconnection.DbConnection;
import general.Entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import Author.Entity.Author;
import ConferenceChair.Entity.ConferenceChair;
import Reviewer.Entity.Reviewer;
import SystemAdmin.entity.SystemAdmin;

public class UserDAO  {
    
    public User getMyInfo(String userName, String profileName) {
        User tempP = new SystemAdmin().getProfile(profileName);
        String SELECT_ONE_FOR_READ = "select * from "+ profileName + " inner join userprofile on "+ tempP.getProfileID() + 
                " = userprofile.profileID where username = ?;";
        User temp = new User();        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_FOR_READ))
        {
//            preparedStatement.setString(1, profileName);
            preparedStatement.setString(1, userName);
            ResultSet rs = preparedStatement.executeQuery();
            
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String name = rs.getString("fullname");
                if (profileName.equalsIgnoreCase("reviewer")) {
                    int maxPapers =Integer.parseInt(rs.getString("max_no_paper"));
                    temp = new Reviewer(id,username, name, password, email, tempP.getProfileID(),tempP.getProfileName(), maxPapers);
                    
                }else {
                    temp = new User(id,username, name, password, email, tempP.getProfileID(),tempP.getProfileName());                    
                }
                

            }
            
        }catch (SQLException e) {
            printSQLException (e);
        }
        

        return temp;

    }
    
    public User getInfoByID(int myID, String profileName) {
        User tempP = new SystemAdmin().getProfile(profileName);
        String getMyInfoByID = "Select * from "+ profileName + " where id = ?;" ; 
        User temp = new User();   
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(getMyInfoByID))
        {
            preparedStatement.setInt(1, myID);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String email = rs.getString("email");
                String name = rs.getString("fullname");
                if (profileName.equalsIgnoreCase("reviewer")) {
                    int maxPapers =Integer.parseInt(rs.getString("max_no_paper"));
                    temp = new Reviewer(id,username, name, password, email, tempP.getProfileID(),tempP.getProfileName(), maxPapers);
                    
                }else {
                    temp = new User(id,username, name, password, email, tempP.getProfileID(),tempP.getProfileName());                    
                }
            }
        }catch (SQLException e ) {
            return null;
        }
        
        return temp;
    }
    
  //  private static final String SELECT_USERACCOUNT_FOR_LOGIN = "select 1 from ? where username = ? and password = ?;";
    
    public boolean updateMyAccount(int myID, String username, String password, String name ,String email, String profileName) {
        String updateMyAccount = "update "+ profileName +" set username = ?, fullname = ?, password = ?, email = ? where id = ?;";
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(updateMyAccount))
        {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, myID);
            
            preparedStatement.executeUpdate();
            return true;
            
            
        }catch (SQLException e) {
            return false;
        }

    }
    
    public boolean login(String username, String password, String profileName) {
        boolean loginSuccessful = false;
        String SELECT_USERACCOUNT_FOR_LOGIN = "select 1 from " + profileName +" where username = ? and password = ?;";
        
        
        try(Connection connection = DbConnection.init();
       
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USERACCOUNT_FOR_LOGIN))
        {
//            preparedStatement.setString(1, profileName);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet rs = preparedStatement.executeQuery();
            loginSuccessful = rs.next();
        }catch (SQLException e) {
            printSQLException (e);
        }
        
        return loginSuccessful;
    }
    
    public boolean usernameExist(String username, String profileName) {
        final String CHECK_USERNAME_EXIST_ID = "select 1 from " + profileName +" where username = ? ;";
        
        boolean usernameExist = false;
        try (Connection connection = DbConnection.init();
                PreparedStatement preparedStatement = connection.prepareStatement(CHECK_USERNAME_EXIST_ID )) 
        {
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            usernameExist = rs.next();
            
        }catch (SQLException e) {
            printSQLException (e);
        }
        
        return usernameExist;
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
