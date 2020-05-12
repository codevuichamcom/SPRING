package com.hongquan.model;

public class BillProductDTO {
	private int id;

	private ProductDTO product;

	private int quantity;

	private float unitPrice;

	private BillDTO bill;

	public BillProductDTO() {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ProductDTO getProduct() {
		return product;
	}

	public void setProduct(ProductDTO product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(float unitPrice) {
		this.unitPrice = unitPrice;
	}

	public BillDTO getBill() {
		return bill;
	}

	public void setBill(BillDTO bill) {
		this.bill = bill;
	}

}
