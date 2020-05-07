package com.hongquan.dao;

import java.util.List;

import com.hongquan.entity.User;

public interface UserDao {
	public List<User> getAllUsers();

	public void addUser(User user);

	public void deleteUser(User user);

	public void updateUser(User user);
	
	public User getUserByUsename(String username);
	
	public List<User> search(String name, int start, int length);
	
	public int countPageWhenSearch(String name);
}
