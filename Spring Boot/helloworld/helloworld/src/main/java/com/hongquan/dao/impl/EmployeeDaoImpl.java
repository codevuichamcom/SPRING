package com.hongquan.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hongquan.dao.EmployeeDao;
import com.hongquan.model.Employee;
@Repository
@Transactional
public class EmployeeDaoImpl implements EmployeeDao {

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public List<Employee> getAllEmployees() {
		String sql ="SELECT * FROM EMPLOYEE";
		
		return jdbcTemplate.query(sql, new RowMapper<Employee>() {

			@Override
			public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
				Employee employee = new Employee();
				employee.setId(rs.getInt("id"));
				employee.setName(rs.getString("name"));
				employee.setAge(rs.getInt("age"));
				return employee;
			}
			
		});
	}

	@Override
	public void addEmployee(Employee employee) {
		String sql = "INSERT INTO employee.employee (name, age) VALUES (?, ?)";
		jdbcTemplate.update(sql,employee.getName(),employee.getAge());
	}

}
