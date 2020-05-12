package com.hongquan.service;

import java.util.List;

import com.hongquan.model.BillDTO;

public interface BillService {
	public void addBillDTO(BillDTO billDTO);

	public void deleteBillDTO(int id);

	public void updateBillDTO(BillDTO billDTO);

	public BillDTO getBillDTOById(int id);

	public List<BillDTO> search(String keyword, int start, int length);
	
	public int countBillDTOWhenSearch(String keyword);
}
