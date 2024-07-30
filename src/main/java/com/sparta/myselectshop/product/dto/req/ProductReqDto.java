package com.sparta.myselectshop.product.dto.req;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductReqDto {
	private String title;
	private String image;
	private String link;
	private int lprice;
}
