package com.hongquan.dao;

import java.util.List;

import com.hongquan.entity.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();

	public void addEmployee(Employee employee);

	public void deleteEmployee(Employee employee);

	public void updateEmployee(Employee employee);
	
	public Employee getEmployee(int id);
	
}
