package com.sparta.myselectshop.user.service;

import com.sparta.myselectshop.user.dto.req.SignupReqDto;
import com.sparta.myselectshop.user.entity.User;
import com.sparta.myselectshop.user.entity.UserRoleEnum;
import com.sparta.myselectshop.user.mapper.UserMapper;
import com.sparta.myselectshop.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	private final UserMapper userMapper;

	@Value("${key}")
	private String ADMIN_TOKEN;

	public void signup(SignupReqDto dto) throws IllegalAccessException {
		verifiedUsername(dto.getUsername());
		verifiedEmail(dto.getEmail());
		UserRoleEnum role = verifiedUserRole(dto.isAdmin(), dto.getAdminToken());

		User user = userMapper.toUser(dto, role);
		user.setPassword(passwordEncoder.encode(user.getPassword()));

		userRepository.save(user);
	}

	//회원 중복 확인
	public void verifiedUsername(String username) throws IllegalAccessException {
		findUser(username, null);
	}

	//회원 email 중복 확인
	public void verifiedEmail(String email) throws IllegalAccessException {
		findUser(null, email);
	}

	//사용자 조회
	public void findUser(String username, String email) throws IllegalAccessException {
		Optional<User> findUser = userRepository.findUserByUsernameOrEmail(username, email);

		if(findUser.isPresent()) {
			throw new IllegalAccessException("중복된 값이 존재합니다.");
		}
	}

	//사용자 ROLE 확인
	public UserRoleEnum verifiedUserRole(boolean isAdmin, String adminToken) throws IllegalAccessException {
		if(isAdmin) {
			if (!adminToken.equals(ADMIN_TOKEN)) {
				throw new IllegalAccessException("관리자 암호가 틀려 등록이 불가능합니다.");
			}
			return UserRoleEnum.ADMIN;
		}
		return UserRoleEnum.USER;
	}
}
