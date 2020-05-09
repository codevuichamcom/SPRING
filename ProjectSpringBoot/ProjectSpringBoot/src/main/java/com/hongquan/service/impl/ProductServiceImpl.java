package com.hongquan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongquan.dao.ProductDao;
import com.hongquan.entity.Category;
import com.hongquan.entity.Product;
import com.hongquan.entity.User;
import com.hongquan.model.CategoryDTO;
import com.hongquan.model.ProductDTO;
import com.hongquan.model.UserDTO;
import com.hongquan.service.ProductService;

@Transactional
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductDao productDao;

	@Override
	public List<ProductDTO> getAllProductDTOs(int start, int length) {
		List<Product> listProducts = productDao.getAllProducts(start, length);
		List<ProductDTO> listProductDTOs = new ArrayList<ProductDTO>();
		for (Product product : listProducts) {
			ProductDTO productDTO = new ProductDTO();
			productDTO.setId(product.getId());
			productDTO.setName(product.getName());
			productDTO.setPrice(product.getPrice());
			productDTO.setQuantity(product.getQuantity());
			productDTO.setDescription(product.getDescription());
			productDTO.setImage(product.getImage());

			CategoryDTO categoryDTO = new CategoryDTO(product.getCategory().getId(), product.getCategory().getName());
			productDTO.setCategory(categoryDTO);

			listProductDTOs.add(productDTO);
		}

		return listProductDTOs;
	}

	@Override
	public int countAllProduct() {
		return productDao.countAllProduct();
	}

	@Override
	public void addProductDTO(ProductDTO productDTO) {
		// TODO Auto-generated method stub
		Product product = new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(),
				productDTO.getQuantity(), productDTO.getDescription(), productDTO.getImage(),
				new Category(productDTO.getCategory().getId(), productDTO.getCategory().getName()));
		productDao.addProduct(product);
	}

	@Override
	public void deleteProductDTO(int id) {
		Product product = productDao.getProductById(id);
		if (product != null) {
			productDao.deleteProduct(product);
		}
	}

	@Override
	public void updateProductDTO(ProductDTO productDTO) {
		Product product = productDao.getProductById(productDTO.getId());
		if (product != null) {
			product = new Product(productDTO.getId(), productDTO.getName(), productDTO.getPrice(),
					productDTO.getQuantity(), productDTO.getDescription(), productDTO.getImage(),
					new Category(product.getCategory().getId(), product.getCategory().getName()));

			productDao.updateProduct(product);
		}

	}

	@Override
	public ProductDTO getProductDTOById(int id) {
		Product product = productDao.getProductById(id);
		if (product != null) {
			ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice(),
					product.getQuantity(), product.getDescription(), product.getImage(),
					new CategoryDTO(product.getCategory().getId(), product.getCategory().getName()));

			return productDTO;
		}
		return null;
	}

	@Override
	public List<ProductDTO> search(String keyword, int start, int length) {
		List<Product> listProducts = productDao.search(keyword, start, length);
		List<ProductDTO> listProductDTOs = new ArrayList<ProductDTO>();
		for (Product product : listProducts) {
			ProductDTO productDTO = new ProductDTO(product.getId(), product.getName(), product.getPrice(),
					product.getQuantity(), product.getDescription(), product.getImage(),
					new CategoryDTO(product.getCategory().getId(), product.getCategory().getName()));

			listProductDTOs.add(productDTO);
		}

		return listProductDTOs;
	}

	@Override
	public int countProductWhenSearch(String keyword) {
		return productDao.countProductWhenSearch(keyword);
	}

}
