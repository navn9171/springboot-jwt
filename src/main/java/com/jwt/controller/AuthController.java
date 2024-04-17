package com.jwt.controller;

import com.jwt.DTO.LoginRequestDTOEntity;
import com.jwt.DTO.LoginResponseDTOEntity;
import com.jwt.entity.User;
import com.jwt.service.AuthService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

	AuthService authService;

	@PostMapping("register")
	public ResponseEntity<?> register(@RequestBody User user){
		boolean b = authService.registerUser(user);
		if(b)
			return new ResponseEntity<>("User Registered Successfully", HttpStatus.CREATED);
		else
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
	}

	@PostMapping("login")
	public ResponseEntity<?> login(@RequestBody LoginRequestDTOEntity loginRequestDTOEntity){
		try {
			LoginResponseDTOEntity login = authService.login(loginRequestDTOEntity);
			return new ResponseEntity<>(login, HttpStatus.OK);
		}catch(Exception e){
			e.printStackTrace();
			return new ResponseEntity<>("Something went wrong.", HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
