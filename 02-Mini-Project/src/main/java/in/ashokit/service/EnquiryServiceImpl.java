package in.ashokit.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.servlet.http.HttpSession;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import in.ashokit.binding.DashboardResponse;
import in.ashokit.binding.EnquiryForm;
import in.ashokit.binding.EnquirySearchCriteria;
import in.ashokit.entity.CourseEntity;
import in.ashokit.entity.EnqStatusEntity;
import in.ashokit.entity.StudentEnqEntity;
import in.ashokit.entity.UserDtlsEntity;
import in.ashokit.repo.CourseRepo;
import in.ashokit.repo.EnqStatusRepo;
import in.ashokit.repo.StudentEnqRepo;
import in.ashokit.repo.UserDtlsRepo;

@Service
public class EnquiryServiceImpl implements EnquiryService {

	@Autowired
	private UserDtlsRepo userDtlsRepo;

	@Autowired
	private StudentEnqRepo enqRepo;

	@Autowired
	private CourseRepo courseRepo;

	@Autowired
	private EnqStatusRepo StatusRepo;

	@Autowired
	private HttpSession session;

	@Override
	public DashboardResponse getDashboardData(Integer userId) {

		DashboardResponse response = new DashboardResponse();

		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);

		if (findById.isPresent()) {
			UserDtlsEntity userEntity = findById.get();
			List<StudentEnqEntity> enquiries = userEntity.getEnquiries();
			Integer totalCnt = enquiries.size();

			/*
			 * List<StudentEnqEntity> enrolled = enquiries.stream()
			 * .filter(e->e.getEnqStatus().equals("Enrolled"))
			 * .collect(Collectors.toList());
			 * 
			 * Integer enrolledCnt = enrolled.size();
			 */

			Integer enrolledCnt = enquiries.stream().filter(e -> e.getEnqStatus().equals("Enrolled"))
					.collect(Collectors.toList()).size();

			Integer lostCnt = enquiries.stream().filter(e -> e.getEnqStatus().equals("Lost"))
					.collect(Collectors.toList()).size();

			response.setTotalEnq(totalCnt);
			response.setEnrolledEnq(enrolledCnt);
			response.setLostEnq(lostCnt);

		}

		return response;
	}

	@Override
	public List<String> getCourses() {

		// It will give all the records available in the table
		// CourseEntity has(id,name) but we need only name
		List<CourseEntity> findAll = courseRepo.findAll();

		// so we had created a collection List of name and whatever list
		// of entities we got in findAll,we are iterating that list of entities
		// and we are getting the names ,finally we are adding that name
		// to the collection and returning only names.
		List<String> names = new ArrayList<>();

		// get each entity and from that get course name and add it to list
		for (CourseEntity entity : findAll) {
			names.add(entity.getCourseName());
		}

		return names;
	}

	@Override
	public List<String> getEnqStatuses() {
		List<EnqStatusEntity> findAll = StatusRepo.findAll();
		List<String> status = new ArrayList<>();

		// From the above collection get only status and add that to list
		for (EnqStatusEntity entity : findAll) {
			status.add(entity.getStatusName());
		}

		return status;
	}

	@Override
	public boolean saveEnquiry(EnquiryForm form) {

		/*
		 * From the binding obj/form we are getting the data and we are copying the data
		 * to entity object.Get the userId from session to know which user is inserting
		 * the record .Once we get userId then based on userId retrieve the user record
		 * and set that user record to enquiry bcz enquiry is having reference of user
		 * Many to one relation.setting user obj to enqEntity. Finally inserting record
		 * into table by saving enqEntity.It means this enquiry belongs to particular
		 * user.
		 */
		StudentEnqEntity enqEntity = new StudentEnqEntity();
		BeanUtils.copyProperties(form, enqEntity);

		Integer userId = (Integer) session.getAttribute("userId");
		UserDtlsEntity userEntity = userDtlsRepo.findById(userId).get();
		enqEntity.setUser(userEntity);

		enqRepo.save(enqEntity);

		return true;
	}

	@Override
	public List<StudentEnqEntity> getEnquiries() {

		Integer userId = (Integer) session.getAttribute("userId");
		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if (findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();
			return enquiries;
		}

		return null;
	}

	@Override
	public List<StudentEnqEntity> getFilteredEnqs(EnquirySearchCriteria criteria, Integer userId) {

		Optional<UserDtlsEntity> findById = userDtlsRepo.findById(userId);
		if (findById.isPresent()) {
			UserDtlsEntity userDtlsEntity = findById.get();
			List<StudentEnqEntity> enquiries = userDtlsEntity.getEnquiries();

			// filter logic
			if (null != criteria.getCourse() && !"".equals(criteria.getCourse())) {

				enquiries = enquiries.stream().filter(e -> e.getCourseName().equals(criteria.getCourse()))
						.collect(Collectors.toList());

			}
			if (null != criteria.getEnqStatus() && !"".equals(criteria.getEnqStatus())) {

				enquiries = enquiries.stream().filter(e -> e.getEnqStatus().equals(criteria.getEnqStatus()))
						.collect(Collectors.toList());

			}
			if (null != criteria.getClassMode() && !"".equals(criteria.getClassMode())) {

				enquiries = enquiries.stream().filter(e -> e.getClassMode().equals(criteria.getClassMode()))
						.collect(Collectors.toList());

			}

			return enquiries;
		}
		return null;
	}

	@Override
	public EnquiryForm getEditPage(Integer enqId) {
		StudentEnqEntity studentEntity = enqRepo.findById(enqId).get();
		EnquiryForm form = new EnquiryForm();
		BeanUtils.copyProperties(studentEntity, form);
		return form;
	}

	@Override
	public boolean updateEnquiry(EnquiryForm form, Integer enqId) {
		StudentEnqEntity studentEntity = enqRepo.findById(enqId).get();

		if (form.getStudentName() != null && !form.getStudentName().equals("")) {
			studentEntity.setStudentName(form.getStudentName());
		}

		if (form.getPhno() != null && !form.getPhno().equals("")) {
			studentEntity.setPhno(form.getPhno());
		}

		if (form.getClassMode() != null && !form.getClassMode().equals("")) {
			studentEntity.setClassMode(form.getClassMode());
		}

		if (form.getCourseName() != null && !form.getCourseName().equals("")) {
			studentEntity.setCourseName(form.getCourseName());
		}

		if (form.getEnqStatus() != null && !form.getEnqStatus().equals("")) {
			studentEntity.setEnqStatus(form.getEnqStatus());
		}
		//BeanUtils.copyProperties(form, studentEntity);

		enqRepo.save(studentEntity);
		return true;
	}

}
