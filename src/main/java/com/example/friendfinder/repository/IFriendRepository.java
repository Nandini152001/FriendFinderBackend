package com.example.friendfinder.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.friendfinder.entity.FriendRequest;

@Repository
public interface IFriendRepository extends JpaRepository<FriendRequest, Long> {
	
	// method to get list of sent friend request
	@Query(value="select friend_request fr from friend_request join users on friend_request.user_sender_fk=users.user_id where user_id=:user_id", nativeQuery=true)
	List<FriendRequest> getSentRequestByUserId(@Param("user_id") long userId);
	
	// method to get friend request object
	@Query("select f from FriendRequest f where f.from.userId=:from and f.to.userId=:to")
	FriendRequest getRequestById(@Param("from") long fromUserId, @Param("to") long fromToId);
	
	@Modifying
	@Query("delete from FriendRequest f where f.from.userId=:from and f.to.userId=:to")
	void deleteFriendRequestById(@Param("from") long fromUserId, @Param("to") long fromToId);
}
