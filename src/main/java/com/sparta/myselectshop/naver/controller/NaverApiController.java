package com.sparta.myselectshop.naver.controller;

import com.sparta.myselectshop.naver.dto.res.ItemListDto;
import com.sparta.myselectshop.naver.service.NaverService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class NaverApiController {
	private final NaverService naverService;

	@GetMapping("/search")
	public ResponseEntity searchItemList(@RequestParam String query) {
		ItemListDto res = naverService.findItemList(query);
		return ResponseEntity.ok(res.getItems());
	}

}
