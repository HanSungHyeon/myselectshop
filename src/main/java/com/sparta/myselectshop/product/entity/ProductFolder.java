package com.sparta.myselectshop.product.entity;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.sparta.myselectshop.folder.entity.Folder;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "product_folder")
@Getter
@Setter
@NoArgsConstructor
public class ProductFolder {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	@ManyToOne
	@JoinColumn(name = "folder_id")
	private Folder folder;

	public ProductFolder(Product product, Folder folder) {
		this.product = product;
		this.folder = folder;
	}
}
