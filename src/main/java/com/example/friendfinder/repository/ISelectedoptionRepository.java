package com.example.friendfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friendfinder.dto.ISelectedOption;
import com.example.friendfinder.entity.SelectedOption;
import com.example.friendfinder.entity.SelectedOptionKey;

@Repository
public interface ISelectedoptionRepository extends JpaRepository<SelectedOption, SelectedOptionKey> {

	//custom methods , not provided by JPA repository
	@Query(value="select u.selected_option,count(u.selected_option) from user_polls u where poll_id=:poll_id group by u.selected_option",nativeQuery=true)
	List<ISelectedOption> findByPollId(@Param("poll_id") long pollId);
	
}
