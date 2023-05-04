package in.ashokit.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class PostController {

	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		
		return "dashboard";
	}
	
	
}
