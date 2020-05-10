package com.hongquan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.ProductDTO;
import com.hongquan.model.UserDTO;
import com.hongquan.service.UserService;
import com.hongquan.utils.PasswordGenerator;

@Controller
@RequestMapping(value = "/admin")
public class AdminUserController {

	@Autowired
	UserService userService;

	@GetMapping(value = "/user/users")
	public String getAllUser(HttpServletRequest request,@RequestParam(value = "page", required = false) Integer page) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		int totalProduct = userService.countUserWhenSearch("");
		int pageCount = (totalProduct % PAGE_SIZE == 0) ? totalProduct / PAGE_SIZE : totalProduct / PAGE_SIZE + 1;

		List<UserDTO> listUsers = userService.search("", 0, PAGE_SIZE);

		List<Integer> listCount = new ArrayList<Integer>();
		for(int i=1;i<=pageCount;i++) {
			listCount.add(i);
		}
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		return "admin/user/users";
	}

	@GetMapping(value = "/user/add-user")
	public String addUser() {
		return "admin/user/addUser";
	}

	@PostMapping(value = "/user/add-user")
	public String addUser(HttpServletRequest request, @ModelAttribute(name = "user") UserDTO user) {
		user.setEnable((byte) 1);
		user.setPassword(PasswordGenerator.encode(user.getPassword()));
		userService.addUserDTO(user);
		return "redirect:/admin/user/users";
	}

	@GetMapping(value = "/user/edit-user")
	public String editUser(HttpServletRequest request, @RequestParam(value = "username") String username, Model model) {
		UserDTO userDTO = userService.getUserDTOByUsename(username);
		model.addAttribute("user", userDTO);
		return "admin/user/editUser";
	}

	@PostMapping(value = "/user/edit-user")
	public String editUser(HttpServletRequest request, @ModelAttribute(name = "user") UserDTO user) {
		userService.updateUserDTO(user);
		return "redirect:/admin/user/users";
	}

	@GetMapping(value = "/user/delete-user")
	public String deleteUser(HttpServletRequest request, @RequestParam(value = "username") String username) {

		userService.deleteUserDTO(username);
		return "redirect:/admin/user/users";
	}

	@PostMapping(value = "/user/search")
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(value = "page", required = false) Integer page) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		int totalPage = userService.countUserWhenSearch(keyword);
		
		int pageCount = (totalPage % PAGE_SIZE == 0) ? totalPage / PAGE_SIZE : totalPage / PAGE_SIZE + 1;
		
		List<UserDTO> listUsers = userService.search(keyword, (page-1)*PAGE_SIZE,PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for(int i=1;i<=pageCount;i++) {
			listCount.add(i);
		}
		request.setAttribute("listUsers", listUsers);
		request.setAttribute("page", page);
		request.setAttribute("keyword", keyword);
		request.setAttribute("listCount", listCount);
		return "admin/user/users";
	}
}
