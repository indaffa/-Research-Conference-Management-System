package TestFolder;

import java.sql.SQLException;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import SystemAdmin.entity.SystemAdmin;
import general.Entity.User;

public class TestCases {
    private SystemAdmin temp;

    @Before
    public void setUp() throws Exception {
        this.temp = new SystemAdmin();
    }

    @After
    public void tearDown() throws Exception {
        this.temp = null;
    }
    
    @Test
    public void loginTest() throws SQLException {
        assertTrue("Test Login", temp.login("admin", "admin", "systemadmin"));
        assertFalse ("Test Random Login", temp.login("1231", "aaa", "systemadmin") );

    }

    @Test
    public void createNewUserTest() throws SQLException {
        temp = new SystemAdmin();
        //Since already in Database
        assertTrue("Test Create New User", temp.createNewUser("ONORE", "Onore", "YY", "ONORE@gmail.com", "systemadmin"));        
        assertFalse("Test Create New User with existing username", temp.createNewUser("admin", "admin", "YAY", "admin@gmail.com", "systemadmin"));
        
    }
    
    @Test
    public void viewAllUserAccountsTest() throws SQLException {
        assertTrue("View All User Account", temp.viewAllUser() instanceof List);
    }
    
    @Test
    public void readUser() throws SQLException {
        User myAcc = new User(1,"reviewer", "Reviewer", "reviewer",  "reviewer1@asda.com", 2, "Reviewer");
        User expected = temp.getMyInfo("reviewer", "reviewer");
        
        assertEquals("Test if same account" , myAcc, expected);
    }
    
    @Test
    public void updateUserTest() throws SQLException {
        temp = new SystemAdmin();
        temp.setUsername("admin");
        temp.setEmail("tosomeRandomemail@mail.com");
        temp.setFullname("AdminLongName");
        temp.setProfileName("systemadmin");
        assertTrue("Test Update user", temp.updateUser("admin", "admin", "AdminTest", "adminTest@gmail.com", "systemadmin", "admin", "systemadmin"));
        
        assertFalse("Test Update user with existing username", temp.updateUser("reviewer", "reviewer", "Reviewertest", "adminTest@gmail.com", "reviewer", "reviewer", "systemadmin"));
        
    }
    
    
    
    @Test
    public void createNewProfile() throws SQLException {
        assertTrue("Test create new Profile", temp.createNewProfile("Test", "Test Build"));
    }
    
    @Test
    public void updateProfile() throws SQLException {
        assertTrue("Test Update Profile", temp.updateProfile(1, "systemadmin", "Change Admin Description Namae"));
    }
    
    
    
    @Test
    public void viewAllProfileTest() throws SQLException {
        assertTrue("View All Profile Account", temp.viewAllProfile() instanceof List);
    }
    
    @Test
    public void searchByUsernameTest() throws SQLException {
        assertTrue("View All Profile Account", temp.SearchUserAccount("admin") instanceof List);
    }
    
    
    
    
    
    
    

    
    
    

}
