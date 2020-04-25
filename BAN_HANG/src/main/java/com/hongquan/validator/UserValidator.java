package com.hongquan.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.hongquan.Model.User;
	
@Component
public class UserValidator implements Validator {

	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return User.class.isAssignableFrom(clazz);
	}

	public void validate(Object target, Errors errors) {
		// TODO Auto-generated method stub
		User user = (User) target;
		
		if(user.getName() == null || user.getName().length()==0) {
			errors.rejectValue("name", "field.required");
		}
		
		if(user.getPhone().length()<6 || user.getPhone().length()>12) {
			errors.rejectValue("phone", "phone.valid");
		}
	}

}
