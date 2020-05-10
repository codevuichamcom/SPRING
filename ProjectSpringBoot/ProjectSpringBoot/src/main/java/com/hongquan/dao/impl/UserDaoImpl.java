package com.hongquan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.UserDao;
import com.hongquan.entity.User;

@Repository
@Transactional
public class UserDaoImpl implements UserDao {

	@PersistenceContext
	EntityManager entityManager;
	

	@Override
	public void addUser(User user) {
		entityManager.persist(user);
	}

	@Override
	public void deleteUser(User user) {
		entityManager.remove(user);
	}

	@Override
	public void updateUser(User user) {
		entityManager.merge(user);
	}

	@Override
	public User getUserByUsename(String username) {
		return entityManager.find(User.class, username);
	}

	@Override
	public List<User> search(String name, int start, int length) {
		String jql ="SELECT u from User u where name like :name";
		Query query = entityManager.createQuery(jql,User.class);
		query.setParameter("name","%"+ name+"%");
		query.setFirstResult(start);
		query.setMaxResults(length);
		return query.getResultList();
	}

	@Override
	public int countUserWhenSearch(String name) {
		String jql ="SELECT u from User u where name like :name";
		Query query = entityManager.createQuery(jql,User.class);
		query.setParameter("name","%"+ name+"%");
		return (int) query.getResultList().size();
	}

}
