package com.hongquan.dao;

import java.util.List;

import com.hongquan.model.Employee;

public interface EmployeeDao {
	public List<Employee> getAllEmployees();
	public void addEmployee(Employee employee);
}
