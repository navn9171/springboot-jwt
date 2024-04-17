package com.jwt.DTO;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class LoginRequestDTOEntity {
	private String email;
	private String password;
}
