package com.hongquan.controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.hongquan.Model.User;
import com.hongquan.validator.UserValidator;

@Controller
public class HelloController {
	@Autowired
	private UserValidator userValidator;

	@RequestMapping("/say-hello")
	public ModelAndView sayHello1(HttpServletRequest request, HttpServletResponse response, HttpSession session,
			@RequestParam(name = "user", required = true, defaultValue = "quan") String user) {
		// ngoài ra có thể dùng @RequesHeader để đọc hearder từ người dùng
		request.setAttribute("msg", user);
		return new ModelAndView("hello");
	}

	@RequestMapping("/hello/{id}/{name}")
	public String sayHello(HttpServletRequest request, @PathVariable(name = "id") int userId,
			@PathVariable(name = "name") String name) {
		request.setAttribute("info", "ID:" + userId + ", Name: " + name);
		return "hello";
	}

	@RequestMapping(value = "/them-user", method = RequestMethod.GET)
	public String addUser(HttpServletRequest request) {
		User user = new User();
		user.setName("Spring");
		request.setAttribute("user", user);

		List<String> listFavourites = new ArrayList<String>();
		listFavourites.add("Xem Phim");
		listFavourites.add("Nghe Nhac");
		listFavourites.add("Coding");
		request.setAttribute("listFavourites", listFavourites);
		return "addUser";
	}

	@RequestMapping(value = "/them-user", method = RequestMethod.POST)
	public String addUser(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {
			List<String> listFavourites = new ArrayList<String>();
			listFavourites.add("Xem Phim");
			listFavourites.add("Nghe Nhac");
			listFavourites.add("Coding");
			request.setAttribute("listFavourites", listFavourites);
			return "addUser";
		}

		MultipartFile file = user.getAvatar();
		// luu file xuong o cung
		try {
			File newFile = new File("F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\BanHang\\src\\main\\webapp\\resources\\image\\" + file.getOriginalFilename());
			FileOutputStream fileOutputStream;

			fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("u", user);
		return "viewUser";
	}

	@RequestMapping(value = "/upload-file", method = RequestMethod.GET)
	public String upload(HttpServletRequest request) {
		return "upload";
	}

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public String upload(HttpServletRequest request, @RequestParam(name = "file") MultipartFile file) {
		// luu file xuong o cung
		try {
			File newFile = new File("F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\BanHang\\src\\main\\webapp\\resources\\image\\" + file.getOriginalFilename());
			FileOutputStream fileOutputStream;

			fileOutputStream = new FileOutputStream(newFile);
			fileOutputStream.write(file.getBytes());
			fileOutputStream.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("file", file);
		return "viewFile";
	}
	
	@RequestMapping(value = "/download-file", method = RequestMethod.GET)
	public void download(HttpServletRequest request,HttpServletResponse response) {
		String dataDirectory = "F:\\Self_Study\\TT_JavaMaster\\class-spring08\\My_Code\\BanHang\\src\\main\\webapp\\resources\\image\\";
		Path file = Paths.get(dataDirectory,"cute.jpg");
		if(Files.exists(file)) {
			response.setContentType("image/jpeg");
			response.addHeader("Content-Disposition", "attachment; filename =anh.jpg");
			try {
				Files.copy(file, response.getOutputStream());
				response.getOutputStream().flush();
			}
			catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
