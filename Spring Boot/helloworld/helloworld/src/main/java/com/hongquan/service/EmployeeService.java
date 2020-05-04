package com.hongquan.service;

import java.util.List;

import com.hongquan.model.Employee;

public interface EmployeeService {
	public List<Employee> getAllEmployees();
	public void addEmployee(Employee employee);
}
