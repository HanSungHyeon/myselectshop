package com.sparta.myselectshop.product.controller;

import com.sparta.myselectshop.product.dto.req.ProductReqDto;
import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.mapper.ProductMapper;
import com.sparta.myselectshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
	private final ProductService productService;
	private final ProductMapper productMapper;

	//등록
	@PostMapping("/products")
	public ResponseEntity createProduct(@RequestBody ProductReqDto dto) {
		Product product = productService.creatProduct(productMapper.toProduct(dto));
		ProductResDto res = productMapper.toProductResDto(product);
		return ResponseEntity.ok(res);
	}

}
