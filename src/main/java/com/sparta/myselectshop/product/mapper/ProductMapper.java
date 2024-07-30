package com.sparta.myselectshop.product.mapper;

import com.sparta.myselectshop.product.dto.req.ProductReqDto;
import com.sparta.myselectshop.product.dto.res.ProductResDto;
import com.sparta.myselectshop.product.entity.Product;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
	Product toProduct(ProductReqDto dto);

	ProductResDto toProductResDto(Product product);
}
