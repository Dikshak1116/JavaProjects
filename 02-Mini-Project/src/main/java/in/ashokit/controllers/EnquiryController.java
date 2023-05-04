package in.ashokit.controllers;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.service.EnquiryService;

@Controller
public class EnquiryController {
	
	@Autowired
	private EnquiryService enqService;
	
	@Autowired
	private HttpSession session;
	
	@GetMapping("/logout")
	public String logout() {
		session.invalidate();
		return "index";
	}
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		
		//TODO: logic to fetch the data for Dashboard
		
		/*once login is success,we had stored userId in session,when
		user going to dashboard we are getting userId from session.
		Based on userId,we are calling service layer method that is 
		giving us dashboard response object.We need to send response
		object to UI using model.addAttribute()
		 */		
		Integer userId=(Integer) session.getAttribute("userId");
		DashboardResponse dashboardData = enqService.getDashboardData(userId);
		model.addAttribute("dashboardData", dashboardData);
		
		return "dashboard";
	}
	
	@GetMapping("/enquiry")
	public String addEnquiryPage(Model model) {
		initForm(model);
		
		return "add-enquiry";
	}

	private void initForm(Model model) {
		//Get courses for drop down
		List<String> courses = enqService.getCourses();
		
		//Get enq status for drop down
		List<String> enqStatuses = enqService.getEnqStatuses();
				
		//create binding class object
		EnquiryForm formObj=new EnquiryForm();
				
		//set data in model obj
		model.addAttribute("courseNames", courses);
		model.addAttribute("enqStatus", enqStatuses);
		model.addAttribute("formObj",formObj);
	}
	
	@PostMapping("/enquiry")
	public String addEnquiry(@ModelAttribute("formObj") EnquiryForm formObj,Model model) {
		
		boolean status = enqService.saveEnquiry(formObj);
		
		if (status) {
			model.addAttribute("succMsg", "Enquiry Added...!!!");
		} else {
			model.addAttribute("errMsg", "Problem Occured");
		}
		
		return "add-enquiry";
	}
	
	@GetMapping("/enquires")
	public String viewEnquiryPage(EnquirySearchCriteria criteria,Model model) {
		
		initForm(model);
		model.addAttribute("searchForm", new EnquirySearchCriteria());
		List<StudentEnqEntity> enquiries = enqService.getEnquiries();
		model.addAttribute("enquiries", enquiries);
		
		return "view-enquiries";
	}
	
	@GetMapping("/filter-enquiries")
	public String getFilteredEnqs(@RequestParam String cname,
			@RequestParam String status,
			@RequestParam String mode,
			Model model) {
		
		EnquirySearchCriteria criteria=new EnquirySearchCriteria();
		criteria.setCourse(cname);
		criteria.setClassMode(mode);
		criteria.setEnqStatus(status);
		
		Integer userId=(Integer) session.getAttribute("userId");
		List<StudentEnqEntity> filteredEnqs = enqService.getFilteredEnqs(criteria, userId);
		model.addAttribute("enquiries",filteredEnqs);
		
		return "filter-enquiries-page";
	}
	
	@GetMapping("/edit/{id}")
	public String editEnquiry(@PathVariable("id") Integer enqId,Model model) {
		
		EnquiryForm form = enqService.getEditPage(enqId);
		List<String> courses = enqService.getCourses();
		List<String> enqStatus = enqService.getEnqStatuses();
		
		model.addAttribute("courseNames", courses);
		model.addAttribute("enqStatus", enqStatus);
		model.addAttribute("formObj",form);
		model.addAttribute("enqId", enqId);
		//initForm(model);
		
		
		
		return "update-enquiry";
	}

	
	@PostMapping("/update")
	public String update(@ModelAttribute("student") EnquiryForm form,@RequestParam("enqId") Integer enqId,Model model) {
		boolean status = enqService.updateEnquiry(form, enqId);
		if(status) {
			return "redirect:/dashboard";
		}
		else{
			model.addAttribute("errMsg", "Something went wrong...Plz update again");
			return "update-enquiry";
		}
	}
}
