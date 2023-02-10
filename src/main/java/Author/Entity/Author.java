package Author.Entity;

import java.util.ArrayList;

import general.Entity.*;
import Author.DAO.*;


public class Author extends User {
    
    private AuthorDAO authorDao = new AuthorDAO();

    public Author() {
        super();
        // TODO Auto-generated constructor stub
    }

    public ArrayList<String> dropDownList(){
        return authorDao.dropdownlist();
    }

}
