package in.ashokit.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

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
	
	@GetMapping("/pdf")
	public void pdfEport(HttpServletResponse response,Model model) throws Exception{
		
		response.setContentType("application/pdf");
		response.addHeader("Content-Disposition", "attachment;filename=plans.pdf;");
		service.exportPdf(response);
		
	}
	
	@GetMapping("/excel")
	public void excelEport(HttpServletResponse response,Model model) throws Exception{
		
		response.setContentType("application/octet-stream");
		response.addHeader("Content-Disposition", "attachment;filename=plans.xls;");
		service.exportExcel(response);
		
		
	}
	
	@PostMapping("/search")
	public String handleSearch(@ModelAttribute("search") SearchRequest request,Model model) {
		
		System.out.println(request);
		
		List<CitizenPlan> searchInfo = service.search(request);
		model.addAttribute("searches", searchInfo);
		
		//model.addAttribute("search", search);
		
		init(model);
		
		return "index";
	}
	
	//shortcut for doc comment-/**enter
	/**
	 * This method is used to load index page
	 * @param model
	 * @return String
	 */
	
 
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
