package in.ashokit.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.LoginForm;
import in.ashokit.binding.SignUpForm;
import in.ashokit.binding.UnlockForm;
import in.ashokit.service.UserService;

@Controller
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/signup")
	public String signUpPage(Model model) {
		model.addAttribute("user", new SignUpForm());
		return "signup";
	}

	@PostMapping("/signup")
	public String handleSignUp(@ModelAttribute("user") SignUpForm form, Model model) {

		boolean status = userService.signup(form);

		if (status) {
			model.addAttribute("succMsg", "Account Created, Check your Email");
		} else {
			model.addAttribute("errMsg", "Choose Unique Email");
		}
		return "signup";
	}

	@GetMapping("/unlock")
	public String unlockPage(@RequestParam String email, Model model) {

		// This method is taking the data from query param,setting the
		// data to binding object and sending the binding object to UI
		// create object for binding class and set the data
		UnlockForm unlockFormObj = new UnlockForm();
		unlockFormObj.setEmail(email);
		model.addAttribute("unlock", unlockFormObj);

		return "unlock";
	}

	@PostMapping("/unlock")
	public String unlockUserAccount(@ModelAttribute ("unlock") UnlockForm unlock, Model model) {

		if(unlock.getNewPwd().equals(unlock.getCnfmPwd())) {
			
			boolean status = userService.unlockAccount(unlock);
			if (status) {
				model.addAttribute("succMsg", "Your Account Unlocked Successfully");
			} else {
				model.addAttribute("errMsg", "Given Temporary Password is Incorrect, Check your Email");
			}
			
		}else {
			
		model.addAttribute("errMsg", "New Password and Confirm password should be same");
		
		}
		
		return "unlock";
		
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

	@GetMapping("/forgot") 
	public String forgotPwdPage() {
		return "forgotPwd";
	}
	
	@PostMapping("/forgot")
	public String forgotPwd(@RequestParam ("email")String email,Model model) {
		
		boolean status = userService.forgotPwd(email);
		if (status) {
			model.addAttribute("succMsg", "Password Sent to Your Email");
		}else {
			model.addAttribute("errMsg", "Invalid Email");
		}
		return "forgotPwd";
	}

}
