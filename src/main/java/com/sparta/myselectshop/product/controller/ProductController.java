package com.sparta.myselectshop.product.controller;

import com.sparta.myselectshop.product.dto.req.ProductMyPriceReqDto;
import com.sparta.myselectshop.product.dto.req.ProductReqDto;
import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.mapper.ProductMapper;
import com.sparta.myselectshop.product.service.ProductService;
import com.sparta.myselectshop.user.entity.User;
import com.sparta.myselectshop.user.mapper.UserMapper;
import com.sparta.myselectshop.user.service.UserDetailsImpl;
import com.sparta.myselectshop.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
	public ResponseEntity createProduct(@RequestBody ProductReqDto dto, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IllegalAccessException {
		User user = userDetails.getUser();

		Product product = productService.creatProduct(productMapper.toProduct(dto), user);
		ProductResDto res = productMapper.toProductResDto(product);
		return ResponseEntity.ok(res);
	}

	@GetMapping("/products")
	public Page<ProductResDto> getProducts(
			@RequestParam("page") int page,
			@RequestParam("size") int size,
			@RequestParam("sortBy") String sortBy,
			@RequestParam("isAsc") boolean isAsc,
			@AuthenticationPrincipal UserDetailsImpl userDetails) {
		// 응답 보내기
		return productService.getProducts(userDetails.getUser(),  page-1, size, sortBy, isAsc);
	}

	@PutMapping("/products/{id}")
	public ResponseEntity updateProduct(@PathVariable Long id, @RequestBody ProductMyPriceReqDto dto) throws IllegalAccessException {
		ProductResDto res = productMapper.toProductResDto(productService.updateProduct(id, dto));

		return ResponseEntity.ok(res);
	}

	@GetMapping("/admin/products")
	public ResponseEntity getAllProducts() {
		List<ProductResDto> res = productMapper.toProductResDtoList(productService.findAllProduct());

		return ResponseEntity.ok(res);
	}

	@PostMapping("/products/{productId}/folder")
	public void addFolder(
			@PathVariable Long productId,
			@RequestParam Long folderId,
			@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		productService.addFolder(productId, folderId, userDetails.getUser());
	}

	@GetMapping("/folders/{folderId}/products")
	public Page<ProductResDto> getProductsInFolder(
			@PathVariable Long folderId,
			@RequestParam int page,
			@RequestParam int size,
			@RequestParam String sortBy,
			@RequestParam boolean isAsc,
			@AuthenticationPrincipal UserDetailsImpl userDetails
	) {
		return productService.getProductsInFolder(
				folderId,
				page-1,
				size,
				sortBy,
				isAsc,
				userDetails.getUser()
		);
	}
}
