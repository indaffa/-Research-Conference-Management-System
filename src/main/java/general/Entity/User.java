package general.Entity;

import general.DAO.*;

public class User {
    private int ID;
	private String username;
	private String fullname;
	private String password;
	private String email;
	private int profileID;
	private String profileName;
    private String description;
    
	private UserDAO myDAO;
	
	//For test
	@Override
	public boolean equals(Object obj) {
	    boolean isSame = true;
	    if (obj instanceof User) {
	        User tempUser = (User) obj;
	        
	        if (!username.equalsIgnoreCase(tempUser.getUsername()))
	            isSame  = false;
	        
	        if (!fullname.equalsIgnoreCase(tempUser.getFullname()))
	            isSame  = false;
	        
	        if (!password.equalsIgnoreCase(tempUser.getPassword()))
	            isSame  = false;
	        
	        if (!email.equalsIgnoreCase(tempUser.getEmail()))
	            isSame  = false;
	        
	        if (profileID != tempUser.getProfileID())
	            isSame  = false;
        }
	    
	    
        
        
        
        return isSame;
    }
	
	public User getInfoByID (int id, String profileName) {
	    return myDAO.getInfoByID(id, profileName);
	}
	
    public User getMyInfo(String userName,String profileName) {
        return myDAO.getMyInfo(userName ,profileName);
    }
	
	public boolean login(String username, String password,String profileName) {
	    return myDAO.login(username, password, profileName);
	}
	
	public boolean updateMyAccount(int myID, String username, String pasword, String name, String email, String profileName, String oldUserName) {
	    if (this.checkUserNameSame(username, oldUserName) || !(this.usernameExist(username, profileName)))
	        return myDAO.updateMyAccount(myID, username, pasword, name, email, profileName); 
        else
            return false;
	}
	
	public boolean usernameExist(String username, String profileName) {
	    return myDAO.usernameExist(username, profileName);
	}
	
	public User() {
        super();
        myDAO = new UserDAO();
    }
	
	public boolean checkUserNameSame(String username, String oldusername) {
	    if (username.equalsIgnoreCase(oldusername)) {
	        return true;
	    }else
	        return false;
	}

	
    public User(int id, String username, String fullname, String password, String email, int profileID, String profileName) {
        super();
        this.ID = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.profileID = profileID;
        this.profileName = profileName;
        myDAO = new UserDAO();
    }
    
    

    public User(int id,String username, String fullname, String password, String email, int profileID) {
        super();
        this.ID = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.profileID = profileID;
        myDAO = new UserDAO();
    }

    public User(int id, String username, String fullname, String password, String email, int profileID, String profileName,
            String description) {
        super();
        this.ID = id;
        this.username = username;
        this.fullname = fullname;
        this.password = password;
        this.email = email;
        this.profileID = profileID;
        this.profileName = profileName;
        this.description = description;
        myDAO = new UserDAO();
    }
    
    


   

    public User(int profileID, String profileName, String description) {
        super();
        this.profileID = profileID;
        this.profileName = profileName;
        this.description = description;
    }

    public User(int profileID, String description) {
        super();
        this.profileID = profileID;
        this.description = description;
        myDAO = new UserDAO();
    }

    
    public int getID() {
        return ID;
    }

    public void setID(int iD) {
        ID = iD;
    }

    

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	


    public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserDAO getMyDAO() {
        return myDAO;
    }

    public void setMyDAO(UserDAO myDAO) {
        this.myDAO = myDAO;
    }

    public int getProfileID() {
        return profileID;
    }

    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }
   

}
