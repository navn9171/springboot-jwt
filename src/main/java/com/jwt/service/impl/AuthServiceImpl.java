package com.jwt.service.impl;

import com.jwt.DTO.LoginRequestDTOEntity;
import com.jwt.DTO.LoginResponseDTOEntity;
import com.jwt.entity.Role;
import com.jwt.entity.User;
import com.jwt.repository.RoleRepository;
import com.jwt.repository.UserRepository;
import com.jwt.security.JwtTokenProvider;
import com.jwt.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class AuthServiceImpl implements AuthService {

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Override
	public boolean registerUser(User user) {
		boolean flag = false;

		try {
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			Role role = roleRepository.findByName("ROLE_USER");
			Set<Role> roles = Set.of(role);
			user.setRoles(roles);
			System.out.println(roles);
			System.out.println(user);
			userRepository.save(user);
			flag = true;
		}catch (Exception e){
			e.printStackTrace();
			flag = false;
		}

		return flag;
	}

	@Override
	public LoginResponseDTOEntity login(LoginRequestDTOEntity loginRequestDTOEntity) {
		Authentication authenticate = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequestDTOEntity.getEmail(),
						loginRequestDTOEntity.getPassword()
				)
		);
		SecurityContextHolder.getContext().setAuthentication(authenticate);

		String token = jwtTokenProvider.generateToken(authenticate);

		LoginResponseDTOEntity loginResponseDTOEntity = new LoginResponseDTOEntity();
		loginResponseDTOEntity.setToken(token);

		return loginResponseDTOEntity;
	}
}
