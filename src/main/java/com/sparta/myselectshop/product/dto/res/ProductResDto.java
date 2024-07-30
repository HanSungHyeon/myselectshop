package com.sparta.myselectshop.product.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductResDto {
	private Long id;
	private String title;
	private String link;
	private String image;
	private int lprice;
	private int myprice;
}
