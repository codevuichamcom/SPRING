package com.hongquan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.CategoryDTO;
import com.hongquan.model.ProductDTO;
import com.hongquan.model.UserDTO;
import com.hongquan.service.CategoryService;
import com.hongquan.service.ProductService;
import com.hongquan.service.UserService;
import com.hongquan.utils.PasswordGenerator;

@Controller
public class MainController {

	@Autowired
	UserService userService;

	@Autowired
	ProductService productService;
	
	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/")
	public String home(HttpServletRequest request) {
		List<ProductDTO> listProducts = productService.search("",-1, 0, 100);

		List<CategoryDTO> listCategories = categoryService.getAllCategorys(0, 100);
		request.setAttribute("listProducts", listProducts);
		request.setAttribute("listCategories", listCategories);

		return "client/index";
	}

	@GetMapping(value = "/login")
	public String login(HttpServletRequest request, @RequestParam(name = "e", required = false) String error) {
		if (error != null) {
			request.setAttribute("e", error);
		}
		return "client/login";
	}

	@GetMapping(value = "/register")
	public String register() {
		return "client/register";
	}

	@PostMapping(value = "/register")
	public String addUser(HttpServletRequest request, @ModelAttribute(name = "user") UserDTO user) {
		user.setEnable((byte) 1);
		user.setRole("ROLE_MEMBER");
		user.setPassword(PasswordGenerator.encode(user.getPassword()));
		userService.addUserDTO(user);
		return "redirect:/";
	}
}
