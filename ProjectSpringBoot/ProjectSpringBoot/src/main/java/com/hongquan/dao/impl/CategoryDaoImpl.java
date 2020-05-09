package com.hongquan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.CategoryDao;
import com.hongquan.entity.Category;

@Repository
@Transactional
public class CategoryDaoImpl implements CategoryDao {
	
	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<Category> getAllCategorys(int start, int length) {
		String jql = "SELECT c from Category c";

		Query query = entityManager.createQuery(jql, Category.class);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

}
