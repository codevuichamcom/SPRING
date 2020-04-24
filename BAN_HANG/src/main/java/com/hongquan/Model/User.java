package com.hongquan.Model;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;


public class User {
	private String name;
	private String phone;
	private String password;
	private int id;
	private List<String> favourites;
	private String gender;
	private String about;
	private boolean acceptAgreement;
	private MultipartFile avatar;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public List<String> getFavourites() {
		return favourites;
	}
	public void setFavourites(List<String> favourites) {
		this.favourites = favourites;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAbout() {
		return about;
	}
	public void setAbout(String about) {
		this.about = about;
	}
	public boolean isAcceptAgreement() {
		return acceptAgreement;
	}
	public void setAcceptAgreement(boolean acceptAgreement) {
		this.acceptAgreement = acceptAgreement;
	}
	public MultipartFile getAvatar() {
		return avatar;
	}
	public void setAvatar(MultipartFile avatar) {
		this.avatar = avatar;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
	
}
