package com.hongquan.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hongquan.Model.User;
import com.hongquan.dao.UserDao;
import com.hongquan.service.UserService;
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserDao userDao;//Co the goi bean thong qua interface

	public void addUser(User user) {
		userDao.addUser(user);
		
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	public void deleteUser(int id) {
		userDao.deleteUser(id);
		
	}

	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return userDao.getUserById(id);
	}

	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userDao.getAllUsers();
	}
}
