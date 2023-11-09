package com.example.friendfinder.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.friendfinder.dto.ISelectedOption;
import com.example.friendfinder.dto.PollInputDto;
import com.example.friendfinder.dto.PollOutputDto;
import com.example.friendfinder.entity.Polls;
import com.example.friendfinder.entity.SelectedOption;
import com.example.friendfinder.entity.SelectedOptionKey;
import com.example.friendfinder.entity.User;
import com.example.friendfinder.exceptions.DuplicatePollParticipationException;
import com.example.friendfinder.exceptions.PollNotExistException;
import com.example.friendfinder.exceptions.UserNotFoundException;
import com.example.friendfinder.repository.IAuthenticationRepository;
import com.example.friendfinder.repository.IPollRepository;
import com.example.friendfinder.repository.ISelectedoptionRepository;

@Service
public class PollServiceImpl implements IPollService {

	@Autowired
	IPollRepository pollRepo;
	
	@Autowired
	ISelectedoptionRepository optRepo;
	
	@Autowired
	IAuthenticationRepository authRepo;
	
	//creating new poll
	 @Override
   	public PollOutputDto createPoll(PollInputDto poll) {
			
	     //convert from pollsInputdto to polls
		 Polls pollIn = new Polls();
		// pollIn.setPollId(poll.getPollId());
		 pollIn.setQuestion(poll.getQuestion());
		 pollIn.setAnswers(poll.getAnswers());
		 pollIn.setCreatedOn(poll.getCreatedOn());
		 
		 //saving the pollInputdto values to polls
		 Polls newPoll = pollRepo.save(pollIn);
					
		 //converting from polls to pollsOutputdto
		 PollOutputDto pollOut = new PollOutputDto();
		 pollOut.setPollId(newPoll.getPollId());
		 pollOut.setQuestion(newPoll.getQuestion());
		 pollOut.setAnswers(newPoll.getAnswers());
		 pollOut.setCreatedOn(newPoll.getCreatedOn());
					
		 return pollOut;
		}

	 //view poll by poll Id
	 @Override
	 public PollOutputDto viewPollsById(long pollId) {
		 
		 Optional<Polls> opt = pollRepo.findById(pollId);
		 Polls viewPoll = null;
		 PollOutputDto polls = new PollOutputDto();
		 if(opt.isPresent())
			{
				viewPoll = opt.get();
				polls.setPollId(viewPoll.getPollId());
				polls.setQuestion(viewPoll.getQuestion());
				polls.setAnswers(viewPoll.getAnswers());
				polls.setCreatedOn(viewPoll.getCreatedOn());
				return polls;
			}
			else 
			{
				throw new PollNotExistException("give poll id dose not exist:"+ pollId);
			}
		}

	 //view all the created polls
	 @Override
	 public List<Polls> viewAllPolls() {
		//finding all polls by using method	
		 List<Polls> allPolls = pollRepo.findAll();
	   	
		 if(allPolls.isEmpty())
		 {
			 throw new PollNotExistException("No records found in this table");
		 }
		 else
		 {
			 return allPolls;
		 }
	  }
		
	//deleting a particular poll by admin
	@Override
	public String deletePolls(long pollId) {
		//reading pollId 
		Optional<Polls> opt = pollRepo.findById(pollId);
		//checking if pollId is present
		if(opt.isPresent())
		{
			pollRepo.deleteById(pollId);
			return "Deleted successfully";
		}
		else 
		{
			throw new PollNotExistException("given poll id does not exist: "+ pollId);
		}
	}

	@Override
	public boolean participatePoll(long userId, long pollId, String answer) {
		// reading userId
		Optional<User> opt = authRepo.findById(userId);
		
		Polls poll = new Polls();
		User user = new User();
		
		//finding whether userId exist else throw UserNotFoundException
		if(opt.isPresent())
		{
			user=opt.get();
			
			Optional<Polls> opt1 = pollRepo.findById(pollId);
			
			//checking if pollId exists else throw PollNotExistException
			if(opt1.isPresent())
			{
				poll=opt1.get();
				
				//creating objects for SelectedOptionKey to store userId and pollId
				SelectedOptionKey id =new SelectedOptionKey();
				SelectedOptionKey idchk =new SelectedOptionKey();
				idchk.setUserId(userId);
				idchk.setPollId(pollId);
				
				//reading userId and pollId
				Optional<SelectedOption> chk = optRepo.findById(idchk);
				
				//checking if user has already participated or not
				if(chk.isPresent())
				{
					throw new DuplicatePollParticipationException("already participated in poll"+pollId);
				}
				else
				{
					//creating objects for selectedOption to store selected answer
					SelectedOption option = new SelectedOption();
					id.setPollId(pollId);
					id.setUserId(userId);
					option.setPoll(poll);
					option.setUser(user);
					option.setSelected_option(answer);
					optRepo.save(option);
					//return true if participated in poll
					return true;
				}	
			}
			else
			{
				throw new PollNotExistException("given pollId does not exist:"+pollId);
			}	
		}
		else
		{
			throw new UserNotFoundException("Login Id not available:"+userId);
		}
		
	}

	@Override
	public List<ISelectedOption> viewPollResult(long pollId) {
		
		List<ISelectedOption> result = optRepo.findByPollId(pollId);
		if(result.isEmpty())
		{
			throw new PollNotExistException("given pollId does not exist: "+pollId);
		}
		else
		{
			return result;
		}
		
	}
	
}	