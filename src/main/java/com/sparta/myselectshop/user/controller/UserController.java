package com.sparta.myselectshop.user.controller;

import com.sparta.myselectshop.user.dto.req.SignupReqDto;
import com.sparta.myselectshop.user.entity.UserRoleEnum;
import com.sparta.myselectshop.user.mapper.UserMapper;
import com.sparta.myselectshop.user.service.UserDetailsImpl;
import com.sparta.myselectshop.user.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {
	private final UserService userService;
	private final UserMapper userMapper;

	//페이지 이동
	@GetMapping("/user/login-page")
	public String loginPage() {
		return "login";
	}

	//페이지 이동
	@GetMapping("/user/signup")
	public String signupPage() {
		return "signup";
	}

	//회원 가입
	@PostMapping("/user/signup")
	public String signup(@Valid SignupReqDto dto, BindingResult bindingResult) throws IllegalAccessException {
		List<FieldError> fieldErrors = bindingResult.getFieldErrors();
		if (!fieldErrors.isEmpty()) {
			for (FieldError fieldError : bindingResult.getFieldErrors()) {
				log.error(fieldError.getField() + " 필드 : " + fieldError.getDefaultMessage());
			}
			return "redirect:/api/user/signup";
		}
		userService.signup(dto);

		return "redirect:/api/user/login-page";
	}

	//회원 정보 받기
	@GetMapping("/user-info")
	@ResponseBody
	public ResponseEntity getUserInfo(@AuthenticationPrincipal UserDetailsImpl userDetails) {
		String username = userDetails.getUser().getUsername();
		UserRoleEnum role = userDetails.getUser().getRole();
		boolean isAdmin = role == UserRoleEnum.ADMIN;

		return ResponseEntity.ok(userMapper.toUserInfo(username, isAdmin));
	}
}
