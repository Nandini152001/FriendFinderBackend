package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Collection;
import java.util.List;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.friendfinder.dto.PollInputDto;
import com.example.friendfinder.entity.Polls;
import com.example.friendfinder.exceptions.PollNotExistException;

@SpringBootTest
class PollServiceTest {
	
	@Autowired
	IPollService pollServ;
	/*
	@Disabled
	@Test
	void viewPollByIdTest()
	{
		PollInputDto poll = pollServ.viewPollsById(6);
		assertEquals("How many hours you spend on social media each day?",poll.getQuestion());
	}
	
	@Disabled
	@Test
	void viewAllPollsTest()
	{
		List<Polls> pollList = pollServ.viewAllPolls();
		assertEquals("Languages known",pollList.get(1).getQuestion());
		
		Collection<Polls> answers = pollServ.viewAllPolls();
		assertEquals(true,answers.containsAll(answers));
		
		int totalPoll = pollList.size();
		assertEquals(4,totalPoll);
	}
	@Disabled
	@Test
	void createPollTest()
	{
		PollInputDto newPoll = new PollInputDto();
		newPoll.setQuestion("Who will win match");
		
		
		//storing userInputDto values to userOutputDto
		PollInputDto pollDto = pollServ.createPoll(newPoll);
		
		//validation
		assertEquals("Who will win match",pollDto.getQuestion());
	}
	*/
	@Test
	void PollNotExistExceptionTest()
	{
		assertThrows(PollNotExistException.class, () -> {
			pollServ.viewPollsById(8);
		});
	}	
}
