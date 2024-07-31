package com.sparta.myselectshop.product.repository;

import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.user.entity.User;

import java.util.List;

public interface ProductRepositoryCustom {
	List<ProductResDto> findAllByUser(User user);
}
