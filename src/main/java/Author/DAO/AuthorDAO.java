package Author.DAO;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dbconnection.DbConnection;

public class AuthorDAO{
    
    public ArrayList<String> dropdownlist(){
            
            ArrayList<String> listAuthor = new ArrayList<>();
             
            try (Connection connection = DbConnection.init()) {
                String sql = "SELECT username FROM author;";
                Statement statement = connection.createStatement();
                ResultSet result = statement.executeQuery(sql);
                 
                while (result.next()) {
                    String name = result.getString("username");
                    listAuthor.add(name);
                }          
                 
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }      
             
            return listAuthor;
        }
}