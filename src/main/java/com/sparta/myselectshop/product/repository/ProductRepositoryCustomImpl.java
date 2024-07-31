package com.sparta.myselectshop.product.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.product.entity.QProduct;
import com.sparta.myselectshop.user.entity.QUser;
import com.sparta.myselectshop.user.entity.User;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static com.sparta.myselectshop.product.entity.QProduct.product;
import static com.sparta.myselectshop.user.entity.QUser.user;

@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {
	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<ProductResDto> findAllByUser(User user) {
		return jpaQueryFactory
				.select(Projections.bean(ProductResDto.class,
						product.id,
						product.link,
						product.image,
						product.lprice,
						product.myprice,
						product.title
						))
				.from(product)
				.where(product.user.eq(user))
				.fetch();
	}
}
