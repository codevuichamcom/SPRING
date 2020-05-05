package com.hongquan.service;

import java.util.List;

import com.hongquan.model.EmployeeDTO;

public interface EmployeeService {
	public List<EmployeeDTO> getAllEmployees();

	public void addEmployee(EmployeeDTO employeeDTO);

	public void deleteEmployee(int id);

	public void updateEmployee(EmployeeDTO employeeDTO);
}
