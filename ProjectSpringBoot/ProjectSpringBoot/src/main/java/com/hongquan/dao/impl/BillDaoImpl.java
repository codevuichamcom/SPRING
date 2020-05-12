package com.hongquan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.BillDao;
import com.hongquan.entity.Bill;

@Repository
@Transactional
public class BillDaoImpl implements BillDao {

	@PersistenceContext
	EntityManager entityManager;
	
	@Override
	public void addBill(Bill bill) {
		entityManager.persist(bill);
		
	}

	@Override
	public void deleteBill(Bill bill) {
		entityManager.remove(bill);
	}

	@Override
	public void updateBill(Bill bill) {
		entityManager.merge(bill);
	}

	@Override
	public Bill getBillById(int id) {
		return entityManager.find(Bill.class, id);
	}

	@Override
	public List<Bill> search(String keyword, int start, int length) {
		String jql ="SELECT b from Bill b where b.user.name like :keyword";
		Query query = entityManager.createQuery(jql,Bill.class);
		query.setParameter("keyword","%"+ keyword+"%");
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int countBillWhenSearch(String keyword) {
			String jql ="SELECT b from Bill b where b.user.name like :keyword";
			Query query = entityManager.createQuery(jql,Bill.class);
			query.setParameter("keyword","%"+ keyword+"%");
			return query.getResultList().size();
	}

	@Override
	public List<Bill> searchByUsername(String username, int start, int length) {
		String jql ="SELECT b from Bill b where b.user.username = :username";
		Query query = entityManager.createQuery(jql,Bill.class);
		query.setParameter("username",username);
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int countBillWhenSearchByUsername(String username) {
		String jql ="SELECT b from Bill b where b.user.username = :username";
		Query query = entityManager.createQuery(jql,Bill.class);
		query.setParameter("username",username);
		return query.getResultList().size();
	}
	
	

}
