package Bid.Entity;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bid.DAO.*;
import Paper.Entity.Paper;
import Reviewer.Entity.Reviewer;
import general.Entity.User;


public class Bid{
    private int bid_id;
    private int paper_id;
    private int reviewer_id;
    private String allocateStatus;
    private User reviewer;
    private Paper paper;
    private BidDAO dao;
    
    
    public Bid(){
        dao = new BidDAO();
    }
    
    public Bid(int bid_id) {
        super();
        this.bid_id = bid_id;
        dao = new BidDAO();
    }

    public Bid(int bid_id, int paper_id, int reviewer_id) {
        super();
        this.bid_id = bid_id;
        this.paper_id = paper_id;
        this.reviewer_id = reviewer_id;
        dao = new BidDAO();
    }


    public Bid(int bid_id, int paper_id, int reviewer_id, String status ) {
        super();
        this.bid_id = bid_id;
        this.paper_id = paper_id;
        this.reviewer_id = reviewer_id;
        this.allocateStatus = status;
        dao = new BidDAO();
    }
    

    public Bid(int bid_id, int paper_id, int reviewer_id, String allocateStatus, User reviewer, Paper paper) {
        super();
        this.bid_id = bid_id;
        this.paper_id = paper_id;
        this.reviewer_id = reviewer_id;
        this.allocateStatus = allocateStatus;
        this.reviewer = reviewer;
        this.paper = paper;
        dao = new BidDAO();
    }

    public Bid(int bid_id, int paper_id, int reviewer_id, String allocateStatus, User reviewer) {
        super();
        this.bid_id = bid_id;
        this.paper_id = paper_id;
        this.reviewer_id = reviewer_id;
        this.allocateStatus = allocateStatus;
        this.reviewer = reviewer;
        dao = new BidDAO();
    }



    public String getAllocateStatus() {
        return allocateStatus;
    }


    public void setAllocateStatus(String allocateStatus) {
        this.allocateStatus = allocateStatus;
    }
    
    public ArrayList<Bid> getAllBid(){
        return dao.getAllBid();
    }
    
    public ArrayList<Bid> getBidThatUnallocated(){
        return dao.getBidThatUnallocated();
    }
    
    public void updateBidStatus(int bidID, String status) {
        dao.updateBidStatus(bidID, status);
    }
    
    public boolean RejectBid(int bidID) {
        return dao.RejectBid(bidID);
    }
    


    public boolean bidPaper(int paper_id, int reviewer_id) {
        return dao.bidPaper(paper_id, reviewer_id);
    }
    
    public Bid getBidInfoByID(int bidID) {
        return dao.getBidInfoByID(bidID);
    }


    public int getBid_id() {
        return bid_id;
    }


    public void setBid_id(int bid_id) {
        this.bid_id = bid_id;
    }


    public int getPaper_id() {
        return paper_id;
    }


    public void setPaper_id(int paper_id) {
        this.paper_id = paper_id;
    }


    public int getReviewer_id() {
        return reviewer_id;
    }


    public void setReviewer_id(int reviewer_id) {
        this.reviewer_id = reviewer_id;
    }


    public BidDAO getDao() {
        return dao;
    }


    public void setDao(BidDAO dao) {
        this.dao = dao;
    }


    public User getReviewer() {
        return reviewer;
    }


    public void setReviewer(Reviewer reviewer) {
        this.reviewer = reviewer;
    }


    public Paper getPaper() {
        return paper;
    }


    public void setPaper(Paper paper) {
        this.paper = paper;
    }
    
    
    
    
    
    
}