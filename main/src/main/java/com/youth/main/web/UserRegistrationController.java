package com.youth.main.web;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.youth.main.model.UserModel;
import com.youth.main.service.UserServiceImpl;
import com.youth.main.web.dto.UserRegistrationDto;

@Controller
@RequestMapping("/user_registration")
public class UserRegistrationController {

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private UserServiceImpl userServiceImpl;
//
//   public UserRegistrationController(UserService userService) {
//      super();
//      this.userService = userService;
//   }
	
//	private UserRepository userRepository;


//	public UserRegistrationController(UserRepository userRepository) {
//	    super();
//	    this.userRepository = userRepository;
//	}

//	@ModelAttribute("user")
//	public UserRegistrationDto userRegistrationDto() {
//		return new UserRegistrationDto();
//	}

	@GetMapping
	public String RegistrationForm(Model model) {
		model.addAttribute("user", new UserRegistrationDto());
		return "user_registration";
	}

	@PostMapping
	public String registerUserAccount(@Valid @ModelAttribute("user") UserRegistrationDto userRegistrationDto,
									  BindingResult result,
									  Model model,
									  HttpSession session) {
      
		try {
			session.removeAttribute("message");
			
			if(result.hasErrors()) {
				model.addAttribute("user",userRegistrationDto);
				return "user_registration";
			}
			
			String email = userRegistrationDto.getEmail();
			UserModel user = userServiceImpl.findByUsername(email);
			
			if(user!=null) {
				model.addAttribute("user",userRegistrationDto);
				session.setAttribute("message","Email registration successful.");
				return "user_registration";
			}
			
			if(userRegistrationDto.getPassword().equals(userRegistrationDto.getRetypepassword())) {
				userRegistrationDto.setPassword(passwordEncoder.encode(userRegistrationDto.getPassword()));
				userServiceImpl.save(userRegistrationDto);
				model.addAttribute("user",userRegistrationDto);
				session.setAttribute("message","Registration successful.");
			}else {
				model.addAttribute("user",userRegistrationDto);
				session.setAttribute("message","Provided passwords are not same.");
				return "redirect:/user_registration";
			}
			
			
		}catch(Exception e) {
			session.setAttribute("message","Error Signing up user."+e.getMessage());
		}
	     
	   
		return "redirect:/user_registration?success";
	}
}





















