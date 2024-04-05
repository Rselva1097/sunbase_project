package com.customer.customerapplication.service;


import com.customer.customerapplication.model.Login;
import com.customer.customerapplication.repository.CustomerRepository;

import com.customer.customerapplication.security.JwtTokenProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



@Service
public class AuthServiceImple implements AuthService
{

	private AuthenticationManager authtentication;
	
	private CustomerRepository customerrepo;

	private PasswordEncoder passwordEncoder;
	
	private JwtTokenProvider jwtTokenProvider;
	
	

	
	public AuthServiceImple(AuthenticationManager authtentication, CustomerRepository customerrepo,
                            PasswordEncoder passwordEncoder, JwtTokenProvider jwtTokenProvider){
		
		
		this.authtentication = authtentication;
		this.customerrepo = customerrepo;
		this.passwordEncoder = passwordEncoder;
		this.jwtTokenProvider = jwtTokenProvider;
	}


	public String login(Login login) {
		Authentication auth = authtentication.authenticate
				(new UsernamePasswordAuthenticationToken(
				login.getUsernameOrEmail(),login.getPassword()));
		
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		String token = jwtTokenProvider.generateToken(auth);
		return token;
	}

}

