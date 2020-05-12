package com.hongquan.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.BillProductDTO;
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
	
	@GetMapping(value = "/member/cart")
	public String Cart() {
		return "client/cart";
	}
	
	@GetMapping(value = "/member/add-to-cart")
	public String AddToCart(@RequestParam(name = "productId") Integer productId, HttpSession session) throws IOException {
		ProductDTO product = productService.getProductDTOById(productId);

		Object object = session.getAttribute("cart");
		if (object == null) {
			BillProductDTO billProduct = new BillProductDTO();
			billProduct.setProduct(product);
			billProduct.setQuantity(1);
			billProduct.setUnitPrice(product.getPrice());
			Map<Integer, BillProductDTO> map = new HashMap<>();
			map.put(productId, billProduct);
			session.setAttribute("cart", map);
		} else {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;
			BillProductDTO billProduct = map.get(productId);
			if (billProduct == null) {
				billProduct = new BillProductDTO();
				billProduct.setProduct(product);
				billProduct.setQuantity(1);
				billProduct.setUnitPrice(product.getPrice());
				map.put(productId, billProduct);
			} else {
				billProduct.setQuantity(billProduct.getQuantity() + 1);

			}
			session.setAttribute("cart", map);

		}
		return "redirect:/";
	}
	
	@GetMapping(value = "/member/delete-from-cart")
	public String Deletefromtocart(HttpServletRequest req, @RequestParam(name = "key", required = true) Integer key) {
		HttpSession session = req.getSession();
		Object object = session.getAttribute("cart");
		if (object != null) {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;
			map.remove(key);
			session.setAttribute("cart", map);
		}
		return "redirect:/member/cart";
	}
}
