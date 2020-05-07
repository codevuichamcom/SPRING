package com.hongquan.utils;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
	public static String encode(String pass) {
		String endcode = new BCryptPasswordEncoder().encode(pass);
		return endcode;
	}
}
