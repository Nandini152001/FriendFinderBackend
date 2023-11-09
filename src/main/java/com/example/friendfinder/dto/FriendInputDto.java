package com.example.friendfinder.dto;

import java.time.LocalDate;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class FriendInputDto {
	
	private long userIdFrom;
    private long userIdTo;
    private LocalDate dateOfRequest;
    
    @NotEmpty(message= "Meassage must not Empty.")
    @NotNull(message= "Meassage must not Null.")
    @NotBlank(message= "Meassage must not Blank.")
    private String message;
}
