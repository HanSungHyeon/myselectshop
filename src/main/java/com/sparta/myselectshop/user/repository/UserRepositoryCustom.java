package com.sparta.myselectshop.user.repository;

import com.sparta.myselectshop.user.entity.User;

import java.util.Optional;

public interface UserRepositoryCustom {
	Optional<User> findUserByUsernameOrEmail(String username, String email);
}
