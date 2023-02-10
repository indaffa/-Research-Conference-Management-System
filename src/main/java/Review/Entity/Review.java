package Review.Entity;

import java.sql.SQLException;
import java.util.ArrayList;

import Bid.Entity.Bid;
import Review.DAO.ReviewDAO;

public class Review{
    int reviewid;
    Bid bidfk;
    int paperid;
    int rating;
    int authorid;
    String papername;
    String review;
    String reviewername;
    ReviewDAO revDAO;
    
    public Review() {
        revDAO = new ReviewDAO();
    }
    
    public Review(String reviewername, String review, int rating) {
        this.reviewername = reviewername;
        this.review = review;
        this.rating = rating;
    }
    
    
    
    public Review(int reviewid, Bid bidfk) {
        super();
        this.reviewid = reviewid;
        this.bidfk = bidfk;
        revDAO = new ReviewDAO();
    }

    public Review(int reviewid, Bid bidfk, int rating, String review) {
        this.reviewid = reviewid;
        this.bidfk = bidfk;
        this.rating = rating;
        this.review = review;
        revDAO = new ReviewDAO();
    }

    public Review(String review, int rating) {
        this.review = review;
        this.rating = rating;
    }
    
    public Review(int paperid, int reviewid, int rating, String papername, String review) {
        this.reviewid = reviewid;
        this.rating = rating;
        this.papername = papername;
        this.review = review;
        this.paperid = paperid;
        
    }
    
    public Review(int paperid, String papername, String review, int rating, String reviewername) {
        this.paperid = paperid;
        this.papername = papername;
        this.review = review;
        this.rating = rating;
        this.reviewername = reviewername;
    }
    
    public boolean submitReview(String review, int rating, int paperid, int reviewerid){
        return revDAO.submitReview(review, rating, paperid, reviewerid);
    }
    
    public ArrayList<Review> showMyReviews(int userid){
        return revDAO.showMyReviews(userid);
    }
    
    public ArrayList<Review> showReviewsforAuthor(int userid){
        return revDAO.showReviewsforAuthor(userid);
    }
    
    public ArrayList<Review> showOtherReviews(int paperid, int userid){
        return revDAO.showOtherReviews(paperid, userid);
    }
    
    public ArrayList<Review> getPaperReview(int paperID){
        return revDAO.getPaperReview(paperID);
    }
    
    public boolean editReview(int reviewid, String reviewcontent, int rating) {
        return revDAO.editReview(reviewid, reviewcontent, rating);
    }
    
    public boolean deleteReview(int id) {
        return revDAO.deleteReview(id);
    }
    
    public Review getInfoforEdit(int revid) {
        return revDAO.getInfoforEdit(revid);
    }
    
    
    public Review getReviewInfoByID(int revID) {
        return revDAO.getReviewInfoByID(revID);
    }
    
    
    
    public boolean AllocatePaper(int bidID) {
        return revDAO.allocatePaper(bidID);
    }
    
    public boolean UnAllocatePaper(int bidID) {
        return revDAO.UnAllocatePaper(bidID);
    }
    
    
    public int getReviewid() {
        return reviewid;
    }
    
    public int getPaperid() {
        return paperid;
    }
    
    public int getRating() {
        return rating;
    }
    
    public int getAuthorid() {
        return authorid;
    }
    
    public String getPapername() {
        return papername;
    }
    
    public String getReview() {
        return review;
    }
    
    public String getReviewername() {
        return reviewername;
    }



    public Bid getBidfk() {
        return bidfk;
    }



    public void setBidfk(Bid bidfk) {
        this.bidfk = bidfk;
    }
    
    

}