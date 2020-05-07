package com.hongquan.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongquan.dao.UserDao;
import com.hongquan.entity.User;
import com.hongquan.model.UserDTO;
import com.hongquan.service.UserService;

@Transactional
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDao userDao;

	@Override
	public List<UserDTO> getAllUserDTOs() {
		List<User> listUsers = userDao.getAllUsers();
		List<UserDTO> listUserDTOs = new ArrayList<UserDTO>();
		for (User user : listUsers) {
			UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getName(), user.getAge(),
					user.getGender(), user.getEmail(), user.getAddress(), user.getPhone(), user.getRole(),
					user.getEnable());

			listUserDTOs.add(userDTO);
		}

		return listUserDTOs;
	}

	@Override
	public void addUserDTO(UserDTO userDTO) {
		User user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getAge(),
				userDTO.getGender(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPhone(), userDTO.getRole(),
				userDTO.getEnable());
		userDao.addUser(user);
	}

	@Override
	public void deleteUserDTO(String username) {
		User user = userDao.getUserByUsename(username);
		if (user != null) {
			userDao.deleteUser(user);
		}
	}

	@Override
	public void updateUserDTO(UserDTO userDTO) {
		User user = userDao.getUserByUsename(userDTO.getUsername());
		if (user != null) {
			user = new User(userDTO.getUsername(), userDTO.getPassword(), userDTO.getName(), userDTO.getAge(),
					userDTO.getGender(), userDTO.getEmail(), userDTO.getAddress(), userDTO.getPhone(), userDTO.getRole(),
					userDTO.getEnable());
			userDao.updateUser(user);
		}
	}

	@Override
	public UserDTO getUserDTOByUsename(String username) {
		User user = userDao.getUserByUsename(username);
		if (user != null) {
			UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getName(), user.getAge(),
					user.getGender(), user.getEmail(), user.getAddress(), user.getPhone(), user.getRole(),
					user.getEnable());
			return userDTO;
		}
		return null;
	}

	@Override
	public List<UserDTO> search(String name, int start, int length) {
		List<User> listUsers = userDao.search(name, start, length);
		List<UserDTO> listUserDTOs = new ArrayList<UserDTO>();
		for (User user : listUsers) {
			UserDTO userDTO = new UserDTO(user.getUsername(), user.getPassword(), user.getName(), user.getAge(),
					user.getGender(), user.getEmail(), user.getAddress(), user.getPhone(), user.getRole(),
					user.getEnable());

			listUserDTOs.add(userDTO);
		}

		return listUserDTOs;
	}

	@Override
	public int countPageWhenSearch(String name) {
		return userDao.countPageWhenSearch(name);
	}

}
