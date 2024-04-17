package com.jwt.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("common")
	public String commonEndPoint(){
		return "Hello Dear";
	}

	@PreAuthorize("hasRole('USER')")
	@GetMapping("/user")
	public String userEndPoint(){
		return "Welcome User";
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/admin")
	public String adminEndPoint(){
		return "Welcome Admin";
	}
}
