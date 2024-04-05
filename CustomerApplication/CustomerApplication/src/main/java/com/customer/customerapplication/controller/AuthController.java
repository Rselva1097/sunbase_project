package com.customer.customerapplication.controller;


import com.customer.customerapplication.model.JwtAuthResponse;
import com.customer.customerapplication.model.Login;
import com.customer.customerapplication.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private AuthService authservice;

	public AuthController(AuthService authservice) {
		
		this.authservice = authservice;
	}
	// for multiple values value ={"/login","/signup"}
	@PostMapping(value ={"/login","/signup"})
	public ResponseEntity<JwtAuthResponse> login(@RequestBody Login logindto){
		String response = authservice.login(logindto);
		
		JwtAuthResponse jwtAuthResponse = new JwtAuthResponse();
		jwtAuthResponse.setAccessToken(response);
		
		return  ResponseEntity.ok(jwtAuthResponse);
	}

}
