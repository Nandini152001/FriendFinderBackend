package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.friendfinder.dto.AdminInputDto;
import com.example.friendfinder.entity.Complaint;
import com.example.friendfinder.repository.IComplaintRepository;
import com.example.friendfinder.repository.IUserRepository;

@ExtendWith(SpringExtension.class)
public class ComplaintServiceMockitoTest {
	
	//@InjectMock - Creates instances of a class and injects with @Mock
	@InjectMocks
	ComplaintServiceImpl compServ;
	
	//MockBean-creates Mock of a class or interface
	@MockBean
	IComplaintRepository compRepo;
	
	@MockBean
	IUserRepository userRepo;
	
	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}
	
	//check add complaint service
	@Test
	void addComplaintTest() {
		Complaint comp = new Complaint();
		comp.setComplaintId(100);
		comp.setComplaintText("DOB correction");
		comp.setRegisteredOn(LocalDateTime.now());
		comp.setStatus(Complaint.Status.QUERY_RAISED);
		comp.setComments("Resolve ASAP");
		
		Mockito.when(compRepo.save(comp)).thenReturn(comp);
		Complaint response = compServ.addComplaint(comp);
		
		assertEquals(100, response.getComplaintId());
		assertEquals("DOB correction", response.getComplaintText());
		assertEquals(Complaint.Status.QUERY_RAISED, response.getStatus());
		assertEquals("Resolve ASAP", response.getComments());
	}
	
	//Test delete complaint
	@Test
	void deleteComplaintByIdTest() {
		Complaint comp = new Complaint();
		comp.setComplaintId(100);
		comp.setComplaintText("DOB correction");
		comp.setRegisteredOn(LocalDateTime.now());
		comp.setStatus(Complaint.Status.QUERY_RAISED);
		comp.setComments("Resolve ASAP");
		
		when(compRepo.findById((long)100)).thenReturn(Optional.of(comp));
		doNothing().when(compRepo).deleteById((long)100);
		compServ.deleteComplaintById((long)100);
		 verify(compRepo, times(1)).deleteById((long)100);
		 verify(compRepo, times(1)).findById((long)100);
	}
	
	//Test view complaint by id
	@Test
	void viewComplaintByIdTest() {
		Complaint comp = new Complaint();
		comp.setComplaintId(100);
		comp.setComplaintText("DOB correction");
		comp.setRegisteredOn(LocalDateTime.now());
		comp.setStatus(Complaint.Status.QUERY_RAISED);
		comp.setComments("Resolve ASAP");
		
		Mockito.when(compRepo.findById((long)10)).thenReturn(Optional.of(comp));
		Complaint response = compServ.viewComplaintById(100);
		
		assertEquals(100, response.getComplaintId());
		assertEquals("DOB correction", response.getComplaintText());
		assertEquals(Complaint.Status.QUERY_RAISED, response.getStatus());
		assertEquals("Resolve ASAP", response.getComments());
	} 
	
	//Test view all complaint
	@Test
	void viewAllComplaintsTest() {
		Complaint comp1 = new Complaint();
		comp1.setComplaintId(100);
		comp1.setComplaintText("DOB correction");
		comp1.setRegisteredOn(LocalDateTime.now());
		comp1.setStatus(Complaint.Status.QUERY_RAISED);
		comp1.setComments("Resolve ASAP");
		
		Complaint comp2 = new Complaint();
		comp2.setComplaintId(200);
		comp2.setComplaintText("Inappropriate post");
		comp2.setRegisteredOn(LocalDateTime.now());
		comp2.setStatus(Complaint.Status.QUERY_RAISED);
		comp2.setComments("Delete post ASAP");
		
		List<Complaint> compList = new ArrayList<>();
		compList.add(comp1);
		compList.add(comp2);
		
		Mockito.when(compRepo.findAll()).thenReturn(compList);
		List<Complaint> complaintList = compServ.viewAllComplaints();
		
		assertEquals(2, complaintList.size());
		assertEquals(100, complaintList.get(0).getComplaintId());
		assertEquals("DOB correction", complaintList.get(0).getComplaintText());
		assertEquals(Complaint.Status.QUERY_RAISED, complaintList.get(0).getStatus());
		assertEquals("Resolve ASAP", complaintList.get(0).getComments());
		assertEquals(200, complaintList.get(1).getComplaintId());
		assertEquals("Inappropriate post", complaintList.get(1).getComplaintText());
		assertEquals(Complaint.Status.QUERY_RAISED, complaintList.get(1).getStatus());
		assertEquals("Delete post ASAP", complaintList.get(1).getComments());
	} 
	
	//Test resolve complaint
	@Test
	void resolveComplaintTest() {
		Complaint comp = new Complaint();
		comp.setComplaintId(100);
		comp.setComplaintText("DOB correction");
		comp.setRegisteredOn(LocalDateTime.now());
		comp.setStatus(Complaint.Status.QUERY_RAISED);
		comp.setComments("Resolve ASAP");
		
		Mockito.when(compRepo.findById((long)100)).thenReturn(Optional.of(comp));
		Mockito.when(compRepo.save(comp)).thenReturn(comp);
		boolean bool = compServ.resolveComplaint(comp);
		
		assertTrue(bool);
	} 
	
	//Test method used by admin to resolve compliaint by giving user id as input 
	@Test
	void adminResolveComplaintTest() {
		Complaint comp = new Complaint();
		comp.setComplaintId(100);
		comp.setComplaintText("DOB correction");
		comp.setRegisteredOn(LocalDateTime.now());
		comp.setStatus(Complaint.Status.QUERY_RAISED);
		comp.setComments("Resolve ASAP");
		
		AdminInputDto inputDto = new AdminInputDto();
		inputDto.setComplaintId(100);
		inputDto.setStatus(Complaint.Status.QUERY_RESOLVED);
		
		Mockito.when(compRepo.findById((long)100)).thenReturn(Optional.of(comp));
		Mockito.when(compRepo.save(comp)).thenReturn(comp);
		boolean bool = compServ.adminResolveComplaint(inputDto);
		
		assertTrue(bool);
	} 
	 
}
