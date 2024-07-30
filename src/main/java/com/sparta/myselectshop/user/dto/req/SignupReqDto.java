package com.sparta.myselectshop.user.dto.req;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class SignupReqDto {
	@NotBlank
	private String username;

	@NotBlank
	private String password;

	@Email
	@NotBlank
	private String email;
	private boolean admin = false;
	private String adminToken = "";

}
