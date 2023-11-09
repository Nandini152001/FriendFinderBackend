package com.example.friendfinder.service;

import static org.junit.jupiter.api.Assertions.*;

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

import com.example.friendfinder.dto.PollInputDto;
import com.example.friendfinder.entity.Polls;
import com.example.friendfinder.repository.IPollRepository;

@ExtendWith(SpringExtension.class)
class PollServiceMockitoTest {

	    // @InjectMock - Creates instance of a class and injects mocks that are created
		// with @Mock

		@InjectMocks
		PollServiceImpl pollServ;

		// @MockBean - Creates Mock of a class or interface
		@MockBean
		IPollRepository pollRepo;

		// Initialization of mock objects
		@BeforeEach
		void init() {
			MockitoAnnotations.openMocks(this);
		}
		/*
		@Test
		void createPollTest()
		{
			PollInputDto poll = new PollInputDto();
			//poll.setPollId(6);
			poll.setQuestion("Who will win match");
			List<String> ans = new ArrayList<>();
			ans.add("RCB");
			ans.add("CSK");
			poll.setAnswers(ans);
			
			Polls poll1 = new Polls();
			//poll1.setPollId(poll.getPollId());
			poll1.setAnswers(poll.getAnswers());
			poll1.setQuestion(poll.getQuestion());
			
			Mockito.when(pollRepo.save(poll1)).thenReturn(poll1);
			
			//PollInputDto newPoll = pollServ.createPoll(poll);
			assertEquals("Who will win match",newPoll.getQuestion());
		}
		
		@Test
		void viewPollByIdTest()
		{
			PollInputDto poll = new PollInputDto();
			poll.setPollId(6);
			poll.setQuestion("Who will win match");
			List<String> ans = new ArrayList<>();
			ans.add("RCB");
			ans.add("CSK");
			poll.setAnswers(ans);
			
			Polls poll1 = new Polls();
			poll1.setPollId(poll.getPollId());
			poll1.setAnswers(poll.getAnswers());
			poll1.setQuestion(poll.getQuestion());
			
			Mockito.when(pollRepo.findById((long) 6)).thenReturn(Optional.of(poll1));
			
			PollInputDto response = pollServ.viewPollsById(6);
			assertEquals("Who will win match",response.getQuestion());
		}
		
		@Test
		void viewAllPollTest()
		{
			PollInputDto poll1 = new PollInputDto();
			poll1.setPollId(6);
			poll1.setQuestion("Who will win match");
			List<String> ans = new ArrayList<>();
			ans.add("RCB");
			ans.add("CSK");
			poll1.setAnswers(ans);
			
			Polls newPoll1 = new Polls();
			newPoll1.setPollId(poll1.getPollId());
			newPoll1.setAnswers(poll1.getAnswers());
			newPoll1.setQuestion(poll1.getQuestion());
			
			PollInputDto poll2 = new PollInputDto();
			poll2.setPollId(7);
			poll2.setQuestion("Hobbies");
			List<String> answer = new ArrayList<>();
			answer.add("Reading");
			answer.add("Drawing");
			answer.add("Playing cricket");
			poll2.setAnswers(ans);
			
			Polls newPoll2 = new Polls();
			newPoll2.setPollId(poll2.getPollId());
			newPoll2.setAnswers(poll2.getAnswers());
			newPoll2.setQuestion(poll2.getQuestion());
			
			List<Polls> newPollList = new ArrayList<>();
			newPollList.add(newPoll1);
			newPollList.add(newPoll2);
			
			Mockito.when(pollRepo.findAll()).thenReturn(newPollList);
			
			List<Polls> pollList = pollServ.viewAllPolls();
			assertEquals(2,pollList.size());
		}
*/
}





