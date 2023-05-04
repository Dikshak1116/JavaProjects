package in.ashokit.rest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class UserRestController {

	@GetMapping("/")
	public String load() {
		return "index";
	}
	
	//Sync Req
	/*@GetMapping("/msg")
	public String getMsg(@RequestParam("name") String name,Model model) {
		
		String msg= "Hello, "+name;
		model.addAttribute("msg", msg);
		return "index";
		
	}*/
	
	
	//Async Req
	@GetMapping("/msg")
	@ResponseBody
	public String getMsg(@RequestParam("name") String name) {
		
		String msg= "Hello, "+name;
		
		return msg;
		
	}
	
	@GetMapping("/cmsg")
	@ResponseBody
	public String getDropDownMsg(@RequestParam("cname") String cname) {
		
		String msg= "I am going to "+cname+" next month...";
		
		return msg;
		
	}
}
