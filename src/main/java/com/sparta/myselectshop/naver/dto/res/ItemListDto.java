package com.sparta.myselectshop.naver.dto.res;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ItemListDto {
	private List<ItemDto> items;
}
