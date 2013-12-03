package com.machine.vending.domain;

import java.math.BigDecimal;

import com.machine.vending.persistence.VendorEntity;

public class VendorItem {

	private Integer id;
	
	private String name;
	
	private Integer stock;
	
	private BigDecimal price;
	
	private String imagePath;
	
	public VendorItem() {
		super();
	}
	
	public VendorItem(VendorEntity entity) {
		setId(entity.getId());
		setName(entity.getName());
		setStock(entity.getStock());
		setPrice(entity.getPrice());
		setImagePath(entity.getImagePath());
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
}
