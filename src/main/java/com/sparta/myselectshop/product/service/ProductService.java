package com.sparta.myselectshop.product.service;

import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductService {

	private ProductRepository productRepository;

	//등록
	public Product creatProduct(Product product) {
		return productRepository.save(product);
	}
}
