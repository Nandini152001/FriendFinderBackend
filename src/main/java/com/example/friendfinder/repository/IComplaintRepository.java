package com.example.friendfinder.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.friendfinder.entity.Complaint;

public interface IComplaintRepository extends JpaRepository<Complaint, Long> {

//	Optional<Complaint> findByRegisteredOn(LocalDateTime complaint_Date);

}
