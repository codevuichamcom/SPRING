package com.hongquan.model;

import java.util.Date;
import java.util.List;

import com.hongquan.entity.BillProduct;

public class BillDTO {
	private int id;
	
	private Date buyDate;

	private float discountPercent;

	private float priceTotal;

	private UserDTO user;

	private List<BillProductDTO> billProducts;
	
	public BillDTO() {
		// TODO Auto-generated constructor stub
	}

	public BillDTO(int id, Date buyDate, float discountPercent, float priceTotal, UserDTO user,
			List<BillProductDTO> billProducts) {
		super();
		this.id = id;
		this.buyDate = buyDate;
		this.discountPercent = discountPercent;
		this.priceTotal = priceTotal;
		this.user = user;
		this.billProducts = billProducts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBuyDate() {
		return buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public float getDiscountPercent() {
		return discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getPriceTotal() {
		return priceTotal;
	}

	public void setPriceTotal(float priceTotal) {
		this.priceTotal = priceTotal;
	}

	public UserDTO getUser() {
		return user;
	}

	public void setUser(UserDTO user) {
		this.user = user;
	}

	public List<BillProductDTO> getBillProducts() {
		return billProducts;
	}

	public void setBillProducts(List<BillProductDTO> billProducts) {
		this.billProducts = billProducts;
	}

	
	
	
	
}
