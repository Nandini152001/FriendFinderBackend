package com.example.friendfinder.controller;

import java.time.LocalDateTime;

import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.friendfinder.dto.AdminInputDto;
import com.example.friendfinder.dto.AdminOutputDto;
import com.example.friendfinder.dto.ComplaintInputDto;
import com.example.friendfinder.dto.ComplaintOutputDto;
import com.example.friendfinder.entity.Complaint;
import com.example.friendfinder.service.IComplaintService;

@RestController
@CrossOrigin
public class ComplaintController {
	@Autowired
	IComplaintService complaintServ;
	
	//Create logger object
//	private Logger logger = LogManager.getLogger();
	
	//Add complaint
		@PostMapping("/complaint/add")
		ResponseEntity<Complaint> addComplaint(@Valid @RequestBody Complaint comp) {//Request data from body of variable
//			logger.debug(comp);
			Complaint newComp = complaintServ.addComplaint(comp);
//			logger.debug(newComp);
			ResponseEntity<Complaint> response = new ResponseEntity<>(newComp, HttpStatus.CREATED);//200 created
			return response;
		};
		//Delete complaint controller
		@DeleteMapping("/complaint/delete/{compId}")
		ResponseEntity<String> deleteComplaintById(@PathVariable long compId) {
			complaintServ.deleteComplaintById(compId);
			String s = "Comlaint is deleted by complaint ID: ";
			return new ResponseEntity<>(s+compId, HttpStatus.OK); //200 OK
		};
		
		//Method to view complaint by complaintId
		@GetMapping("/complaint/viewById/{complaint_Id}")
		ResponseEntity<Complaint> viewComplaintById(@PathVariable long complaint_Id) { //PathVariable read data from URL
//			logger.info("Calling ComplaintServiceImpl using Complaint_Id");
			Complaint comp = complaintServ.viewComplaintById(complaint_Id);
//			logger.info("Got informantion for complaint service");
			return new ResponseEntity<>(comp, HttpStatus.OK);//200 Ok
		};
		

		//Admin view complain
		@GetMapping("/admin/complaint/findAll")
		ResponseEntity<List<Complaint>> adminViewAllComplaint(){
//			logger.info("Calling ComplaintServiceImpl and then calling viewAllComplaints to view all complaints");
			List<Complaint> compList = complaintServ.viewAllComplaints();
//			logger.info("List of all complaints received for complaint Service");
			return new ResponseEntity<>(compList, HttpStatus.OK);
		};
			//Resolve complaint
			@PutMapping("/admin/resolveComplaint")
			ResponseEntity<AdminOutputDto> resolveComplaint(@RequestBody AdminInputDto comp) {//Request data from body of variable
//				logger.info("Calling complaintService method by passing AdminInputDto object");
				boolean flag = complaintServ.adminResolveComplaint(comp);
				AdminOutputDto outComp = new AdminOutputDto();		
//				logger.info("Got boolean value decide admin has resolved query or not");
				if(flag) {
					outComp.setAdminFeedback("Admin resolved the query Successfully");
				}
				else {
					outComp.setAdminFeedback("Admin faild to resolve the query");
				}
//				logger.info("Creating outputDto objet and setting its field");
				Complaint comp1 = complaintServ.viewComplaintById(comp.getComplaintId());
				outComp.setComplaintId(comp1.getComplaintId());
				outComp.setComplaintText(comp1.getComplaintText());
				outComp.setStatus(comp1.getStatus());
//				logger.info("Date and time when current is resolved by the Admin");
				outComp.setDateTime(LocalDateTime.now());
//				logger.info("OutputDto fields are set successfully");
				ResponseEntity<AdminOutputDto> response = new ResponseEntity<>(outComp, HttpStatus.OK);//200 created
				return response;
			};
			
			//Add Complaint by user id
			@PutMapping("/complaint/add/{userId}/{comp_msg}/{comment}")
			ResponseEntity<ComplaintOutputDto> addComplaintByUserId(@PathVariable long userId, @PathVariable String comp_msg, @PathVariable String comment){
				ComplaintOutputDto out = complaintServ.addComplaintByUserId(userId, comp_msg, comment);
				return new ResponseEntity<>(out, HttpStatus.OK);
			};
			
			//View by user id
			@GetMapping("/complaint/viewAllComplaintForUser/{user_Id}")
			ResponseEntity<ComplaintOutputDto> viewComplaintByUserId(@PathVariable long user_Id) { //PathVariable read data from URL
				ComplaintOutputDto compOut = complaintServ.viewComplaintByUserId(user_Id);
				return new ResponseEntity<>(compOut, HttpStatus.OK);//200 Ok
			};
			
			//Report a post
			@PostMapping("complaint/reportPost/{postId}")
			ResponseEntity<Complaint> complaintByPostId(@PathVariable long postId, @RequestBody ComplaintInputDto complaint ){
				Complaint newComplaint = complaintServ.reportPost(postId, complaint);
				return new ResponseEntity<>(newComplaint, HttpStatus.OK);
			}
			
			//Report a comment
			@PostMapping("complaint/reportComment/{commentId}")
			ResponseEntity<Complaint> complaintByCommentId(@PathVariable long commentId, @RequestBody ComplaintInputDto complaint ){
				Complaint newComplaint = complaintServ.reportComment(commentId, complaint);
				return new ResponseEntity<>(newComplaint, HttpStatus.OK);
			}
}
