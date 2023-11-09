package com.example.friendfinder.service;

import java.time.LocalDateTime;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friendfinder.dto.AdminInputDto;
import com.example.friendfinder.dto.ComplaintInputDto;
import com.example.friendfinder.dto.ComplaintOutputDto;
import com.example.friendfinder.entity.Comment;
import com.example.friendfinder.entity.Complaint;
import com.example.friendfinder.entity.Posts;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.exceptions.ComplaintNotFoundException;
import com.example.friendfinder.exceptions.UserNotFoundException;
import com.example.friendfinder.repository.ICommentRepository;
import com.example.friendfinder.repository.IComplaintRepository;
import com.example.friendfinder.repository.IPostRepository;
import com.example.friendfinder.repository.IUserRepository;

@Service
public class ComplaintServiceImpl implements IComplaintService {
	@Autowired
	IComplaintRepository complaintRepo;
	@Autowired
	IUserRepository userRepo;
	@Autowired
	IPostRepository postRepo;
	@Autowired
	ICommentRepository commentRepo;

	// Add complain method
	@Override
	public Complaint addComplaint(Complaint complaint) {
		Complaint comp1 = complaintRepo.save(complaint);
		return comp1;
	}

	// Delete complain by id
	@Override
	public void deleteComplaintById(long compId) {
		Optional<Complaint> opt = complaintRepo.findById(compId);
		if (opt.isPresent()) {
			// Read employee object from the optional
			complaintRepo.deleteById(compId);
		} else {
			// If given id doesn't match it will throw complaint not found exception
			throw new ComplaintNotFoundException("Complaint not found with given id: " + compId);
		}
	}

	// View complain by id
	@Override
	public Complaint viewComplaintById(long complaint_Id) {
		Optional<Complaint> opt1 = complaintRepo.findById(complaint_Id);
		Complaint comp = null;
		if (opt1.isPresent()) {
			// read complaint object form optional
			comp = opt1.get();
		} else {
			// If given id doesn't match it will throw complaint not found exception
			throw new ComplaintNotFoundException("Complaint not found with given id: " + complaint_Id);
		}
		return comp;
	}

	@Override
	public List<Complaint> viewAllComplaints() {
		List<Complaint> compList = complaintRepo.findAll();
		return compList;
	}

	@Override
	public boolean resolveComplaint(Complaint complaint) {
		Optional<Complaint> opt = complaintRepo.findById(complaint.getComplaintId());
		if (opt.isPresent()) {
			Complaint comp1 = opt.get();
			if (complaint.getStatus().name() == "QUERY_RESOLVED") {
				return false;
			}
			comp1.setStatus(complaint.getStatus());
			System.out.println("Complain details" + comp1);
			complaintRepo.save(comp1);
			return true;
		} else {
			// If given id doesn't match it will throw complaint not found exception
			throw new ComplaintNotFoundException("Complaint not found for given information: " + complaint);
		}
	}

	@Override
	public boolean adminResolveComplaint(AdminInputDto complaint) {
		Optional<Complaint> opt = complaintRepo.findById(complaint.getComplaintId());
		if (opt.isPresent()) {
			Complaint comp1 = opt.get();
			if (complaint.getStatus().name() == "QUERY_RESOLVED") {
				comp1.setStatus(complaint.getStatus());
				complaintRepo.save(comp1);
				return true;
			} else {
				comp1.setStatus(complaint.getStatus());
				complaintRepo.save(comp1);
				return false;
			}
		} else {
			// If given id doesn't match it will throw complaint not found exception
			throw new ComplaintNotFoundException("Complaint not found for given information: " + complaint);
		}
	}

	@Override
	public ComplaintOutputDto addComplaintByUserId(long userId, String comp_msg, String comment) {
		Optional<User> opt = userRepo.findById(userId);
		Complaint comp = new Complaint();
		ComplaintOutputDto outDto = new ComplaintOutputDto();
		if (opt.isPresent()) {
			User user = opt.get();
//			comp.setComplaintId(0);
			comp.setComplaintText(comp_msg);
			comp.setRegisteredOn(LocalDateTime.now());
			comp.setStatus(Complaint.Status.QUERY_RAISED);
			comp.setComments(comment);
			List<Complaint> listOfComplaint = user.getComplaint();
			listOfComplaint.add(addComplaint(comp));
			user.setComplaint(listOfComplaint);
			userRepo.save(user);
			outDto.setUserId(user.getUserId());
			outDto.setFirstName(user.getFirstName());
			outDto.setLastName(user.getLastName());
			outDto.setEmail(user.getEmail());
			outDto.setMobile(user.getMobile());
			outDto.setUsername(user.getUsername());
			outDto.setSchool(user.getSchool());
			outDto.setCollege(user.getCollege());
			outDto.setStatus(user.getStatus());
			outDto.setComplaint(user.getComplaint());
		} else {
			throw new UserNotFoundException("User is not found with Id: " + userId);
		}
		return outDto;
	}

	@Override
	public ComplaintOutputDto viewComplaintByUserId(long user_Id) {
		Optional<User> opt = userRepo.findById(user_Id);
		ComplaintOutputDto outDto = new ComplaintOutputDto();
		if(opt.isPresent()) {
			User user = opt.get();
			outDto.setUserId(user.getUserId());
			outDto.setFirstName(user.getFirstName());
			outDto.setLastName(user.getLastName());
			outDto.setEmail(user.getEmail());
			outDto.setMobile(user.getMobile());
			outDto.setUsername(user.getUsername());
			outDto.setSchool(user.getSchool());
			outDto.setCollege(user.getCollege());
			outDto.setStatus(user.getStatus());
			outDto.setComplaint(user.getComplaint());
			
		}
		else {
			throw new UserNotFoundException("User is not found with Id: " + user_Id);
		}
		
		return outDto;
	}

	@Override
	public Complaint reportPost(long postId, ComplaintInputDto complaint) {
		Optional<Posts> opt = postRepo.findById(postId);
		Posts newPost = new Posts();
		Complaint newComp = new Complaint();
		if(opt.isPresent()) {
			newPost = opt.get();
			newComp.setComplaintText(complaint.getComplaintText());
			newComp.setComments(complaint.getComments());
			newComp.setRegisteredOn(LocalDateTime.now());
			newComp.setStatus(Complaint.Status.QUERY_RAISED);
			newComp.setPost(newPost);
			complaintRepo.save(newComp);
		}
		return newComp;
	}

	@Override
	public Complaint reportComment(long commentId, ComplaintInputDto complaint) {
		Optional<Comment> opt = commentRepo.findById(commentId);
		Comment newComment = new Comment();
		Complaint newComp = new Complaint();
		if(opt.isPresent()) {
			newComment = opt.get();
			newComp.setComplaintText(complaint.getComplaintText());
			newComp.setComments(complaint.getComments());
			newComp.setRegisteredOn(LocalDateTime.now());
			newComp.setStatus(Complaint.Status.QUERY_RAISED);
			newComp.setPostComment(newComment);
			complaintRepo.save(newComp);
		}
		return newComp;
	}

	

}
