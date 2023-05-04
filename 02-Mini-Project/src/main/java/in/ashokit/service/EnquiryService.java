package in.ashokit.service;

import java.util.List;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.StudentEnqEntity;

public interface EnquiryService {

	public DashboardResponse getDashboardData(Integer userId);
	
	public List<String> getCourses();
	
	public List<String> getEnqStatuses();
	
	public boolean saveEnquiry(EnquiryForm form);
	
	public List<StudentEnqEntity> getEnquiries();
	
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria,Integer userId);
	
	public EnquiryForm getEditPage(Integer enqId);
	
	public boolean updateEnquiry(EnquiryForm form,Integer enqId);
	
}
