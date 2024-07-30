package com.sparta.myselectshop.naver.service;

import com.sparta.myselectshop.naver.dto.res.ItemDto;
import com.sparta.myselectshop.naver.dto.res.ItemListDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
@Slf4j
public class NaverService {
	@Value("${api.naver.base}")
	private String baseUrl;

	@Value("${api.naver.client-id}")
	private String clientId;

	@Value("${api.naver.client-secret}")
	private String secret;

	public ItemListDto findItemList(String query) {
		WebClient webClient = WebClient.builder().baseUrl(baseUrl).build();

		return webClient.get()
				.uri(uriBuilder -> uriBuilder
						.path("/v1/search/shop.json")
						.queryParam("query", query)
						.queryParam("display", 15)
						.build())
				.header("X-Naver-Client-Id", clientId)
				.header("X-Naver-Client-Secret", secret)
				.retrieve()
				.bodyToMono(ItemListDto.class)
				.block();
	}
}
