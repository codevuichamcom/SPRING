package com.hongquan.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


/**
 * The persistent class for the bill_product database table.
 * 
 */
@Entity
@Table(name="bill_product")
public class BillProduct implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product product;

	private int quantity;

	@Column(name="unit_price")
	private float unitPrice;

	@ManyToOne
	@JoinColumn(name = "bill_id")
	private Bill bill;

	public BillProduct() {
	}
	
	

	public BillProduct(int id, Product product, int quantity, float unitPrice, Bill bill) {
		super();
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.unitPrice = unitPrice;
		this.bill = bill;
	}



	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
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

	public Bill getBill() {
		return bill;
	}

	public void setBill(Bill bill) {
		this.bill = bill;
	}

	

}