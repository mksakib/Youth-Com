package com.youth.main.web;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.youth.main.model.User;
import com.youth.main.repository.UserRepository;

@Controller
@RequestMapping("/user_registration")
public class UserRegistrationController {

//   private UserService userService;
//
//   public UserRegistrationController(UserService userService) {
//      super();
//      this.userService = userService;
//   }
	
	private UserRepository userRepository;


	public UserRegistrationController(UserRepository userRepository) {
	    super();
	    this.userRepository = userRepository;
	}

//	@ModelAttribute("user")
//	public UserRegistrationDto userRegistrationDto() {
//		return new UserRegistrationDto();
//	}

	@GetMapping
	public String RegistrationForm(Model model) {
		model.addAttribute("user", new User());
		return "user_registration";
	}

	@PostMapping
	public String registerUserAccount(User user) {
      
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(user.getPassword());
		user.setPassword(encodedPassword);
	     
	   
		userRepository.save(user);
		return "redirect:/user_registration?success";
	}
}





















