package com.hongquan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
import org.springframework.web.multipart.MultipartFile;

import com.hongquan.model.CategoryDTO;
import com.hongquan.model.ProductDTO;
import com.hongquan.service.CategoryService;
import com.hongquan.service.ProductService;

@Controller
@RequestMapping(value = "/admin")
public class AdminProductController {

	@Autowired
	ProductService productService;

	@Autowired
	CategoryService categoryService;

	@GetMapping(value = "/product/products")
	public String getAllProduct(HttpServletRequest request,
			@RequestParam(value = "page", required = false) Integer page) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		int totalProduct = productService.countProductWhenSearch("",-1);
		int pageCount = (totalProduct % PAGE_SIZE == 0) ? totalProduct / PAGE_SIZE : totalProduct / PAGE_SIZE + 1;

		List<ProductDTO> listProducts = productService.search("",-1, 0, PAGE_SIZE);

		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}
		List<CategoryDTO> listCategories = categoryService.getAllCategorys(0, 100);
		
		request.setAttribute("listProducts", listProducts);
		request.setAttribute("listCategories", listCategories);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);

		return "admin/product/products";
	}

	@PostMapping(value = "/product/search")
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", required = false) Integer page,
			@RequestParam(name = "category") int categoryId) {
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;
		keyword = keyword == null ? "" : keyword;
		int totalProduct = productService.countProductWhenSearch(keyword,categoryId);

		int pageCount = (totalProduct % PAGE_SIZE == 0) ? totalProduct / PAGE_SIZE : totalProduct / PAGE_SIZE + 1;

		List<ProductDTO> listProducts = productService.search(keyword,categoryId, (page - 1) * PAGE_SIZE, PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}
		
		List<CategoryDTO> listCategories = categoryService.getAllCategorys(0, 100);
		
		request.setAttribute("listProducts", listProducts);
		request.setAttribute("listCategories", listCategories);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("keyword", keyword);
		request.setAttribute("categoryId", categoryId);

		return "admin/product/products";
	}

	@GetMapping(value = "/product/add-product")
	public String addProduct(HttpServletRequest request, Model model) {
		model.addAttribute("product", new ProductDTO());
		List<CategoryDTO> listCategories = categoryService.getAllCategorys(0, 100);
		request.setAttribute("listCategories", listCategories);
		return "admin/product/addProduct";
	}

	@PostMapping(value = "/product/add-product")
	public String addProduct(HttpServletRequest request, @ModelAttribute("product") ProductDTO productDTO) {

		MultipartFile file = productDTO.getFile();
		// luu file xuong o cung
		try {
			File newFile = new File(
					"F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\ProjectSpringBoot\\ProjectSpringBoot\\src\\main\\resources\\static\\images\\"
							+ file.getOriginalFilename());
			FileOutputStream fileOutputStream;

			fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			
			
			newFile = new File(
					"F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\ProjectSpringBoot\\ProjectSpringBoot\\target\\classes\\static\\images\\"
							+ file.getOriginalFilename());

			fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
			
			
			productDTO.setImage(file.getOriginalFilename());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		productService.addProductDTO(productDTO); // luu vao csdl

		return "redirect:/admin/product/products";
	}

	@GetMapping(value = "/product/edit-product")
	public String editUser(HttpServletRequest request, @RequestParam(value = "id") int id, Model model) {
		ProductDTO productDTO = productService.getProductDTOById(id);
		model.addAttribute("product", productDTO);
		List<CategoryDTO> listCategories = categoryService.getAllCategorys(0, 100);
		request.setAttribute("listCategories", listCategories);
		return "admin/product/editProduct";
	}

	@PostMapping(value = "/product/edit-product")
	public String editUser(HttpServletRequest request, @ModelAttribute("product") ProductDTO productDTO) {

		MultipartFile file = productDTO.getFile();
		if (file.getOriginalFilename().equals("")) {
			productDTO.setImage(productService.getProductDTOById(productDTO.getId()).getImage());
		} else {

			// luu file xuong o cung
			try {
				File newFile = new File(
						"F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\ProjectSpringBoot\\ProjectSpringBoot\\src\\main\\resources\\static\\images\\"
								+ file.getOriginalFilename());
				FileOutputStream fileOutputStream;

				fileOutputStream = new FileOutputStream(newFile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();
				
				newFile = new File(
						"F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\ProjectSpringBoot\\ProjectSpringBoot\\target\\classes\\static\\images\\"
								+ file.getOriginalFilename());

				fileOutputStream = new FileOutputStream(newFile);
				fileOutputStream.write(file.getBytes());
				fileOutputStream.close();
				
				productDTO.setImage(file.getOriginalFilename());
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}

		productService.updateProductDTO(productDTO);
		return "redirect:/admin/product/products";
	}

	@GetMapping(value = "/product/delete-product")
	public String deleteUser(HttpServletRequest request, @RequestParam(value = "id") int id) {

		productService.deleteProductDTO(id);
		return "redirect:/admin/product/products";
	}
}
