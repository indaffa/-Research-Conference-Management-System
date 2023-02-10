package SystemAdmin.DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Reviewer.Entity.Reviewer;
import SystemAdmin.entity.SystemAdmin;
import dbconnection.DbConnection;
import general.Entity.User;

public class SystemAdminDAO {  
    
    public ArrayList<User> SearchUserAccount(String userName){
        ArrayList<User> tempUser = new ArrayList<User>();
        List<User> tempProfile = getAllProfile();
        for (int i = 0 ; i < tempProfile.size(); i++) {
            String searchUserByUserName = "select * from " + tempProfile.get(i).getProfileName() + ""
                    + " inner join userprofile on " + tempProfile.get(i).getProfileName() + ".profileID =  userprofile.profileID "
                            + "where username = ?";
            
            try(Connection connection = DbConnection.init();
                    PreparedStatement preparedStatement = connection.prepareStatement(searchUserByUserName))
            {
                preparedStatement.setString(1, userName);
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    User temp ;
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String name = rs.getString("fullname");
                    int profileID = Integer.parseInt(rs.getString("profileID"));
                    String profileName = rs.getString("profileName");
                    String desc = rs.getString("description");
                            
                    temp = new User(id,username, name, password, email, profileID, profileName, desc);
                    
                    tempUser.add(temp);
                }
            }catch (SQLException e) {
                printSQLException (e);
                
                return null;
            }
    
        
        }
        return tempUser;
        
    }
    
    public boolean createNewProfile(String profileName, String description){
           String insert_new_profile = "insert into userprofile (`profilename`, `description`) VALUES"
                   + " (?, ?)";
           try(Connection connection = DbConnection.init();
                   
                   PreparedStatement preparedStatement = connection.prepareStatement(insert_new_profile))
           {
               preparedStatement.setString(1, profileName);
               preparedStatement.setString(2, description);
               preparedStatement.executeUpdate();
               createNewProfileDB(profileName);
               return true;
           }catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
    }
    
    public void createNewProfileDB(String profileName) {
        String newDB = "CREATE TABLE " + profileName + " ( "
                + "`id` int(11) primary key auto_increment,"
                + "`username` varchar(50) not null,`fullname` varchar(50) not null,"
                + "`password` varchar(50) not null,`email` varchar(70) not null,"
                + "`profileID` int(11) not null,"
                + " FOREIGN KEY fk_"+profileName+"(`profileID`) REFERENCES userprofile(profileID));"; 
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(newDB))
        {
            preparedStatement.executeUpdate();
            
        }catch (SQLException e) {
            e.printStackTrace();
            
        }
        
    }
   
    public boolean updateProfile(int profileID,String profileName, String description){
        String update_user_profile = "update userprofile set profilename = ?, description = ? where profileID = ?;";
   
        List<User> tempProfile = getAllProfile();
        for (int i = 0 ; i < tempProfile.size(); i++) {
            if (tempProfile.get(i).getProfileName().equalsIgnoreCase(profileName) && !(profileID == tempProfile.get(i).getProfileID()))
                return false;

        }
        
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(update_user_profile))
        {
            preparedStatement.setString(1, profileName);
            preparedStatement.setString(2, description);
            preparedStatement.setInt(3, profileID);
            preparedStatement.executeUpdate();
            return true;
        }catch (SQLException e) {
        	return false;
        }
 }

    public boolean updateUserAccount(String username, String password, String name, String email, String profileName, String oldUserName, String oldProfileName) throws SQLException  {        
        if (!(oldProfileName.equalsIgnoreCase(profileName))) {
            
            if (usernameExist(username, profileName)) {
                return false;
            }
            
            String delete_one_from_db = "delete from " +oldProfileName + " where username = ?;";
            try(Connection connection = DbConnection.init();
                    
                    PreparedStatement preparedStatement = connection.prepareStatement(delete_one_from_db))
            {
                preparedStatement.setString(1, oldUserName);
                preparedStatement.executeUpdate();
                createNewUser (username, password, name, email, profileName);
                return true;
            }catch (SQLException e) {
                return false;
            }
        }else {
            if (!(usernameExist(username, profileName)) || oldUserName.equalsIgnoreCase(username) ) {
                String update_one_in_database = "update " + profileName+ " set username = ?, fullname = ?, password = ?, email = ? "
                        + "where username = ?;";
                try(Connection connection = DbConnection.init();
                        
                        PreparedStatement preparedStatement = connection.prepareStatement(update_one_in_database))
                {
                    preparedStatement.setString(1, username);
                    preparedStatement.setString(2, name);
                    preparedStatement.setString(3, password);
                    preparedStatement.setString(4, email);
                    preparedStatement.setString(5, oldUserName);
                    
                    preparedStatement.executeUpdate();
                    
                    
                    
                }catch (SQLException e) {
                    return false;
                }
                return true;
            }else {
                return false;
            }
            
            
        }
       
    }
   
    public User readUser(String userName, String profileName) {
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
    
    public List<User> getAllProfile(){
        List<User> allProfile = new ArrayList<User>();  
        String SELECT_ALL_USER_DATABASE = "select * from userprofile ;";
        try(Connection connection = DbConnection.init();
                
                PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER_DATABASE))
        {
//            preparedStatement.setString(1, profileName);
            
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int profileID = Integer.parseInt(rs.getString("profileID"));
                String profilename = rs.getString("profilename");
                String description = rs.getString("description");
                allProfile.add(new User(profileID ,profilename,description));
                
    
            }
        }catch (SQLException e) {
            printSQLException (e);
        }
           return allProfile;
    }
    
    public User getProfile(String profileName) {
        User temp = new User();
        String select_one_profile = "select * from userprofile where profilename = ? ;";
        try(Connection connection = DbConnection.init();
                PreparedStatement preparedStatement = connection.prepareStatement(select_one_profile))
        {
            preparedStatement.setString(1, profileName);
            ResultSet rs = preparedStatement.executeQuery();
            
            rs.next();
            int profileID = Integer.parseInt(rs.getString("profileID"));
            String profilename = rs.getString("profilename");
            String description = rs.getString("description");
            temp = new User(profileID,profilename, description);
            
            

        }catch(SQLException e) {
            printSQLException (e);
        }
        return temp;
        
    }
    
    public List<User> viewAllUserAccount(){
        List<User> tempUser = new ArrayList<User>();
        List<User> tempProfile = getAllProfile();
        for (int i = 0 ; i < tempProfile.size(); i++) {
            String SELECT_USER_FROM_EACH_DATABASE= "select * from " + tempProfile.get(i).getProfileName()+" inner join userprofile on "
                    + tempProfile.get(i).getProfileID()+ " = userprofile.profileID";
            try(Connection connection = DbConnection.init();
                    //private static final String SELECT_ALL_USERACCOUNTS = "select * from useraccount inner join userprofile on useraccount.userprofile "
                      //      + "= userprofile.id order by useraccount.id;";                    
                    PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_FROM_EACH_DATABASE))
            {
//                
                ResultSet rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    User temp ;
                    int id = rs.getInt("id");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String email = rs.getString("email");
                    String name = rs.getString("fullname");
                    int profileID = Integer.parseInt(rs.getString("profileID"));
                    String profileName = rs.getString("profileName");
                    String desc = rs.getString("description");
                            
                    temp = new User(id,username, name, password, email, profileID, profileName, desc);
                    
                    tempUser.add(temp);
                }
            }catch (SQLException e) {
                printSQLException (e);
            }

        }
        return tempUser;
    }
    
    public void createNewUser(String username, String password, String name, String email, String profileName) throws SQLException {
        
        final String INSERT_USEERACCOUNT = "INSERT INTO " + profileName + "(`username`,`fullname`, `password`, email , `profileID`) VALUES"
                + "(?, ?, ?, ?, ?)";
        try (Connection connection = DbConnection.init();
                PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USEERACCOUNT)) 
        {
            User tempProfile = getProfile(profileName);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, password);
            preparedStatement.setString(4, email);
            preparedStatement.setInt(5, tempProfile.getProfileID());
            preparedStatement.executeUpdate(); 
        }
    }
    
    public boolean userProfileExist(String profilename) {
        final String check_profilename_exist = "select 1 from userprofile where profilename = ?";
        boolean profileExist = false;
        
        try (Connection connection = DbConnection.init();
                PreparedStatement preparedStatement = connection.prepareStatement(check_profilename_exist )) 
        {
            preparedStatement.setString(1, profilename);
            ResultSet rs = preparedStatement.executeQuery();
            profileExist = rs.next();
            
        }catch (SQLException e) {
            printSQLException (e);
        }
        
        return profileExist;
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
