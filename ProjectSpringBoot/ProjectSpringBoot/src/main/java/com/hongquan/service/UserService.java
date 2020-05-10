package com.hongquan.service;

import java.util.List;

import com.hongquan.model.UserDTO;

public interface UserService {

	public void addUserDTO(UserDTO userDTO);

	public void deleteUserDTO(String username);

	public void updateUserDTO(UserDTO userDTO);

	public UserDTO getUserDTOByUsename(String username);
	
	public List<UserDTO> search(String name, int start, int length);
	
	public int countUserWhenSearch(String name);
}
