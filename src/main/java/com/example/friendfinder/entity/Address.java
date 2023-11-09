package com.example.friendfinder.entity;

import javax.persistence.Entity;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Address {

	@Id
	@GeneratedValue
	private long id;
	private String city;
	private String state;
	private String pincode;
	private String country;
}
