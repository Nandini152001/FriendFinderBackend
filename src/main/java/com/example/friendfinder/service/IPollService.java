package com.example.friendfinder.service;

import java.util.List;

import com.example.friendfinder.dto.ISelectedOption;
import com.example.friendfinder.dto.PollInputDto;
import com.example.friendfinder.dto.PollOutputDto;
import com.example.friendfinder.entity.Polls;
import com.example.friendfinder.entity.SelectedOption;

public interface IPollService {

	//abstract methods
	List<Polls> viewAllPolls();
	PollOutputDto createPoll(PollInputDto poll);
	PollOutputDto viewPollsById(long pollId);
	String deletePolls(long pollId);
	boolean participatePoll(long userId, long pollId, String answer);
	List<ISelectedOption> viewPollResult(long pollId);
	
}
