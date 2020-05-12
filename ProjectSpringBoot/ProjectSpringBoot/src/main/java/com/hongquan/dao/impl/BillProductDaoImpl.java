package com.hongquan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.hongquan.dao.BillProductDao;
import com.hongquan.entity.BillProduct;

@Repository
@Transactional
public class BillProductDaoImpl implements BillProductDao {
	
	@PersistenceContext
	EntityManager entityManager;

	@Override
	public void addBillProduct(BillProduct billProduct) {
		entityManager.persist(billProduct);
	}

	@Override
	public void deleteBillProduct(BillProduct billProduct) {
		entityManager.remove(billProduct);
	}

	@Override
	public void updateBillProduct(BillProduct billProduct) {
		entityManager.merge(billProduct);
	}

	@Override
	public BillProduct getBillProductById(int id) {
		return entityManager.find(BillProduct.class, id);
	}

	@Override
	public List<BillProduct> search(String keyword, int start, int length) {
		String jql ="SELECT bp from BillProduct bp";
		Query query = entityManager.createQuery(jql,BillProduct.class);
//		query.setParameter("keyword","%"+ keyword+"%");
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int countBillProductWhenSearch(String keyword) {
		String jql ="SELECT bp from BillProduct bp";
		Query query = entityManager.createQuery(jql,BillProduct.class);
		return query.getResultList().size();
	}

	
}
