package com.sparta.myselectshop.user.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.myselectshop.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

import static com.sparta.myselectshop.user.entity.QUser.user;

@RequiredArgsConstructor
public class UserRepositoryCustomImpl implements UserRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public Optional<User> findUserByUsernameOrEmail(String username, String email) {
		return Optional.ofNullable(jpaQueryFactory
				.selectFrom(user)
				.where(usernameEq(username),
						emailEq(email))
				.fetchOne());
	}

	private BooleanExpression usernameEq(String username) {
		return username != null ? user.username.eq(username) : null;
	}

	private BooleanExpression emailEq(String email) {
		return email != null ? user.email.eq(email) : null;
	}
}
