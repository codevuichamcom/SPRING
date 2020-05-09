package com.hongquan.service;

import java.util.List;

import com.hongquan.model.CategoryDTO;

public interface CategoryService {
	public List<CategoryDTO> getAllCategorys(int start, int length);
}
