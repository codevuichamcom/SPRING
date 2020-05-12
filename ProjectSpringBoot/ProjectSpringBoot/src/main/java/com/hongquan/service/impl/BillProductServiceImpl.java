package com.hongquan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongquan.dao.BillProductDao;
import com.hongquan.entity.Bill;
import com.hongquan.entity.BillProduct;
import com.hongquan.entity.Product;
import com.hongquan.model.BillDTO;
import com.hongquan.model.BillProductDTO;
import com.hongquan.model.ProductDTO;
import com.hongquan.service.BillProductService;

@Service
@Transactional
public class BillProductServiceImpl implements BillProductService {

	@Autowired
	BillProductDao billProductDao;

	@Override
	public void addBillProductDTO(BillProductDTO billProductDTO) {
		BillProduct billProduct = new BillProduct();

		Product product = new Product();
		product.setId(billProductDTO.getProduct().getId());
		billProduct.setProduct(product);
		billProduct.setQuantity(billProductDTO.getQuantity());
		billProduct.setUnitPrice(billProductDTO.getUnitPrice());

		Bill bill = new Bill();
		bill.setId(billProductDTO.getBill().getId());
		billProduct.setBill(bill);

		billProductDao.addBillProduct(billProduct);

	}

	@Override
	public void deleteBillProductDTO(int id) {
		// TODO Auto-generated method stub
		BillProduct billProduct = billProductDao.getBillProductById(id);
		if (billProduct != null) {
			billProductDao.deleteBillProduct(billProduct);
		}
	}

	@Override
	public void updateBillProductDTO(BillProductDTO billProductDTO) {
		// TODO Auto-generated method stub
		BillProduct billProduct = billProductDao.getBillProductById(billProductDTO.getId());
		if (billProduct != null) {
			billProduct = new BillProduct();

			Product product = new Product();
			product.setId(billProductDTO.getProduct().getId());
			billProduct.setProduct(product);
			billProduct.setQuantity(billProductDTO.getQuantity());
			billProduct.setUnitPrice(billProductDTO.getUnitPrice());

			Bill bill = new Bill();
			bill.setId(billProductDTO.getBill().getId());
			billProduct.setBill(bill);

			billProductDao.updateBillProduct(billProduct);
		}
	}

	@Override
	public BillProductDTO getBillProductDTOById(int id) {
		BillProduct billProduct = billProductDao.getBillProductById(id);
		if (billProduct != null) {
			BillProductDTO billProductDTO = new BillProductDTO();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(billProduct.getProduct().getId());
			billProductDTO.setProduct(productDTO);
			billProductDTO.setQuantity(billProduct.getQuantity());
			billProductDTO.setUnitPrice(billProduct.getUnitPrice());

			BillDTO billDTO = new BillDTO();
			billDTO.setId(billProduct.getBill().getId());

			billProductDTO.setBill(billDTO);

			return billProductDTO;
		}
		return null;
	}

	@Override
	public List<BillProductDTO> search(String keyword, int start, int length) {

		List<BillProduct> billProducts = billProductDao.search(keyword, start, length);
		List<BillProductDTO> billProductDTOs = new ArrayList<BillProductDTO>();

		for (BillProduct billProduct : billProducts) {
			BillProductDTO billProductDTO = new BillProductDTO();
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(billProduct.getProduct().getId());
			billProductDTO.setProduct(productDTO);
			billProductDTO.setQuantity(billProduct.getQuantity());
			billProductDTO.setUnitPrice(billProduct.getUnitPrice());

			BillDTO billDTO = new BillDTO();
			billDTO.setId(billProduct.getBill().getId());

			billProductDTO.setBill(billDTO);

			billProductDTOs.add(billProductDTO);
		}

		return billProductDTOs;
	}

	@Override
	public int countBillProductDTOWhenSearch(String keyword) {
		return countBillProductDTOWhenSearch(keyword);
	}

}
