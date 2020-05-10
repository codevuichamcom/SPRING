package com.hongquan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.hongquan.service.impl.UserLoginServiceImpl;

@SpringBootApplication
public class ProjectSpringBootApplication extends WebSecurityConfigurerAdapter {

	@Autowired
	UserLoginServiceImpl loginServiceImpl;
	
	public static void main(String[] args) {
		SpringApplication.run(ProjectSpringBootApplication.class, args);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http.csrf().disable().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN").antMatchers("/member/**").hasRole("MEMBER").anyRequest().permitAll().and().
		formLogin().loginPage("/login").defaultSuccessUrl("/").failureUrl("/login?e=error").
		and().exceptionHandling().accessDeniedPage("/login?e=deny");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/css/**","/js/**","/img/**");
	}
	
	//Cấu hình tài khoản người dùng
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(loginServiceImpl).passwordEncoder(new BCryptPasswordEncoder());
	}

}
