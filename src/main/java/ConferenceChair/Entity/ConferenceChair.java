package ConferenceChair.Entity;

import ConferenceChair.DAO.ConferenceChairDAO;
import general.Entity.*;

public class ConferenceChair  extends User{
    private ConferenceChairDAO myDAO = new ConferenceChairDAO();

    public ConferenceChair() {
        super();
        myDAO = new ConferenceChairDAO();
    }
    
    public boolean allocatePaper(int paperID, String username) {
        
        return true;
    }

    public ConferenceChair(int id, String username, String fullname, String password, String email, int profileID,
            String profileName, String description) {
        super(id, username, fullname, password, email, profileID, profileName, description);
        myDAO = new ConferenceChairDAO();
    }

    public void setMyDAO(ConferenceChairDAO myDAO) {
        this.myDAO = myDAO;
    }
    
    
    
    

    
    
}
