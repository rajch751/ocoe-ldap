package com.uob.ocoe.ldap.ocoeldap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = { "/v1/login/" })
public class LoginController {

	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	

	@PostMapping("/validate")
	public JwtResponse authenticate(@RequestBody JwtRequest jwtRequest) throws Exception {

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(jwtRequest.getUsername(), jwtRequest.getPassword()));
			
			return new JwtResponse("Ok","LDAP SUCESS");
		} catch (BadCredentialsException e) {
			return new JwtResponse("INVALID_CREDENTIALS");
		//	throw new Exception("INVALID_CREDENTIALS", e);
		}

		// UserDetails userDetails=new User(jwtRequest.getUsername(),null,null);

		// final User userDetails =
		// userService.loadUserByUsername(jwtRequest.getUsername());

		/*
		 * final UserDetails userDetails =
		 * userService.loadUserByUsername(jwtRequest.getUsername());
		 */

		//final String token = jwtUtility.generateToken(jwtRequest.getUsername());

	//	System.out.println("token" + token);

		//return null;
	}

	
}
