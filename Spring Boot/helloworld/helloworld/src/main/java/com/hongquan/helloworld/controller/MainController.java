package com.hongquan.helloworld.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MainController {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello(HttpServletRequest request) {
		request.setAttribute("msg", "Hello Spring Boost");
		return "index";
	}
}
