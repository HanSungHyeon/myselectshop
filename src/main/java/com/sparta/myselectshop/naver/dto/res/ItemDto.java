package com.sparta.myselectshop.naver.dto.res;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ItemDto {
	private String title;
	private String link;
	private String image;
	private int lprice;
}
