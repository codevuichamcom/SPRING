package com.hongquan.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.hongquan.Model.User;
import com.hongquan.service.UserService;
import com.hongquan.validator.UserValidator;

@Controller
public class UserController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	UserValidator userValidator;

	
	@RequestMapping(value = "danh-sach-khach-hang", method = RequestMethod.GET)
	public String getAllUser(HttpServletRequest request) {
		
		List<User> users = userService.getAllUsers();
		request.setAttribute("users", users);
		return "user/listUsers";
	}
	
	@RequestMapping(value = "chi-tiet-khach-hang/{userId}", method = RequestMethod.GET)
	public String viewUser(HttpServletRequest request,
			@PathVariable(name = "userId") int userId) {
		request.setAttribute("user", userService.getUserById(userId));
		return "user/viewUser";
	}
	
	@RequestMapping(value = "/them-khach-hang", method = RequestMethod.GET)
	public String addUser(HttpServletRequest request) {
		
		request.setAttribute("user", new User());
		return "user/addUser";
	}
	
	@RequestMapping(value = "/them-khach-hang", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "view/addUser";
		}

		userService.addUser(user); //luu cao csdl
		
		return "redirect:/danh-sach-khach-hang";
	}
	
	@RequestMapping(value = "/xoa-khach-hang/{userId}", method = RequestMethod.GET)
	public String deleteUser(HttpServletRequest request,
			@PathVariable(name = "userId") int userId) {
		
		userService.deleteUser(userId);
		return "redirect:/danh-sach-khach-hang";
	}
	
	@RequestMapping(value = "/sua-khach-hang/{userId}", method = RequestMethod.GET)
	public String editUser(HttpServletRequest request,
			@PathVariable(name = "userId") int userId) {
		
		request.setAttribute("user", userService.getUserById(userId));
		
		return "user/editUser";
	}
	
	@RequestMapping(value = "/sua-khach-hang", method = RequestMethod.POST)
	public String editUser(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			return "view/editUser";
		}

		userService.updateUser(user); //luu cao csdl
		
		return "redirect:/danh-sach-khach-hang";
	}
}
