package in.ashokit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.RegistrationForm;
import in.ashokit.service.UserService;




@Controller
public class UserController {
	
	@Autowired
	private UserService userService;

	@GetMapping("/register")
	public String RegistrationPage(Model model) {
		model.addAttribute("user", new RegistrationForm());
		return "register";
	}
	
	
	@PostMapping("/register")
	public String handleSignUp(@ModelAttribute("user") RegistrationForm form, Model model) {

		boolean status = userService.register(form);

		if (status) {
			model.addAttribute("succMsg", "Account Created");
		} else {
			model.addAttribute("errMsg", "Problem Occured");
		}
		return "register";
	}
	
	@GetMapping("/login")
	public String loginPage(Model model) {
		
		model.addAttribute("loginForm", new LoginForm());
		
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute ("loginForm") LoginForm loginForm,Model model) {
		
		String status = userService.login(loginForm);
		
		if (status.contains("success")) {
			//redirect request to dashboard display method
			return "redirect:/dashboard";
		} 
			model.addAttribute("errMsg", status);
		
		return "login";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		
		return "dashboard";
	}

	
}
