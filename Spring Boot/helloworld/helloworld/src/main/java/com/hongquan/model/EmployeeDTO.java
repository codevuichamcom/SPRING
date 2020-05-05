package com.hongquan.model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class EmployeeDTO {
	private int id;
	private String name;
	private int age;
	private MultipartFile avatar;
	private List<String> phones;
	
	public EmployeeDTO() {
	}

	public EmployeeDTO(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public MultipartFile getAvatar() {
		return avatar;
	}

	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}

	public List<String> getPhones() {
		return phones;
	}

	public void setPhones(List<String> phones) {
		this.phones = phones;
	}
	
	
	
}
