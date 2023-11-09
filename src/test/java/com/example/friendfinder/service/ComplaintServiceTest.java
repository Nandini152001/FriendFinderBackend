package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.friendfinder.entity.Complaint;
import com.example.friendfinder.exceptions.ComplaintNotFoundException;

@SpringBootTest
public class ComplaintServiceTest {
	@Autowired
	IComplaintService complaintServ;
	
	@Disabled
	@Test
	void getComplaintByIdTest() {
		//create object for complaint
		Complaint comp = complaintServ.viewComplaintById(1);
		//Validate
		assertEquals("Name", comp.getComplaintText());
	}
	
	@Disabled
	@Test
	void addComplaint() {
		//create object for complaint
		Complaint comp = new Complaint();
		comp.setComplaintId(11);
		comp.setComplaintText("Name");
		comp.setStatus(Complaint.Status.QUERY_RAISED);
		comp.setRegisteredOn(LocalDateTime.now());
		comp.setComments("Resolve ASAP");
		//Validate
		assertEquals(11, comp.getComplaintId());
		assertEquals("Name", comp.getComplaintText());
		assertEquals(Complaint.Status.QUERY_RAISED, comp.getStatus());
//		assertEquals(LocalDateTime.now(), comp.getRegisteredOn());
		assertEquals("Resolve ASAP", comp.getComments());
		
	}
	
	@Test
	void verifyComplaintNotFoundExcetpion() {
		ComplaintNotFoundException ex = assertThrows(ComplaintNotFoundException.class, ()->{
			complaintServ.viewComplaintById(1000);
		});
		assertTrue(ex.getMessage().contains("Complaint not found with given id: 1000"));
	}
}
