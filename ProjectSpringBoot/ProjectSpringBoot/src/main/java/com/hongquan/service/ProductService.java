package com.hongquan.service;

import java.util.List;

import com.hongquan.model.ProductDTO;


public interface ProductService {
	public void addProductDTO(ProductDTO productDTO);

	public void deleteProductDTO(int id);

	public void updateProductDTO(ProductDTO productDTO);
	
	public ProductDTO getProductDTOById(int id);
	
	public List<ProductDTO> search(String keyword,int categoryId, int start, int length);

	public int countProductWhenSearch(String keyword,int categoryId);
}
