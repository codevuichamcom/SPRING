package com.hongquan.model;

import org.springframework.web.multipart.MultipartFile;

public class ProductDTO {
	private int id;

	private String name;
	
	private float price;
	
	private int quantity;
	
	private String description;

	private String image;
	
	private CategoryDTO category;
	
	private MultipartFile file;
	
	public ProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public ProductDTO(int id, String name, float price, int quantity, String description, String image , CategoryDTO category) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.quantity = quantity;
		this.description = description;
		this.image = image;
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public CategoryDTO getCategory() {
		return category;
	}

	public void setCategory(CategoryDTO category) {
		this.category = category;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
	
	
	
}
