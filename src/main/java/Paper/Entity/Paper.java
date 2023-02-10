package Paper.Entity;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;

import Paper.DAO.PaperDAO;



public class Paper{
    
    private String papername;
    private int id;
    private String author;
    private String authorusername;
    private String coauthor;
    private InputStream inputstream;
    private String status;
    private PaperDAO paperDao;
    
    public Paper(){
        paperDao = new PaperDAO();
    }
    
    public Paper(String filename, InputStream stream) {
        this.papername = filename;
        this.inputstream = stream;
    }
    
    public Paper(int id,String papername, String status, String author, String coauthor, String authorusername) {
        this.id = id;
        this.papername = papername;
        this.status = status;
        this.author = author;
        this.coauthor = coauthor;
        this.authorusername = authorusername;
        paperDao = new PaperDAO();
    }
    
    public Paper(int id,String papername, String author, String coauthor) {
        this.id = id;
        this.papername = papername;
        this.author = author;
        this.coauthor = coauthor;
        paperDao = new PaperDAO();
    }
    
    public Paper(String papername, int id, String co_author) {
        this.id = id;
        this.papername = papername;
        this.coauthor = co_author;
        paperDao = new PaperDAO();
    }
    
    public Paper(String papername, String author, FileInputStream inputstream, String status){
        this.papername = papername;
        this.author = author;
        this.inputstream = inputstream;
        this.status = status;
        paperDao = new PaperDAO();
        
    }
    
    public Paper(String papername, String status){
        this.papername = papername;
        this.status = status;
        paperDao = new PaperDAO();
        
    }
    
    public Paper(String papername, String author, String status){
        this.papername = papername;
        this.author = author;
        this.status = status;
        paperDao = new PaperDAO();
        
    }
    
    
    
    public Paper(String papername, int id, String author, String status) {
        this.papername = papername;
        this.id = id;
        this.author = author;
        this.status = status;
        paperDao = new PaperDAO();
    }

    public boolean createSubmission(String filename, ArrayList<String>authors, InputStream inputStream ) {
        return paperDao.createSubmission(filename, authors, inputStream);
    }
    
    public ArrayList<Paper> showMyPapers(String username){
        return paperDao.showMyPapers(username);
    }
    
    public ArrayList<Paper> showAllPaperstoBid(int reviewer_id){
        return paperDao.showAllPaperstoBid(reviewer_id);
    }
    
    public boolean AcceptPaper(int paperID) {
        return paperDao.AcceptPaper(paperID);
    }
    
    public boolean RejectPaper(int paperID) {
        return paperDao.RejectPaper(paperID);
    }
    
    public boolean UpdatePaperStatus(int paperID, String paperStatus) {
        return paperDao.UpdatePaperStatus(paperID, paperStatus);
    }
    
    public ArrayList<Paper> getAllPapersByStatus(String Status){
        return paperDao.getAllPapersByStatus(Status);
    }
    
    public boolean deletePaper(int id){
        return paperDao.deletePaper(id);
    }
    
    public boolean editPaper(int id, String papername, ArrayList<String> authors){
        return paperDao.editPaper(id, papername, authors);
    }
    
    public Paper getpaperInfo(int id, String username){
        return paperDao.getpaperInfo(id, username);
    }
    
    public Paper getPaperInfoBySpecificAuthor(int id, String username){
        return paperDao.getPaperInfoBySpecificAuthor(id, username);
    }
    
    public Paper downloadPaper(int paperid) {
        return paperDao.downloadPaper(paperid);
    }
    
    public String getPapername() {
        return papername;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public int getId() {
        return id;
    }
    
    public String getCoauthor() {
        return coauthor;
    }
    
    public String getStatus() {
        return status;
    }
    
    public String getAuthorusername() {
        return authorusername;
    }
    
    public InputStream getInputstream() {
        return inputstream;
    }
    
}