package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import in.ashokit.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> searchInfo = service.search(request);
		model.addAttribute("searches", searchInfo);
		
		//model.addAttribute("search", search);
		
		init(model);
		
		return "index";
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		SearchRequest searchObj=new SearchRequest();
		
		model.addAttribute("search",searchObj);
		
		init(model);
		
		return "index";
	}


	private void init(Model model) {
		
		/*SearchRequest searchObj=new SearchRequest();
		
		model.addAttribute("search",searchObj);*/
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status",service.getPlanStatuses());
	}

}
