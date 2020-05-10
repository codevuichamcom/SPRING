package com.hongquan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.ProductDao;
import com.hongquan.entity.Product;

@Repository
@Transactional
public class ProductDaoImpl implements ProductDao {

	@PersistenceContext
	EntityManager entityManager;


	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);

	}

	@Override
	public void deleteProduct(Product product) {
		entityManager.remove(product);

	}

	@Override
	public void updateProduct(Product product) {
		entityManager.merge(product);
	}

	@Override
	public Product getProductById(int id) {
		return entityManager.find(Product.class, id);
	}

	@Override
	public List<Product> search(String keyword,int categoryId, int start, int length) {
		String jql ="SELECT p from Product p where (name like :keyword or description like :keyword)";
		if(categoryId!=-1) {
			jql+=" and category.id = :categoryId";
		}
		Query query = entityManager.createQuery(jql,Product.class);
		query.setParameter("keyword","%"+ keyword+"%");
		if(categoryId!=-1) {
			query.setParameter("categoryId",categoryId);
		}
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}
	
	@Override
	public int countProductWhenSearch(String keyword, int categoryId) {
		String jql ="SELECT p from Product p where (name like :keyword or description like :keyword)";
		if(categoryId!=-1) {
			jql+=" and category.id = :categoryId";
		}
		Query query = entityManager.createQuery(jql,Product.class);
		query.setParameter("keyword","%"+ keyword+"%");
		if(categoryId!=-1) {
			query.setParameter("categoryId",categoryId);
		}
		return query.getResultList().size();
	}

}
