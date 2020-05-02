package com.hongquan.controller;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongquan.model.Employee;

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
	
	
	List<Employee> employees = Arrays.asList(new Employee(1,"A",50),new Employee(2,"B",10),new Employee(3,"C",25)); 
	
	@GetMapping(value = "/")
	public String hello(HttpServletRequest request) {
		request.setAttribute("msg", environment.getProperty("message"));
		return "index";
	}
	
	@GetMapping(value = "/employees")
	public String employees(HttpServletRequest request) {
		request.setAttribute("employees", employees);
		return "employees";
	}
}
