package com.hongquan.controller;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.EmployeeDTO;
import com.hongquan.service.EmployeeService;

@Controller
public class MainController {

//	//Doc noi dung trong file message.properties
//	@Autowired
//	MessageSource messageSource;

	// Doc noi dung tu file application.properties - C1:
//	@Value("${message}")
//	String message;

	// Cx co the doc noi dung tu file application.properties bang cach autowired
	// environment - C2:
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

	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(name = "e", required = false) String error) {
		if (error != null) {
			request.setAttribute("e", error);
		}
		return "login";
	}

	@GetMapping(value = "/employees")
	public String employees(HttpServletRequest request, Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		List<EmployeeDTO> employees = employeeService.getAllEmployees();
		request.setAttribute("employees", employees);
		model.addAttribute("employee", new EmployeeDTO(1, "Demo", 23));
		return "employees";
	}

	@PostMapping(value = "/employee")
	public String addEmployee(HttpServletRequest request, @ModelAttribute(name = "employee") EmployeeDTO employee) {

		employee.setPhones(Arrays.asList("0123456", "789123"));
		employeeService.addEmployee(employee);
		return "redirect:/employees";
	}

	// Với updatre thì xóa hết phone cũ đi và add phone mới vào
}
