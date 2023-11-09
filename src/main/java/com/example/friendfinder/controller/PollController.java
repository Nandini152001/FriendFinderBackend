package com.example.friendfinder.controller;

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

import com.example.friendfinder.dto.ISelectedOption;
import com.example.friendfinder.dto.ParticipatePollDto;
import com.example.friendfinder.dto.PollInputDto;
import com.example.friendfinder.dto.PollOutputDto;
import com.example.friendfinder.entity.Polls;
import com.example.friendfinder.service.IPollService;

@RestController
@CrossOrigin(origins="http://localhost:3000")
public class PollController {

	@Autowired
	IPollService pollServ;
    
	//creating new polls
	@PostMapping("/poll/create")
	ResponseEntity<PollOutputDto> createPoll(@Valid @RequestBody PollInputDto polls)
	{
		
		PollOutputDto poll = pollServ.createPoll(polls);
		return new ResponseEntity<>(poll,HttpStatus.OK);
	};
	
	//to view all the poll question and answers
	@GetMapping("/poll/view")
	ResponseEntity<List<Polls>> viewAllPolls()
	{
		List<Polls> viewPoll = pollServ.viewAllPolls();
		return new ResponseEntity<>(viewPoll,HttpStatus.OK);
	};
	
	//to view the poll question and answers based on pollId
	@GetMapping("/poll/view/{id}")
	ResponseEntity<PollOutputDto> viewPollsById(@PathVariable long id)
	{
		PollOutputDto viewPoll = pollServ.viewPollsById(id);
		return new ResponseEntity<>(viewPoll,HttpStatus.OK);
	};
	
	//to delete a particular poll by pollId
	@DeleteMapping("/poll/delete/{pollId}")
	ResponseEntity<String> deletePolls(@PathVariable long pollId)
	{
		String msg = pollServ.deletePolls(pollId);
		return new ResponseEntity<>(msg,HttpStatus.OK);
	};
	
	//participation of users in each poll by pollId
	@PostMapping("/poll/participate")
	boolean participatePoll(@RequestBody ParticipatePollDto polls)
	{
		boolean participation = pollServ.participatePoll(polls.getUserId(),polls.getPollId(),polls.getOption());
		return participation;
	};
	
	//result of each poll by pollId
	@GetMapping("/poll/result/{pollId}")
	ResponseEntity<List<ISelectedOption>> viewPollResult(@PathVariable long pollId)
	{
		List<ISelectedOption> viewPoll = pollServ.viewPollResult(pollId);
		return new ResponseEntity<>(viewPoll,HttpStatus.OK);
		
	};
	
	
}




