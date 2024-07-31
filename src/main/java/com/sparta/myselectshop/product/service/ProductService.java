package com.sparta.myselectshop.product.service;

import com.sparta.myselectshop.folder.entity.Folder;
import com.sparta.myselectshop.folder.repository.ProductFolderRepository;
import com.sparta.myselectshop.folder.service.FolderService;
import com.sparta.myselectshop.naver.dto.res.ItemDto;
import com.sparta.myselectshop.product.dto.req.ProductMyPriceReqDto;
import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.entity.ProductFolder;
import com.sparta.myselectshop.product.mapper.ProductMapper;
import com.sparta.myselectshop.product.repository.ProductRepository;
import com.sparta.myselectshop.user.entity.User;
import com.sparta.myselectshop.user.entity.UserRoleEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;
	private final ProductFolderRepository productFolderRepository;
	private final FolderService folderService;
	private final ProductMapper productMapper;
	public static final int MIN_MY_PRICE = 100;

	//등록
	public Product creatProduct(Product product, User user) {
		log.info("관심 상품 : {}", product.getTitle());
		product.setUser(user);

		return productRepository.save(product);
	}

	//전부 조회 - 해당 유저
	public List<ProductResDto> findAllProduct(User user) {
		return productRepository.findAllByUser(user);
	}

	//전부 조회
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	//최저가 업데이트
	public Product updateProduct(Long id, ProductMyPriceReqDto dto) {
		if (MIN_MY_PRICE > dto.getMyprice()) {
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

	//스케줄러로 찾아서 업데이트
	public void updateBySearch(Long id, ItemDto dto) {
		Product findProduct = findProduct(id);

		findProduct.setLprice(dto.getLprice());
	}

	public Page<ProductResDto> getProducts(User user,
	                                       int page, int size, String sortBy, boolean isAsc) {
		// 페이징 처리
		Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sort = Sort.by(direction, sortBy);
		Pageable pageable = PageRequest.of(page, size, sort);

		// 사용자 권한 가져와서 ADMIN 이면 전체 조회, USER 면 본인이 추가한 부분 조회
		UserRoleEnum userRoleEnum = user.getRole();

		Page<Product> productList;

		if (userRoleEnum == UserRoleEnum.USER) {
			// 사용자 권한이 USER 일 경우
			productList = productRepository.findAllByUser(user, pageable);
		} else {
			productList = productRepository.findAll(pageable);
		}
		return productList.map(product -> productMapper.toProductResDto(product));
	}

	public void addFolder(Long productId, Long folderId, User user) {
		Product product = findProduct(productId);

		Folder folder = folderService.findFolder(folderId);

		if (!product.getUser().getId().equals(user.getId()) ||
				!folder.getUser().getId().equals(user.getId())) {
			throw new IllegalArgumentException("회원님의 관심상품이 아니거나, 회원님의 폴더가 아닙니다.");
		}

		findProductFolder(product, folder);

		productFolderRepository.save(new ProductFolder(product, folder));
	}

	public void findProductFolder(Product product, Folder folder) {
		Optional<ProductFolder> findProductFolder = productFolderRepository.findByProductAndFolder(product, folder);

		if(findProductFolder.isPresent()) {
			throw new IllegalArgumentException("중복된 폴더입니다.");
		}
	}

	public Page<ProductResDto> getProductsInFolder(Long folderId, int page, int size, String sortBy, boolean isAsc, User user) {

		// 페이징 처리
		Sort.Direction direction = isAsc ? Sort.Direction.ASC : Sort.Direction.DESC;
		Sort sort = Sort.by(direction, sortBy);
		Pageable pageable = PageRequest.of(page, size, sort);

		// 해당 폴더에 등록된 상품을 가져옵니다.
		Page<Product> products = productRepository.findAllByUserAndProductFolderList_FolderId(user, folderId, pageable);

		Page<ProductResDto> responseDtoList = products.map(productMapper::toProductResDto);

		return responseDtoList;
	}
}
