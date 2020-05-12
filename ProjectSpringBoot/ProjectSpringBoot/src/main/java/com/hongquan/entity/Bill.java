package com.hongquan.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;
import java.util.List;


/**
 * The persistent class for the bill database table.
 * 
 */
@Entity
@Table(name = "bill")
public class Bill implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="buy_date")
	private Date buyDate;

	@Column(name="discount_percent")
	private float discountPercent;

	@Column(name="price_total")
	private float priceTotal;

	//bi-directional many-to-one association to User
	@ManyToOne
	@JoinColumn(name="username")
	private User user;

	//bi-directional many-to-one association to BillProduct
	@OneToMany(mappedBy="bill")
	private List<BillProduct> billProducts;

	public Bill() {
	}
	
	

	public Bill(int id, Date buyDate, float discountPercent, float priceTotal, User user,
			List<BillProduct> billProducts) {
		super();
		this.id = id;
		this.buyDate = buyDate;
		this.discountPercent = discountPercent;
		this.priceTotal = priceTotal;
		this.user = user;
		this.billProducts = billProducts;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getBuyDate() {
		return this.buyDate;
	}

	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}

	public float getDiscountPercent() {
		return this.discountPercent;
	}

	public void setDiscountPercent(float discountPercent) {
		this.discountPercent = discountPercent;
	}

	public float getPriceTotal() {
		return this.priceTotal;
	}

	public void setPriceTotal(float priceTotal) {
		this.priceTotal = priceTotal;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public List<BillProduct> getBillProducts() {
		return this.billProducts;
	}

	public void setBillProducts(List<BillProduct> billProducts) {
		this.billProducts = billProducts;
	}

	public BillProduct addBillProduct(BillProduct billProduct) {
		getBillProducts().add(billProduct);
		billProduct.setBill(this);

		return billProduct;
	}

	public BillProduct removeBillProduct(BillProduct billProduct) {
		getBillProducts().remove(billProduct);
		billProduct.setBill(null);

		return billProduct;
	}

}