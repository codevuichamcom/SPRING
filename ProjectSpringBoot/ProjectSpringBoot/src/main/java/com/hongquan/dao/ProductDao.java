package com.hongquan.dao;

import java.util.List;

import com.hongquan.entity.Product;
import com.hongquan.entity.User;

public interface ProductDao {
	public List<Product> getAllProducts(int start, int length);

	public int countAllProduct();

	public void addProduct(Product product);

	public void deleteProduct(Product product);

	public void updateProduct(Product product);

	public Product getProductById(int id);

	public List<Product> search(String keyword, int start, int length);

	public int countProductWhenSearch(String keyword);
}
