package com.sparta.myselectshop.product.controller;

import com.sparta.myselectshop.product.dto.req.ProductMyPriceReqDto;
import com.sparta.myselectshop.product.dto.req.ProductReqDto;
import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.mapper.ProductMapper;
import com.sparta.myselectshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
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

	//조회
	@GetMapping("/products")
	public ResponseEntity findAllProductList() {
		List<ProductResDto> res = productMapper.toProductResDtoList(productService.findAllProduct());

		return ResponseEntity.ok(res);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceReqDto dto) throws IllegalAccessException {
		ProductResDto res = productMapper.toProductResDto(productService.updateProduct(id, dto));

		return ResponseEntity.ok(res);
	}

}
