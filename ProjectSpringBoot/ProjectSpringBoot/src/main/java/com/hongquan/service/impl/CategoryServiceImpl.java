package com.hongquan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongquan.dao.CategoryDao;
import com.hongquan.entity.Category;
import com.hongquan.model.CategoryDTO;
import com.hongquan.service.CategoryService;

@Transactional
@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryDao categoryDao;
	
	@Override
	public List<CategoryDTO> getAllCategorys(int start, int length) {
		List<Category> lisCategories = categoryDao.getAllCategorys(start, length);
		List<CategoryDTO> listCategoryDTOs = new ArrayList<CategoryDTO>();
		for (Category category : lisCategories) {
			CategoryDTO categoryDTO = new CategoryDTO(category.getId(), category.getName());
			listCategoryDTOs.add(categoryDTO);
		}
		return listCategoryDTOs;
	}
	
}
