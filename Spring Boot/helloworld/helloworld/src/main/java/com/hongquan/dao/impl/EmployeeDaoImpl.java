package com.hongquan.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.EmployeeDao;
import com.hongquan.entity.Employee;
@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@PersistenceContext
	EntityManager entityManager;
	
	
	@Override
	public List<Employee> getAllEmployees() {
		//Thao tac với tên class thay cho tên bảng
		String jql ="SELECT e from Employee e";
		return entityManager.createQuery(jql,Employee.class).getResultList();
	}

	@Override
	public void addEmployee(Employee employee) {
		entityManager.persist(employee);
	}

	@Override
	public void deleteEmployee(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.remove(employee);
	}

	@Override
	public void updateEmployee(Employee employee) {
		// TODO Auto-generated method stub
		entityManager.merge(employee);
	}

	@Override
	public Employee getEmployee(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Employee.class, id);
	}

}
