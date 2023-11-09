package com.example.friendfinder.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Data
@Embeddable
public class SelectedOptionKey implements Serializable {

	@Column(name="user_id")
	long userId;
	
	@Column(name="poll_id")
	long pollId;
}
