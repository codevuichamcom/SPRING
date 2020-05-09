package com.hongquan.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the category database table.
 * 
 */
@Entity
@Table(name = "category")
public class Category implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	private String name;

	public Category() {
	}
	

	public Category(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}


	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}