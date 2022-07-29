package com.youth.main.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youth.main.web.dto.UserLoginDto;

@Controller
@RequestMapping("/user_login")
public class UserLoginController {
	
	@GetMapping
	public String userlogin() {
		return "user_login";
	}
	
	@Autowired
	   private BCryptPasswordEncoder passwordEncoder;
	
	@PostMapping
	public String loginHandler(@ModelAttribute("user") UserLoginDto userLoginDto, Model model) {
		
		String email = userLoginDto.getEmail();
		String password = passwordEncoder.encode(userLoginDto.getPassword());
		
		if("user".equals(email) && "user".equals(password)) {
			return "index";
		}
		
		model.addAttribute("invalidCredentials", true);
		return "user_login";
	}

}
