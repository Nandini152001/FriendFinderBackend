package com.example.friendfinder.entity;

import java.time.LocalDate;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Entity
@Data
public class Polls {

	@Id
	@GeneratedValue
	private long pollId;
	
	@NotNull(message="question should not be null")
	@NotEmpty(message="question should not be empty")
	@NotBlank(message="question should not be blank")
	private String question;
	
	@Column
	@ElementCollection
	private List<String> answers;
	
	@JsonIgnore
	@OneToMany(mappedBy = "poll",cascade=CascadeType.ALL)
	Set<SelectedOption> id;
	
	private LocalDate createdOn;
}
