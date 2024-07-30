package com.sparta.myselectshop.user.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserInfo {
	private String username;
	private boolean isAdmin;
}
