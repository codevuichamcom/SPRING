package com.hongquan.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.EmployeeDao;
import com.hongquan.entity.Employee;
import com.hongquan.entity.Phone;
import com.hongquan.model.EmployeeDTO;
import com.hongquan.service.EmployeeService;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	EmployeeDao employeeDao;

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = employeeDao.getAllEmployees();
		List<EmployeeDTO> employeeDTOs = new ArrayList<EmployeeDTO>();
		for (Employee e : employees) {

			EmployeeDTO employeeDTO = new EmployeeDTO(e.getId(), e.getName(), e.getAge());

			// get phone number
			List<String> phoneList = new ArrayList<String>();
			for (Phone phone : e.getPhones()) {
				phoneList.add(phone.getPhone());
			}
			employeeDTO.setPhones(phoneList);
			employeeDTOs.add(employeeDTO);
		}
		return employeeDTOs;
	}

	@Override
	public void addEmployee(EmployeeDTO employeeDTO) {
		Employee e = new Employee();
		e.setName(employeeDTO.getName());
		e.setAge(employeeDTO.getAge());
		
		//Gia su them so dien thoai
		
		List<Phone> phones = new ArrayList<Phone>();
		for(String s : employeeDTO.getPhones()) {
			Phone phone = new Phone();
			phone.setPhone(s);
			phone.setEmployee(e);
			
			phones.add(phone);
		}
		e.setPhones(phones);
		
		employeeDao.addEmployee(e);
	}

	@Override
	public void deleteEmployee(int id) {
		// TODO Auto-generated method stub
		Employee employee = employeeDao.getEmployee(id);
		if (employee != null) {
			employeeDao.deleteEmployee(employee);
		}
	}

	@Override
	public void updateEmployee(EmployeeDTO employeeDTO) {
		// TODO Auto-generated method stub
		Employee employee = employeeDao.getEmployee(employeeDTO.getId());
		if (employee != null) {
			employee.setName(employeeDTO.getName());
			employee.setAge(employeeDTO.getAge());

			employeeDao.updateEmployee(employee);
		}
	}

}
