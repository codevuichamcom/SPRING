package com.hongquan.dao;

import java.util.List;

import com.hongquan.entity.Category;

public interface CategoryDao {
	public List<Category> getAllCategorys(int start, int length);
}
