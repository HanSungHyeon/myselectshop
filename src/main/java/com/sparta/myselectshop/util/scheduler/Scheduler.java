package com.sparta.myselectshop.util.scheduler;

import com.sparta.myselectshop.naver.dto.res.ItemDto;
import com.sparta.myselectshop.naver.dto.res.ItemListDto;
import com.sparta.myselectshop.naver.service.NaverService;
import com.sparta.myselectshop.product.entity.Product;
import com.sparta.myselectshop.product.repository.ProductRepository;
import com.sparta.myselectshop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@Component
@RequiredArgsConstructor
public class Scheduler {
	private final NaverService naverService;
	private final ProductService productService;
	private final ProductRepository productRepository;

	@Scheduled(cron = "0 0 1 * * *")
	public void updatePrice() throws InterruptedException {
		log.info("스케줄러 실행");
		List<Product> productList = productService.findAllProduct();

		for (Product product : productList) {
			TimeUnit.SECONDS.sleep(1);

			List<ItemDto> itemList = naverService.findItemList(product.getTitle()).getItems();

			if (!itemList.isEmpty()) {
				ItemDto itemDto = itemList.get(0);

				try {
					productService.updateBySearch(product.getId(), itemDto);
				} catch (Exception e) {
					log.error(product.getId() + " : " + e.getMessage());

				}
			}
		}
	}
}
