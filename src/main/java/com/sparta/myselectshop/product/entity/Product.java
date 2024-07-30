package com.sparta.myselectshop.product.entity;

import com.sparta.myselectshop.util.TimeStamped;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Product extends TimeStamped {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "product_id")
	private Long id;

	private String title;

	private String image;

	private int lprice;

	private int myprice = 0;

	private String link;

}
