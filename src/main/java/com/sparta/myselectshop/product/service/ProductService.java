package com.sparta.myselectshop.product.service;

import com.sparta.myselectshop.product.dto.req.ProductMyPriceReqDto;
import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	public static final int MIN_MY_PRICE = 100;

	//등록
	public Product creatProduct(Product product) {
		log.info("관심 상품 : {}", product.getTitle());
		return productRepository.save(product);
	}

	//전부 조회
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	//최저가 업데이트
	public Product updateProduct(Long id, ProductMyPriceReqDto dto) {
		if(MIN_MY_PRICE > dto.getMyprice()) {
			throw new IllegalArgumentException("유효하지 않은 관심 가격입니다. 최소 " + MIN_MY_PRICE + " 원 이상으로 설정해 주세요.");
		}

		Product findProduct = findProduct(id);
		findProduct.setMyprice(dto.getMyprice());
		return productRepository.save(findProduct);
	}

	//단일 상품 조회
	public Product findProduct(Long id) {
		Optional<Product> findProduct = productRepository.findById(id);

		return findProduct
				.orElseThrow(() -> new NullPointerException("해당 상품을 찾을 수 없습니다."));

	}
}
