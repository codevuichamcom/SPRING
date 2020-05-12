package com.hongquan.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.hongquan.model.BillDTO;
import com.hongquan.model.BillProductDTO;
import com.hongquan.service.BillProductService;
import com.hongquan.service.BillService;

@Controller
@RequestMapping(value = "/admin")
public class AdminBillController {

	@Autowired
	BillService billService;

	@Autowired
	BillProductService billProductService;

	@GetMapping(value = "/bill/bills")
	public String search(HttpServletRequest request, @RequestParam(name = "keyword", required = false) String keyword,
			@RequestParam(name = "page", required = false) Integer page) {

		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		keyword = keyword == null ? "" : keyword;
		int totalBill = billService.countBillDTOWhenSearch(keyword);

		int pageCount = (totalBill % PAGE_SIZE == 0) ? totalBill / PAGE_SIZE : totalBill / PAGE_SIZE + 1;

		List<BillDTO> listBillDTOs = billService.search(keyword, (page - 1) * PAGE_SIZE, PAGE_SIZE);
		List<Integer> listCount = new ArrayList<Integer>();
		for (int i = 1; i <= pageCount; i++) {
			listCount.add(i);
		}

		request.setAttribute("bills", listBillDTOs);
		request.setAttribute("page", page);
		request.setAttribute("listCount", listCount);
		request.setAttribute("keyword", keyword);

		return "admin/bill/bills";
	}

	int billId;

	@GetMapping(value = "bill/detail")
	public String Detail(HttpServletRequest request, @RequestParam(name = "billId") int billId,
			@RequestParam(name = "page", required = false) Integer page) {

		this.billId = billId;
		final int PAGE_SIZE = 2;
		page = page == null ? 1 : page;

		int totalBillProduct = billProductService.countBillProductWhenSearchByBillId(billId);
		if(totalBillProduct==0) {
			billService.deleteBillDTO(billId);
			return "redirect:/admin/bill/bills";
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

		return "admin/bill/billProduct";
	}

	@GetMapping(value = "bill/delete-bill")
	public String DeleteBill(@RequestParam(name = "billId", required = false) Integer billId) {

		if (billId != null) {
			billService.deleteBillDTO(billId);
		}

		return "redirect:/admin/bill/bills";
	}

	@GetMapping(value = "bill/delete-bill-product")
	public String DeleteBillProduct(@RequestParam(name = "billProductId", required = false) Integer billProductId) {

		if (billProductId != null) {
			billProductService.deleteBillProductDTO(billProductId);

		}
		return "redirect:/admin/bill/detail?billId=" + this.billId;
	}
}
