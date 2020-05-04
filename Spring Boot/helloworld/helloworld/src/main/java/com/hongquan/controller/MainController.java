package com.hongquan.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.Employee;
import com.hongquan.service.EmployeeService;

@Controller
public class MainController {
	
//	//Doc noi dung trong file message.properties
//	@Autowired
//	MessageSource messageSource;
	
	//Doc noi dung tu file application.properties - C1:
//	@Value("${message}")
//	String message;
	
	//Cx co the doc noi dung tu file application.properties bang cach autowired environment - C2:
	@Autowired
	Environment environment;
	
	
//	List<Employee> employees = new ArrayList<Employee>(); 
	
	@Autowired
	EmployeeService employeeService;
	
	@GetMapping(value = "/")
	public String hello(HttpServletRequest request) {
		request.setAttribute("msg", environment.getProperty("message"));
		return "index";
	}
	
	@GetMapping(value = "/employees")
	public String employees(HttpServletRequest request,Model model) {
		
		List<Employee> employees = employeeService.getAllEmployees();
		request.setAttribute("employees", employees);
		model.addAttribute("employee", new Employee(1,"Demo",23));
		return "employees";
	}
	
	@PostMapping(value = "/employee")
	public String addEmployee(HttpServletRequest request,
			@ModelAttribute(name = "employee") Employee employee) {
		
		employeeService.addEmployee(employee);
		return "redirect:/employees";
	}
}
