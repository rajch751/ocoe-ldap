package com.uob.ocoe.ldap.ocoeldap.controller;

import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize.Inclusion;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonSerialize(include=Inclusion.NON_NULL)
public class JwtResponse {

	private String jwtToken;
	private String expiresDuration;
	private LocalDateTime expiryTime;
	private String successMsg;
	private String  responseCode;

	private String errorMsg;

	public JwtResponse(String jwtToken, String expiresDuration, LocalDateTime expiryTime) {
		super();
		this.jwtToken = jwtToken;
		this.expiresDuration = expiresDuration;
		this.expiryTime = expiryTime;
	}

	public JwtResponse(String errorMsg) {
		super();
		this.errorMsg = errorMsg;
	}

	public JwtResponse(String successMsg, String responseCode) {
		super();
		this.successMsg = successMsg;
		this.responseCode = responseCode;
	}
	
	

}
