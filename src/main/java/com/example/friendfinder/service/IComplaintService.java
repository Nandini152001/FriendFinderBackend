package com.example.friendfinder.service;

import java.util.List;
import com.example.friendfinder.dto.AdminInputDto;
import com.example.friendfinder.dto.ComplaintInputDto;
import com.example.friendfinder.dto.ComplaintOutputDto;
import com.example.friendfinder.entity.Complaint;

public interface IComplaintService {
	
	    public Complaint addComplaint(Complaint complaint);
	    public Complaint viewComplaintById(long id);
	    public List<Complaint> viewAllComplaints();
	    public boolean resolveComplaint(Complaint complaint);
	    public boolean adminResolveComplaint(AdminInputDto complaint_id);
	    public void deleteComplaintById(long compId);
	    public ComplaintOutputDto addComplaintByUserId(long userId, String comp_msg, String comment);
	    public ComplaintOutputDto viewComplaintByUserId(long user_Id);
	    public Complaint reportPost(long postId, ComplaintInputDto complaint);
	    public Complaint reportComment(long commentId, ComplaintInputDto complaint);
}
