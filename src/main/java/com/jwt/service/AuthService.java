package com.jwt.service;

import com.jwt.DTO.LoginRequestDTOEntity;
import com.jwt.DTO.LoginResponseDTOEntity;
import com.jwt.entity.User;

public interface AuthService {
	boolean registerUser(User user);

	LoginResponseDTOEntity login(LoginRequestDTOEntity loginRequestDTOEntity);
}
