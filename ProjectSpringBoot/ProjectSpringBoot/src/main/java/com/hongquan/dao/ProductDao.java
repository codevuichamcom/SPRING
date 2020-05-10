package com.hongquan.dao;

import java.util.List;

import com.hongquan.entity.Product;

public interface ProductDao {

	public void addProduct(Product product);

	public void deleteProduct(Product product);

	public void updateProduct(Product product);

	public Product getProductById(int id);

	public List<Product> search(String keyword,int categoryId, int start, int length);

	public int countProductWhenSearch(String keyword, int categoryId);
}
