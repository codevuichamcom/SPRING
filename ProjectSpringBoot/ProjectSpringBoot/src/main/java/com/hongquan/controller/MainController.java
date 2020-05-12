package com.hongquan.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.BillDTO;
import com.hongquan.model.BillProductDTO;
import com.hongquan.model.CategoryDTO;
import com.hongquan.model.ProductDTO;
import com.hongquan.model.UserDTO;
import com.hongquan.service.BillProductService;
import com.hongquan.service.BillService;
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

	@Autowired
	BillService billService;

	@Autowired
	BillProductService billProductService;

	@GetMapping(value = "/")
	public String home(HttpServletRequest request) {
		List<ProductDTO> listProducts = productService.search("", -1, 0, 100);

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
	public String AddToCart(@RequestParam(name = "productId") Integer productId, HttpSession session)
			throws IOException {
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

	@GetMapping(value = "member/bill/bills")
	public String Bills(HttpServletRequest request, @RequestParam(name = "page", required = false) Integer page) {

		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		int totalBill = billService.countBillWhenSearchByUsername(userDetails.getUsername());
		int pageCount = (totalBill % PAGE_SIZE == 0) ? totalBill / PAGE_SIZE : totalBill / PAGE_SIZE + 1;

		List<BillDTO> listBillDTOs = billService.searchByUsername(userDetails.getUsername(), (page - 1) * PAGE_SIZE,
				PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}

		request.setAttribute("bills", listBillDTOs);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);

		return "client/member/bills";
	}

	@GetMapping(value = "/member/bill/add")
	public String addOrder(HttpSession session, Model model) throws IOException {
		// lay member dang nhap hien tai
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		UserDTO user = new UserDTO();
		user.setUsername(userDetails.getUsername());

		// lay sp trong gio hang
		Object object = session.getAttribute("cart");

		if (object != null) {
			Map<Integer, BillProductDTO> map = (Map<Integer, BillProductDTO>) object;

			BillDTO bill = new BillDTO();
			bill.setUser(user);
			billService.addBillDTO(bill);// Làm sao để sau khi add thì lấy đk cái generate key ạ

			float totalPrice = 0f;
			float finalTotalPrice = 0f;
			for (Entry<Integer, BillProductDTO> entry : map.entrySet()) {
				BillProductDTO billProduct = entry.getValue();
				billProduct.setBill(bill);

				billProductService.addBillProductDTO(billProduct);

				totalPrice = totalPrice + (billProduct.getQuantity() * billProduct.getUnitPrice());
				finalTotalPrice = totalPrice;
				/// discount
				List<BillDTO> listBillDTOs = billService.searchByUsername(user.getUsername(), 0, 100);

				// update so luong sp sau khi mua hang thanh cong.
				ProductDTO productDTO = productService.getProductDTOById(entry.getValue().getProduct().getId());
				productDTO.setQuantity(productDTO.getQuantity() - billProduct.getQuantity());
				productService.updateProductDTO(productDTO);

				// if (list.isEmpty() == true ) {// lan dau mua
				if (listBillDTOs.size() == 1) { // lan dau mua
					finalTotalPrice = (totalPrice - (billProduct.getQuantity() * billProduct.getUnitPrice() * 5 / 100));
					bill.setPriceTotal(finalTotalPrice);
					bill.setDiscountPercent(5);
					// bill.setStatus("OLD");
					// bill.setTotal(totalPrice);
					// model.addAttribute("a", totalPrice);
					System.out.println("khuyen mai 5% cho lan thanh toan dau tien cua ban!!!!" + bill.getPriceTotal());
					// chưa cho hiển thị dc giá lúc chưa giảm.
				} else {

					bill.setPriceTotal(totalPrice);
					bill.setDiscountPercent(0);
				}
				billService.updateBillDTO(bill);// udpate lai gia
			}

			// xóa cart khi đã thanh toán
			session.removeAttribute("cart");

//			return "redirect:/member/bills";
		}
		return "redirect:/";
	}
	int billId;
	@GetMapping(value = "member/bill/detail")
	public String Detail(HttpServletRequest request, @RequestParam(name = "billId") int billId,
			@RequestParam(name = "page", required = false) Integer page) {
		
		this.billId=billId;
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		int totalBillProduct = billProductService.countBillProductWhenSearchByBillId(billId);
		if (totalBillProduct == 0) {
			billService.deleteBillDTO(billId);
			return "redirect:/member/bill/bills";
		}
		int pageCount = (totalBillProduct % PAGE_SIZE == 0) ? totalBillProduct / PAGE_SIZE
				: totalBillProduct / PAGE_SIZE + 1;

		List<BillProductDTO> billProductDTOs = billProductService.searchByBillId(billId, (page - 1) * PAGE_SIZE,
				PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}

		request.setAttribute("billProducts", billProductDTOs);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("billId", billId);

		return "client/member/billProduct";
	}

	@GetMapping(value = "member/bill/delete-bill")
	public String DeleteBill(@RequestParam(name = "billId", required = false) Integer billId) {

		if (billId != null) {
			billService.deleteBillDTO(billId);
		}

		return "redirect:/member/bill/bills";
	}

	@GetMapping(value = "member/bill/delete-bill-product")
	public String DeleteBillProduct(@RequestParam(name = "billProductId", required = false) Integer billProductId) {

		if (billProductId != null) {
			billProductService.deleteBillProductDTO(billProductId);

		}
		return "redirect:/member/bill/detail?billId=" + this.billId;
	}

}
