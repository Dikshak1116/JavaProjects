package in.ashokit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import in.ashokit.entity.CitizenPlan;
import in.ashokit.request.SearchRequest;
import in.ashokit.service.ReportService;

@Controller
public class ReportController {
	
	@Autowired
	private ReportService service;
	
	@PostMapping("/search")
	public String handleSearch(SearchRequest request,Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> searchInfo = service.search(request);
		model.addAttribute("searches", searchInfo);
		
		init(model);
		
		return "index";
	}
	
	
	@GetMapping("/")
	public String indexPage(Model model) {
		
		init(model);
		
		return "index";
	}


	private void init(Model model) {
		
		SearchRequest searchObj=new SearchRequest();
		
		model.addAttribute("search",searchObj);
		model.addAttribute("names", service.getPlanNames());
		model.addAttribute("status",service.getPlanStatuses());
	}

}
