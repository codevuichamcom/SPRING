package com.hongquan.service;

import java.util.List;

import com.hongquan.model.BillProductDTO;

public interface BillProductService {
	public void addBillProductDTO(BillProductDTO billProductDTO);

	public void deleteBillProductDTO(int id);

	public void updateBillProductDTO(BillProductDTO billProductDTO);

	public BillProductDTO getBillProductDTOById(int id);

	public List<BillProductDTO> search(String keyword, int start, int length);

	public int countBillProductDTOWhenSearch(String keyword);

	public List<BillProductDTO> searchByBillId(int billId, int start, int length);

	public int countBillProductWhenSearchByBillId(int billId);
}
